## A-tink

#### Technologies
+ Java 11 OpenJDK
+ Spring Boot 2.6.6
+ Maven
+ PostgreSQL 12.3

#### Installation
1\. Install PostgreSQL DB;

2\. Clone project:
```
https://git.andersenlab.com/Andersen/a-tink/a-tink-backend.git
```
#### Build
You can build the application with by running:
```
mvn clean install
```

#### Run
You can launch the application with by running:
```
./maven bootRun
java -jar a_tink_back.jar --server.port={port}
```

##### Parameters
The parameters that you must specify to run the application:
+ server.port - server port;