package com.xc.sample;

import java.util.List;
import java.util.UUID;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class Sjkdsa implements PageProcessor {

    public static final String BASE_URL = "http://i.xpaper.net/cnsports";

    private Site site = Site.me()
            .setDomain(BASE_URL)
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        List<Selectable> nodess = page.getHtml().xpath("//div[@class='content']/div[@class='content-pic']/a/").nodes();
        for (Selectable selectable : nodess) {
            List<String> all = selectable.all();
            if (!all.isEmpty()) {
                for (String string : all) {
                    System.err.println(string);
                    int indexOf = string.indexOf("src=\"");
                    int indexOfs = string.indexOf("\">");
                    String substring = string.substring(indexOf + 5, indexOfs);
                    TestURL.xc(substring, UUID.randomUUID().toString(), page.getUrl().toString());
                    //System.out.println(page.getUrl().toString());
                }

            }
        }
        List<Selectable> nodes = page.getHtml().xpath("//div[@class='main']/dl[@class='list-left public-box']/dd/").nodes();
        for (Selectable selectable : nodes) {
            List<String> all = selectable.links().all();
            if (!all.isEmpty()) {
                page.addTargetRequests(all);
            }

        }
        List<Selectable> nodesss = page.getHtml().xpath("//div[@class='content']/div[@class='content-page']/").nodes();
        for (Selectable selectable : nodesss) {
            List<String> all = selectable.links().all();
            if (!all.isEmpty()) {
                page.addTargetRequests(all);
            }

        }

    }

}
