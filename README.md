# SpringBoot 自定义注解测试项目

这是一个用于测试和验证自定义注解功能的 Spring Boot 项目。通过本项目，您可以了解如何创建和使用自定义注解，以及如何通过 AOP 实现注解的功能。

## 技术栈

- Java 17
- Spring Boot 3.3.0
- Spring AOP
- AspectJ
- Lombok

## 环境要求

- JDK 17 或更高版本
- Maven 3.6.0 或更高版本

## 项目结构

```
src/main/java/com/p/springboot_test/
├── SpringbootTestApplication.java    # 应用程序入口
├── aspect/                          # AOP 切面实现和自定义注解
│   ├── Slice.java                   # 切片注解定义
│   └── SliceAspect.java            # 切片注解的 AOP 实现
├── controller/                      # 控制器层（用于测试注解）
└── service/                         # 服务层
```

## 自定义注解说明

本项目包含以下自定义注解：

### @Slice
- 用途：用于方法参数的分片处理
- 使用方式：在方法参数上添加 `@Slice(size = n)` 注解，其中 n 为分片大小
- 实现原理：通过 AOP 在方法执行前对参数进行分片处理
- 示例：
```java
public void processData(@Slice(size = 10) List<Data> dataList) {
    // 方法实现
}
```
