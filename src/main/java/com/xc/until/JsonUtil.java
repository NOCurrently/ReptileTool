package com.xc.until;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 阿里fastjson
 *
 * @author nachuan
 * @create 2019-04-28 18:10
 */
@Slf4j
public class JsonUtil {

    private static final SerializeConfig SERIALIZE_CONFIG;
    private static final ParserConfig global = ParserConfig.global;

    static {
        SERIALIZE_CONFIG = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        SERIALIZE_CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        SERIALIZE_CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
       /* IdentityHashMap<Type, ObjectDeserializer> deserializers = global.getDeserializers();
        deserializers.put(String.class,new DateCompatibilityFormat());
        ObjectDeserializer deserializer = global.getDeserializer(String.class);*/

    }
    /*@Slf4j
    public static class DateCompatibilityFormat extends StringCodec {
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
            return super.deserialze(parser, clazz, fieldName);
        }
    }*/
    /**
     * 序列化
     */
    private static final SerializerFeature[] features = {
            SerializerFeature.SkipTransientField,
            SerializerFeature.DisableCircularReferenceDetect
            //SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            //SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
//            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            //SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            //SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * 类转json字符串 时间复杂化处理,并且会打印空属性
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object, String defaultStr) {
        if (object == null) {
            return defaultStr;
        }
        try {
            return JSON.toJSONString(object, SERIALIZE_CONFIG, features);
        } catch (Exception e) {
            log.error("toJSONString Exception :" + object, e);
        }
        return defaultStr;
    }
    public static String toJSONString(Object object) {
        return  toJSONString(object,null);
    }
    /**
     * json字符串转bean
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            log.error("parseObject Class Exception :" + text, e);
        }
        return null;
    }

    public static Map<String, String> parseMapString(String text) {
        return parseObject(text, new TypeReference<Map<String, String>>() {
        });
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        try {
            return JSON.parseObject(text, typeReference);
        } catch (Exception e) {
            log.error("parseObject typeReference Exception :" + text, e);
        }
        return null;
    }

    /**
     * json字符串转为 对象集合
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception e) {
            log.error("parseArray Exception :" + text, e);
        }
        return null;
    }

}