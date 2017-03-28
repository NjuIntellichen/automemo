package com.intellichens.api.impl;

import com.intellichens.api.HttpBuilder;
import com.intellichens.api.SpeechTranslateAPI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/26
 */
@Service
public class SpeechTranslateImpl extends HttpBuilder implements SpeechTranslateAPI {
    private static final String PRE_REG_URI = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    private static final String RECOGNIZE_URI = "https://speech.platform.bing.com/recognize";

    @Override
    public String translateSpeech(String fileName) {

        // set binary data from voice file
        File file = new File(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[1024];
            int len;
            while((len = is.read(b, 0, b.length)) != -1){
                baos.write(b, 0, len);
            }
            baos.flush();
            is.close();

        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return translateSpeech(baos.toByteArray());
    }

    @Override
    public String translateSpeech(byte[] speechStream) {
        try {
            URI PreUri = this.initURI(PRE_REG_URI).build();
            HttpPost preRequest = new HttpPost(PreUri);
            this.setHeader(preRequest);

            // get token
            HttpResponse PreResponse = this.httpClient.execute(preRequest);
            String token =  EntityUtils.toString(PreResponse.getEntity());

            // request recognize
            String scenarios = "ulm";
            String appid = "D4D52672-91D7-4C74-8AD8-42B1D98141A5";
            String locale = "en-US";
            String deviceOs = "macOS";
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
                    .setParameter("maxnbest","3")
                    .build();

            HttpPost request= new HttpPost(uri);
            request.setHeader("Content-Type","audio/wav; samplerate=16000");
            request.setHeader("Authorization","Bearer "+token);

            HttpEntity entity = new ByteArrayEntity(speechStream);
            request.setEntity(entity);

            // get result
            HttpResponse response = httpClient.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println(responseCode);
            if(responseCode!=200){
                System.err.println("response error");
                return null;
            }

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line);
            }

            System.out.println(stringBuilder.toString());
            // analyze result
            JSONObject json = new JSONObject(stringBuilder.toString());
            JSONObject header = (JSONObject) json.get("header");
            if("success".equals(header.get("status").toString())){
                return header.get("lexical").toString();
            }
            else {
                System.err.println("result is unsuccessful");
                return null;
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setHeader(HttpRequestBase base) {
        base.setHeader("Content-Type", "application/x-www-form-urlencoded");
        base.setHeader("Ocp-Apim-Subscription-Key", "ee65337b917143b9be9f397daed75b6c");
    }

    public static void main(String[] args) {
        String fileName1 = "/Users/yuminchen/Desktop/4.wav";
        String fileName2 = "/Users/yuminchen/Desktop/5.wav";
        System.out.println();
        SpeechTranslateImpl speechTranslate = new SpeechTranslateImpl();
        String first = speechTranslate.translateSpeech(fileName1);
        System.err.println(first + speechTranslate.translateSpeech(fileName2));

    }

}
