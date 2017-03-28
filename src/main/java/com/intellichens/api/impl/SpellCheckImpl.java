package com.intellichens.api.impl;

import com.intellichens.api.HttpBuilder;
import com.intellichens.api.SpellCheckAPI;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

/**
 * Created by raychen on 2017/3/26.
 */
public class SpellCheckImpl extends HttpBuilder implements SpellCheckAPI {

    private static final String checkURI = "https://api.cognitive.microsoft.com/bing/v5.0/spellcheck";

    @Override
    public String checkSpell(String text, String mode, String mkt) {
        try {
            URIBuilder builder = this.initURI(checkURI);
            if (mode != null) builder.setParameter("mode", mode);
            if (mkt != null) builder.setParameter("mkt", mkt);
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            setHeader(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setHeader(HttpRequestBase base) {
//        base.setHeader("Content-Type", );
    }
}
