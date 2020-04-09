# Rest API for Customer Statement Processor(CSP)
Spring WEB ans Spring Data based http REST API.
DB: PostgreSql

### Run Using Docker

Build

```
./gradlew build
```

Test coverage report:

```
./gradlew clean build jacocoTestReport
```

Simple build command will run next checks:
1. [Checkstyle](http://checkstyle.sourceforge.net/) 
2. [Spotbugs](https://spotbugs.github.io/)

Run Integration tests

```
docker-compose up -d database
./gradlew integrationTest
```

Run
 
```
docker-compose up -d --build
```



