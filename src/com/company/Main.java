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
       // controller.addSeed("http://www.estekhdamiran.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AA%D9%87%D8%B1%D8%A7%D9%86");
       // controller.addSeed("http://www.estekhdamiran.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%AA%D9%87%D8%B1%D8%A7%D9%86");
       // controller.addSeed("http://www.estekhdamiran.com/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D9%87%D8%B1%D8%B3%D8%AA%D8%A7%D9%86");
        controller.addSeed("http://karfa.ir/");

        controller.start(karfa_ir.class, numberOfCrawlers);
        indexer.close();
    }

}
