package com.intellichens.api.impl;

import com.intellichens.api.HttpBuilder;
import com.intellichens.api.SpeechTranslateAPI;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/26
 */
public class SpeechTranslateImpl extends HttpBuilder implements SpeechTranslateAPI {
    private static final String PRE_REG_URI = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    private static final String RECOGNIZE_URI = "https://speech.platform.bing.com/recognize";

    @Override
    public StringBuilder translateSpeech(String fileName) {
        StringBuilder stringBuilder = null;
        try {
            URI PreUri = this.initURI(PRE_REG_URI).build();
            HttpPost preRequest = new HttpPost(PreUri);
            this.setHeader(preRequest);

            HttpResponse PreResponse = this.httpClient.execute(preRequest);
            String token =  EntityUtils.toString(PreResponse.getEntity());

            // request recognize
            String scenarios = "ulm";
            String appid = "D4D52672-91D7-4C74-8AD8-42B1D98141A5";
            String locale = "en-US";
            String deviceOs = "centOS";
            String version = "3.0";
            String format = "json";
            String requestId = UUID.randomUUID().toString();

            URI uri = this.initURI(RECOGNIZE_URI)
                    .setParameter("scenarios",scenarios)
                    .setParameter("appid",appid)
                    .setParameter("locale",locale)
                    .setParameter("device.os",deviceOs)
                    .setParameter("version",version)
                    .setParameter("format", format)
                    .setParameter("requestid", requestId)
                    .setParameter("instanceid",requestId)
                    .build();



        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    @Override
    public void setHeader(HttpRequestBase base) {
        base.setHeader("Content-Type", "application/x-www-form-urlencoded");
        base.setHeader("Ocp-Apim-Subscription-Key", "ee65337b917143b9be9f397daed75b6c");
    }

    private void setRegHeader(HttpRequestBase base){
        base.setHeader("Content-Type","audio/wav; samplerate=16000");
        base.setHeader("Authorization","audio/wav; samplerate=16000");
    }
}
