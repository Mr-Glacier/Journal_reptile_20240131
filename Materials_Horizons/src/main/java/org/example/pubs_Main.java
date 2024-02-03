package org.example;

import org.example.Controller.MHController;
import org.example.Until.HelpCreateFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class pubs_Main {
    public static void main(String[] args) throws Exception {

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("Materials Horizons","https://pubs.rsc.org/en/journals/journalissues/mh#!recentarticles&adv");
        hashMap.put("Nanoscale","https://pubs.rsc.org/en/journals/journalissues/nr#!recentarticles&adv");

        MHController MH = new MHController();

        String main_path = "E:\\ZKZD2024\\期刊爬虫\\T_pubs_rsc_org_20240201\\";
        Set set = hashMap.entrySet();
        for(Object key:set){
            Map.Entry entry = (Map.Entry) key;

            String first_name = "firsthtml.txt";
            String first_path = main_path+entry.getKey().toString().replace(" ", "")+"\\";
            HelpCreateFile.Method_Creat_folder(first_path);

//            1.下载首页,为了获取日期year列表
//            MH.Method_1_Down_firstHtml(first_path,first_name,entry.getValue().toString());

            String second_url = "https://pubs.rsc.org/en/journals/JournalIssuesForYear?root=";
            String articleUrlSavePath = first_path+"articleUrlList\\";
            HelpCreateFile.Method_Creat_folder(articleUrlSavePath);
            String articleUrlFileName = "_urlList.txt";
//            2.解析首页
//            MH.Method_2_Analysis_firstHtml(first_path,first_name,articleUrlSavePath,articleUrlFileName,second_url,entry.getKey().toString());

        }

        String listPagePath = main_path+"articleListPage\\";
        HelpCreateFile.Method_Creat_folder(listPagePath);
        String listsaveName = "_listPage.txt";
        String ListPageUrl = "https://pubs.rsc.org/en/journals/issues";
        //        3.下载文章列表页面
//        MH.Method_5_Down_articleListPage(listPagePath,listsaveName,ListPageUrl);


//        6.解析所有文章
//        MH.Method_6_Analysis_articleListPage(listPagePath,listsaveName);

        String articledetailPath= main_path+"article_detail\\";
        String articlesavename = "_detail.txt";

//        7.下载所有文章具体页面
        MH.Method_7_Down_oneArticle(articledetailPath,articlesavename);

    }
}