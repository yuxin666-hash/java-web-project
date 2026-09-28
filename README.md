# Java Web 项目

这是一个基于 Java 和 Maven 开发的 Web 管理系统项目，主要用于学习和实践 Java Web 开发技术。

## 技术栈

- Java
- Maven
- Spring Boot
- MyBatis
- MySQL
- HTML / CSS / JavaScript

## 项目结构

```text
java-web-project
├── tlias-parent              # Maven 父工程
├── tlias-pojo                # 实体类和数据对象
├── tlias-utils               # 工具类
├── tlias-web-management      # Web 管理模块
└── pom.xml                   # Maven 配置文件
```

## 主要功能

- 员工信息管理
- 部门信息管理
- 用户登录
- 数据查询
- 数据新增、修改和删除
- Web 管理后台

## 运行方式

1. 使用 IntelliJ IDEA 打开项目。
2. 等待 Maven 自动下载项目依赖。
3. 配置 MySQL 数据库。
4. 根据本地环境修改数据库连接配置。
5. 启动 `tlias-web-management` 模块。
6. 在浏览器中访问项目地址。

## 注意事项

- 运行项目前，请确保已经安装 JDK、Maven 和 MySQL。
- 请根据本地环境修改数据库用户名、密码和数据库名称。
- `target` 等编译生成的文件不需要提交到 GitHub。

## 项目用途

本项目用于 Java Web 学习、练习和课程作业。
