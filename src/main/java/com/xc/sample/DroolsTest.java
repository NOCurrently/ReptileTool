package com.xc.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import us.codecraft.webmagic.Spider;

/**
 * This is a sample class to launch a rule.
 */
@Configuration
@EnableScheduling
public class DroolsTest {
	@Scheduled(cron = "0 10 4 ? * *")
	public void xc() {

		
		

	}
	
public static void main(String[] args) {
		Spider spider=Spider.create(new Sjkdsa());
        spider.addUrl("http://www.mm131.com/chemo/");
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();

}
}
