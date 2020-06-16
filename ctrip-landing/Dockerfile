FROM openjdk:11-jdk
VOLUME /tmp
ADD ctrip-landing/target/ctrip-landing-0.0.1-SNAPSHOT.jar /ctrip-landing-1.0.jar
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","/ctrip-landing-1.0.jar","--spring.profiles.active=pro"]
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone
ENV LANG C.UTF-8