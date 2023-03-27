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

/**
 * <p>
 * ����������
 * </p>
 *
 * @author ibacon66
 * @since 2022-12-19
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1����������������
        AutoGenerator mpg = new AutoGenerator();

        // 2��ȫ������
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\projects\\muti_project\\kaoyan\\kaoyanService\\institutions" + "/src/main/java");
        gc.setAuthor("cxn");
        gc.setOpen(false); //���ɺ��Ƿ����Դ������
        gc.setFileOverride(false); //��������ʱ�ļ��Ƿ񸲸�
        gc.setServiceName("%sService");	//ȥ��Service�ӿڵ�����ĸI
        gc.setIdType(IdType.ASSIGN_ID); //��������
        gc.setDateType(DateType.ONLY_DATE);//�������ɵ�ʵ��������������
        gc.setSwagger2(true);//����Swagger2ģʽ

        mpg.setGlobalConfig(gc);

        // 3������Դ����
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://101.43.219.115:3306/kaoyandata?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("cxn");
        dsc.setPassword("2001101025Cxn!");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4��������
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("institutions"); //ģ����
        pc.setParent("com.kaoyan");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5����������
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("kaoyandata","dzxx","jsj","rjgc","wlaq","schools","schoolDetail");
        strategy.setNaming(NamingStrategy.underline_to_camel);//���ݿ��ӳ�䵽ʵ�����������
        strategy.setTablePrefix(pc.getModuleName() + "_"); //����ʵ��ʱȥ����ǰ׺

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//���ݿ���ֶ�ӳ�䵽ʵ�����������
        strategy.setEntityLombokModel(true); // lombok ģ�� @Accessors(chain = true) setter��ʽ����

        strategy.setRestControllerStyle(true); //restful api��������
        strategy.setControllerMappingHyphenStyle(true); //url���շ�ת���ַ�

        mpg.setStrategy(strategy);


        // 6��ִ��
        mpg.execute();
    }
}