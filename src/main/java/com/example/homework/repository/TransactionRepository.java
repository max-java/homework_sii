package com.example.homework.repository;

import com.example.homework.bean.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.Instant;
import java.util.Set;

@RepositoryRestResource
public interface TransactionRepository extends PagingAndSortingRepository<TransactionEntity, Integer> {

    @Query("select t from TransactionEntity t inner join t.data as d where " +
            "(:id is null or t.id = :id) and " +
            "(:timestamp is null or t.timestamp = :timestamp) and " +
            "(:type is null or t.type like :type) and " +
            "(:actor is null or t.actor like :actor) and " +
            "(:data_key is null or key(d) like :data_key) and " +
            "(:data_value is null or d like :data_value)")
    Set<TransactionEntity> filterBy(@Param("id") Integer id,
                                    @Param("timestamp") Instant timestamp,
                                    @Param("type") String type,
                                    @Param("actor") String actor,
                                    @Param("data_key") String dataKey,
                                    @Param("data_value") String dataValue);
}
