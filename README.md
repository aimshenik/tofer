# Tofer

Configure
	set **tcid**, **ip** and **port** in **src/main/resources/spring.xml*


Package
	set websetver port (if necessary) in **src/main/resources/application.properties**
	run command **mvn package**

Run
	run command : **java -jar target/tofer-1.0-SNAPSHOT.jar**

Use

http://localhost:8085/tofer - TOF status request
http://localhost:8085/tofer/t1 - TOF ticket request with ticket number 1
http://localhost:8085/tofer/c1 - TOF conversation request with ticket number 1
http://localhost:8085/tofer/tfirst - first ticket available via TOF protocol
http://localhost:8085/tofer/tlast - last ticket available via TOF protocol
http://localhost:8085/tofer/cfirst - first conversation available via TOF protocol
http://localhost:8085/tofer/clast - last conversation available via TOF protocol
http://localhost:8085/tofer/first - first ticket NUMBER available via TOF protocol
http://localhost:8085/tofer/last - last ticket NUMBER available via TOF protocol
