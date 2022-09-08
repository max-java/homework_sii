package com.example.homework.proxy;

import com.example.homework.bean.TransactionEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "issue", url = "http://localhost:8080")
public interface TransactionProxy {

    @GetMapping("/new")
    CollectionModel<TransactionEntity> getNewTransactions();
}
