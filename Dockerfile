FROM openjdk:11
COPY "./target/infoIp-1.0-SNAPSHOT.jar" "infoIpMeli.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","infoIpMeli.jar"]