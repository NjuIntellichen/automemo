package com.intellichens.api.impl;

import com.intellichens.api.EntityLinkAPI;
import com.intellichens.api.HttpBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * Created by raychen on 2017/3/26.
 */
public class EntityLinkImpl extends HttpBuilder implements EntityLinkAPI {

    private static final String entityURI = "https://westus.api.cognitive.microsoft.com/entitylinking/v1.0/link";

    @Override
    public String getEntities(String text, String selection, int offset) {
        try {
            URIBuilder uriBuilder = this.initURI(entityURI);
            if (selection != null){
                uriBuilder.setParameter("selection", selection);
                if (offset != -1)
                    uriBuilder.setParameter("offset", String.valueOf(offset));
            }
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);
            setHeader(request);
            request.setEntity(new StringEntity(text));
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setHeader(HttpRequestBase base) {
        base.setHeader("Content-Type", "text/plain");
        base.setHeader("Ocp-Apim-Subscription-Key", "1cead581f28b43a9a023fce1b8b9e5df");
    }
}
