package com.company;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

/**
 * Created by Microsoft on 11/12/2015.
 */
public class webdivar_com extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
//        System.out.println(href +href.startsWith("http://www.webdivar.com/employer/"));
//        System.out.println("start tag = "+href.startsWith("http://estekhdame.ir/blog/tag/")+url);
        if (FILTERS.matcher(href).matches())
            return false;

        return href.startsWith("http://www.webdivar.com");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
//        System.out.println(page.getWebURL());
        //url title date body
//        System.out.println(page.getWebURL());
//        System.out.println(page.getWebURL().toString().contains("Employer"));
       if (page.getWebURL().toString().contains("Employer"))
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

                title = doc.getElementsByClass("company-title").get(0).text();
                body=doc.getElementsByAttributeValue("id","ContentPlaceHolder1_GridView7").get(0).text();
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
