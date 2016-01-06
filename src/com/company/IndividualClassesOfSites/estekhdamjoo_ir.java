package com.company.IndividualClassesOfSites;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;

import java.util.regex.Pattern;

/**
 * Created by Taghizadeh on 12/9/2015.
 */
public class estekhdamjoo_ir extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static Pattern adsPattern = Pattern.compile("^http://job.estekhtam.com/.* ads-.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches() || href.contains("service") || href.startsWith("http://estekhdamjoo.ir/استخدام/"))
            return false;
        return (href.startsWith("http://estekhdamjoo.ir/") && href.contains("استخدام"))
                && (!href.startsWith("http://estekhdamjoo.ir/%D8%AF%D8%B1%D8%AC-%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85%DB%8C-%D8%B1%D8%A7%DB%8C%DA%AF%D8%A7%D9%86/")
                && !href.startsWith("http://estekhdamjoo.ir/3687/%D8%AF%D8%B1-%DA%A9%D8%A7%D9%86%D8%A7%D9%84-%D8%AE%D8%A8%D8%B1%DB%8C-%C2%AB%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AC%D9%88%C2%BB-%D8%AF%D8%B1-%D8%AA%D9%84%DA%AF%D8%B1%D8%A7%D9%85-%D8%B9%D8%B6%D9%88-%D8%B4%D9%88%DB%8C%D8%AF-%D9%88-%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85%DB%8C-%D8%B1%D8%A7-%D8%AF%D8%B1%DB%8C%D8%A7%D9%81%D8%AA-%DA%A9%D9%86%DB%8C%D8%AF.html")
                && !href.startsWith("http://estekhdamjoo.ir/news/"));
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
            String date = "نا مشخص";

            url = page.getWebURL().getURL();
            String pattern = "http://estekhdamjoo.ir/[0-9][0-9][0-9]+/.*";

            if (!url.toString().contains("PageId") && url.matches(pattern)){
                //title = doc.title();

                System.out.println("URL: " + url);
                title=doc.getElementById("ctl00_ContentPlaceHolder1_DataList1_ctl00_TitleLabel").text();
                System.out.println("title: " + title);
                body=doc.getElementsByClass("OrgBox").get(0).getElementsByClass("boxInformation").get(0).text();
                body = body + doc.getElementById("divText").text();
                System.out.println("body: " + body);

                System.out.println("date: " + date);


            }

        }
    }

}
