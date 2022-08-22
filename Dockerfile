FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/*.war app.war
EXPOSE 80
CMD ["java","-jar","app.war"]