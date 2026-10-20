# spring-boot-template

This is a basic starter project that I use to build a spring boot restful service.

It has its own docker service to test live deploy.

## Instructions

| Command                | Description                                                                                                        |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------ |
| `./gradlew bootRun`    | Starts your Spring Boot application locally on your machine with a live process.                                   |
| `./gradlew build`      | Compiles the code, runs all JUnit tests, and packages a .war file in build/libs/.                                  |
| `./gradlew test`       | Executes all tests written with JUnit/Mockito and generates an HTML report in build/reports/tests/test/index.html. |
| `./gradlew clean`      | Deletes the entire build/ directory to force a completely fresh re-compilation of your source code.                |
| `docker compose up -d` | Start headless docker container.                                                                                   |
| `docker compose down`. | stop docker                                                                                                        |

end
