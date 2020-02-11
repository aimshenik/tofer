# Tofer

Package

1.	set **tcid**, **ip** and **port** in **src/main/resources/spring.xml**
2.	set port (if necessary) in **src/main/resources/application.properties**
3.	run command **mvn package**

Run

3.	run command **java -jar target/tofer-1.0-SNAPSHOT.jar**

Use

http://localhost:8080/tofer - TOF status request
http://localhost:8080/tofer/t1 - TOF ticket request with ticket number 1
http://localhost:8080/tofer/c1 - TOF conversation request with ticket number 1
http://localhost:8080/tofer/tfirst - first ticket available via TOF protocol
http://localhost:8080/tofer/tlast - last ticket available via TOF protocol
http://localhost:8080/tofer/cfirst - first conversation available via TOF protocol
http://localhost:8080/tofer/clast - last conversation available via TOF protocol
http://localhost:8080/tofer/first - first ticket NUMBER available via TOF protocol
http://localhost:8080/tofer/last - last ticket NUMBER available via TOF protocol
