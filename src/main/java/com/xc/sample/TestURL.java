package com.xc.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestURL {
	public static void xc(String path,String name,String sour) {
		URL url = null;
 
		//从网络上下载一张图片
		InputStream inputStream = null;
		OutputStream outputStream = null;
		//建立一个网络链接
		HttpURLConnection con = null;
		try {
			url = new URL(path);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Referer", sour);
			inputStream = con.getInputStream();
			outputStream = new FileOutputStream(new File("E:/img/"+name+".jpg"));
			int n = -1;
			byte b [] = new byte[1024];
			while ((n = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, n);
			}
			outputStream.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
 
}
