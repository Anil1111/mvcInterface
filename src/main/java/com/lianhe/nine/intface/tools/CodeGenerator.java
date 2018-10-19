package com.lianhe.nine.intface.tools;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
