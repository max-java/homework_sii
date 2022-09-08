# homework SII
## Start application.
Service requires docker engine installed and java 17.
1. Run `docker-compose.yml`
2. Start application
3. Open [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/) for Rest API user web interface
### 1. Service generates information about transactions.
`src/main/java/com/example/homework/service/TransactionProvider.java` responsible for new generation new transactions, 
and expose them at `\new` endpoint via `src/main/java/com/example/homework/controller/TransactionProviderController.java` 
### 2. Service collects transactions and store them in db.
`src/main/java/com/example/homework/service/TransactionService.java` scheduled job every 5 seconds makes call
to `\new` endpoint using Feign proxy and stores them in database using SpringData repository 
### 3. Service provides CRUD api.
Having Spring Data Rest and Spring HATEOAS modules in classpath makes `src/main/java/com/example/homework/repository/TransactionRepository.java`
pageable and sortable RestFULL API endpoint out of the box, providing GET, POST, PUT, PATCH, DELETE 
methods on `/transactionEntities` and `/transactionEntities/{id}`. Work of that endpoints is proved by `src/test/java/com/example/homework/mvc/TransactionControllerTest.java`
### 4. Service provides search operations.
Service exposes `/transactionEntities/search/filterBy`endpoint that works as filter by any transaction property, including
transaction data key or value. Work of filtering is proved by `src/test/java/com/example/homework/mvc/TransactionControllerTest.java`
### 5. Service should use MySQL database.
To make application environment independent, it uses docker image of MySQL with settings listed in `docker-compose.yml`
