package com.company;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Taghizadeh on 12/9/2015.
 */

public class Indexer {
    private final static String luceneIndexPath = "index";
    Analyzer analyzer;
    Directory index;
    IndexWriterConfig config;
    static IndexWriter w;

    public Indexer() throws IOException {
        analyzer = new PersianAnalyzer();
        config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        index = FSDirectory.open(new File(luceneIndexPath).toPath());
        w = new IndexWriter(index, config);
    }

    public void close(){
        try {
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getUrlByTitle(String title){
        try {
            ArrayList<String> urls = new ArrayList<>();
            IndexReader rdr = DirectoryReader.open(FSDirectory.open(new File("index").toPath()));
            IndexSearcher is = new IndexSearcher(rdr);
            QueryParser parser = new QueryParser("title", new PersianAnalyzer());
            Query query = parser.parse(title);
            TopDocs hits = is.search(query, 1000);
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                urls.add(is.doc(scoreDoc.doc).get("url"));
            }
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> getUrlByBody(String body){
        try {
            ArrayList<String> urls = new ArrayList<>();
            IndexReader rdr = DirectoryReader.open(FSDirectory.open(new File("index").toPath()));
            IndexSearcher is = new IndexSearcher(rdr);
            QueryParser parser = new QueryParser("body", new PersianAnalyzer());
            Query query = parser.parse(body);
            TopDocs hits = is.search(query, 1000);
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                urls.add(is.doc(scoreDoc.doc).get("url"));
            }
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> getUrlByBodyAndTitle(String body, String title){

        try {
            ArrayList<String> urls1 = new ArrayList<>();
            IndexReader rdr = DirectoryReader.open(FSDirectory.open(new File("index").toPath()));
            IndexSearcher is = new IndexSearcher(rdr);
            QueryParser parser1 = new QueryParser("body", new PersianAnalyzer());
            Query query1 = parser1.parse(body);
            TopDocs hits1 = is.search(query1, 1000);
            for (ScoreDoc scoreDoc : hits1.scoreDocs) {
                urls1.add(is.doc(scoreDoc.doc).get("url"));
            }


            ArrayList<String> urls2 = new ArrayList<>();
            QueryParser parser2 = new QueryParser("title", new PersianAnalyzer());
            Query query2 = parser2.parse(title);
            TopDocs hits2 = is.search(query2, 1000);
            for (ScoreDoc scoreDoc : hits2.scoreDocs) {
                urls2.add(is.doc(scoreDoc.doc).get("url"));
            }

            ArrayList<String>results = new ArrayList<>(urls1);
            results.retainAll(urls2);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public synchronized static void add(String url, String title, String body, String date){

        Document document = new Document();
        TextField field1 = new TextField("url", url, Field.Store.YES);
        TextField field2 = new TextField("title", title, Field.Store.YES);
        TextField field3 = new TextField("body", body, Field.Store.YES);
        StringField field4 = new StringField("date", date, Field.Store.YES);
        System.out.println("Added Title: " + title);
        System.out.println("URL: "+ url);
        document.add(field1);
        document.add(field2);
        document.add(field3);
        document.add(field4);
        try {
            w.addDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
