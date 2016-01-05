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

//        controller.addSeed("http://estekhdame.ir/");
//        controller.start(estekhdame_ir.class,numberOfCrawlers);
        controller1.addSeed("http://www.estekhdamiran.com/");
        controller1.start(estekhdamiran_com.class,numberOfCrawlers);
//        controller2.addSeed("http://www.jobfind.ir/job/");
//        controller2.start(jobfind_ir.class,numberOfCrawlers);
//        controller3.addSeed("http://estekhdamia.ir/");
//        controller3.start(estekhdamia_ir.class,numberOfCrawlers);
//        controller4.addSeed("http://www.mihanwork.com/");
//        controller4.start(mihanwork_com.class,numberOfCrawlers);
//        controller5.addSeed("http://www.webdivar.com");
//        controller5.start(webdivar_com.class,numberOfCrawlers);
        indexer.close();
    }

}
