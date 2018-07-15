package com.gzxant.constant;

import java.io.File;

import org.springframework.stereotype.Component;

/**
 * Created by chen on 2017/9/4.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统的参数设置，后面要放到数据库和缓存中。
 */
@Component("setting")
public class Setting {
	/**
	 * 启动类扫描mapper文件路径
	 */
	public static final String SCAN_MAPPER_PATH = "com.gzxant.dao";
    public static final String COMMON_SCAN_MAPPER_PATH = "com.gzxant.common.dao";
	
    /**
     * 文件根目录
     */
    public static final String BASEFLODER = File.separator + "var" + File.separator + "file";
    
    /**
     * excel 目录
     */
    public static final String EXCELADDRESS = "excel";

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

}
