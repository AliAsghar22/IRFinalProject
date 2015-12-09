package com.company;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

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

    public static void add(String url, String title, String body, String date){

        Document document = new Document();
        TextField field1 = new TextField("url", url, Field.Store.YES);
        TextField field2 = new TextField("title", title, Field.Store.YES);
        TextField field3 = new TextField("body", body, Field.Store.YES);
        StringField field4 = new StringField("date", date, Field.Store.YES);

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
