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
public class jobfind_ir  extends WebCrawler{
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
//        System.out.println("start tag = "+href.startsWith("http://estekhdame.ir/blog/tag/")+url);
        if (FILTERS.matcher(href).matches() )
            return false;

        return href.startsWith("http://www.jobfind.ir/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {

        //url title date body
//        System.out.println("asd");
        if (page.getParseData() instanceof HtmlParseData &&page.getWebURL().toString().contains("/job/")) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);

            String url;
            String title;
            String body = null;
            String date = null;

            url = page.getWebURL().getURL();
            title = doc.title();
//            System.out.println(title);
            if (doc.getElementsByClass("date").size()>0)
                date=doc.getElementsByClass("date").get(0).text();
            if (doc.getElementsByClass("excerpt").size()>0){
                body=doc.getElementsByClass("excerpt").get(0).text();
            }
//            System.out.println(date);
//            System.out.println(body);
//            System.out.println("URL: " + url);
//            System.out.println("title: " + title);
//            System.out.println("date: " + date);
//            System.out.println("body: " + body);
            System.out.println(date);
            Indexer.add(url, title, body, date);
        }
    }

}
