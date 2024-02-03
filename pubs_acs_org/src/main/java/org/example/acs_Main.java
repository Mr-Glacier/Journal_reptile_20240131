package org.example;

import org.example.Controller.ACS_Controller;
import org.example.Until.HelpCreateFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class acs_Main {
    public static void main(String[] args) {


        HashMap<String,String> hashMap = new HashMap<>();
//        共15个 期刊
        hashMap.put("ACS Energy Letters","https://pubs.acs.org/journal/aelccp");
        hashMap.put("ACS Nano","https://pubs.acs.org/journal/ancac3");
        hashMap.put("NANO LETTERS","https://pubs.acs.org/journal/nalefd");
        hashMap.put("ACS Materials Letters","https://pubs.acs.org/journal/amlcef");
        hashMap.put("CHEMISTRY OF MATERIALS","https://pubs.acs.org/journal/cmatex");
        hashMap.put("ACS Applied Materials & Interfaces","https://pubs.acs.org/journal/aamick");
        hashMap.put("ACS Photonics","https://pubs.acs.org/journal/apchd5");
        hashMap.put("ACS Applied Energy Materials","https://pubs.acs.org/journal/aaemcq");
        hashMap.put("Journal of Physical Chemistry Letters","https://pubs.acs.org/journal/jpclcd");
        hashMap.put("ACS Applied Nano Materials","https://pubs.acs.org/journal/aanmf6");
        hashMap.put("ACS Applied Polymer Materials","https://pubs.acs.org/journal/aapmcd");
        hashMap.put("ACS Applied Electronic Materials","https://pubs.acs.org/journal/aaembp");
        hashMap.put("LANGMUIR","https://pubs.acs.org/journal/langd5");
        hashMap.put("Journal of Physical Chemistry C","https://pubs.acs.org/journal/jpccck");
        hashMap.put("CRYSTAL GROWTH & DESIGN","https://pubs.acs.org/journal/cgdefu");

        String main_path = "E:\\ZKZD2024\\期刊爬虫\\ACS\\";
//        本类期刊主要存储地址
        HelpCreateFile.Method_Creat_folder(main_path);

        ACS_Controller ACS = new ACS_Controller();

        Set set = hashMap.entrySet();
        for(Object key:set) {
            Map.Entry entry = (Map.Entry) key;

            String 期刊 = entry.getKey().toString();
            String 期刊网址 = entry.getValue().toString();

            String firstName = 期刊网址.replace("https://pubs.acs.org/journal/", "")+"_yearList.txt";
            String yearListUrl = "https://pubs.acs.org/loi/"+期刊网址.replace("https://pubs.acs.org/journal/", "");

            //https://pubs.acs.org/loi/aelccp
            System.out.println(yearListUrl);

//            1.下载年款所在页面,获取列表
//            ACS.Method_1_DownFirst(yearListUrl,main_path,firstName);

//            2.解析年款列表
            ACS.Method_2_AnalysisYearList(main_path,firstName);
        }
    }
}