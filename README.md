

<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Sparion</h1>
<h4 align="center">基于 Vue 和 Spring Boot/Spring Cloud & Alibaba 前后端分离的分布式微服务架构</h4>

### 系统模块

```
sparion     
├── sparion-ui              // 前端框架 [80]
├── sparion-gateway         // 网关模块 [11000]
├── sparion-auth            // 认证中心 [12000]
├── sparion-api             // 接口模块
│       └── sparion-api-bom                             // 清单模块
│       └── sparion-api-client                          // 外部接口 [client]
│       └── sparion-api-client-feign                    // 外部feign
│       └── sparion-api-resource                        // 资源接口
│       └── sparion-api-resource-feign                  // 资源feign
│       └── sparion-api-server                          // 外部接口 [server]
│       └── sparion-api-server-feign                    // 外部feign
│       └── sparion-api-system                          // 系统接口
│       └── sparion-api-system-feign                    // 系统feign
├── sparion-common          // 通用模块
│       └── sparion-common-bom                          // 清单模块
│       └── sparion-common-cache                        // 缓存服务
│       └── sparion-common-core                         // 核心模块
│       └── sparion-common-feign                        // 远程调用
│       └── sparion-common-mybatis                      // 数据模块
│       └── sparion-common-nacos                        // 注册/配置中心
│       └── sparion-common-web                          // web接口
├── sparion-modules         // 业务模块
│       └── sparion-module-system                       // 系统服务 [13000]
│       └── sparion-module-resource                     // 资源服务 [14000]
│       └── sparion-module-ai                           // 资源服务 [15000]
│       └── sparion-module-product                      // 资源服务 [16000]
├── sparion-external        // 外部模块
│       └── sparion-external-client                      // 调用外部服务 [15000]
│       └── sparion-external-server                      // 外部服务回调 [16000]
├──pom.xml                	// 公共依赖
```

### 开发环境

| 环境  | 版本   |
| ----- | ------ |
| jdk   | 21     |
| maven | 3.8.6  |
| mysql | 8.4.3  |
| redis | 7.4.2  |
| nacos | 2.4.3  |
| minio | latest |

### 开发步骤

1. 准备环境(Docker)，如果不使用docker，请保持各组件版本与上述一致

   运行docker-compose.yaml文件

   ```shell
   doc/config/docker/docker-compose.yml
   ```

2. 在mysql里导入如下文件夹里的所有sql

   ```shell
   doc/config/sql
   ```

4. 启动如下微服务(必须)

   ```
   必须
   sparion-gateway
   sparion-auth
   sparion-module-system
   
   可选
   ```

### 参考

- [RuoYi-Cloud](https://github.com/yangzongzhuan/RuoYi-Cloud)
- [RuoYi-Cloud-Plus](https://github.com/dromara/RuoYi-Cloud-Plus)