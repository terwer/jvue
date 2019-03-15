FROM node:10.15.1-alpine

# CDN注册
RUN npm config set registry https://registry.npm.taobao.org && \
    npm set chromedriver_cdnurl https://npm.taobao.org/mirrors/chromedriver && \
    npm cache clean --force

WORKDIR /app

COPY ./jvue-front/package.json ./
RUN npm install

COPY ./jvue-front .

RUN npm run build

CMD ["npm", "run", "serve"]