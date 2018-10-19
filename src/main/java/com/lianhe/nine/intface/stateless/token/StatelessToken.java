//package com.noob.stateless.token;
//
//import lombok.Data;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.springframework.beans.BeanWrapperImpl;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.PropertyValue;
//import org.springframework.beans.PropertyValues;
//import org.springframework.validation.DataBinder;
//
//import java.util.HashMap;
//import java.util.Map;
//@Data
//public class StatelessToken implements AuthenticationToken {
//    private String username;
//    private Map<String, ?> params;
//    private String clientDigest;
//
//    public StatelessToken(String username,  Map<String, ?> params, String clientDigest) {
//        this.username = username;
//        this.params = params;
//        this.clientDigest = clientDigest;
//    }
//
//
//    @Override
//    public Object getPrincipal() {
//        return username;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return clientDigest;
//    }
//
//    public static void main(String[] args) {
//
//    }
//    public static void test1() {
//        StatelessToken token = new StatelessToken(null, null, null);
//        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(token);
//        beanWrapper.setPropertyValue(new PropertyValue("username", "hjzgg"));
//        System.out.println(token.getUsername());
//    }
//
//    public static void test2() {
//        StatelessToken token = new StatelessToken(null, null, null);
//        DataBinder dataBinder = new DataBinder(token);
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", "hjzgg");
//        PropertyValues propertyValues = new MutablePropertyValues(params);
//        dataBinder.bind(propertyValues);
//        System.out.println(token.getUsername());
//    }
//}
