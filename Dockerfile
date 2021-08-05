FROM openjdk:11

ARG UID=123
ARG GID=0
RUN useradd -u ${UID} -g ${GID} -m go
RUN apt update
RUN apt install -y libfreetype6
RUN apt install -y fontconfig

COPY product.yml /
COPY entrypoint.sh /

EXPOSE 7000

ENTRYPOINT ["chmod", "+x", "/entrypoint.sh" ]

COPY ./build/libs/demo-0.0.1-SNAPSHOT-plain.jar /service.jar
