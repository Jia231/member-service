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