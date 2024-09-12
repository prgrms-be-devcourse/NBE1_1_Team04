package com.grepp.nbe1_1_clone_mw1.payment.toss.controller.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto.*;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentMethod;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentStatus;
import com.grepp.nbe1_1_clone_mw1.payment.toss.service.TossPaymentClient;
import com.grepp.nbe1_1_clone_mw1.payment.toss.service.TossPaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/payments")
public class TossPaymentController {
    // 토스가 제공하는 테스트용 시크릿 키
    public static final String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    // ObjectMapper를 사용하여 JSON 처리를 수행합니다.
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TossPaymentService tossPaymentService;
    private final TossPaymentClient tossPaymentClient;

    public TossPaymentController(TossPaymentService tossPaymentService, TossPaymentClient tossPaymentClient) {
        this.tossPaymentService = tossPaymentService;
        this.tossPaymentClient = tossPaymentClient;
    }


    /**
     * Order 테이블의 ID로 결제정보를 조회
     * @param backendOrderId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPayment(@PathVariable("id") String backendOrderId) {
        ConfirmPaymentResponse payment = tossPaymentService.getPayment(backendOrderId);
        return ResponseEntity.ok(payment);
    }

    /**
     * 토스에 결제 승인받기
     * @param confirmPaymentRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) throws Exception {

        HttpResponse<String> response = tossPaymentClient.requestConfirm(confirmPaymentRequest); // 토스에게 결제 승인 요청
        int code = response.statusCode();
        boolean isSuccess = code == 200;

        String responseBody = response.body();
        JsonNode responseObject = objectMapper.readTree(responseBody);

        String backendOrderId;
        String tossOrderId;
        String paymentKey = "";
        String paymentMethod;
        String paymentStatus;
        LocalDateTime requestedAt;
        LocalDateTime approvedAt;

        try {
            if (isSuccess) {
                backendOrderId = confirmPaymentRequest.backendOrderId(); // 결제에 대한 주문 ID
                tossOrderId = responseObject.get("orderId").asText(); // 토스 상의 주문 ID
                paymentKey = responseObject.get("paymentKey").asText();
                paymentMethod = responseObject.get("method").asText();
                paymentStatus = responseObject.get("status").asText();
                requestedAt = OffsetDateTime.parse(responseObject.get("requestedAt").asText()).toLocalDateTime();
                approvedAt = OffsetDateTime.parse(responseObject.get("approvedAt").asText()).toLocalDateTime();
                long totalAmount = responseObject.get("totalAmount").asLong();

                ConfirmSuccessPaymentInfo confirmSuccessPaymentInfo =
                        ConfirmSuccessPaymentInfo.create(
                                backendOrderId,
                                tossOrderId,
                                paymentKey,
                                TossPaymentMethod.valueOf(paymentMethod),
                                TossPaymentStatus.valueOf(paymentStatus),
                                totalAmount,
                                requestedAt,
                                approvedAt
                        );

                // 결제 정보 저장
                ConfirmPaymentResponse confirmPaymentResponse = tossPaymentService.addPayment(confirmSuccessPaymentInfo);
                return ResponseEntity.status(code).body(confirmPaymentResponse);
            }
        } catch (RuntimeException e) {
            // 데이터베이스 작업 도중 예외 발생 시 결제 취소
            if (!paymentKey.isEmpty()) {
                tossPaymentClient.requestPaymentCancel(paymentKey);
            }
            // 데이터베이스 오류 응답
            responseObject = objectMapper.valueToTree(PaymentErrorResponse.builder()
                    .code(500)
                    .message("데이터베이스 오류 발생, 결제가 취소되었습니다.")
                    .build());
            code = 500;
        } finally {

        }

        // 결제 실패 혹은 예외 발생 시 반환
        return ResponseEntity.status(code).body(responseObject);
    }


    /**
     * 결제의 금액을 세션에 임시저장
     * 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도
     */
    @PostMapping("/saveAmount")
    public ResponseEntity<?> tempsave(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {
        session.setAttribute(saveAmountRequest.orderId(), saveAmountRequest.amount());
        return ResponseEntity.ok("Payment temp save successful");
    }

    /**
     * 결제 금액을 검증
     */
    @PostMapping("/verifyAmount")
    public ResponseEntity<?> verifyAmount(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {

        String amount = (String) session.getAttribute(saveAmountRequest.orderId());
        // 결제 전의 금액과 결제 후의 금액이 같은지 검증
        if(amount == null || !amount.equals(saveAmountRequest.amount()))
            return ResponseEntity.badRequest().body(PaymentErrorResponse.builder().code(400).message("결제 금액 정보가 유효하지 않습니다.").build());

        // 검증에 사용했던 세션은 삭제
        session.removeAttribute(saveAmountRequest.orderId());

        return ResponseEntity.ok("Payment is valid");
    }


    /**
     * 결제 취소
     */
    @PostMapping("/cancel")
    public ResponseEntity<?> cancelPayment(@RequestBody CancelPaymentRequest cancelPaymentRequest) throws IOException, InterruptedException {
        HttpResponse<String> response = tossPaymentClient.requestPaymentCancel(cancelPaymentRequest.paymentKey());
        if(response.statusCode() == 200) tossPaymentService.changePaymentStatus(cancelPaymentRequest.paymentKey(), TossPaymentStatus.CANCELED);
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }
}
