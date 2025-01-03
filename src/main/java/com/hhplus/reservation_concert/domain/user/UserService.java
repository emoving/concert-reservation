package com.hhplus.reservation_concert.domain.user;

import com.hhplus.reservation_concert.domain.user.point.PointHistory;
import com.hhplus.reservation_concert.global.exception.ErrorCode;
import com.hhplus.reservation_concert.global.exception.CustomException;
import com.hhplus.reservation_concert.infrastructure.persistence.user.PointHistoryRepositoryImpl;
import com.hhplus.reservation_concert.infrastructure.persistence.user.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final PointHistoryRepositoryImpl pointHistoryRepository;

    public User getUser(Long id) {
        return userRepository.findByIdWithPessimisticLock(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public PointHistory chargePoint(User user, Integer point) {
        PointHistory pointHistory = user.charge(point);

        return pointHistoryRepository.save(pointHistory);
    }

    public void usePoint(User user, Integer point) {
        PointHistory pointHistory = user.use(point);
        pointHistoryRepository.save(pointHistory);
    }
}
