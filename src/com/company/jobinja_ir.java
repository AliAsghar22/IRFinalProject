package com.company;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

/**
 * Created by Microsoft on 10/12/2015.
 */
public class jobinja_ir extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
//        System.out.println("start tag = "+href.startsWith("http://jobinja.ir/jobs/")+url);
        if (FILTERS.matcher(href).matches())
            return false;

        return href.startsWith("https://jobinja.ir/jobs") || href.startsWith("https://jobinja.ir/companies/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String urlp=page.getWebURL().toString();
        //url title date body
        if (urlp.contains("companies")&&urlp.contains("job")&&!urlp.endsWith("job")&&page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);

            String url;
            String title;
            String body = null;
            String date = null;

            url = page.getWebURL().getURL();
//            System.out.println(doc.getElementsByClass("intro").get(0).getElementsByTag("header").get(0).getElementsByTag("h2").text());
            title = doc.getElementsByClass("job-title").get(0).text();
//            System.out.println(title);
            body=doc.getElementsByClass("three").get(0).text();
//            System.out.println(body);
            date="نامعین";

//            System.out.println(body);
//            System.out.println("URL: " + url);
//            System.out.println("title: " + title);
//            System.out.println("date: " + date);
//            System.out.println("body: " + body);
            Indexer.add(url, title, body, date);
        }
    }

}
