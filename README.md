# Tlias 员工管理系统

基于 Java、Spring Boot 和 Maven 开发的员工管理系统。项目采用 Maven 多模块结构，主要用于学习 Java Web 后端开发、数据库访问和 RESTful 接口设计。

## 功能特性

- **员工管理**：支持员工信息的查询、新增、修改和删除
- **部门管理**：支持部门数据的查询和维护
- **员工分页查询**：支持按条件分页查询员工信息
- **员工登录**：提供后台管理系统登录功能
- **数据校验**：对请求参数进行基本校验
- **统一响应**：使用统一的数据响应结构返回接口结果
- **多模块管理**：使用 Maven 管理实体类、工具类和 Web 管理模块

## 技术栈

| 层级 | 技术 |
|------|------|
| 开发语言 | Java 17 |
| 项目管理 | Maven |
| Web 框架 | Spring Boot |
| 持久层 | MyBatis |
| 数据库 | MySQL |
| 前端交互 | RESTful API |
| 开发工具 | IntelliJ IDEA / VS Code |

## 项目结构

```text
web-project02/
├── tlias-parent/                    # Maven 父工程
├── tlias-pojo/                      # 实体类、DTO 和数据对象
├── tlias-utils/                     # 工具类和公共组件
├── tlias-web-management/            # Web 管理模块
│   └── src/main/
│       ├── java/                    # Java 源代码
│       └── resources/               # 配置文件和静态资源
├── src/                             # 根项目相关资源
├── pom.xml                          # Maven 多模块配置
└── .gitignore                       # Git 忽略配置
```

## 如何运行

### 1. 环境准备

确保本地已安装：

- JDK 17 或更高版本
- Maven 3.8 或更高版本
- MySQL 8.0 或更高版本
- IntelliJ IDEA 或其他 Java IDE

### 2. 配置数据库

创建项目所需的 MySQL 数据库，并根据本地环境修改数据库连接配置。配置文件通常位于：

```text
tlias-web-management/src/main/resources/
```

请将数据库地址、端口、数据库名、用户名和密码修改为本地配置。

### 3. 编译项目

在项目根目录执行：

```bash
mvn clean package
```

### 4. 启动项目

使用 IntelliJ IDEA 打开项目，等待 Maven 依赖加载完成后，运行 `tlias-web-management` 模块中的启动类。

也可以在项目根目录执行：

```bash
mvn spring-boot:run -pl tlias-web-management
```

启动成功后，在浏览器中访问项目配置的服务地址。

## 接口功能

项目后端主要提供以下接口能力：

- 员工列表查询
- 员工分页查询
- 员工信息新增
- 员工信息修改
- 员工信息删除
- 部门列表查询
- 用户登录认证

具体接口地址和请求参数请以项目代码及接口配置为准。

## Git 使用说明

修改项目后，可以执行以下命令提交并同步到 GitHub：

```bash
git add .
git commit -m "描述本次修改"
git push
```

## 注意事项

- 请不要将数据库密码、Token 等敏感信息提交到 GitHub。
- `target/` 等 Maven 编译生成的文件不需要提交。
- 首次运行前请确认 MySQL 服务已经启动。
- 如果 Maven 依赖下载失败，请检查网络和 Maven 配置。

## 项目用途

本项目用于 Java Web 学习、练习和课程作业。
