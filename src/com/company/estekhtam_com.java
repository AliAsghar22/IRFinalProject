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
public class estekhtam_com extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static Pattern adsPattern = Pattern.compile("^http://www.estekhtam.com/.* استخدام /.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches() || href.startsWith("http://job.estekhtam.com/"))
            return false;

        return (href.startsWith("http://www.estekhtam.com/")
                || href.startsWith("http://www.estekhtam.com/page/"))
                && (!href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AA%D9%87%D8%B1%D8%A7%D9%86/")
                && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%A8%D8%A7%D9%86%DA%A9-%D9%87%D8%A7/")
                && ! href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D9%87%D8%A7%DB%8C-%D9%88%DB%8C%DA%98%D9%87/")
                &&  !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86-%D9%87%D8%A7%DB%8C-%DA%A9%D8%B4%D9%88%D8%B1/")
                &&  !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D8%AF%D9%87-%D9%87%D8%A7/"));
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
                title= doc.select("h2.box-blog").get(0).select("a").get(0).text();
                System.out.println("title: " + title);
                /*for (int i=0 ; i < doc.getElementsByClass("entry").get(0).getElementsByTag("p").size(); i++){
                   body = body + doc.getElementsByClass("entry").get(0).getElementsByTag("p").get(i).text();
                }*/
                body = doc.getElementsByClass("entry").get(0).text();
                System.out.println("body: " + body);
                System.out.println("URL: " + url);

                System.out.println("date: " + date);

                Indexer.add(url, title, body, date);
           // }

        }
    }

}
