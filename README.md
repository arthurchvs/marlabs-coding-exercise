# Coding Exercise

### Introduction

This application provides two services:
* fetch a question
  * It will always return "Please sum the numbers " with a random list of 2 to 3 numbers ranging between 1-10
  * ex: "Please sum the numbers 1, 2" | "Please sum the numbers 4, 10, 9"
  * Once a question is generated, it is added to an internal in-memory cache for future validation
  * ```
    GET http://localhost:8080/
    ```
* check the answer for a question
  * Pass a question (returned from the other service) and the answer to it in a request body
  * Returns 200 OK and a message if the answer is correct
  * The request must be valid and the question must exist in the cache, otherwise it will return 400 Bad Request
  * ```
    POST http://localhost:8080/
    {
        "question": "Please sum the numbers 1, 2, 3",
        "answer": 10
    }
    ```

### Build and Run

```
mvn clean install spring-boot:start
```

This will build, run the tests and start the server

You can also use ``mvnw`` instead of ``mvn`` if you don't have maven installed