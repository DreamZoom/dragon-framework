package cn.dragon.framework.utils;


import com.google.common.base.CaseFormat;

public class CaseUtils {
    public static String camelToUnderline(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,name);
    }

}
