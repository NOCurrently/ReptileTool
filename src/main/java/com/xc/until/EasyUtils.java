package com.xc.until;


import lombok.extern.slf4j.Slf4j;

/**
 * @author changliang time:2013-3-27 19:48:00
 */
@Slf4j
public abstract class EasyUtils {
    public static int parseInt(Object origin) {
        return parseInt(origin, -1);
    }

    public static int parseInt(Object origin, int defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        if (origin.getClass() == int.class || origin.getClass() == Integer.class) {
            return (int) origin;
        }
        String s = origin.toString();
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + s);
        }
        return defaultValue;
    }

    public static long parseLong(Object origin) {
        return parseLong(origin, -1);
    }

    public static long parseLong(Object origin, long defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        if (origin.getClass() == long.class || origin.getClass() == Long.class) {
            return (long) origin;
        }
        String s = origin.toString();
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + s);
        }
        return defaultValue;
    }


    public static double parseDouble(Object origin) {
        return parseDouble(origin, -1);
    }

    public static double parseDouble(Object origin, double defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        if (origin.getClass() == double.class || origin.getClass() == Double.class) {
            return (double) origin;
        }
        String s = origin.toString();
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + s);
        }
        return defaultValue;
    }

    public static boolean parseBoolean(Object origin) {
        return parseBoolean(origin, false);
    }

    public static boolean parseBoolean(Object origin, boolean defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        if (origin.getClass() == boolean.class || origin.getClass() == Boolean.class) {
            return (boolean) origin;
        }
        String s = origin.toString();
        try {
            return Boolean.parseBoolean(s);
        } catch (Exception e) {
            log.error("转型异常，参数：" + s);
        }
        return defaultValue;
    }


    public static String toString(Object o) {
        return toString(o, "");
    }

    public static String toString(Object origin, String defaultValue) {
        if (origin == null) {
            return defaultValue;
        }
        String temp = origin.toString();
        return temp;
    }

}

