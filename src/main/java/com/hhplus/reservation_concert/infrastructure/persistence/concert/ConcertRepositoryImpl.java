package com.hhplus.reservation_concert.infrastructure.persistence.concert;

import com.hhplus.reservation_concert.domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepositoryImpl extends JpaRepository<Concert, Long> {
}
