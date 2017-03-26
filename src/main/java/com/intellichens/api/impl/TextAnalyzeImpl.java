package com.intellichens.api.impl;

import com.intellichens.api.HttpBuilder;
import com.intellichens.api.TextAnalyzeAPI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.List;

/**
 * Created by raychen on 2017/3/24.
 */
public class TextAnalyzeImpl extends HttpBuilder implements TextAnalyzeAPI{

    private static final String languageURI = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/languages";
    private static final String topicURI = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/topics";
    private static final String keysURI = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases";

    @Override
    public String getLanguage(String text, int languages){
        try {
            URI uri = this.initURI(languageURI)
                    .setParameter("numberOfLanguagesToDetect", languages+"")
                    .build();
            HttpPost request = new HttpPost(uri);
            this.setHeader(request);
            JSONObject obj = new JSONObject()
                    .put("id", "test")
                    .put("text", text);
            request.setEntity(new StringEntity(this.getContent(obj).toString()));
            HttpResponse response = this.httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getKeys(String text){
        try {
            URI uri = this.initURI(keysURI).build();
            HttpPost request = new HttpPost(uri);
            this.setHeader(request);
            JSONObject obj = new JSONObject()
                    .put("language", "en")
                    .put("id", "test")
                    .put("text", text);
            request.setEntity(new StringEntity(this.getContent(obj).toString()));
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getTopic(List<String> docs) {
        return null;
    }

    @Override
    public void setHeader(HttpRequestBase base) {
        base.setHeader("Content-Type", "application/json");
        base.setHeader("Ocp-Apim-Subscription-Key", "e81ad5cce6cb4709b62ca362a92d0ed8");
    }

    public JSONObject getContent(JSONObject document){
        JSONObject ret = new JSONObject();
        JSONArray documents = new JSONArray();
        documents.put(document);
        ret.put("documents", documents);
        return ret;
    }

}
