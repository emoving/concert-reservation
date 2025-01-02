package com.hhplus.reservation_concert.domain.event;

import com.hhplus.reservation_concert.domain.reservation.Reservation;
import org.springframework.context.ApplicationEvent;

public class PaidEvent extends ApplicationEvent {

    private final Reservation reservation;

    public PaidEvent(Object source, Reservation reservation) {
        super(source);
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
