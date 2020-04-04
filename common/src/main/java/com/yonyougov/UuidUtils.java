package com.yonyougov;


/**
 * @Author yindwe@yonyou.com
 * @Date 2019/7/20
 * @Description
 */
public class UuidUtils {
    static {
        cruxIdGenerator = new CruxIdGenerator();
    }
    private static CruxIdGenerator cruxIdGenerator;

    public static Long getCruxUUid(){
        return cruxIdGenerator.generate();
    }
}

