package com.company;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main{

    private static int maxPagesToFetch = 1000;
    private static int maxDepthOfCrawling = 10;
    private static int numberOfCrawlers = 2;

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
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//        controller.addSeed("http://persianagahi.com/search/node/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85");
//        controller.addSeed("http://estekhdam24.com/hire-bank/");
//        controller.addSeed("http://www.karyab.net/");
//        controller.addSeed("http://estekhdam-yab.persianblog.ir/");
//        controller.addSeed("http://www.e-estekhdam.com/");
//        controller.addSeed("http://www.estekhdami.org/");
//        controller.addSeed("http://www.irantalent.com/home/opportunities.php?r=60761504");
//        controller.addSeed("http://www.cityad.ir/");
//        controller.addSeed("http://job.estekhtam.com/");
        controller.addSeed("http://koomeshkar.ir/jobs/?j=%DA%A9%D9%84%DB%8C%D9%87%20%D9%85%D8%B4%D8%A7%D8%BA%D9%84");
        controller.start(Crawler.class, numberOfCrawlers);

        indexer.close();
    }

}
