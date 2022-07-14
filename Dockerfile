FROM openjdk:11

ADD target/student.jar student.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","student.jar"]