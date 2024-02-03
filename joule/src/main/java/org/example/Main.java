package org.example;

import org.example.Controller.JouleController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JouleController JC = new JouleController();

        String firstUrl = "https://www.cell.com/joule/archive";
        String main_path= "E:\\ZKZD2024\\期刊爬虫\\joule\\";
        String firsthtml_name = "joule_html.txt";
//        JC.Method_1_DownDate(firstUrl,main_path,firsthtml_name);

        String yearPath = "E:\\ZKZD2024\\期刊爬虫\\joule\\year\\";

//        JC.Method_2_Analysis(main_path,firsthtml_name,yearPath,"_year.txt");

//        JC.Method_3_Analysis_year(yearPath,"d2020_v4_Volume4_2020_year.txt");


        String articleListPAth = "E:\\ZKZD2024\\期刊爬虫\\joule\\article\\";
        String article_name = "_article.txt";

//        4.下载文章列表
//        JC.Method_4_Down_ArticleList(articleListPAth,article_name);

//        5.解析文章列表数据
//        JC.Method_5_Analysis_ArticleList(articleListPAth,article_name);

        String article_detail_path = "E:\\ZKZD2024\\期刊爬虫\\joule\\article_detail\\";
        String article_detail_fileName = "_detail.txt";
//        6.下载单个文章页面
//        JC.Method_6_Down_Articles(article_detail_path,article_detail_fileName);

//        解析单个文章
//        JC.Method_7_Analysis_Article(article_detail_path,article_detail_fileName);

//        JC.Method_8_Analysis_Article(article_detail_path,article_detail_fileName);
        JC.Method_8_toMysql();

//        JC.Method_9_test();
    }
}