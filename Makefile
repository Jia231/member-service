include .env
export

JAVA_VERSION=11.0.4-zulu

local: install-java
	ENVIRONMENT=gradle-local SERVER_PORT=5000 ./gradlew clean bootRun

install-java:
	([[ -d ${HOME}/.sdkman/ ]] && echo "SDKMAN installed!") || (curl -s "https://get.sdkman.io" | bash) && \
	source "${HOME}/.sdkman/bin/sdkman-init.sh" && \
	(sdk current | grep -q ${JAVA_VERSION}) || sdk install java ${JAVA_VERSION} && \
	sdk use java ${JAVA_VERSION}

build-demo: install-java
	./gradlew build

docker-jar:
	docker-compose up -d

run-demo:
	docker run -t demo:v1

check: install-java
	./gradlew clean check