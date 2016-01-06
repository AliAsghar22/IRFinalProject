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
public class job_estekhtam_com extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static Pattern adsPattern = Pattern.compile("^http://job.estekhtam.com/.* ads-.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches() || href.startsWith("http://job.estekhtam.com/tariff") || href.startsWith("http://www.estekhtam.com/"))
            return false;
        System.out.println(href);
        return href.startsWith("http://job.estekhtam.com/");
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
            String body = null;
            String date = null;

            url = page.getWebURL().getURL();

            if (url.toString().contains("ads-")){
                //title = doc.title();
                title=doc.getElementsByClass("detail-nav-row").get(0).text();
                body=doc.getElementsByClass("emp-right").get(0).getElementsByTag("p").get(0).text();
                System.out.println("URL: " + url);
                System.out.println("title: " + title);
                System.out.println("date: " + date);
                System.out.println("body: " + body);

                Indexer.add(url, title, body, date);
             }

        }
    }

}
