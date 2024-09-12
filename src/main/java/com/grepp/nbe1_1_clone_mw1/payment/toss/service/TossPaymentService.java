package com.grepp.nbe1_1_clone_mw1.payment.toss.service;

import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import com.grepp.nbe1_1_clone_mw1.order.model.Order;
import com.grepp.nbe1_1_clone_mw1.order.repository.OrderRepository;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto.ConfirmSuccessPaymentInfo;
import com.grepp.nbe1_1_clone_mw1.payment.toss.controller.dto.ConfirmPaymentResponse;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPayment;
import com.grepp.nbe1_1_clone_mw1.payment.toss.model.TossPaymentStatus;
import com.grepp.nbe1_1_clone_mw1.payment.toss.reposiotry.TossPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TossPaymentService {

    private final TossPaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public TossPaymentService(TossPaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public ConfirmPaymentResponse getPayment(String backendOrderId) {
        TossPayment tossPayment = paymentRepository.findByOrder_OrderId(UUIDUtil.hexStringToByteArray(backendOrderId)).orElseThrow(() -> new RuntimeException("Payment not found"));
        return tossPayment.toResponse();
    }

    public ConfirmPaymentResponse addPayment(ConfirmSuccessPaymentInfo confirmSuccessPaymentInfo) {
        Order order = orderRepository.findById(UUIDUtil.hexStringToByteArray(confirmSuccessPaymentInfo.backendOrderId())).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return paymentRepository.save(confirmSuccessPaymentInfo.toTossPayment(order)).toResponse();
    }


    @Transactional
    public void changePaymentStatus(String paymentKey, TossPaymentStatus tossPaymentStatus) {
        TossPayment tossPayment = paymentRepository.findByTossPaymentKey(paymentKey).orElseThrow(() -> new RuntimeException("Payment not found"));
        tossPayment.changePaymentStatus(tossPaymentStatus);
    }
}
