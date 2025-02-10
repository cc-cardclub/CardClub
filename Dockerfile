# Author: Raven Burkard
FROM gradle:jdk17 AS build
LABEL stage=builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:17-jdk

ARG databaseUrl
ARG databaseUser
ARG databasePassword

ENV serverDatabaseUrl=$databaseUrl
ENV serverDatabaseUser=$databaseUser
ENV serverDatabasePassword=$databasePassword

RUN mkdir /app

COPY --from=build "/home/gradle/src/build/libs/CardClubServer.jar" /app/CardClubServer.jar
ENTRYPOINT java -cp /app/CardClubServer.jar at.rennweg.htl.cardclubserver.Main "databaseUrl:"$serverDatabaseUrl "databaseUser:"$serverDatabaseUser "databasePassword:"$serverDatabasePassword
