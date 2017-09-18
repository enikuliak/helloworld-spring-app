mvn -Dmaven.test.skip=true install && java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5001 -jar target/hello-world-0.0.1-SNAPSHOT.jar
