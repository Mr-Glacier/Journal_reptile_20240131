package org.example.Controller;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import org.example.Until.ReadUntil;
import org.example.Until.SaveUntil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ACS_Controller {

    private SaveUntil saveUntil = new SaveUntil();
    private ReadUntil readUntil = new ReadUntil();

    public String Method_JSOUP(String mian_Url){
        String result = "Error";
        try{
            Document document = Jsoup.parse(new URL(mian_Url).openStream(), "UTF-8", mian_Url);
            result = document.toString();
            Thread.sleep(1050);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public String Method_Response(String main_url, String type) {
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
    public String Method_playwright(String url) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.firefox().launch();
//            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

            browser.newContext(new Browser.NewContextOptions()
                    .setIgnoreHTTPSErrors(true)
                    .setJavaScriptEnabled(true)
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
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
            headers.put("Cookie", "ACSEnt=434228_6105_1706862224624; MAID=vny5wlRy3npUd9zfDiry5g==; MACHINE_LAST_SEEN=2024-02-02T00%3A23%3A44.689-08%3A00; JSESSIONID=F88CCD55D8F12F900D5665469A46B5A5; __cf_bm=GUwfF69MYSoeXd5BVc627eLnp5HQXAxEC1.D5W2Ev4c-1706862224-1-AftUjqli4yjXVwCxi/QjbyKZAAAmn3badYN6ud0YNTXsxStT4QO0q+BnkxJMank5rPaPrteRNqWGe0SjY/hXPdA=; _ga_Q72ZQB7GTR=GS1.1.1706862226.1.0.1706862226.60.0.0; _ga=GA1.2.1585808813.1706862226; SnapABugRef=https%3A%2F%2Fpubs.acs.org%2Floi%2Faelccp%20; SnapABugHistory=1#; SnapABugVisit=1#1706862227…yDYxPRXZgBgccv1eWLd0uw==; _gid=GA1.2.1116015626.1706862229; _gat_gtag_UA_7663985_4=1; _hjSessionUser_3120009=eyJpZCI6ImQ3NGJiNDNiLWQyZTYtNWNjYS1iM2I0LWQ5YzNkZTI1NWQ5ZCIsImNyZWF0ZWQiOjE3MDY4NjIyMzAzMjcsImV4aXN0aW5nIjpmYWxzZX0=; _hjSession_3120009=eyJpZCI6IjA0ODVmMzlhLTA2MTQtNDJiNC1iYjQwLTQ4YjBhZmI4NGYyNyIsImMiOjE3MDY4NjIyMzAzMjcsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjoxLCJzcCI6MH0=; ELOQUA=GUID=5DBAE6DCCD0245F79CD86D91B15A7F20; __adroll_fpc=a9c8008811f9b8aff0c0e812b3c7fc6a-1706862235875; __ar_v4=");
            headers.put("Host", "pubs.acs.org");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Sec-Fetch-Dest", "document");
            headers.put("Sec-Fetch-Mode", "navigate");
            headers.put("Sec-Fetch-Site", "same-origin");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0");
            headers.put("Sec-Fetch-User","?1");
            headers.put("TE","trailers");


            page.setExtraHTTPHeaders(headers);
            // 启用 JavaScript
            page.navigate(url);

            // 等待页面加载完成
            page.waitForLoadState(LoadState.LOAD);

            // 获取页面源码
//            String pageSource = page.content();

            //System.out.println(pageSource);
            Thread.sleep(1800);

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

    public void Method_1_DownFirst(String main_url,String savePath,String saveName){
        String content = Method_playwright(main_url);
        if (!content.equals("Error")){
            saveUntil.Method_SaveFile(savePath+saveName,content);
        }

    }


    public void Method_2_AnalysisYearList(String savePath,String saveName){
        String Content = readUntil.Method_ReadFile(savePath+saveName);
        Document document = Jsoup.parse(Content);

        Elements Items = document.select(".swipe__wrapper.loi-list__wrapper").select(".scroll").select(".rlist.loi__list.tab__nav.swipe__list").select(".tab__nav__item");

        for (Element element:Items){
            String year = element.select("a").text();
            System.out.println(year);
        }


    }

}
