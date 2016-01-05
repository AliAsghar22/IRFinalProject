package com.company;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Taghizadeh on 12/9/2015.
 */
public class Crawler extends WebCrawler {

    private final static String VISIT_PATTERN = ".*\\.(html||Aspx)";
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
    private final static String estekhdam = "%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85";

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches())
            return false;
//        System.out.println(url);
        return
                //AliAsghar
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
                        || ((href.startsWith("http://www.estekhtam.com/") || href.startsWith("http://www.estekhtam.com/page/"))
                        && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AA%D9%87%D8%B1%D8%A7%D9%86/")
                        && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%A8%D8%A7%D9%86%DA%A9-%D9%87%D8%A7/")
                        && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D9%87%D8%A7%DB%8C-%D9%88%DB%8C%DA%98%D9%87/")
                        && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86-%D9%87%D8%A7%DB%8C-%DA%A9%D8%B4%D9%88%D8%B1/")
                        && !href.startsWith("http://www.estekhtam.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D8%AF%D9%87-%D9%87%D8%A7/"))
                        || (href.startsWith("http://www.estekhdamiran.com/"))
                        || (href.startsWith("http://estekhdamjoo.ir/") && href.contains("استخدام"))
                        && (!href.startsWith("http://estekhdamjoo.ir/%D8%AF%D8%B1%D8%AC-%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85%DB%8C-%D8%B1%D8%A7%DB%8C%DA%AF%D8%A7%D9%86/")
                        && !href.startsWith("http://estekhdamjoo.ir/3687/%D8%AF%D8%B1-%DA%A9%D8%A7%D9%86%D8%A7%D9%84-%D8%AE%D8%A8%D8%B1%DB%8C-%C2%AB%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AC%D9%88%C2%BB-%D8%AF%D8%B1-%D8%AA%D9%84%DA%AF%D8%B1%D8%A7%D9%85-%D8%B9%D8%B6%D9%88-%D8%B4%D9%88%DB%8C%D8%AF-%D9%88-%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85%DB%8C-%D8%B1%D8%A7-%D8%AF%D8%B1%DB%8C%D8%A7%D9%81%D8%AA-%DA%A9%D9%86%DB%8C%D8%AF.html")
                        && !href.startsWith("http://estekhdamjoo.ir/news/"))
                        || (href.startsWith("http://iranestekhdam.ir/"))
                        && (!href.startsWith("http://iranestekhdam.ir/%D9%86%D8%B1%D8%AE-%D8%AA%D8%A8%D9%84%DB%8C%D8%BA%D8%A7%D8%AA-%D8%AF%D8%B1-%D8%A7%DB%8C%D8%B1%D8%A7%D9%86-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/")
                        && !href.startsWith("http://iranestekhdam.ir/%D9%86%D9%85%D8%A7%DB%8C%D8%B4-%D8%AF%D9%84%D8%AE%D9%88%D8%A7%D9%87-%D8%A8%D8%B1%D8%A7%D8%B3%D8%A7%D8%B3-%D8%A7%D8%B3%D8%AA%D8%A7%D9%86%D9%87%D8%A7/"))
                        || (href.startsWith("http://karfa.ir/"))
                        || ((href.startsWith("http://www.estekhdami.com/") && href.contains("%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85")) || href.startsWith("http://www.estekhdami.com/page/"))

                        //vahid
                        || (href.startsWith("http://estekhdame.ir/blog") && !href.startsWith("http://estekhdame.ir/blog/category/") && !href.startsWith("http://estekhdame.ir/blog/tag/"))
                        || (href.startsWith("http://www.jobfind.ir/"))
                        || (href.startsWith("https://jobinja.ir/jobs") || href.startsWith("https://jobinja.ir/companies/"))
                        || (href.startsWith("http://www.webdivar.com/"))
                        || (href.startsWith("http://eshetab.com/") && !(href.contains("city") || href.contains("study")))
                        || (href.startsWith("http://ekaar.ir/joblist.aspx") || href.startsWith("http://ekaar.ir/job"))


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

            //AliAsghar
            if (url.startsWith("http://persianagahi.com/")) {
                if (url.contains("page") || url.contains("search")
                        || url.contains("%d9%87%d9%85%d9%87_%d8%a2%da%af%d9%87%db%8c_%d9%87%d8%a7%db%8c")//همه گروره ها
                        || url.contains("category"))
                    return;
                title = doc.select("div.node_title").get(0).text();
                body = doc.select("div.agahi_content").get(0).text();

                Indexer.add(url, title, body, date);

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
                Indexer.add(url, title, body, date);
            }
            //AliReza
            else if (url.startsWith("http://www.estekhdami.com/")) {
                if (!url.startsWith("http://www.estekhdami.com/page/")) {
                    title = doc.getElementById("post-header").getElementsByTag("h1").get(0).text();
                    body = doc.getElementsByClass("post-content").get(0).text();
                    date = doc.getElementsByClass("date").get(0).text();
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://karfa.ir/")) {
                title = doc.getElementById("news").getElementsByTag("h1").get(0).getElementsByTag("a").get(0).text();
                body = doc.getElementById("news").text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://iranestekhdam.ir/")) {
                url = page.getWebURL().getURL();

                if (url.toString().contains("%d8%a7%d8%b3%d8%aa%d8%ae%d8%af%d8%a7%d9%85")) {
                    title = doc.select("h2 > a").get(0).text();
                    body = doc.getElementsByClass("single").get(0).text();
                    date = doc.getElementsByClass("pix-Publish").get(0).text();
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://estekhdamjoo.ir/")) {
                String pattern = "http://estekhdamjoo.ir/[0-9][0-9][0-9]+/.*";

                if (!url.toString().contains("PageId") && url.matches(pattern)) {
                    //title = doc.title();

                    title = doc.getElementById("ctl00_ContentPlaceHolder1_DataList1_ctl00_TitleLabel").text();
                    body = doc.getElementsByClass("OrgBox").get(0).getElementsByClass("boxInformation").get(0).text();
                    body = body + doc.getElementById("divText").text();
                    Indexer.add(url, title, body, date);

                }
            } else if (url.startsWith("http://www.estekhdamiran.com/")) {
                String pattern = "http://www.estekhdamiran.com/[0-9]+/.*";
                if (url.matches(pattern)) {
                    title = doc.getElementsByClass("entry-title").get(0).text();
                    body = doc.getElementsByClass("entry-content").get(0).text();
                    date = doc.getElementsByClass("updated").get(0).text();
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://www.estekhtam.com/")) {
                title = doc.select("h2.box-blog").get(0).select("a").get(0).text();
                body = doc.getElementsByClass("entry").get(0).text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://job.estekhtam.com/")) {
                if (url.contains("ads-")) {
                    title = doc.getElementsByClass("detail-nav-row").get(0).text();
                    body = doc.getElementsByClass("emp-right").get(0).getElementsByTag("p").get(0).text();
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://koomeshkar.ir/")) {
                for (int i = 1; i < doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").size(); i++) {
                    title = doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(1).text();
                    body = doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(3).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(4).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(5).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(6).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(7).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(8).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(9).text();
                    body = body + doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(12).text();
                    date = doc.getElementsByClass("job-tbl").get(0).getElementsByTag("tr").get(i).getElementsByTag("td").get(2).text();
                    Indexer.add(url, title, body, date);
                }
            }
            //Vahid
            else if (url.startsWith("http://eshetab.com/")) {
                if (url.contains("/ads/") || url.contains("/view/")) {
                    url = page.getWebURL().getURL();
                    title = doc.title();
                    date = doc.getElementsByClass("nameDiv").get(1).text();
                    body = doc.getElementsByClass("addsTexts").get(0).text();
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://www.webdivar.com/")) {
                if (url.contains("Employer")) {
//                    System.out.println(url);
                    url = page.getWebURL().getURL();
                    title = doc.getElementsByClass("company-title").get(0).text();
                    body = doc.getElementsByAttributeValue("id", "ContentPlaceHolder1_GridView7").get(0).text();
                    Indexer.add(url, title, body, date);
                }

            } else if (url.startsWith("http://ekaar.ir/job-")) {
                    title = doc.title();
                    date = doc.getElementsByClass("jobrightinfo").get(0).getElementsByTag("span").get(2).text();
                    body = doc.getElementsByClass("text").get(0).text();
                    Indexer.add(url, title, body, date);
            } else if (url.startsWith("https://jobinja.ir/")) {
                title = doc.getElementsByClass("job-title").get(0).text();
                body = doc.getElementsByClass("three").get(0).text();
                Indexer.add(url, title, body, date);
            } else if (url.startsWith("http://www.jobfind.ir/")) {
                if (url.contains("/job/")){
                    title = doc.title();
                    if (doc.getElementsByClass("date").size() > 0)
                        date = doc.getElementsByClass("date").get(0).text();
                    if (doc.getElementsByClass("excerpt").size() > 0) {
                        body = doc.getElementsByClass("excerpt").get(0).text();
                    }
                    Indexer.add(url, title, body, date);
                }
            } else if (url.startsWith("http://estekhdame.ir/")) {
                title = doc.title();
                if (doc.getElementsByClass("aexpire").size() > 0)
                    date = doc.getElementsByClass("aexpire").get(0).text();
                if (doc.getElementsByClass("matn").size() > 0)
                    body = doc.getElementsByClass("matn").get(0).text();
                Indexer.add(url, title, body, date);
            }

        }
    }
}

