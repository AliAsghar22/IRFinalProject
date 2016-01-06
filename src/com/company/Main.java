package com.company;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main{

    private static int maxPagesToFetch = 1000;
    private static int maxDepthOfCrawling = 2;
    private static int numberOfCrawlers = 1;

    public static void main(String[] args) throws Exception {

        Indexer indexer = new Indexer();
        String crawlStorageFolder = "crawler";
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setMaxPagesToFetch(maxPagesToFetch);
        config.setResumableCrawling(false);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController controller1 = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController controller2 = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController controller3 = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController controller4 = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController controller5 = new CrawlController(config, pageFetcher, robotstxtServer);

        //AliReza
//        controller.addSeed("http://job.estekhtam.com/");
//        controller.addSeed("http://koomeshkar.ir/jobs/?j=%DA%A9%D9%84%DB%8C%D9%87%20%D9%85%D8%B4%D8%A7%D8%BA%D9%84");
//        controller.addSeed("http://www.estekhtam.com/");
//        controller.addSeed("http://www.estekhdamiran.com/");
//        controller.addSeed("http://estekhdamjoo.ir/");
        controller.addSeed("http://iranestekhdam.ir/");
        controller.addSeed("http://karfa.ir/");
        controller.addSeed("http://www.estekhdami.com/");

        //Vahid
//        controller.addSeed("https://jobinja.ir/jobs");
//        controller.addSeed("http://estekhdame.ir/");
//        controller.addSeed("http://www.webdivar.com/");
//        controller.addSeed("http://www.jobfind.ir/job/");
//        controller.addSeed("http://ekaar.ir/joblist.aspx");
//        controller.addSeed("http://eshetab.com/");

        //AliAsghar

//        controller.addSeed("http://persianagahi.com/");
//        controller.addSeed("http://estekhdam24.com/");
//        controller.addSeed("http://www.karyab.net/");
//        controller.addSeed("http://estekhdam-yab.persianblog.ir/");
//        controller.addSeed("http://www.e-estekhdam.com/");
//        controller.addSeed("http://www.estekhdami.org/");
//        controller.addSeed("http://www.irantalent.com/home/opportunities.php?r=130841466");

        indexer.close();
    }

}
