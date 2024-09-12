package com.grepp.nbe1_1_clone_mw1.payment.toss.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.api.TossPaymentController;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto.ConfirmPaymentRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TossPaymentClient {

    // ObjectMapper를 사용하여 JSON 처리를 수행합니다.
    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 토스에게 결제 승인 요청
     */
    public HttpResponse<String> requestConfirm(ConfirmPaymentRequest confirmPaymentRequest) throws IOException, InterruptedException {
        String tossOrderId = confirmPaymentRequest.orderId();
        String amount = confirmPaymentRequest.amount();
        String tossPaymentKey = confirmPaymentRequest.paymentKey();

        // 승인 요청에 사용할 JSON 객체를 만듭니다.
        JsonNode requestObj = objectMapper.createObjectNode()
                .put("orderId", tossOrderId)
                .put("amount", amount)
                .put("paymentKey", tossPaymentKey);

        // ObjectMapper를 사용하여 JSON 객체를 문자열로 변환
        String requestBody = objectMapper.writeValueAsString(requestObj);

        // 결제 승인 API를 호출
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
                .header("Authorization", getAuthorizations())
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException {
        System.out.println(paymentKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/" + paymentKey + "/cancel"))
                .header("Authorization", getAuthorizations())
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"" + cancelReason + "\"}"))
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String getAuthorizations() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((TossPaymentController.widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes);
    }
}
