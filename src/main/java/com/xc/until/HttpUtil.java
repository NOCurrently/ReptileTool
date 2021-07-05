package com.xc.until;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class HttpUtil {

    private static final CloseableHttpClient httpclient = HttpClient.getHttpClient();

    //默认字符编码
    private static final String CHARSET = "UTF-8";

    public static String doGet(String url, Map<String, String> param, Map<String, String> headers, Integer connTimeout, Integer readTimeout) {

        // 创建Httpclient对象
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null && connTimeout > 0) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null && readTimeout > 0) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            httpGet.setConfig(customReqConf.build());

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), CHARSET);
            }
        } catch (Exception e) {
            log.error("HttpUtil.doGet Exception", e);
        }
        return resultString;
    }

    public static String doGet(String url, Map<String, String> param) {
        return doGet(url, param, null, null, null);
    }

    public static String doGet(String url, Map<String, String> param, Map<String, String> headers) {
        return doGet(url, param, headers, null, null);
    }

    public static String doPost(String url, Map<String, String> param, Map<String, String> headers, Integer connTimeout, Integer readTimeout) {
        // 创建Httpclient对象
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null && connTimeout > 0) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null && readTimeout > 0) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            httpPost.setConfig(customReqConf.build());
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpclient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), CHARSET);
        } catch (Exception e) {
            log.error("HttpUtil.doPost Exception", e);
        }

        return resultString;
    }

    public static String doPost(String url, Map<String, String> param) {
        return doPost(url, param, null, 1000, 1000);
    }

    public static String doPost(String url, Map<String, String> param, Map<String, String> headers) {
        return doPost(url, param, headers, null, null);
    }

    public static String doPostJson(String url, String json, Map<String, String> headers) {
        return doPostJson(url, json, headers, null, null);
    }

    public static String doPostJson(String url, String json) {
        return doPostJson(url, json, null, null, null);
    }

    public static String doPostJson(String url, String json, Map<String, String> headers, Integer connTimeout, Integer readTimeout) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置参数

            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null && connTimeout > 0) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null && readTimeout > 0) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            if (connTimeout != null && readTimeout != null) {
                httpPost.setConfig(customReqConf.build());
            }

            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), CHARSET);
        } catch (Exception e) {
            log.error("HttpUtil.doPostJson Exception", e);
        }

        return resultString;
    }
}