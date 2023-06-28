FROM khipu/openjdk17-alpine
MAINTAINER <maltesh>
COPY ./target/*.jar DC_Api.jar
EXPOSE 9003
ENTRYPOINT [ "java","-jar","DC_Api.jar"]