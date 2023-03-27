package com.kaoyan;

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

public class CodeGenerator {

    @Test
    public void run() {

        // 1????????????????
        AutoGenerator mpg = new AutoGenerator();

        // 2?????????
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\projects\\muti_project\\kaoyan\\kaoyanService\\topicpostService" + "/src/main/java");
        gc.setAuthor("cxn");
        gc.setOpen(false); //??????????????????
        gc.setFileOverride(false); //????????????????
        gc.setServiceName("%sService");	//???Service?????????I
        gc.setIdType(IdType.ASSIGN_ID); //????????
        gc.setDateType(DateType.ONLY_DATE);//????????????????????????
        gc.setSwagger2(true);//????Swagger2??

        mpg.setGlobalConfig(gc);

        // 3???????????
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://101.43.219.115:3306/topicpost?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("cxn");
        dsc.setPassword("2001101025Cxn!");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4????????
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("topicpost"); //?????
        pc.setParent("com.kaoyan");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5??????????
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("category","post","topic","user");
        strategy.setNaming(NamingStrategy.underline_to_camel);//?????????????????????
        strategy.setTablePrefix(pc.getModuleName() + "_"); //???????????????

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//????????????????????????
        strategy.setEntityLombokModel(true); // lombok ??? @Accessors(chain = true) setter???????

        strategy.setRestControllerStyle(true); //restful api????????
        strategy.setControllerMappingHyphenStyle(true); //url???????????

        mpg.setStrategy(strategy);


        // 6?????
        mpg.execute();
    }
}