package com.example.homework.util;

import com.example.homework.bean.TransactionEntity;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.parse;
import static java.time.OffsetDateTime.now;

public class Expected {

    public static List<TransactionEntity> expectedList() {
        return List.of(id1(), id2(), id3());
    }

    public static TransactionEntity id1() {
        return TransactionEntity.builder()
                .id(1)
                .timestamp(parse("2022-09-08T12:24:39.843906").toInstant(now().getOffset()))
                .type("ylQn")
                .actor("oTnXJ")
                .data(Map.of(
                        "4bc36dd6-792b-4e27-9bc5-bd609f460956", "RVHTJSTpPCtAsWJhoXGYNGLCjnQkOuxK",
                        "894b6eb9-28c7-4d9f-a8e8-b742f260bfb8", "eDDNinmXgDJXSFhaSeJdkduxChrQjFWU",
                        "c74eca24-f361-4fa8-ad61-35e5c5b1962a", "lUkrnpzqCCCerozOvZavNJoedJMpuAvA",
                        "d16babf6-11ed-44aa-bb91-b5dca3aa440f", "MisUgpcRpysCQbINLpWPSLsELOpxsFNU",
                        "dfdb8cbd-6cb2-4a12-9aad-9ec3f784bf76", "GcLrjAUtbrbzrAKnnvSjGJxmJkTwtVTe"))
                .build();
    }

    public static TransactionEntity id1Changed() {
        return TransactionEntity.builder()
                .id(1)
                .timestamp(parse("2022-09-08T12:24:39.843906").toInstant(now().getOffset()))
                .type("type")
                .actor("oTnXJ")
                .data(Map.of(
                        "4bc36dd6-792b-4e27-9bc5-bd609f460956", "RVHTJSTpPCtAsWJhoXGYNGLCjnQkOuxK",
                        "894b6eb9-28c7-4d9f-a8e8-b742f260bfb8", "eDDNinmXgDJXSFhaSeJdkduxChrQjFWU",
                        "c74eca24-f361-4fa8-ad61-35e5c5b1962a", "lUkrnpzqCCCerozOvZavNJoedJMpuAvA",
                        "d16babf6-11ed-44aa-bb91-b5dca3aa440f", "MisUgpcRpysCQbINLpWPSLsELOpxsFNU",
                        "dfdb8cbd-6cb2-4a12-9aad-9ec3f784bf76", "GcLrjAUtbrbzrAKnnvSjGJxmJkTwtVTe",
                        "key", "value"))
                .build();
    }
    
    public static TransactionEntity id2() {
        return TransactionEntity.builder()
                .id(2)
                .timestamp(parse("2022-09-08T12:24:39.852090").toInstant(now().getOffset()))
                .type("UpAM")
                .actor("LJSRR")
                .data(Map.of(
                        "19ffc6a9-8d9d-412a-9e50-e08c45c22987", "sITgUSZpZPcVokbdPdOuknwNkgypiOQM",
                        "3bd1661f-d854-4b9b-be28-38e880c6bf24", "sbCLNWGkbSWXZEpqTeclgAALeVmzEWWw",
                        "49504ca3-6b7b-4ca2-acc7-437213963039", "daowpJVjMBlfmPKjYZxkceSUfLToczEe",
                        "5bd1a46a-ada8-4041-a693-96b82198f202", "wvuCdccUaPfOEXNTBSHwPVPYfuavNrjz",
                        "bff23e42-f3e5-41e1-8276-1637dafee50c", "AxdtEUazFnMoSWfnDHJfEIYhKybtwvOa"))
                .build();
    }

    public static TransactionEntity id3() {
        return TransactionEntity.builder()
                .id(3)
                .timestamp(parse("2022-09-08T12:24:39.853862").toInstant(now().getOffset()))
                .type("isBj")
                .actor("ynBfw")
                .data(Map.of(
                        "1794458c-d569-4f22-9ba3-1b2e593c3cfa", "kYKrhAdywBJHRyMusDbMYEQskEFXAbfb",
                        "21e437c1-b9b9-4478-8594-1c39c59cf4dd", "rVKZwqtOqAVRvUdEENFuLWTFCKONhcdN",
                        "8f6c94d6-e870-4bc5-8401-86ae7ef9e8e3", "BAaePmtkvGNcBjFQMBpleVwrqqABNMJM",
                        "ab77d1ea-a3a3-46f3-b0e4-efb7969e868d", "AZhieHflpldpqdCSbYasxNPfsRFsQenw",
                        "f513b436-5e1a-4ce8-8479-0b055bdf9e26", "SIorEEBZTwdnZqYmNGhYcOiqhTXYohus"))
                .build();
    }

    public static TransactionEntity id4() {
        return TransactionEntity.builder()
                .id(4)
                .type("type")
                .actor("actor")
                .data(Map.of(
                        "key", "value",
                        "key2", "value2"))
                .build();
    }
}