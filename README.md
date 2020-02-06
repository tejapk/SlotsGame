# SlotsGame

## Requirements

For building and running the application you need:


- [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the tests locally
Maven should be added to the terminal path
Go to the project root directory, open a terminal and execute

```shell
mvn clean test -Dbrowser=chrome -Durl="http://127.0.0.1:8000"
```

| Parameter | Default | Description |
| :--- | :--- | :--- |
| `browser` | `chrome` | **Optional**. Allowed values: chrome, firefox, ie, chrome-android-mobile, chrome-ios-mobile |
| `url` | `http://127.0.0.1:8000` | **Optional**. The url where the server is running |

## Test Reports

To generate reports, run the following in the terminal
```shell
mvn verify -DskipTests
```


To view the reports open `overview-features.html` file from `target/cucumber/cucumber-html-reports` folder in the root directory