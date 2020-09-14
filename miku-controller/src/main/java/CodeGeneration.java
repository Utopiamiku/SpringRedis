import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 自动生成类
 *
 * @author utopiamiku
 * @date 2020/9/13 8:13
 **/
class CodeGeneration {

    //main函数
    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String oPath = System.getProperty("user.dir");//得到当前项目的路径

        gc.setOutputDir(oPath + "/src/main/java");   //生成文件输出根目录
        gc.setOpen(false);//生成完成后不弹出文件框
        gc.setFileOverride(true);  //文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("utopiamiku");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);   //设置数据库类型，我是postgresql
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8");  //指定数据库
        autoGenerator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);      // 表名生成策略
        strategy.setInclude(new String[]{"tb_user"});     // 需要生成的表
//        strategy.setSuperServiceClass((Class<?>) null);
//        strategy.setSuperServiceImplClass((Class<?>) null);
        strategy.setSuperMapperClass(null);
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.miku");
//        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("xml");
        autoGenerator.setPackageInfo(pc);

        // 执行生成
        autoGenerator.execute();
    }
}