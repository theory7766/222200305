# 基础镜像
FROM eclipse-temurin:17
# 作者
MAINTAINER theory7766
# 工作目录
WORKDIR /usr/local/java
# 同步到docker内部的时间
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 设置时区
ENV TZ=Asia/shanghai
EXPOSE 81
# 复制jar包到/usr/local/java下
ARG JAR_FILE
ADD ${JAR_FILE} ./juejin-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["nohup","java","-Dspring.config.location=/usr/local/java/application.yaml","-jar","/usr/local/java/juejin-0.0.1-SNAPSHOT.jar"]