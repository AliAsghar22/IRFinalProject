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
public class koomeshkar_ir extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
 //   private final static Pattern adsPattern = Pattern.compile("^http://job.estekhtam.com/.* ads-.*");


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (FILTERS.matcher(href).matches())
            return false;
        System.out.println(href);
        return href.startsWith("http://koomeshkar.ir/jobs/?j=%DA%A9%D9%84%DB%8C%D9%87%20%D9%85%D8%B4%D8%A7%D8%BA%D9%84");
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


                //title = doc.title();
               for (int i =1 ; i<doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").size(); i++){
                   title = doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(1).text();

                   body =  doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(3).text();
                   body = body  + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(4).text();
                   body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(5).text();
                   body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(6).text();
                   body = body  + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(7).text();
                   body = body  + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(8).text();
                   body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(9).text();
                   body = body  + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(12).text();



                   date = doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(2).text();
                   System.out.println("URL: " + url);
                   System.out.println("title: " + title);
                   System.out.println("date: " + date);
                   System.out.println("body: " + body);

                   Indexer.add(url, title, body, date);
               }



        }
    }

}
