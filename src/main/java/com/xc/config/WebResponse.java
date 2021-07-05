package com.xc.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description:响应类
 *
 * @author 肖超
 * @date 2018年9月20日
 */
@Setter
@Getter
@ToString
public class WebResponse {

    private String code;
    private String displayMessage;
    private String errorMessage;
    private Object data;

    private WebResponse(String code, String displayMessage, String errorMessage, Object data) {
        this.code = code;
        this.displayMessage = displayMessage;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static WebResponse fail(String code, String displayMessage, String errorMessage, Object data) {

        return execute(code, displayMessage, errorMessage, data);
    }

    public static WebResponse fail(String code, String displayMessage, Object data) {

        return execute(code, displayMessage, null, data);
    }

    public static WebResponse fail(String code, String displayMessage) {
        return execute(code, displayMessage, null, null);
    }

    private static WebResponse execute(String code, String displayMessage, String errorMessage, Object data) {

        return new WebResponse(code, displayMessage, errorMessage, data);
    }


    public static WebResponse succeed(String displayMessage, Object data) {
        return execute("0", displayMessage, null, data);

    }

    public static WebResponse succeed(Object data) {
        return execute("0", "success", null, data);

    }

}
