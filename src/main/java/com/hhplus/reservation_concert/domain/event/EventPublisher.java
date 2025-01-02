package com.hhplus.reservation_concert.domain.event;

import com.hhplus.reservation_concert.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishPaidEvent(Reservation reservation) {
        PaidEvent paidEvent = new PaidEvent(this, reservation);

        eventPublisher.publishEvent(paidEvent);
    }
}
