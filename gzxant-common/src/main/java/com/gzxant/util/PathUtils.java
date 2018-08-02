package com.gzxant.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.gzxant.constant.Global;
import com.gzxant.constant.Setting;

/**
 * 获取路径工具类
 */
public class PathUtils {
	/**
	 * 获取项目的绝对路径
	 * 如果是jar包，则返回jar目录
	 * @return 项目的绝对路径
	 */
	public static String getRootPath() {
		String classPath="",rootPath  = "";
		try {
			//防止有空格,%20等的出现
			classPath = URLDecoder.decode(PathUtils.class.getClassLoader().getResource("").getPath(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(!"".equals(classPath)){
			//windows下
			if("\\".equals(File.separator)){
				rootPath  = classPath.substring(1,classPath.indexOf("/target/classes"));
				rootPath = rootPath.replace("/", "\\");
			}
			//linux下
			if("/".equals(File.separator)){
				rootPath  = classPath.substring(0,classPath.indexOf("/target/classes"));
				rootPath = rootPath.replace("\\", "/");
			}
		}
		return rootPath;
	}
	
	/**
	 * 获取访问者用户的ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
	
	/**
	 * <p>
	 * 校验详情页面跳转接口参数的正确性，参数错误则返回false
	 * </p>
	 * 
	 * <p>
	 * action 不能为空，并且是["detail", "insert", "update"] 其中一个
	 * </p>
	 * <p>
	 * action为update或者detail时，id不能为空
	 * </p>
	 * 
	 * @param action
	 * @param id
	 * @return
	 */
	public static boolean checkDetailPath(String action, String id) {
		// action 不能为空，并且是["detail", "insert", "update"] 其中一个
		if (StringUtils.isBlank(action)
			|| !Global.ACTION_TYPE.contains(action)) {
			return false;
		}
		
		// action为update或者detail时，id不能为空
		if (StringUtils.isBlank(id) && !action.equals(Global.ACTION_INSERT)) {
			return false;
		}
		
		return true;
	}
	
	public static String getUploadPath() {
		return Setting.BASEFLODER + File.separator + "gzxant" + File.separator + "enroll";
	}
	
}
