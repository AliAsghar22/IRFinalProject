package com.company;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Taghizadeh on 12/9/2015.
 */
public class Crawler extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.html";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static String estekhdam = "%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85";

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches())
            return false;
        return
                (href.startsWith("http://persianagahi.com/")
                        && href.contains(estekhdam)
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7-%d9%85%d8%af%d8%b1%da%a9-%d9%81%d9%88%d9%82-%d8%af%db%8c%d9%be%d9%84%d9%85")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%af%d9%88%d9%84%d8%aa%db%8c")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7%d9%86%da%a9%d9%87%d8%a7")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7-%d9%85%d8%af%d8%b1%da%a9-%d8%af%db%8c%d9%be%d9%84%d9%85")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7-%d9%85%d8%af%d8%b1%da%a9-%d9%81%d9%88%d9%82-%d8%af%db%8c%d9%be%d9%84%d9%85")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7-%d9%85%d8%af%d8%b1%da%a9-%d9%84%db%8c%d8%b3%d8%a7%d9%86%d8%b3")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%a8%d8%a7-%d9%85%d8%af%d8%b1%da%a9-%d9%81%d9%88%d9%82-%d9%84%db%8c%d8%b3%d8%a7%d9%86%d8%b3")
                        && !href.equals("http://persianagahi.com/%d8%b3%d8%a7%db%8c%d8%b1-%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85-%d8%ae%d8%b5%d9%88%d8%b5%db%8c")
                        && !href.equals("http://persianagahi.com/%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85"))
                        || (href.startsWith("http://estekhdam24.com/") && href.contains("استخدام"))
                        || (href.startsWith("http://www.karyab.net/") && href.contains(estekhdam))
                        || (href.startsWith("http://estekhdam-yab.persianblog.ir/"))
                        || (href.startsWith("http://www.e-estekhdam.com/") && href.contains(estekhdam))
                        || (href.startsWith("http://www.estekhdami.org/") && href.contains(estekhdam))
                        || (href.startsWith("http://www.irantalent.com/") && href.contains("job"))

                        //Alireza
                        || (href.startsWith("http://job.estekhtam.com/"))
                        || (href.startsWith("http://koomeshkar.ir/jobs/?j=%DA%A9%D9%84%DB%8C%D9%87%20%D9%85%D8%B4%D8%A7%D8%BA%D9%84"))
                ;
    }

    @Override
    public void visit(Page page) {

        //url title date body
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            org.jsoup.nodes.Document doc = Jsoup.parse(html);

            String url = page.getWebURL().getURL();
            String title = doc.title();
            String body = null;
            String date = Calendar.getInstance().getTime().toString();
            String place = null;
            System.out.println("hello");

            if (url.startsWith("http://persianagahi.com/")) {
                if (url.contains("page") || url.contains("search")
                        || url.contains("%d9%87%d9%85%d9%87_%d8%a2%da%af%d9%87%db%8c_%d9%87%d8%a7%db%8c")//همه گروره ها
                        || url.contains("category"))
                    return;
                title = doc.select("div.node_title").get(0).text();
                body = doc.select("div.agahi_content").get(0).text();

                Indexer.add(url, title, body);

            } else if (url.startsWith("http://estekhdam24.com/")) {
                title = doc.select("h1.singlegavi").get(0).text();
                body = doc.select("div.entry").get(0).text();
                date = doc.select("li.roshitepa").get(0).text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://www.karyab.net/")) {
                title = doc.select("h3").get(0).text();
                date = doc.select("span#date").get(0).text();
                body = doc.select("h3 ~ p").text();

                Indexer.add(url, title, body, date);

            } else if (url.startsWith("http://estekhdam-yab.persianblog.ir/post/")) {

                title = doc.select("a.post_title").get(0).text();
                if (title.contains("میلاد") || title.contains("ولادت") || title.contains("گرامی باد") || title.contains("سالروز") || title.contains("مبارک باد"))
                    return;
                body = doc.select("div.midbox_content").get(0).text();
                date = doc.select("div.writer").get(0).text().replace("نویسنده: استخدام یاب - ", "");
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://www.e-estekhdam.com/")) {
                title = doc.select("h1.entry-title").get(0).text();
                date = doc.select("time").get(0).text();
                body = doc.select("div.entry-content").get(0).text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://www.estekhdami.org/")) {
                title = doc.select("div.title").get(0).text();
                body = doc.select("div.matn > p").text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://www.irantalent.com/")) {
                if (url.startsWith("http://www.irantalent.com/home/recruitment") || url.startsWith("http://www.irantalent.com/home/jobs"))
                    return;
                title = doc.select("td.pageTitle2").get(0).text();
                body = doc.select("table.nopadding.centerTable.jobsMarginBottom28.jobMarginTop5").get(0).text()
                        + doc.select("table.nopadding.centerTable.jobsMarginBottom28").text();

                Indexer.add(url, title, body);
            } else if (url.startsWith("http://job.estekhtam.com/")) {
                if (url.toString().contains("ads-")) {
                    title = doc.getElementsByClass("detail-nav-row").get(0).text();
                    body = doc.getElementsByClass("emp-right").get(0).getElementsByTag("p").get(0).text();
                    Indexer.add(url, title, body, date);
                }

            }else if(url.startsWith("http://koomeshkar.ir/")){
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
                    Indexer.add(url, title, body, date);
                }
            }

        }
    }

}
