package com.intellichens.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URI;
import java.util.UUID;

/**
 * @author cheney
 * @version V1.0
 * @date 2017/3/26
 */
public class SpeechTranslator {



    public static StringBuilder speech2Text(String fileName){
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
        System.out.println(UUID.randomUUID().toString());
    }
}
