package com.gytech.Utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

/**
 * Created by LQ on 2018/6/30.
 * i.lq.web.MyBatis
 */
public class MGenerator {


    public static void main(String[] args) {
        String[] tableNamesAdmin =
        /*{ "sys_user","sys_menu","sys_role","sys_role_menu","sys_user_role"
                ,"sys_area","sys_file","sys_log","sys_maplayer","sys_organization"
                ,"sys_run_log","sys_dict"
        };*/
                { "t_point_pos"
                };
        for (String tableName:tableNamesAdmin) {
            shell(tableName);
        }
    }
    private static void shell(String tableName){
        File file = new File("src");
        String path = file.getAbsolutePath();
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setIdType(IdType.ID_WORKER);
        //gc.setOutputDir(path+"\\src\\main\\java");
        gc.setOutputDir(path+"\\main\\java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        // .setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor("LL");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        /*dsc.setDbType(DbType.ORACLE);
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("lsdc");
        dsc.setPassword("zdww1402");
        dsc.setUrl("jdbc:oracle:thin:@192.168.3.17:1521:orcl");*/

        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("litugaoke01!");
        dsc.setUrl("jdbc:mysql://118.190.202.102:3306/odp?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { ""});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { tableName }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
//         strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.gytech");
        pc.setEntity("entity.work");
        pc.setMapper("mapper.work");
        pc.setXml("xml");
//        pc.setModuleName("entity");
        mpg.setPackageInfo(pc);


        // 执行生成
        mpg.execute();
    }

}
