package com.xc.sample;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DownLoadData {
    public static void downLoadFromUrl(List<String> urlStrs, String savePath, Map<String, String> requestProperty) {
        for (String urlStr : urlStrs) {
            URL url = null;
            try {
                url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置超时间为3秒
                conn.setConnectTimeout(3 * 1000);
                //防止屏蔽程序抓取而返回403错误
                for (Map.Entry<String, String> stringStringEntry : requestProperty.entrySet()) {
                    conn.setRequestProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
                }
                //得到输入流
                InputStream inputStream = conn.getInputStream();
                //获取自己数组
                byte[] getData = readInputStream(inputStream);

                //文件保存位置
                File saveDir = new File(savePath);
                if (!saveDir.exists()) {
                    saveDir.mkdir();
                }
                // String mimeType = CommonUtil.getMimeType(urlStr, getData);
                String fileName = UUID.randomUUID().toString() + "." + "jpg";
                File file = new File(saveDir + File.separator + fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(getData);
                if (fos != null) {
                    fos.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("info:" + url + " download success");
        }


    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
