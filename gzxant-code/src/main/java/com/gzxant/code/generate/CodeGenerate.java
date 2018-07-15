package com.gzxant.code.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.*;

/**
 * 代码生成器
 * 根据表名自动生成对应目录以及文件
 * 例如：
 *   shop_order_goods ==> {
 *   	"controller" : com.gzxant.controller.shop.order.goods.ShopOrderGoodsController, 
 *   	"service" : [com.gzxant.service.shop.order.goods.ShopOrderGoodsService, 
 *   				 com.gzxant.service.shop.order.goods.IShopOrderGoodsService],
 *   	"dao" : com.gzxant.dao.shop.order.goods.ShopOrderGoodsDao,
 *   	"entity" : com.gzxant.entity.shop.order.goods.ShopOrderGoods,
 *   	".xml" : gzxant-common/src/main/resource/mapper/shop/order/goods/ShopOrderGoodsDao.xml
 *   }
 * 
 * @author xiaoyc
 * @since 2018-4-19
 */
public class CodeGenerate {
	// TODO 用扩展的方式实现自定义 mybatis plus

	// --------------变动参数区 start------------------------------------------
	/* === 表名 === */
	private static String[] tableNames = {"sys_message"}; //, "shop_order_goods", "shop_order_pay"
	/* === 开发者 === */
	public static String author = "ycxiao";
	/* === 输出目标项目，为空则生成在当前项目中 === */
	public static String targetProject = "gzxant-common";
	/* === 准备生成文件["controller", "service", "dao", "entity", "xml"] === */
	public static List<String> beGenerateFile = new ArrayList<>(
			Arrays.asList("controller", "service", "dao", "entity", "xml"));
	// --------------变动参数区 end------------------------------------------

	// --------------数据源配置区 start------------------------------------------
	public static String url = "jdbc:mysql://192.168.1.200:3306/gzxant_enroll?useUnicode=true&characterEncoding=utf8&useSSL=false";
	public static String name = "gzxant";
	public static String passWord = "gzxant1804";
	public static String driver = "com.mysql.jdbc.Driver";
	// --------------数据源配置区 end------------------------------------------

	public static void main(String[] args) {
		String output = "";

		AutoGenerator mpg = new AutoGenerator();
		// 选择 freemarker 引擎，默认 Veloctiy
		// mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		CodeGenerate cg = new CodeGenerate();
		output = cg.getPath(targetProject)
				+ File.separator + "src"
				+ File.separator + "main"
				+ File.separator + "java";

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(output);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor(author);

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sDao");
		gc.setXmlName("%sDao");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sService");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 自定义包配置
		PackageConfig pg = new PackageConfig();
		pg.setParent("com.gzxant");
		pg.setController("controller");
		pg.setMapper("dao");
		pg.setXml("xml");

		mpg.setPackageInfo(pg);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				// 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName(driver);
		dsc.setUsername(name);
		dsc.setPassword(passWord);
		dsc.setUrl(url);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true); // 全局大写命名 ORACLE 注意
		// strategy.setTablePrefix(new String[] { "tlog_", "tsys_" }); // 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel); // 表名生成策略
		strategy.setInclude(tableNames); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.gzxant.base.service.IBaseService");
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass("com.gzxant.base.service.impl.BaseService");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("com.gzxant.base.controller.BaseController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("author", this.getConfig().getGlobalConfig().getAuthor());
				this.setMap(map);
			}
		};

		// 页面 生成
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		// focList.add(new FileOutConfig("/template/list.ftl.vm") {
		// @Override
		// public String outputFile(TableInfo tableInfo) {
		// // 自定义输入文件名称
		// return output + "\\html\\" + tableInfo.getEntityName() + "\\list.ftl";
		// }
		// });
		//
		// focList.add(new FileOutConfig("/template/detail.ftl.vm") {
		// @Override
		// public String outputFile(TableInfo tableInfo) {
		// // 自定义输入文件名称
		// return output + "\\html\\" + tableInfo.getEntityName() + "\\detail.ftl";
		// }
		// });

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 调整 xml 生成目录
		if (!beGenerateFile.isEmpty() && beGenerateFile.contains("xml")) {
			focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
				@Override
				public String outputFile(TableInfo tableInfo) {
					CodeGenerate cg = new CodeGenerate();
					String path = cg.getPath("gzxant-common")
							+ File.separator + "src"
							+ File.separator + "main"
							+ File.separator + "resources"
							+ File.separator + "mapper";
					String[] names = tableInfo.getName().split("_");
					for (int i = 0; i < names.length; i++) {
						path = path + File.separator + names[i];
					}

					path = path + File.separator + tableInfo.getEntityName() + "Dao.xml";
					System.out.println("dao path : " + path);
					return path;
				}
			});
			cfg.setFileOutConfigList(focList);
		}
		mpg.setCfg(cfg);

		// 关闭默认 xml 生成，调整生成 至 根目录
		// TemplateConfig tc = new TemplateConfig();
		// tc.setXml(null);
		// mpg.setTemplate(tc);

		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
		// 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		if (!beGenerateFile.isEmpty() && beGenerateFile.contains("controller")) {
			tc.setController("/controller.java.vm");
		} else {
			tc.setController("");
		}

		if (!beGenerateFile.isEmpty() && beGenerateFile.contains("service")) {
			tc.setServiceImpl("/serviceImpl.java.vm");
		} else {
			tc.setServiceImpl("");
			tc.setService("");
		}

		tc.setXml("");

		if (!beGenerateFile.isEmpty() && !beGenerateFile.contains("entity")) {
			tc.setEntity("");
		}
		if (!beGenerateFile.isEmpty() && !beGenerateFile.contains("dao")) {
			tc.setMapper("");
		}
		// 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();

		// 打印注入设置【可无】
		System.err.println(mpg.getCfg().getMap().get("abc"));

	}

	/**
	 * 获取目标项目所在目录
	 * 
	 * @param targetProject
	 *            为空时默认为当前项目
	 * @return
	 */
	public String getPath(String targetProject) {
		String path = (new File(this.getClass().getResource(File.separator).getPath())).getAbsolutePath();
		path = path.replace( File.separator + "target" + File.separator + "classes", "");
		if (targetProject != null && !targetProject.equals("")) {
			path = path.replace("gzxant-code", targetProject);
		}

		return path;
	}
}
