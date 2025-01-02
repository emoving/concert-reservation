package com.hhplus.reservation_concert.domain.event;

import com.hhplus.reservation_concert.infrastructure.kafka.KafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EventListener {

    private final KafkaPublisher kafkaPublisher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaidEvent(PaidEvent paidEvent) {
        kafkaPublisher.publishPayment(paidEvent.getReservation());
    };
}
