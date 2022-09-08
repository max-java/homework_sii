package com.example.homework.service;

import com.example.homework.proxy.TransactionProxy;
import com.example.homework.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    private final TransactionProxy proxy;
    private final TransactionRepository repository;

    public TransactionService(final TransactionProxy proxy,
                              final TransactionRepository repository) {
        this.proxy = proxy;
        this.repository = repository;
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void getNewTransactions() {
        try {
            repository.saveAll(proxy.getNewTransactions().getContent());
        } catch (Exception e) {
            log.error("Can't get new transactions, {}", e.getMessage(), e);
        }
    }
}
