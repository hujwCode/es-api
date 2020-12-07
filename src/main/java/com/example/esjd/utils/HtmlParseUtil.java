package com.example.esjd.utils;

import com.example.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hujiawei
 * @version : 1.0.0
 * @date : 2020/12/7 9:07 下午
 */
@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        new HtmlParseUtil().parseJD("c").forEach(System.out::println);
    }
    public ArrayList<Content> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyword;
        // 解析网页
        Document document = Jsoup.parse(new URL(url),30000);
        // 所有js方法
        Element element = document.getElementById("J_goodsList");
        // 获取所有li元素
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodList = new ArrayList<>();

        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setTitle(title);
            content.setPrice(price);
            content.setImg(img);
            goodList.add(content);
        }
        return goodList;
    }
}
