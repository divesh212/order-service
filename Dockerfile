FROM openjdk:8

RUN wget --user admin --password 21021996 http://192.168.137.1:8082/artifactory/divesh212-nagpDemo/com/nagarro/nagp/msa2/orderservice/0.0.1-SNAPSHOT/orderservice-0.0.1-SNAPSHOT.jar

EXPOSE 8092

ENTRYPOINT ["java","-jar","orderservice-0.0.1-SNAPSHOT.jar"]