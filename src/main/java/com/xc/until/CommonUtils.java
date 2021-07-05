package com.xc.until;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author xiaochao18
 * @date 2021-07-02 17:50
 */
@Slf4j
public class CommonUtils {
    /**
     * 校验
     */
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 打印map中有相同KEy但是不同value的数据
     *
     * @param map1
     * @param map2
     * @param message
     */
    public static void printDifferenceMap(Map map1, Map map2, String message) {
        MapDifference<String, Object> difference = Maps.difference(map1, map2);
        Map<String, MapDifference.ValueDifference<Object>> stringValueDifferenceMap = difference.entriesDiffering();
        if (stringValueDifferenceMap != null && !stringValueDifferenceMap.isEmpty()) {
            log.warn("     {},有重复的key键  {}", message, JsonUtil.toJSONString(stringValueDifferenceMap));
        }
    }

    /**
     * 使用javax.validation进行校验
     *
     * @param paramValue
     * @return
     */
    public static List<String> validator(Object paramValue) {
        Set<ConstraintViolation<Object>> violationSet = validator.validate(paramValue);
        if (violationSet != null && !CollectionUtils.isEmpty(violationSet)) {
            List<String> resultList = violationSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return resultList;
        }
        return new ArrayList<>();
    }

    /**
     * 字符串匹配regex, 匹配到的前加入prefix,后加入suffix
     *
     * @param str
     * @param regex
     * @param prefix
     * @param suffix
     * @return
     */
    public static String stringAppend(String str, String regex, String prefix, String suffix, String deleteStr) {
        Pattern pattern = Pattern.compile(regex);
        Matcher mc = pattern.matcher(str);
        StringBuilder sb = new StringBuilder(str);
        int prefixLength = prefix.length();
        int suffixLength = suffix.length();
        int index = 0;
        while (mc.find()) {
            String group = mc.group();
            int i = str.indexOf(group);
            sb.insert(i + index, prefix);
            index = index + prefixLength;
            sb.insert(i + group.length() + index, suffix);
            index = index + suffixLength;
        }
        if (deleteStr != null && !deleteStr.equals("")) {
            return sb.toString().replace(deleteStr, "");
        }
        return sb.toString();
    }

    public static List<String> getRegexStr(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher mc = pattern.matcher(str);
        List<String> list = new ArrayList<>();
        while (mc.find()) {
            String group = mc.group();
            list.add(group);
        }
        return list;
    }

}
