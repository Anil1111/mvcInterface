//package com.noob.stateless.tools;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.List;
//import java.util.Map;
//
//public class HmacSHA256Utils {
//    public static String digest(String key, String content) {
//        try {
//            Mac mac = Mac.getInstance("HmacSHA256");
//            byte[] secretByte = key.getBytes(StandardCharsets.UTF_8);
//            byte[] dataBytes = content.getBytes(StandardCharsets.UTF_8);
//
//            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
//            mac.init(secret);
//
//            byte[] doFinal = mac.doFinal(dataBytes);
//          //  byte[] hexB = new Hex().encode(doFinal);
//         //   return new String(hexB, StandardCharsets.UTF_8);
//            return null;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String digest(String key, Map<String, ?> map) {
//        StringBuilder s = new StringBuilder();
//        for(Object values : map.values()) {
//            if(values instanceof String[]) {
//                for(String value : (String[])values) {
//                    s.append(value);
//                }
//            } else if(values instanceof List) {
//                for(String value : (List<String>)values) {
//                    s.append(value);
//                }
//            } else {
//                s.append(values);
//            }
//        }
//        return digest(key, s.toString());
//    }
//}
