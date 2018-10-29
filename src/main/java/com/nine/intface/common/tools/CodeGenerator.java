package com.nine.intface.common.tools;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.StringUtils;

/**
 * @author : Rubi
 * @version : 2018-10-12 19:24 下午
 */
public class CodeGenerator {
    /**
     * 迭代次数
     */
    private static final int hashIterations = 9;
    /**
     * 加密规则
     */
    private static final String hashAlgorithmName = "MD5";

    public static String nineMD5(String origin) {
        if (StringUtils.isEmpty(origin)) {
            return origin;
        }

        String credentials = origin;
        return new SimpleHash(hashAlgorithmName, credentials, null, hashIterations).toString();

    }

}
