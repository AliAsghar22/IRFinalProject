package com.company.IndividualClassesOfSites;

import com.company.Indexer;
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
public class eshetab_com extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase().toString();
//        System.out.println("start tag = "+href.startsWith("http://estekhdame.ir/blog/tag/")+url);
        if (FILTERS.matcher(href).matches())
            return false;
        if (href.contains("city")||href.contains("study")){
//            System.out.println("hi"+href);
            return false;
        }
        return href.startsWith("http://eshetab.com/state/")||href.contains("/ads/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
//        System.out.println(page.getWebURL().toString().contains("city")+"  "+page.getWebURL());
        //url title date body
        if (page.getWebURL().toString().contains("/ads/")||page.getWebURL().toString().contains("/view/"))
        {
            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

                String html = htmlParseData.getHtml();
                Document doc = Jsoup.parse(html);

                String url;
                String title;
                String body = null;
                String date = null;

                url = page.getWebURL().getURL();
                title = doc.title();
//                System.out.println(title);

                date = doc.getElementsByClass("nameDiv").get(1).text();
                body=doc.getElementsByClass("addsTexts").get(0).text();
                System.out.println(date);
                System.out.println(body);
    //            System.out.println(body);
//                System.out.println("URL: " + url);
//                System.out.println("title: " + title);
//                System.out.println("date: " + date);
//                System.out.println("body: " + body);
                Indexer.add(url, title, body, date);
            }
        }
    }

}
