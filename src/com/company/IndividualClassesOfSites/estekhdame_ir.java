package com.company.IndividualClassesOfSites;

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
public class estekhdame_ir extends WebCrawler {
    private final static String VISIT_PATTERN = ".*\\.(html||Aspx)";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
//        System.out.println("start tag = "+href.startsWith("http://estekhdame.ir/blog/tag/")+url);
        if (FILTERS.matcher(href).matches() || href.startsWith("http://estekhdame.ir/blog/category/")|| href.startsWith("http://estekhdame.ir/blog/tag/"))
            return false;

        return href.startsWith("http://estekhdame.ir/blog");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {

        //url title date body
//        System.out.println("asd");
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);

            String url;
            String title;
            String body = null;
            String date = null;

            url = page.getWebURL().getURL();

        }
    }

}
