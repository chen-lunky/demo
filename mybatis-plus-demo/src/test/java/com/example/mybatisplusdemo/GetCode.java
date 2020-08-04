package com.example.mybatisplusdemo;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
/**
 * 代码生成器
 */
public class GetCode {
    @Test
    public  void main1() {
        //1.创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //2.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projecyPath = System.getProperty("user.dit");
        System.out.println(projecyPath);
        //生成路径
        gc.setOutputDir(projecyPath+"/src/main/java");
        //作者名
        gc.setAuthor("chen");
        //生成后是否打开资源管理器
        gc.setOpen(false);
        //重新生成时文件是否覆盖
        gc.setFileOverride(false);

        /**
         * mybatis-plus生成service层代码，默认接口名称第一个字母有IUcenterService
         */
        //去掉Service接口的首字母I
        gc.setServiceName("%Service");
        //主键策略
        gc.setIdType(IdType.ID_WORKER_STR);
        //定义生成的实体类日期类型
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);//开启Swagger2模式
        autoGenerator.setGlobalConfig(gc);

        //3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);

        //4.包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("mybatisplusdemo");//模块名
        packageConfig.setParent("com.example");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        autoGenerator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("user_test");//表名，根据表生成代码
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategyConfig.setTablePrefix(packageConfig.getModuleName()+"_");//生成实体时去掉表前缀

        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategyConfig.setRestControllerStyle(true); //restful api风格控制器
        strategyConfig.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        autoGenerator.setStrategy(strategyConfig);

        //6.执行
        autoGenerator.execute();

    }


}
