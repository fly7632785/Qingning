package com.jafir.qingning.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author zaaach on 2016/1/26.
 */
public class StringUtils {
    /**
     * 提取出城市或者县
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district){
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }


    public static boolean isPasswordValid(String password) {
        //密码规则 正则
        String reg = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

}
