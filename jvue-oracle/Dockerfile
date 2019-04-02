#依赖的镜像
FROM sath89/oracle-xe-11g:latest
#镜像创建者的信息
MAINTAINER terwer "cbgtyw@gmail.com"
# 设置编码
ENV LANG C.UTF-8
# 设置时区
# ENV TZ Asia/Shanghai
ENV TZ CST-8
COPY ./jvue-oracle/template.schema.sql /template.schema.sql
COPY ./jvue-oracle/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh