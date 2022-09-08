package com.example.homework.controller;

import com.example.homework.bean.TransactionEntity;
import com.example.homework.service.TransactionProvider;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionProviderController {

    private final TransactionProvider transactionProvider;

    public TransactionProviderController(final TransactionProvider transactionProvider) {
        this.transactionProvider = transactionProvider;
    }

    @GetMapping("/new")
    public CollectionModel<TransactionEntity> getNewTransactions() {
        return CollectionModel.of(transactionProvider.makeTransactionList());
    }
}
