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
public class www_estekhdami_com extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static Pattern adsPattern = Pattern.compile("^http://www.estekhtam.com/.* استخدام /.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches() || url.toString().startsWith("http://www.estekhdami.com/category/"))
            return false;
      //  "%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85"
//"http://www.estekhdami.com/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D9%87%D8%B1%D8%AF%D8%A7%D8%B1%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86-%DA%A9%D8%B1%D9%85%D8%A7%D9%86%D8%B4/"
        return  (href.startsWith("http://www.estekhdami.com/") && href.contains("%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85")) || href.startsWith("http://www.estekhdami.com/page/") ;
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
            if (!url.startsWith("http://www.estekhdami.com/page/")){
                title= doc.getElementById("post-header").getElementsByTag("h1").get(0).text();
                System.out.println("title: " + title);

                body = doc.getElementsByClass("post-content").get(0).text();
                date = doc.getElementsByClass("date").get(0).text();

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

}
