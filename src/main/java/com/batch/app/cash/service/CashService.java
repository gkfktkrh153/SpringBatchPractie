package com.batch.app.cash.service;

import com.batch.app.cash.entity.CashLog;
import com.batch.app.cash.repository.CashLogRepository;
import com.batch.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashLogRepository cashLogRepository;

    public CashLog addCash(Member member, long price) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .build();

        cashLogRepository.save(cashLog);

        return cashLog;
    }
}