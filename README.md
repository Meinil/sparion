<div align="center">
   <h1>
       Sparion
    </h1>
</div>

我自己的一个后台管理系统，预计支持。。。还没想好:laughing::laughing::laughing::laughing:，先搭个架子出来吧

### 工具类
1. FileUtils 文件上传下载工具类

   ```java
   @RestController
   @RequestMapping("/file")
   public class FileInfoController {
   
       @Autowired
       private FileUtils fileUtils;
   
       @PostMapping("/upload")
       public List<FileInfo> upload(@RequestParam("files") MultipartFile[] files) {
           Files localFiles = fileUtils.getLocalFiles();
           return localFiles.upload(files);
       }
   }
   ```

2. `CipherUtils`加解密工具类

   ```java
   // 加密秘钥
   String secretKey = CipherUtils.generateSecret(16);
   String password = "123456";
   try {
       String encrypt = CipherUtils.encrypt(secretKey, password);
   } catch (GeneralSecurityException e) {
       throw new SparionException("密码加密异常");
   }
   
   // 解密
   String secretKey = CipherUtils.generateSecret(16);
   String password = "&*（&…………%￥#@@";
   try {
       String decrypt = CipherUtils.decrypt(secretKey, password);
   } catch (GeneralSecurityException e) {
       throw new SparionException("密码解密异常");
   }
   ```

   