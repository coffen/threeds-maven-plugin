threeds的maven插件

为基于Springmvc的Api测试提供支持

使用方法
1. 生成脚本，mvn com.ouer.threeds.threeds-plugin:postman -Dpkg=xxx.xxx.xxx -DcontextPath=localhost:8080
2. 在目标工程的输出目录（target）找到文件：工程名.json.postman_collection
3. 打开postman界面，点击上方工具栏的import按钮，选择第2步中找到的文件并导入
4. 开始测试

注意事项：
1. 您的工程必须基于springmvc3.0及以上的，并且采用注解的方式（@Controller, @RestController, @RequestMapping等）配置；
2. 目前不支持restful风格的动态参数，如/shop/info/{id}；
3. 支持List，Array，Enum，MutilPartFile，Pageable和自定义类，不支持Map，Set。
