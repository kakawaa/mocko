Mocko
---

# 简介

Mocko是一种SpringBoot环境下的mock解决方案。

在开发或联调测试中经常会遇到一些诸如因为链路过长，或者依赖服务不可用，或者一些其他的情况导致的方法返回结果和预期不一致的问题。这个时候我们想到的解决方案多是通过硬编码的形式mock一些测试数据。

不过通过硬编码的方式mock数据并不是一种很好的解决方案，不提mock数据相关代码带来的污染，这种做法在灵活性上也存在一些问题。

Mocko的开发正是为了解决这类问题。

# 如何使用


## 启动mocko server

Mocko Server 提供了mocko数据的维护能力。在部署时需要分别部署前后端。

后端服务即当前项目的mocko-server模块。

在当前项目pull到本地后，在项目根目录下执行`mvn clean package`指令， 然后在mocko-server/target 下找到mocko-server.jar文件。

执行如下命令Mocko Server后端功能：

```shell
java -jar mocko-server.jar
```

前端项目为： [Mocko Front](https://github.com/zhyea/mocko-front)

项目通过 vue3 + element-plus 实现。

将mocko-front项目pull到本地后，编译并启动。

启动后的页面如下：

![Mocko Server](https://raw.githubusercontent.com/zhyea/mocko/main/doc/imgs/01.png "Mocko Server首页")

## 引入依赖

要使用mocko需要先引入mocko-spring-boot-starter这个依赖：

```xml
<dependency>
    <groupId>org.chobit.mocko</groupId>
    <artifactId>mocko-spring-boot-starter</artifactId>
    <version>${mocko.version}</version>
</dependency>
```
(这个项目目前还没有发布正式版，可以将当前项目pull下来后通过`mvn clean install`到本地仓库后使用)


## 添加配置

在spring-boot配置文件中加入如下配置项：

```yaml
mocko:
  mock-url: http://127.0.0.1:8190/api/mock
```
接口地址通常不需要修改，IP和端口根据自己情况调整即可。

需要注意的是：MockoAutoConfiguration 默认是自动启动的。在引入相关依赖后，MockoAutoConfiguration会随项目启动一起启动。在生产环境下启用mocko相关功能很明显不是一种好的实践，我们需要将之进行显式的关闭。

要停用mocko可以在配置文件中加入如下配置项：

```yaml
mocko:
  enabled: false
```

## 添加@Mocko注解

如果需要对某些类或某些方法的结果进行mock，可以在类或方法上添加`@Mocko`注解。

调用存在`@Mocko`注解的类或方法后，相关的应用就会被注册到Mocko Server：

![Mocko Projects](https://raw.githubusercontent.com/zhyea/mocko/main/doc/imgs/02.png "Mocko应用信息")

点击应用图标，可以看到已注册的有`@Mocko`注解的类和方法以树结构呈现。

![Mocko Types](https://raw.githubusercontent.com/zhyea/mocko/main/doc/imgs/03.png "Mocko类和方法信息")


## mock方法返回值

在Mocko Server的目标项目下展开类结构树，点击方法名可以在右侧打开方法返回值编辑页：

![Mocko Types](https://raw.githubusercontent.com/zhyea/mocko/main/doc/imgs/04.png "Mocko类和方法信息")

在文本框中输入预期的mock值，点击保存，再调用方法就会返回预期中的值了。


# License

[GNU GENERAL PUBLIC LICENSE](https://raw.githubusercontent.com/zhyea/mocko/main/LICENSE)

# Credits

Mocko依赖了如下开源项目：

* SpringBoot
* Jackson
* H2
* Element Plus
* Axios
* Pinia

感谢！！
