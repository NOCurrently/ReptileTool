package com.xc.sample;

import com.xc.until.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample class to launch a rule.
 */
@Slf4j
public class DroolsTest {

    public static void main(String[] args) {
        xc();
    }

    private static void xc() {

        CommonBean commonBean = new CommonBean();
        commonBean.setUrlPrefix("https://www.tupianzj.com");

        List<PaChongBean> dataConfig = new ArrayList<>();
        PaChongBean paChongBean = new PaChongBean();
        paChongBean.setXpath("//div[@id='bigpic']/a/img");
        paChongBean.setAttribute("src=\"");
        paChongBean.setUrlPrefix("");
        dataConfig.add(paChongBean);

        List<PaChongBean> subAddress = new ArrayList<>();
        PaChongBean subAddressBean = new PaChongBean();
        subAddressBean.setXpath("//ul[@class='d1 ico3']/li");
        subAddressBean.setSubXpath("//a");
        subAddressBean.setAttribute("href=\"");
        PaChongBean subAddressBean1 = new PaChongBean();
        subAddressBean1.setXpath("//div[@class='pages']/ul/li");
        subAddressBean1.setSubXpath("//a");
        subAddressBean1.setAttribute("href=\"");
        subAddress.add(subAddressBean);
        subAddress.add(subAddressBean1);

        commonBean.setSubAddress(subAddress);
        commonBean.setDataConfig(dataConfig);
        List<String> validator = CommonUtils.validator(commonBean);
        if (!validator.isEmpty()){
            log.error("validator = {}",validator);
            return;
        }
        Spider spider = Spider.create(new CommonProcessor(commonBean));
        spider.addUrl("https://www.tupianzj.com/meinv/mm/gaogensiwa/");
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
    }

}
