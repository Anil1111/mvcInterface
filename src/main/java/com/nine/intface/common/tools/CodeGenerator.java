package com.nine.intface.common.tools;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author : Rubi
 * @version : 2018-10-12 19:24 下午
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123";
        int hashIterations = 9;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println(obj);
    }

}
