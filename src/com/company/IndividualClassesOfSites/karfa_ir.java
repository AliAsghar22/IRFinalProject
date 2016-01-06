package com.company.IndividualClassesOfSites;

import com.company.Indexer;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;

import java.util.regex.Pattern;

/**
 * Created by Taghizadeh on 12/9/2015.
 */
public class karfa_ir extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static Pattern adsPattern = Pattern.compile("^http://www.estekhtam.com/.* استخدام /.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches() )
            return false;

        return  href.startsWith("http://karfa.ir/")  ;
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {

        //url title date body
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            org.jsoup.nodes.Document doc = Jsoup.parse(html);

            String url;
            String title;
            String body = "";
            String date = "نامشخص";

            url = page.getWebURL().getURL();
            //if ()){
            //title = doc.title();
            title= doc.getElementById("news").getElementsByTag("h1").get(0).getElementsByTag("a").get(0).text();
            System.out.println("title: " + title);

            body = doc.getElementById("news").text();
            //   body.concat(doc.getElementsByClass("hreview").get(0).getElementsByTag("p").get(1).text());
            //   body.concat(doc.getElementsByClass("hreview").get(0).getElementsByTag("p").get(2).text());
            System.out.println("body: " + body);
            System.out.println("URL: " + url);

            System.out.println("date: " + date);

            Indexer.add(url, title, body, date);
            // }

        }
    }

}
