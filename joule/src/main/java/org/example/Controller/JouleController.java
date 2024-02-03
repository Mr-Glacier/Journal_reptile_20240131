package org.example.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.example.Dao.DaoFather;
import org.example.Entity.Bean_Joule;
import org.example.Entity.Bean_article;
import org.example.Entity.Bean_mysql_qikan;
import org.example.Entity.Bean_year;
import org.example.Until.ReadUntil;
import org.example.Until.SaveUntil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class JouleController {
    //    期刊网站爬取获取类
    private SaveUntil saveUntil = new SaveUntil();
    private ReadUntil readUntil = new ReadUntil();

    public String Method_2_RequestArticleData(String url) {
        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.firefox().launch();
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

            browser.newContext(new Browser.NewContextOptions()
                    .setIgnoreHTTPSErrors(true)
                    .setJavaScriptEnabled(true)
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0")
                    .setViewportSize(2880, 1800));
            Page page = browser.newPage();


            Map<String, String> headers = new HashMap<>();
//            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");

            // 81参数一般不进行启动
            //headers.put("X-Zst-81","3_2.0aR_sn77yn6O92wOB8hPZnQr0EMYxc4f18wNBUgpTQ6nxERFZ1TY0-4Lm-h3_tufIwJS8gcxTgJS_AuPZNcXCTwxI78YxEM20s4PGDwN8gGcYAupMWufIeQuK7AFpS6O1vukyQ_R0rRnsyukMGvxBEqeCiRnxEL2ZZrxmDucmqhPXnXFMTAoTF6RhRuLPFeSqRhS0uJXL6AXMVgxBiqt06RVC6GgVAB3CG0S8jbefh9XVcTOLj931DgeTv_Y_xh3LkGp0JvuyXcX9jCHmfgY83uwLIwS_6MeM1JSCkULqygLp9JHKKDS9crH0NBNs6_NGMXV0SrLK9gLpn9HOgDoMAqN_OGHO2Hg17rSYcv3_8wexCqfz2JN9-qufoU29IUYfFbxKwUg_shS1jucf8HpyzggOW9xVnGFY-reL0bpGaBL1_htO0DxyVUgq-wt_SG3LwGF8uJ9BjJN9eTLBDuV0BrCqEuCBChLCBrOC");

            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("X-Requested-With", "fetch");
            headers.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            headers.put("Connection", "keep-alive");
            headers.put("Cookie", "MAID=+lOoz3ovdacPV4c0x6htyg==; MACHINE_LAST_SEEN=2024-02-02T19%3A22%3A54.746-08%3A00; OptanonConsent=isGpcEnabled=0&datestamp=Sat+Feb+03+2024+11%3A24%3A19+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&version=202304.1.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=8dda03e6-9b99-452c-ae95-84cb37210323&interactionCount=1&landingPath=NotLandingPage&groups=1%3A1%2C3%3A1%2C2%3A1%2C4%3A1&geolocation=SG%3B&AwaitingReconsent=false; cf_clearance=cUac1VVWVqvPZWfuMCwdX2tydtoLMBhdQl48CMoRv0…c%3D0%3B%20s_ppvl%3Djb%25253Afulltext%25253Ahtml%25253Aupsellorlogin%252C4%252C4%252C886%252C1299%252C886%252C1920%252C1080%252C1%252CP%3B%20e41%3D1%3B%20s_cc%3Dtrue%3B%20s_ppv%3Djb%25253Afulltext%25253Ahtml%252C4%252C2%252C1894%252C1184%252C886%252C1920%252C1080%252C1%252CP%3B; __hssrc=1; JSESSIONID=aaaP0SC2h_bhi4TU4dO1y; __cf_bm=lDfUOTMF.YBbccq5FZMXQR1yLZQOjlx573FH5iAl5kI-1706930575-1-AapscZkOe2Mt5lbd+Doax6cNkDbxe/kKuELsXyQ65icGTYg9VuUGFN/U1WElwcWY3kn4iLFNAXZS4dDesHfiZoM=; __hssc=25856994.1.1706930672486");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Sec-Fetch-Dest", "document");
            headers.put("Sec-Fetch-Mode", "navigate");
            headers.put("Sec-Fetch-Site", "same-origin");
//            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0");
            headers.put("Sec-Fetch-User","?1");
            headers.put("TE","trailers");

            page.setDefaultTimeout(100000);
            page.setExtraHTTPHeaders(headers);
            // 启用 JavaScript
            page.navigate(url);

            // 等待页面加载完成
            page.waitForLoadState(LoadState.LOAD);

            // 获取页面源码
//            String pageSource = page.content();

            //System.out.println(pageSource);
            Thread.sleep(5800);

            String content = page.content();
//            List<ElementHandle> lis = page.querySelectorAll("pre");
//
//            StringBuilder sb = new StringBuilder();
//
//            for (ElementHandle li : lis) {
//                sb.append(li.innerHTML());
//            }

//            System.out.println(sb.toString());

            Thread.sleep(10200);
            // 关闭浏览器
//            Thread.sleep(100000);
            browser.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public String Method_Down_JSOUP(String url) {
        String result = "Error";
        try {
            Document document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            result = document.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void Method_1_DownDate(String html_url, String path, String fileName) {
        String content = Method_2_RequestArticleData(html_url);
        if (!content.equals("Error")) {
            saveUntil.Method_SaveFile(path + fileName, content);
            System.out.println("保存完成首页数据");
        }
    }

    public void Method_2_Analysis(String filePath, String fileName, String date_filePath, String date_fileName) {
        try {
            String content = readUntil.Method_ReadFile(filePath + fileName);

            Document document = Jsoup.parse(content);

            Elements Item1 = document.select("div.accordion-with-arrow.expandable-accordion").select(".accordion").select("ul.rlist");
            System.out.println(Item1.size());
//            for (Element element : Item1){
//                System.out.println(element);
//                System.out.println("================================================");
//            }
            Element Items2 = Item1.get(0);
            System.out.println(Items2);

            Elements Item3 = Items2.select(".accordion__tab.accordion__closed");
            System.out.println(Item3.size());
            for (Element element : Item3) {
                String year = element.attr("data-groupid");
                System.out.println(year);
                Elements Items4 = element.select(".list-of-issues__group__wrapper.list-of-issues__group__wrapper--issues");
                for (Element element1 : Items4) {
                    String id = element1.attr("data-groupid");
                    String name = element1.select("h3").select("a").text();
                    System.out.println(id + "\t" + name);
//                    String mainURL = "https://www.cell.com/pb/widgets/loi/content?widgetId=fd594919-c81f-4c1f-80e4-2395d8fd27ca&pbContext=%3Bproduct%3Aproduct%3Aelsevier%5C%3Aproduct%5C%3Acell%3Bjournal%3Ajournal%3Ajoul%3Bpage%3Astring%3AList%20of%20Issues%3Bwebsite%3Awebsite%3Acell-site%3Bctype%3Astring%3AJournal%20Content%3BpageGroup%3Astring%3APublication%20Page%3BrequestedJournal%3Ajournal%3Ajoul%3Bwgroup%3Astring%3ADefault%20Website%20Group&id=" +
//                            id;
//                    String content_response = Method_2_RequestArticleData(mainURL);
////                    System.out.println(content_response);
//                    Thread.sleep(2000);
                    String finally_name = id.replace(".", "_") + "_" + name.replace(" ", "").replace("(", "_").replace(")", "");
//                    if (!content_response.equals("Error")) {
//
//                        saveUntil.Method_SaveFile(date_filePath + finally_name + date_fileName, content_response);
//
//                    }

                    Method_3_Analysis_year(date_filePath, finally_name + date_fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Method_responce(String url) {
        String resultJson = "Error";
        try {
            System.out.println(url);
            Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Sec-Ch-Ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"")
                    .header("Sec-Ch-Ua-Arch", "x86")
                    .header("Sec-Ch-Ua-Bitness", "64")
                    .header("Sec-Ch-Ua-Full-Version", "120.0.6099.227")
                    .header("Sec-Ch-Ua-Full-Version-List", "\"Not_A Brand\";v=\"8.0.0.0\", \"Chromium\";v=\"120.0.6099.227\", \"Google Chrome\";v=\"120.0.6099.227\"")
                    .header("Sec-Ch-Ua-Platform", "Windows")
                    .header("Sec-Ch-Ua-Platform-Version", "10.0.0")
                    .header("Sec-Fetch-Dest", "empty")
                    .header("Sec-Fetch-Mode", "cors")
                    .header("Sec-Fetch-Site", "same-origin")
                    .header("Cookie",
                            "MAID=4m+dUXs2WHk3Dj5OY6Vw9A==; OptanonAlertBoxClosed=2024-01-31T03:23:53.388Z; at_check=true; AMCVS_4D6368F454EC41940A4C98A6%40AdobeOrg=1; hubspotutk=e7524c130d84190b03db304079807f84; __hssrc=1; _gcl_au=1.1.1848733621.1706671437; s_fid=455A723DEBB1A350-211790C84063809B; MACHINE_LAST_SEEN=2024-01-30T21%3A53%3A52.005-08%3A00; JSESSIONID=aaaX7rrpLr9nZhxfXSD1y; __cf_bm=ngD.eI4GSgeKSSUSy1eLKvuGD0WPG.2g.TOtU48ZZkY-1706680432-1-AexQSddVPV1zDOggTY4xAsdHTt/g7XYkJTkPt1OA5AopcpZBVhVM3944y8loM8vmk9FGm/GiTDojIZGnqjc/vTM=; AMCV_4D6368F454EC41940A4C98A6%40AdobeOrg=179643557%7CMCIDTS%7C19754%7CMCMID%7C03136687011086334561906165435219953462%7CMCAAMLH-1707285354%7C3%7CMCAAMB-1707285354%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1706687754s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C5.5.0%7CMCCIDH%7C-554789566; __hstc=25856994.e7524c130d84190b03db304079807f84.1706671434894.1706671434894.1706680559655.2; cf_clearance=kTekzSoGJ0ZelXPjOoBp_gVJlGNjxO57UEbf_vTBBhs-1706680628-1-AdWssujqc7T2fC26iPiOk7D8yp07lF16v9uCLFU4XBqh7Mngn2IyxJaI3SuMc1nW3q0FAaqN0XiCf0fb5REzlHA=; mbox=PC#82f952e296704b25a43d5e07617260b6.38_0#1769925811|session#6a1b0d2009824056ae8f553a81298aff#1706682871; OptanonConsent=isGpcEnabled=0&datestamp=Wed+Jan+31+2024+14%3A03%3A33+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&version=202304.1.0&browserGpcFlag=0&isIABGlobal=false&hosts=&consentId=36e5616c-44f2-4771-9e9f-7e5e60abefa2&interactionCount=1&landingPath=NotLandingPage&groups=1%3A1%2C3%3A1%2C2%3A1%2C4%3A1&geolocation=SG%3B&AwaitingReconsent=false; __hssc=25856994.6.1706680559655; s_pers=%20v8%3D1706681330116%7C1801289330116%3B%20v8_s%3DLess%2520than%25201%2520day%7C1706683130116%3B%20c19%3Djb%253Aissues%7C1706683130119%3B%20v68%3D1706673245606%7C1706683130121%3B; s_sess=%20s_cpc%3D0%3B%20s_cc%3Dtrue%3B%20s_ppvl%3Djb%25253Afulltext%25253Ahtml%25253Aupsellorlogin%252C2%252C2%252C1161.6363525390625%252C1177%252C798%252C1920%252C1080%252C1.1%252CP%3B%20s_ppv%3Djb%25253Aissues%252C22%252C22%252C798%252C820%252C798%252C1920%252C1080%252C1.1%252CP%3B%20e41%3D1%3B%20s_sq%3Delsevier-global-prod%253D%252526c.%252526a.%252526activitymap.%252526page%25253Djb%2525253Aissues%252526link%25253DVolume%252525207%25252520%252525282023%25252529%252526region%25253Dissuesd2020%252526pageIDType%25253D1%252526.activitymap%252526.a%252526.c%252526pid%25253Djb%2525253Aissues%252526pidt%25253D1%252526oid%25253Dhttps%2525253A%2525252F%2525252Fwww.cell.com%2525252Farchive%2525253FpublicationCode%2525253Djoul%25252526issueGroupId%2525253Dd2020.v7%252526ot%25253DA%3B")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true).execute();
            resultJson = res.body();
            Thread.sleep(1080);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public void Method_3_Analysis_year(String filePath, String fileName) {
        String content = readUntil.Method_ReadFile(filePath + fileName);
        DaoFather dao_year = new DaoFather(0, 0);
        String deal_content = content.replace("<html><head></head><body>", "").replace("</p></body></html>", "");

        JSONObject jsonObject = JSON.parseObject(deal_content).getJSONObject("data").getJSONObject("journal").getJSONObject("issueGroup");

        String id = jsonObject.getString("id");
        String label = jsonObject.getString("label");
        System.out.println(id + "\t" + label);

        JSONArray jsonArray = jsonObject.getJSONArray("issues");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject Item = jsonArray.getJSONObject(i);

            String C_volume = Item.getString("volume");
            String C_pages = Item.getString("pages");
            String C_issue = Item.getString("issue");
            String C_accessIcon = Item.getString("accessIcon");
            String C_year = Item.getString("year");
            String C_src = Item.getJSONObject("coverImage").getString("src");
            String C_link = Item.getString("link");
            String C_openAccessLabel = Item.getString("openAccessLabel");
            String C_isSupplement = Item.getString("isSupplement");
            String C_coverDate = Item.getString("coverDate");
            String C_HAcoverDate = Item.getString("HAcoverDate");
            String C_isInProgress = Item.getString("isInProgress");


            Bean_year bean_year = new Bean_year();
            bean_year.setC_year(C_year);
            bean_year.setC_accessIcon(C_accessIcon);
            bean_year.setC_coverDate(C_coverDate);
            bean_year.setC_issue(C_issue);
            bean_year.setC_volume(C_volume);
            bean_year.setC_pages(C_pages);
            bean_year.setC_src(C_src);
            bean_year.setC_link(C_link);
            bean_year.setC_openAccessLabel(C_openAccessLabel);
            bean_year.setC_isSupplement(C_isSupplement);
            bean_year.setC_HAcoverDate(C_HAcoverDate);
            bean_year.setC_isInProgress(C_isInProgress);
            bean_year.setC_DownState("否");
            bean_year.setC_DownTime("--");

            System.out.println(C_year);
            dao_year.MethodInsert(bean_year);
        }
    }


    public void Method_4_Down_ArticleList(String filePAth, String file_name) {
        DaoFather dao_year = new DaoFather(0, 0);

        ArrayList<Object> beanList = dao_year.Method_Find();
        for (Object bean : beanList) {
//            https://www.cell.com/joule/issue?pii=S2542-4351(23)X0002-6#Articles
            String link = "https://www.cell.com" + ((Bean_year) bean).getC_link() + "#Articles";
            System.out.println(link);

            String year = ((Bean_year) bean).getC_year();
            String issue = ((Bean_year) bean).getC_issue();
            String content = Method_2_RequestArticleData(link);
            int C_ID = ((Bean_year) bean).getC_ID();
            if (!content.equals("Error")) {
                saveUntil.Method_SaveFile(filePAth + year + "_" + issue + file_name, content);
                dao_year.Method_ChangeState(C_ID);
            }

        }

    }


    public void Method_5_Analysis_ArticleList(String filePath, String fileName) {
        DaoFather dao_year = new DaoFather(0, 0);
        ArrayList<Object> beanList = dao_year.Method_Find();
        DaoFather dao_article = new DaoFather(0, 1);

        for (Object bean : beanList) {
            String year = ((Bean_year) bean).getC_year();
            String issue = ((Bean_year) bean).getC_issue();

            String content = readUntil.Method_ReadFile(filePath + year + "_" + issue + fileName);

            Document document = Jsoup.parse(content);

            Elements elements = document.select(".toc__section");
//            System.out.println(elements.size());


            for (Element element : elements) {
                String type = element.select("h2").select(".toc__heading__header.top").text();

                    System.out.println(type);

                    Elements Items2 = element.select(".toc__body.rlist.clearfix").select(".articleCitation");
                    System.out.println("含有文章个数" + Items2.size());

                    for (Element item3_1 : Items2) {
                        String type2 = item3_1.select(".toc__item__header").text();

                        Elements item3 = item3_1.select(".toc__item.clearfix");
                        String articleID = item3.attr("data-pii");
                        Elements item4 = item3.select(".toc__item__title");
                        String url = item4.select("a").attr("href");
                        System.out.println(url);
                        String title = item4.select("a").text();
                        System.out.println(title);

                        System.out.println(articleID);

                        Bean_article bean_article = new Bean_article();
                        bean_article.setC_articleID(articleID);
                        bean_article.setC_DownState("否");
                        bean_article.setC_issue(issue);
                        bean_article.setC_year(year);
                        bean_article.setC_title(title);
                        bean_article.setC_DownTime("--");
                        bean_article.setC_articelurl("https://www.cell.com"+url);

                        if (!type2.equals("")){
                            bean_article.setC_articleType(type2);
                        }else {
                            bean_article.setC_articleType(type);
                        }

                        dao_article.Method_Insert(bean_article);
                    }
            }
        }
    }


    public void Method_6_Down_Articles(String filePath, String fileName) {
        DaoFather dao_article = new DaoFather(0, 1);
        ArrayList<Object> BeanList = dao_article.Method_Find();

        for (Object bean : BeanList) {
            Bean_article bean_article = (Bean_article) bean;
            //https://www.cell.com/joule/fulltext/S2542-4351(23)00494-4
            String article_url = bean_article.getC_articelurl();
//            System.out.println(article_url);
            String articleID = bean_article.getC_articleID();
            String DownState = bean_article.getC_DownState();
            int C_ID = bean_article.getC_ID();
            if (DownState.equals("否")) {
                System.out.println(article_url);
                String content = Method_2_RequestArticleData(article_url);
                if (!content.equals("Error")) {
                    saveUntil.Method_SaveFile(filePath + articleID + fileName, content);
                    dao_article.Method_ChangeState(C_ID);
                    System.out.println(C_ID);
                }
            }
        }
    }


    public void Method_7_Analysis_Article(String filePath, String fileName){
        DaoFather dao_article = new DaoFather(0, 1);
        DaoFather dao_Joule = new DaoFather(0,2);
        ArrayList<Object> BeanList = dao_article.Method_Find();
        for (Object bean : BeanList) {
            Bean_article bean_article = (Bean_article) bean;

//111111111111111
            String articleID = bean_article.getC_articleID();


            String content = readUntil.Method_ReadFile(filePath+articleID+fileName);

            Document document = Jsoup.parse(content);
            Elements Items1 = document.select(".article-header__meta");
//            System.out.println(Items1.size());
            String articleUrl= Items1.select("a").select(".article-header__vol.faded").attr("href");
            System.out.println(articleUrl);

            String juan = Items1.select("a").select(".article-header__vol.faded").text();

            String[] parts = juan.split(",");
            String volume = parts[0].replace(" ", "");
            String issue = parts[1].replace(" ", "");
            System.out.println(volume);
            System.out.println(issue);

            String page = Items1.select(".article-header__pages.faded").text().replace(",", "").replace(" ", "");
            System.out.println(page);

            String date = Items1.select(".article-header__date.faded").text();
            System.out.println(date);

            String year = date.replace(" ", "").substring(date.replace(" ", "").indexOf(",")+1,date.replace(" ", "").indexOf(",")+5);
            System.out.println(year);

            Elements Items2 = document.select(".article-header__middle");

            String article_title = Items2.select("h1").text();
            System.out.println(article_title);


            Elements Item_author = Items2.select(".rlist.loa.inline-bullet-list.ellipsis-dot");
            String author_number = Item_author.attr("data-number-of-author");
            System.out.println(author_number);

            String authorStr  = "";
            Elements Items4 = Item_author.select("li");
            for (Element element:Items4){
                Elements author_message = element.select(".dropBlock.article-header__info");
                if (author_message.size()>1) {
                    String author_name = author_message.get(0).select(".loa__item__name.article-header__info__ctrl.loa__item__email").text();
                    String author_email = author_message.get(1).select("a").attr("href").replace("mailto:", "");
                    authorStr=authorStr+author_name+","+author_email+";";
                }
                if (author_message.size()==1){
                    String author_name = author_message.get(0).select(".loa__item__name.article-header__info__ctrl.loa__item__email").text();
                    authorStr=authorStr+author_name+";";
                }
            }
            Elements Items5 = Items2.select(".article-header__publish-date.bulleted");
            String type = Items5.select(".article-header__publish-date__label").text();
            String pushtime = Items5.select(".article-header__publish-date__value").text();
//            System.out.println(type+pushtime);

            String DOI = Items2.select(".article-header__doi__value").attr("href");
            System.out.println(DOI);
            System.out.println("==============================");
//            System.out.println("作者名单\t"+authorStr.replace(";;", ";"));


            Elements article_body = document.select(".article__body").select(".container");


            Elements keys = article_body.select(".rlist.keywords-list.inline-bullet-list").select("li");

            String keyWords = "";
            for (Element element:keys){
                String keyStr = element.select("a").text();
                keyWords = keyWords+keyStr+";";
            }

//            System.out.println("这里是关键词\t"+keyWords);


            Elements sum_items = article_body.select(".col-md-7.col-lg-9.article__sections").select("section");
            String summary = "";
            for (Element element:sum_items){
                String judge = element.select("h2").attr("data-left-hand-nav");

                if (judge.equals("Summary")){
                     summary  = element.select(".section-paragraph").text();
                }
            }
//            System.out.println("摘要 \t"+summary);


            Bean_Joule bean_joule = new Bean_Joule();
            bean_joule.setC_articleID(articleID);
            bean_joule.setC_article_title(article_title);
            bean_joule.setC_issue(issue.replace("ISSUE", ""));
            bean_joule.setC_author(authorStr.replace(";;", ";"));
            bean_joule.setC_ISSN("2542-4351");
            bean_joule.setC_关键词(keyWords);
            bean_joule.setC_摘要(summary);
            bean_joule.setC_DOI(DOI);
            bean_joule.setC_year(year);
            bean_joule.setC_volume(volume.replace("Volume", ""));
            dao_Joule.MethodInsert(bean_joule);

        }
    }


    public void Method_8_Analysis_Article(String filePath,String fileName){

    }

    public void Method_8_toMysql(){
        DaoFather dao_Joule = new DaoFather(0,2);
        DaoFather dao_mysql = new DaoFather(1,0);

        ArrayList<Object> BeanList = dao_Joule.Method_Find();

        for (Object bean:BeanList){
            Bean_Joule bean_joule = (Bean_Joule) bean;

            String issn = bean_joule.getC_ISSN();
            String 年 = bean_joule.getC_year();
            String 卷 = bean_joule.getC_volume();
            String 期 = bean_joule.getC_issue();
            String 文章标题 = bean_joule.getC_article_title();
            String 摘要 = bean_joule.getC_摘要();
            String 关键词 = bean_joule.getC_关键词();
            String doi = bean_joule.getC_DOI();
            String image = "https://www.cell.com/"+bean_joule.getImg();
            String 文章号 = bean_joule.getC_articleID();

            String author = bean_joule.getC_author().substring(0,bean_joule.getC_author().length()-1);
            String[] parts = author.split(";");

            String mysql_作者="";
            String mysql_邮箱="";
            for (int i = 0; i < parts.length; i++) {
//                System.out.println(parts[i]);
                if (parts[i].contains(",")){
                    String[] parts2 = parts[i].split(",");
                    mysql_作者 +=parts2[0]+",";
                    mysql_邮箱 +=parts2[0]+"("+parts2[1]+");";
                }else {
                    mysql_作者 +=parts[i]+",";
                }
            }
//            System.out.println("===================");
            Bean_mysql_qikan bean_mysql_qikan = new Bean_mysql_qikan();
            bean_mysql_qikan.setDoi(doi);
            bean_mysql_qikan.setIssn(issn);
            bean_mysql_qikan.setImage(image);
            bean_mysql_qikan.set制作人("任永康");
            bean_mysql_qikan.set关键词(关键词);

            bean_mysql_qikan.set期刊名("Joule");
            bean_mysql_qikan.set年(年);
            bean_mysql_qikan.set卷(卷);
            bean_mysql_qikan.set期(期);
            bean_mysql_qikan.set摘要(摘要);
            bean_mysql_qikan.set文章号(文章号);
            bean_mysql_qikan.set文章标题(文章标题);
            bean_mysql_qikan.setSource("Joule期刊信息标注.pptx");
            bean_mysql_qikan.set官网地址("http://www.journals.elsevier.com/joule");

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());
            bean_mysql_qikan.set爬取日期(time);
            bean_mysql_qikan.set邮箱(mysql_邮箱);
            bean_mysql_qikan.set作者(mysql_作者);
            System.out.println(mysql_作者.substring(0,mysql_作者.length()-1));
            System.out.println(mysql_邮箱.substring(0,mysql_邮箱.length()-1));

            dao_mysql.MethodInsert(bean_mysql_qikan);
        }
    }

    public void Method_9_test(){
        DaoFather daoFather = new DaoFather(1,0);

        ArrayList<Object> BeanList = daoFather.Method_Find();
        for (Object bean:BeanList){
            Bean_mysql_qikan bean_mysql_qikan = (Bean_mysql_qikan) bean;
            String hh = bean_mysql_qikan.getDoi();
            System.out.println(hh);
        }
    }
}
