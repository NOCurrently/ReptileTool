package com.xc.until;

import com.xc.config.SsoFilter;
import com.xc.po.UserDO;

/**
 * @Author xiaochao18
 * @Description TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class SsoUtil {

    public static String getUserName() {
        UserDO user = SsoFilter.sThreadLocal.get();
        if (user == null) {
            return null;
        }
        return user.getName();
    }
}
