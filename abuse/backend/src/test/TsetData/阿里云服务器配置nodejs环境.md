---
title: 阿里云服务器配置nodejs环境.md
date: 2019-05-04 08:38:26
tags:
---


[node-v12.16.1-linux-x64.tar.gz](https://cdn.npm.taobao.org/dist/node/v12.16.1/node-v12.16.1-linux-x64.tar.gz)

```
cd /tmp
```



tar -zxvf node-v12.16.1-linux-x64.tar.gz



ln -s /tmp/node-v12.16.1-linux-x64/bin/node /usr/local/bin/node



ln -s /tmp/node-v12.16.1-linux-x64/bin/npm /usr/local/bin/npm



node -v



npm -v



```
npm install -g cnpm --registry=https://registry.npm.taobao.org
```