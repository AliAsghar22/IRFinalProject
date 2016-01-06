package com.company;

/**
 * Created by Ali Asghar on 1/5/2016.
 */
public class Searcher {
    public static void main(String[] args) throws Exception{
        Indexer indexer = new Indexer();
        String title = "عنوان";
        String body = "بدنه";

        System.out.println(indexer.getUrlByTitle(title));
        System.out.println(indexer.getUrlByBody(body));
        System.out.println(indexer.getUrlByBodyAndTitle(body, title));

        indexer.close();
    }
}
