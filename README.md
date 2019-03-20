# SpringBootMailDemo
在springBoot框架上搭建的简单邮件系统demo。可以提供发送简单邮件、hrml邮件、附件邮件、图片邮件以及模板邮件的功能，模板功能使用了thymeleaf模板引擎。

使用时需将mail/resource/application.properity中的spring.mail.username和spring.mail.password设置好，然后在test包下进行相应的修改和测试。

常见错误：
421 HL:ICC 该IP同时并发连接数过大
451 Requested Mail action not taken：too much fail... 登录失败次数过多，被禁止登录
553 authentication is required 认证失败（账户密码不对或smtp配置不对）

优化点：
做成独立微服务，提供不同的接口
异常处理，发送失败时需自处理
定时重发邮件
异步发送
