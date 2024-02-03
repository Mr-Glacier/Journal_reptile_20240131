package org.example.Until;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class HttpUntil {
//    发送请求类

//    发送POST请求的方法,带请求头,带参数
    public String SentRequestPOST(String main_url, Map<String,String> headerMap, JSONObject data){
        String result = "Error";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(main_url);
            Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                post.addHeader(next.getKey(),next.getValue());
            }
            StringEntity s = new StringEntity(data.toString(), "utf-8");
            s.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/x-www-form-urlencoded; charset=UTF-8"));
            post.setEntity(s);
            // 发送请求
            HttpResponse httpResponse = client.execute(post);
            System.out.println(httpResponse.getEntity().toString());
            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();
            result = strber.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



    public String doPost(String url, HashMap<String, String> map){
        String result = "Error";
        try{
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(550000).setConnectTimeout(550000)
                    .setConnectionRequestTimeout(550000).setStaleConnectionCheckEnabled(true).build();
            client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
            // client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Charset", "UTF-8");
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                params.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                response = client.execute(httpPost);
                if (response != null) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "UTF-8");
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
