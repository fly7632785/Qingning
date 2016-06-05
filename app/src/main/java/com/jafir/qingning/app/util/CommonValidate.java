package com.jafir.qingning.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidate {

	/**
	 * 验证手机号是否合法
	 * 
	 * @param PhoneNumber
	 * @return
	 */
	public static boolean PhoneValidate(String inputPhone) {
		String regExp = "^[1]([3][0-9]{1}|50|51|52|53|55|56|57|58|59|47|70|80|81|82|83|84|85|86|87|88|89|85|86)[0-9]{8}$";
		return match(regExp, inputPhone);
	}

	/**
	 * 验证邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean EmailValidate(String inputEmail) {
		String regExp = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return match(regExp, inputEmail);
	}

	/**
	 * 验证URL地址
	 * 
	 * @param url
	 * @return
	 */
	public static boolean UrlValidate(String inputEmail) {
		String regExp = "^http://.*";
		return match(regExp, inputEmail);
	}

	/**
	 * 验证身份证号码
	 * 
	 * @param IDENTIFICATION
	 * @return
	 */
	public static boolean IdCardValidate(String inputStr) {
		String regExp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		return match(regExp, inputStr);
	}

	/**
	 * 验证密码是否符合规定
	 * 
	 * @param password
	 * @return
	 * */
	public static boolean PasswordValidate(String inputPassword) {
		String regExp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,20}$";
		return match(regExp, inputPassword);
	}

	/**
	 * 验证昵称是否符合规定
	 * 
	 * @param inputNickName
	 * @return
	 * */
	public static boolean NickNameValidate(String inputNickName) {
		String regExp = "^[\u4e00-\u9fa5_a-zA-Z0-9_]{2,8}$";
		return match(regExp, inputNickName);
	}

	/**
	 * 检查字符串是否为数字
	 * 
	 * @param str
	 *            指定的字符串
	 * @return true/false
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static String SQLValidate(String strContent) {
		strContent = strContent.toLowerCase();
		String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
				+ "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (strContent.indexOf(badStrs[i]) >= 0) {
				strContent += strContent.replace(badStrs[i], "");
			}
		}
		return strContent;
	}

	public static String ReplaceSQLValidate(String strContent) {
		strContent = strContent.toLowerCase();
		String regExp = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|like|insert|create|drop|use|table|from|union|order|count|group_concat|limit|#|%|input)\\b)";
		return strContent.replaceAll(regExp, "");
	}
}
