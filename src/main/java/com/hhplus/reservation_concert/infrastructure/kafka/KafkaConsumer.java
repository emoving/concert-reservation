package com.hhplus.reservation_concert.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.reservation_concert.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${payment_topic}", groupId = "reservation-group")
    public void consumePayment(String message) {
        try {
            Reservation reservation = objectMapper.readValue(message, Reservation.class);

            log.info("Consumed message: {}", reservation);
            log.info("좌석 예약이 성공적으로 처리되었습니다. 좌석 ID: {}", reservation.getSeatId());
        } catch (Exception e) {
            log.error("Kafka 메시지를 처리하는 동안 오류가 발생했습니다. 메시지: {}", message, e);
        }
    }
}
