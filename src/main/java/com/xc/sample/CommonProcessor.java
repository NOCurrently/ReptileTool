package com.xc.sample;

import java.util.*;

import com.xc.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
public class CommonProcessor implements PageProcessor {
    private CommonBean commonBean;

    public CommonProcessor(CommonBean commonBean) {
        this.commonBean = commonBean;

    }

    private Site site = Site.me()
            .setDomain("http://127.0.0.1")
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public Site getSite() {
        return site;
    }

    Map<String, String> requestProperty = new HashMap<String, String>() {{
        put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
    }};

    @Override
    public void process(Page page) {
        List<PaChongBean> dataConfig = commonBean.dataConfig;
        requestProperty.put("Referer", page.getUrl().toString());
        if (dataConfig != null) {
            for (PaChongBean paChongBean : dataConfig) {
                List<String> path = getData(page, paChongBean);
                if (path.isEmpty()) {
                    log.error("path is empth");
                    continue;
                }
                DownLoadData.downLoadFromUrl(path, "E:/img/", requestProperty);
            }
        }
        List<PaChongBean> subAddress = commonBean.getSubAddress();
        if (subAddress != null) {
            List<String> strings = new ArrayList<>();
            for (PaChongBean sourcePath : subAddress) {
                List<String> data = getData(page, sourcePath);
                if (data.isEmpty()) {
                    log.error("path is empth");
                    continue;
                }
                strings.addAll(data);
            }
            page.addTargetRequests(strings);
        }

    }

    private List<String> getData(Page page, PaChongBean paChongBean) {
        List<Selectable> nodess = page.getHtml().xpath(paChongBean.getXpath()).nodes();
        String urlPrefix = paChongBean.getUrlPrefix();
        if (urlPrefix == null) {
            urlPrefix = commonBean.getUrlPrefix();
        }
        urlPrefix = urlPrefix == null ? "" : urlPrefix;
        if (StringUtils.isNotBlank(urlPrefix) && !urlPrefix.endsWith("/")) {
            urlPrefix = urlPrefix + "/";
        }
        String subXpath = paChongBean.getSubXpath();
        String attribute = paChongBean.getAttribute();
        List<String> list = new ArrayList<>();

        if (subXpath != null) {
            for (Selectable selectable : nodess) {
                List<Selectable> xpath = selectable.xpath(subXpath).nodes();
                String xc = getSelectableString(xpath, attribute, urlPrefix);
                if (xc != null) {
                    list.add(xc);
                }
            }

        } else {
            String xc = getSelectableString(nodess, attribute, urlPrefix);
            if (xc != null) {
                list.add(xc);
            }

        }

        return list;
    }

    private String getSelectableString(List<Selectable> nodess, String attribute, String urlPrefix) {
        for (Selectable selectable : nodess) {
            List<String> all = selectable.all();
            if (!all.isEmpty()) {
                for (String string : all) {
                    System.err.println(string);

                    int indexOf = string.indexOf(attribute);
                    if (indexOf < 0) {
                        return null;
                    }
                    int indexOfs = string.indexOf("\"", indexOf + attribute.length());
                    String substring = string.substring(indexOf + attribute.length(), indexOfs);
                    String s = urlPrefix + substring;
                    return s;
                }

            }
        }
        return null;
    }
}
