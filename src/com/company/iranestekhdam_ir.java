package com.company;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;

import java.util.regex.Pattern;

/**
 * Created by Taghizadeh on 12/9/2015.
 */
public class iranestekhdam_ir extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    // private final static Pattern adsPattern = Pattern.compile("^http://job.estekhtam.com/.* ads-.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches() || href.startsWith("http://iranestekhdam.ir/category/")  || href.startsWith("http://iranestekhdam.ir/about-me/"))
            return false;

        return ((href.startsWith("http://iranestekhdam.ir/"))
                && (!href.startsWith("http://iranestekhdam.ir/%D9%86%D8%B1%D8%AE-%D8%AA%D8%A8%D9%84%DB%8C%D8%BA%D8%A7%D8%AA-%D8%AF%D8%B1-%D8%A7%DB%8C%D8%B1%D8%A7%D9%86-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/")
                && !href.startsWith("http://iranestekhdam.ir/%D9%86%D9%85%D8%A7%DB%8C%D8%B4-%D8%AF%D9%84%D8%AE%D9%88%D8%A7%D9%87-%D8%A8%D8%B1%D8%A7%D8%B3%D8%A7%D8%B3-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86%D9%87%D8%A7/")
                ));
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

            System.out.println(url);
            if (url.toString().contains("%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85")){
            title = doc.select("h2 > a").get(0).text();
            System.out.println("title: " + title);
            body=doc.getElementsByClass("single").get(0).text();
            System.out.println("body: " + body);
            date = doc.getElementsByClass("pix-Publish").get(0).text();
            System.out.println("date: " + date);
            Indexer.add(url, title, body, date);
            }

        }
    }

}
