package com.onejane.utils;

import java.util.ResourceBundle;

/**
 * 
 * @Description: 配置文件读取辅助类
 * @author yuh
 * @date 2017年11月9日 下午4:12:49
 *
 */
public class PropertiseUtil {

	/**
	 * 获取指定配置文件的指定key值
	 * 
	 * @param fileName
	 * @param key
	 * @return String
	 */
	public static String getDataFromPropertiseFile(String fileName, String key) {
		if (key == null || "".equals(key)) {
			System.out.println("getDataFromPropertiseFile fileName is null");
			return null;
		}
		if (key == null || "".equals(key)) {
			System.out.println("getDataFromPropertiseFile key is null");
			return null;
		}
		try {
			ResourceBundle resource = ResourceBundle.getBundle(fileName);
			if (resource == null) {
				System.out.println(fileName + "配置文件不存在");
				return null;
			}
			return resource.getString(key);
		} catch (Exception e) {
			System.out.println(fileName + "配置文件获取参数异常,key=" + key);
			return null;
		}

	}

	public static void main(String[] args) {
		String CONTRACT_TEMPLATE_PATH_PRE = PropertiseUtil.getDataFromPropertiseFile("sysconf", "contract_template_path_pre");// 业务机构code码;
		System.out.println(CONTRACT_TEMPLATE_PATH_PRE);
	}

}
