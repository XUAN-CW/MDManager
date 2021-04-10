---
title: windows下jdk安装.md
date: 2019-04-25 20:34:19
tags:
- windowswindows
- tomcat3windows
categories:
- categories222
- categories2223
---


## 		1.材料

1. jdk-11.0.4_windows-x64_bin

## 2.步骤

### 1.安装

​	安装jdk，一路 next 即可

### 				2.配置环境变量

此电脑→属性→高级系统设置→高级→环境变量

![1](windows下jdk安装/1.png)

系统变量→新建 JAVA_HOME 变量 。

变量值填写jdk的安装目录（本人是 C:\Program Files\Java\jdk-11.0.4)

![3](windows下jdk安装/3.png)

系统变量→编辑 Path 变量

![4](windows下jdk安装/4.png)

新建变量

```
%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
```

![7](windows下jdk安装/7.png)



系统变量→新建 CLASSPATH 变量，变量值填写

```
.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar
```

![8](windows下jdk安装/8.png)

### 		3.检查配置是否成功

检验是否配置成功 运行cmd 输入 java -version （java 和 -version 之间有空格）

若如图所示 显示版本信息 则说明安装和配置成功。

```
java -version
```

## 3.参考

https://jingyan.baidu.com/article/6dad5075d1dc40a123e36ea3.html

[之前的不行了，点这里](https://www.cnblogs.com/evolve/archive/2019/11/11/11837869.html)