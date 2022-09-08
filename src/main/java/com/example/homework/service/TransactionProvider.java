package com.example.homework.service;

import com.example.homework.bean.TransactionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class TransactionProvider {

    public List<TransactionEntity> makeTransactionList() {
        return range(0, 3).boxed()
                .map(i -> makeTransaction())
                .collect(toList());
    }

    private TransactionEntity makeTransaction() {
        return TransactionEntity.builder()
                .actor(randomAlphabetic(5))
                .type(randomAlphabetic(4))
                .data(range(0, 5).boxed()
                        .map(i -> randomAlphabetic(32))
                        .collect(toMap(this::makeId, e -> e)))
                .build();
    }

    private String makeId(String e) {
        return randomUUID().toString();
    }
}
