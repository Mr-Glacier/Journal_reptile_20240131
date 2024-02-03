package org.example.Controller;

import com.alibaba.fastjson.JSONObject;
import org.example.Dao.DaoFather;
import org.example.Entity.Bean_article;
import org.example.Entity.Bean_articleList;
import org.example.Until.HttpUntil;
import org.example.Until.MD5Until;
import org.example.Until.ReadUntil;
import org.example.Until.SaveUntil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MHController {
    private SaveUntil saveUntil = new SaveUntil();
    private ReadUntil readUntil = new ReadUntil();
    private HttpUntil httpUntil = new HttpUntil();

    //    公共请求方法,JSOUP
    public String Method_Down_JSPOP(String url) {
        String result = "Error";
        try {
            Document document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            result = document.toString();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //    公共请求方法, Response  需要设置头文件
    public String Method_RequestAPI(String main_Url, String param) {
        String resultJson = "Error";
        String Cookie = "CIGUID=849ec451-0627-4ee7-8139-7d0a7233d10a; auto_id=6618b3b2d19037859fee9321a2165348; UserGuid=849ec451-0627-4ee7-8139-7d0a7233d10a; CIGDCID=1f1c18bfd1a13ef3a7c0b2edee9ef3ca; G_CIGDCID=1f1c18bfd1a13ef3a7c0b2edee9ef3ca; selectcity=110100; selectcityid=201; selectcityName=%E5%8C%97%E4%BA%AC; Hm_lvt_610fee5a506c80c9e1a46aa9a2de2e44=1702474832,1703750079; isWebP=true; locatecity=110100; bitauto_ipregion=101.27.236.186%3A%E6%B2%B3%E5%8C%97%E7%9C%81%E4%BF%9D%E5%AE%9A%E5%B8%82%3B201%2C%E5%8C%97%E4%BA%AC%2Cbeijing; yiche_did=5e58467469f6b2c8732f3492175f2a13_._1000_._0_._847319_._905548_._W2311281141108493357; csids=8014_2556_2855_5476_10188_6435_6209_2573_3750_5786; Hm_lpvt_610fee5a506c80c9e1a46aa9a2de2e44=1703842561";
        try {
            Connection.Response res = Jsoup.connect(main_Url).method(Connection.Method.GET)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("X-City-Id", "201")
                    .header("X-Ip-Address", "101.27.236.186")
                    .header("X-Platform", "pc")
                    .header("X-User-Guid", "849ec451-0627-4ee7-8139-7d0a7233d10a")
                    .header("Cookie", Cookie)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Cid", "508")
                    .header("Sec-Ch-Ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                    .header("Sec-Ch-Ua-Mobile", "?0")
                    .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                    .header("Sec-Fetch-Dest", "empty")
                    .header("Sec-Fetch-Mode", "cors")
                    .header("Sec-Fetch-Site", "same-site")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true).execute();
            resultJson = res.body();
            Thread.sleep(480);
//            System.out.println(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public void Method_1_Down_firstHtml(String filrPath, String fileName, String url) {
        System.out.println(url);
        String content = Method_Down_JSPOP(url);
        if (!content.equals("Error")) {
            saveUntil.Method_SaveFile(filrPath + fileName, content);
            System.out.println("首页下载完成");
        }
    }

    public void Method_2_Analysis_firstHtml(String filePath, String fileName, String articleUrlListSavePath, String savefileName, String main_url, String source) {
        String content = readUntil.Method_ReadFile(filePath + fileName);
        Elements Item1 = Jsoup.parse(content).select(".select-field.input--full");
        Elements Items2 = Item1.select("option");
        for (Element element : Items2) {
            String value = element.attr("value").replace("#", "%23");
//            System.out.println(value);
            String name = element.text().replace(".", "_").replace(" ", "").replace("-", "_");
//            System.out.println(name);
            String url = main_url + value;
//            System.out.println(url);

            String value_yuanshi = element.attr("value");
            System.out.println(value_yuanshi);

//            String data_content  = Method_3_Down_one_book(url);
//            if (!data_content.equals("Error")){
//                saveUntil.Method_SaveFile(articleUrlListSavePath+name+savefileName, data_content);
//            }


            String contnt = readUntil.Method_ReadFile(articleUrlListSavePath + name + savefileName);
            Method_4_Analysis_oneurlList(contnt, name.substring(0, 4), source, value_yuanshi);
        }
    }


    public String Method_3_Down_one_book(String main_url) {

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Origin", "https://pubs.rsc.org");
        headerMap.put("X-Requested-With", "XMLHttpRequest");
        headerMap.put("X-Newrelic-Id", "Vg4CUFVVDhABV1BRAgUBUFcJ");
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        headerMap.put("Tracestate", "1029994@nr=0-1-2851366-1386013924-d24a30f436aa6def----1706758250745");
        headerMap.put("Traceparent", "00-17f96dad75de7f8188a2b52947726e39-d24a30f436aa6def-01");
        headerMap.put("Sec-Fetch-Site", "same-origin");
        headerMap.put("Sec-Fetch-Mode", "cors");
        headerMap.put("Sec-Fetch-Dest", "empty");
        headerMap.put("Sec-Ch-Ua-Platform", "\"Windows\"");
        headerMap.put("Sec-Ch-Ua-Mobile", "?0");
        headerMap.put("Sec-Ch-Ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
        headerMap.put("Referer", "https://pubs.rsc.org/en/journals/journalissues/mh");
        headerMap.put("Newrelic", "eyJ2IjpbMCwxXSwiZCI6eyJ0eSI6IkJyb3dzZXIiLCJhYyI6IjI4NTEzNjYiLCJhcCI6IjEzODYwMTM5MjQiLCJpZCI6ImQyNGEzMGY0MzZhYTZkZWYiLCJ0ciI6IjE3Zjk2ZGFkNzVkZTdmODE4OGEyYjUyOTQ3NzI2ZTM5IiwidGkiOjE3MDY3NTgyNTA3NDUsInRrIjoiMTAyOTk5NCJ9fQ==");
        headerMap.put("Host", "pubs.rsc.org");
        headerMap.put("Cookie", "X-Mapping-hhmaobcf=ABB3D061A6B5E29F11E54B0FD08F47D2; _PubsBFCleared=1; ASP.NET_SessionId=dw3vyokizi4obszaa0sdg4rg; ShowEUCookieLawBanner=true; Branding=; _gid=GA1.2.2091476578.1706754626; _hjSession_963544=eyJpZCI6ImE5YThmZjJlLTVmNWUtNDJhYS05MDQ5LWFhZDc0ZjM5NWE1YyIsImMiOjE3MDY3NTQ2MjcwNzksInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjoxLCJzcCI6MH0=; __utma=178204708.998845836.1706754626.1706754650.1706754650.1; __utmc=178204708; __utmz=178204708.1706754650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _hjSessionUser_963544=eyJpZCI6ImQ5NTJiNTcwLWYzMjMtNWQzOS04MTZiLTY2MjBmOGQ2NjNjYyIsImNyZWF0ZWQiOjE3MDY3NTQ2MjcwNzgsImV4aXN0aW5nIjp0cnVlfQ==; __eoi=ID=be415b1ee28b265f:T=1706754671:RT=1706754671:S=AA-AfjbTOjZjx9LjN2bK_xIMa19n; _hjSessionUser_889812=eyJpZCI6ImE3ODQwYWY4LTFhY2UtNTA0NC05ODI4LTZhMWU0NDg3MGY0ZCIsImNyZWF0ZWQiOjE3MDY3NTQ2NzE0ODUsImV4aXN0aW5nIjp0cnVlfQ==; _fbp=fb.1.1706754671725.1082758222; Branding=; _ga_T8TQNW372Z=GS1.1.1706754671.1.1.1706757327.0.0.0; __utma=245083418.998845836.1706754626.1706757328.1706757328.1; __utmc=245083418; __utmz=245083418.1706757328.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _ga_MGLMJ78PFV=GS1.1.1706754626.1.1.1706757327.0.0.0; _ga=GA1.2.998845836.1706754626; _ga_S4B9Q5S7WY=GS1.2.1706754626.1.1.1706757328.0.0.0; __utmb=245083418.6.10.1706757328");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        headerMap.put("Content-Length","62");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("Accept", "text/html, */*; q=0.01");

        JSONObject data = new JSONObject();
//        data.put("root", "2019#6#current#2051-6347#MH#2051-6355#volname");

        return httpUntil.SentRequestPOST(main_url, headerMap, data);
    }

    public void Method_4_Analysis_oneurlList(String contnt, String year, String sorce, String formData) {
        DaoFather dao_articleList = new DaoFather(0, 0);

        Document document = Jsoup.parse(contnt);

        Elements Items1 = document.select(".list__item--dashed");
        System.out.println(Items1.size());

        for (Element element : Items1) {
            String url = "https://pubs.rsc.org" + element.select("a").attr("href");
            System.out.println(url);
            String name = element.select("a").text();
            String id = element.select("a").attr("data-issueid");
            System.out.println(id);
            System.out.println(name);

            Bean_articleList bean_articleList = new Bean_articleList();
            bean_articleList.setC_listID(id);
            bean_articleList.setC_listUrl(url);
            bean_articleList.setC_DownState("否");
            bean_articleList.setC_issue(name);
            bean_articleList.setC_DownTime("--");
            bean_articleList.setC_year(year);
            bean_articleList.setC_Source(sorce);
            bean_articleList.setC_formdata(formData);
            dao_articleList.MethodInsert(bean_articleList);
        }
    }


    public void Method_5_Down_articleListPage(String savepath, String savefileName, String mainUrl) {
        DaoFather dao_articleList = new DaoFather(0, 0);
        ArrayList<Object> BeanList = dao_articleList.Method_Find();
        for (Object bean : BeanList) {
            Bean_articleList bean_articleList = (Bean_articleList) bean;
            String DownState = bean_articleList.getC_DownState();
            int C_ID = bean_articleList.getC_ID();

            String C_articlesubmissionurl = bean_articleList.getC_articlesubmissionurl();
            String C_listID = bean_articleList.getC_listID();
            String source = bean_articleList.getC_Source();
            String latestissueid = bean_articleList.getC_latestissueid();
            String fromdata = bean_articleList.getC_formdata();
            String[] parts = fromdata.split("#");
            String name = parts[4];
            String issnprint = parts[3];
            String issnonline = parts[5];
            if (DownState.equals("否")) {


                HashMap<String, String> data = new HashMap<>();
                data.put("name", name);
                data.put("issueid", C_listID);
                data.put("jname", source);
                data.put("pageno", "1");
                data.put("isarchive", "False");
                data.put("issnprint", issnprint);
                data.put("issnonline", issnonline);
                data.put("iscontentavailable", "True");
                data.put("publishOnlyVolume", "False");
                data.put("articlesubmissionurl", C_articlesubmissionurl);
                data.put("latestissueid", latestissueid);
                data.put("category", "advancearticles");
                data.put("duration", "");

                String content = httpUntil.doPost(mainUrl, data);

                if (!content.equals("Error")) {
                    saveUntil.Method_SaveFile(savepath + C_listID + savefileName, content);
                    dao_articleList.Method_ChangeState(C_ID);
                    System.out.println(C_ID);
                }
            }
        }
    }


    public void Method_6_Analysis_articleListPage(String savepath, String savefileName) {
        DaoFather dao_articleList = new DaoFather(0, 0);
        DaoFather dao_article = new DaoFather(0, 1);
        ArrayList<Object> BeanList = dao_articleList.Method_Find();
        for (Object bean : BeanList) {
            Bean_articleList bean_articleList = (Bean_articleList) bean;
            String C_listID = bean_articleList.getC_listID();
            String C_Source = bean_articleList.getC_Source();
            String content = readUntil.Method_ReadFile(savepath + C_listID + savefileName);

            System.out.println(C_listID);
            Document document = Jsoup.parse(content);

            Elements articles = document.select(".capsule.capsule--article");

            String img = "";
            Elements imgs = document.select(".list__image-col");
            for (Element element : imgs) {
                String img_path = "https://pubs.rsc.org" + element.select(".list__item-img").attr("src");
                img += img_path + ";";
            }


            for (Element element : articles) {
                String articleID = element.select("a").select(".capsule__action").attr("name");
                String article_tag = element.select("a").select("span").select(".capsule__context").text();
                String articleUrl = "https://pubs.rsc.org" + element.select("a").select(".capsule__action").attr("href");

                Bean_article bean_article = new Bean_article();
                bean_article.setC_articleID(articleID);
                bean_article.setC_articleTag(article_tag);
                bean_article.setC_articleUrl(articleUrl);
                bean_article.setC_listID(C_listID);
                bean_article.setC_Source(C_Source);
                bean_article.setC_DownTime("--");
                bean_article.setC_DownState("否");
                bean_article.setC_img(img.substring(0, img.length() - 1));

                dao_article.MethodInsert(bean_article);
            }
        }
    }


    public void Method_7_Down_oneArticle(String savePath, String fileName){
        DaoFather dao_article = new DaoFather(0, 1);
        ArrayList<Object> BeanList = dao_article.Method_Find();
        for (Object bean : BeanList) {
            Bean_article bean_article = (Bean_article) bean;
            String articleID = bean_article.getC_articleID();
            String articleURL = bean_article.getC_articleUrl();
            String DownState = bean_article.getC_DownState();
            String listID = bean_article.getC_listID();
            int C_ID  = bean_article.getC_ID();
            if (DownState.equals("否")) {
//                System.out.println(listID.substring(0,2));
                String content = Method_8_RequestArticle(articleURL,listID.substring(0,2));

                if (!content.equals("Error")){
                    saveUntil.Method_SaveFile(savePath+articleID+fileName, content);
                    dao_article.Method_ChangeState(C_ID);
                    System.out.println(C_ID);
                }
            }
        }
    }

    public String Method_8_RequestArticle(String main_url, String type) {
        String result = "Error";
        try {
            Connection.Response res = Jsoup.connect(main_url).method(Connection.Method.GET)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Sec-Fetch-User", "?1")
                    .header("Sec-Fetch-Site", "same-origin")
                    .header("Sec-Fetch-Mode", "navigate")
                    .header("Sec-Fetch-Dest", "document")
                    .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                    .header("Sec-Ch-Ua-Mobile", "?0")
                    .header("Sec-Ch-Ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                    .header("Referer", "https://pubs.rsc.org/en/journals/journalissues/" + type)
                    .header("Host", "pubs.rsc.org")
                    .header("Cookie", "X-Mapping-hhmaobcf=ABB3D061A6B5E29F11E54B0FD08F47D2; _PubsBFCleared=1; ASP.NET_SessionId=dw3vyokizi4obszaa0sdg4rg; ShowEUCookieLawBanner=true; Branding=; _gid=GA1.2.2091476578.1706754626; __utmc=178204708; __utmz=178204708.1706754650.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _hjSessionUser_963544=eyJpZCI6ImQ5NTJiNTcwLWYzMjMtNWQzOS04MTZiLTY2MjBmOGQ2NjNjYyIsImNyZWF0ZWQiOjE3MDY3NTQ2MjcwNzgsImV4aXN0aW5nIjp0cnVlfQ==; __eoi=ID=be415b1ee28b265f:T=1706754671:RT=1706754671:S=AA-AfjbTOjZjx9LjN2bK_xIMa19n; _hjSessionUser_889812=eyJpZCI6ImE3ODQwYWY4LTFhY2UtNTA0NC05ODI4LTZhMWU0NDg3MGY0ZCIsImNyZWF0ZWQiOjE3MDY3NTQ2NzE0ODUsImV4aXN0aW5nIjp0cnVlfQ==; _fbp=fb.1.1706754671725.1082758222; Branding=; _ga_T8TQNW372Z=GS1.1.1706754671.1.1.1706757327.0.0.0; __utmc=245083418; __utmz=245083418.1706757328.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=178204708.998845836.1706754626.1706754650.1706766026.2; __utma=245083418.998845836.1706754626.1706798562.1706835474.7; _hjSession_963544=eyJpZCI6Ijk4MmUzOTRkLWFiOGUtNDk4My05OWUxLWIyMTY1ZTFiOTQwNiIsImMiOjE3MDY4MzU0NzQ4NzMsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MH0=; __utmt=1; __utmb=245083418.26.10.1706835474; _gat_UA-74486652-1=1; _gat_UA-20361743-10=1; _ga_MGLMJ78PFV=GS1.1.1706837871.9.1.1706837872.0.0.0; _ga=GA1.1.998845836.1706754626; _ga_S4B9Q5S7WY=GS1.2.1706837872.7.0.1706837872.0.0.0")
                    .header("Connection", "keep-alive")
                    .header("Cache-Control", "max-age=0")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true).execute();
            result = res.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
