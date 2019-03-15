#依赖的镜像
FROM nginx:1.15.9-alpine
#镜像创建者的信息
MAINTAINER terwer "csyouwei@gmail.com"
# 设置编码
ENV LANG C.UTF-8
# 设置时区
# ENV TZ Asia/Shanghai
ENV TZ CST-8
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]