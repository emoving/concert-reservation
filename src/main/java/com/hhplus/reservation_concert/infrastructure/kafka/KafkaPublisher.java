package com.hhplus.reservation_concert.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.reservation_concert.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishPayment(Reservation reservation) {
        try {
            String message = objectMapper.writeValueAsString(reservation);
            String partition = String.valueOf(reservation.getId());

            kafkaTemplate.send("${payment_topic}", partition, message);

            //TODO 발행에 성공 또는 실패 시 Outbox 패턴으로 재전송 구현
        } catch (Exception e) {
            throw new RuntimeException("예약 정보 전달에 실패했습니다. 예약정보: " + reservation, e);
        }
    }
}
