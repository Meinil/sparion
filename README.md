

<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Sparion</h1>
<h4 align="center">基于 Vue 和 Spring Boot/Spring Cloud & Alibaba 前后端分离的分布式微服务架构</h4>



### 开发步骤

1. 准备环境(Docker)，如果不使用docker，请保持各组件版本与下述一致

   运行docker-compose.yaml文件

   ```shell
   doc/config/docker/docker-compose.yml
   ```

2. 在mysql里导入如下文件夹里的所有sql

   ```shell
   doc/config/sql
   ```

3. 在nacos里导入如下文件里的配置

   ```shell
   doc/config/nacos
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