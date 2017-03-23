package com.intellichens.util;

/**
 * Created by raychen on 2017/3/23.
 */

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class ApiUtil {

    public static JSONObject request(String url, String text){
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(url);

//            builder.setParameter("selection", "{string}");
//            builder.setParameter("offset", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "text/plain");
            request.setHeader("Ocp-Apim-Subscription-Key", "1cead581f28b43a9a023fce1b8b9e5df");

            // Request body
            StringEntity reqEntity = new StringEntity(text);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                JSONObject obj = new JSONObject(EntityUtils.toString(entity));
                return obj;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://westus.api.cognitive.microsoft.com/entitylinking/v1.0/link";
        String text = "Both the future of space-based astronomy and NASA's ability to build complex science missions depend on its success.";
        JSONObject ret = ApiUtil.request(url, text);
        System.out.println(ret.get("entities").toString());
    }
}