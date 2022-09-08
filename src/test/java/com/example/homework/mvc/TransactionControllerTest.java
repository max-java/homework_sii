package com.example.homework.mvc;

import com.example.homework.bean.TransactionEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.example.homework.util.Expected.expectedList;
import static com.example.homework.util.Expected.id1;
import static com.example.homework.util.Expected.id1Changed;
import static com.example.homework.util.Expected.id2;
import static com.example.homework.util.Expected.id4;
import static java.time.LocalDateTime.parse;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionControllerTest extends BaseControllerTest {
    private static final TypeReference<PagedModel<EntityModel<TransactionEntity>>> TRANSACTION_PAGE = new TypeReference<>() {};
    private static final TypeReference<EntityModel<TransactionEntity>> TRANSACTION_MODEL = new TypeReference<>() {};
    private static final String TRANSACTION_ENTITIES = "transactionEntities";
    private static final String[] FILTER = {TRANSACTION_ENTITIES, "search", "filterBy"};

    @Test
    @Transactional
    void getAll() throws Exception {
        final List<TransactionEntity> result = testGetPagedModel(makeUri(TRANSACTION_ENTITIES), TRANSACTION_PAGE);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedList());
    }

    @Test
    @Transactional
    void getById() throws Exception {
        final TransactionEntity result = testGetEntityModel(makeUri(TRANSACTION_ENTITIES, "1"), TRANSACTION_MODEL);
        assertThat(result).usingRecursiveComparison().isEqualTo(id1());
    }

    @Test
    void saveAndDelete() throws Exception {
        var transaction = TransactionEntity.builder()
                .type("type")
                .actor("actor")
                .data(Map.of("key", "value","key2", "value2"))
                .build();
        assertEquals("4", testPost(makeUri(TRANSACTION_ENTITIES), transaction));
        final TransactionEntity result = testGetEntityModel(makeUri(TRANSACTION_ENTITIES, "4"), TRANSACTION_MODEL);
        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("timestamp")
                .isEqualTo(id4());
        testDelete(makeUri(TRANSACTION_ENTITIES, "1"));
        testDelete(makeUri(TRANSACTION_ENTITIES, "2"));
        testDelete(makeUri(TRANSACTION_ENTITIES, "3"));
        testDelete(makeUri(TRANSACTION_ENTITIES, "4"));
    }

    @Test
    @Transactional
    void update() throws Exception {
        var transaction = TransactionEntity.builder()
                .type("type")
                .data(Map.of("key", "value","key2", "value2"))
                .build();
        testPut(makeUri(TRANSACTION_ENTITIES, "1"), transaction);

        final TransactionEntity result = testGetEntityModel(makeUri(TRANSACTION_ENTITIES, "1"), TRANSACTION_MODEL);

        final TransactionEntity expected = id4();
        expected.setId(1);
        expected.setActor(null);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @Transactional
    void change() throws Exception {
        var transaction = TransactionEntity.builder()
                .type("type")
                .data(Map.of("key", "value"))
                .build();
        testPatch(makeUri(TRANSACTION_ENTITIES, "1"), transaction);

        final TransactionEntity result = testGetEntityModel(makeUri(TRANSACTION_ENTITIES, "1"), TRANSACTION_MODEL);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(id1Changed());
    }

    @Test
    @Transactional
    void filterByTransactionProperties() throws Exception {
        final List<TransactionEntity> result = testGetPagedModel(makeUri(
                        makeQuery("actor", "oTnXJ",
                                "timestamp", tmstmpToString("2022-09-08T12:24:39.843906")),
                        FILTER),
                TRANSACTION_PAGE);
        assertThat(result).usingRecursiveComparison().isEqualTo(List.of(id1()));
    }

    @Test
    @Transactional
    void filterByTransactionData() throws Exception {
        final List<TransactionEntity> result = testGetPagedModel(makeUri(
                        makeQuery("data_value", "sbCLNWGkbSWXZEpqTeclgAALeVmzEWWw"),
                        FILTER),
                TRANSACTION_PAGE);
        assertThat(result).usingRecursiveComparison().isEqualTo(List.of(id2()));
    }

    @NotNull
    private String tmstmpToString(final String timestamp) {
        return parse(timestamp).toInstant(now().getOffset()).toString();
    }
}
