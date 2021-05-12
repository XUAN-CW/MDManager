---
title: tomcat闪退
date: 2020-04-01 14:49:26
tags:
- windows
- tomcat
categories:
- categories1
- categories2
---

### 原因

因为启动tomcat会调用tomcat安装文件中的startup.bat，而它调用了catalina.bat则调用了setclasspath.bat。因此需要在setclasspath.bat的开头手动声明环境变量。

### 解决方案

用vim打开tomcat的bin目录下的setclasspath.bat，添加 JAVA_HOME 环境变量，linux 用 export，

windows 用 set 。

![2020-04-01_144816](tomcat闪退/2020-04-01_144816.png)

```
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.4\
```

![image-20200401145443448](tomcat闪退/image-20200401145443448.png)

### 字符乱码

控制台输出设为 GBK



![image-20200514211205448](tomcat闪退/image-20200514211205448.png)

![image-20200514210931541](tomcat闪退/image-20200514210931541.png)

### 参考

[tomcat error](https://blog.csdn.net/to_Baidu/article/details/52848620?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task)

[Tomcat控制台中文乱码问题解决](https://blog.csdn.net/zymndsc_2012/article/details/85647621)