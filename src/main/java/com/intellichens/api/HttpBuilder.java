package com.intellichens.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by raychen on 2017/3/24.
 */
public abstract class HttpBuilder {
    private URIBuilder uriBuilder;
    public HttpClient httpClient;
    public HttpBuilder(){
        httpClient = HttpClients.createDefault();
    }
    public URIBuilder initURI(String url) throws URISyntaxException {
        uriBuilder = new URIBuilder(url);
        return uriBuilder;
    }
    public abstract void setHeader(HttpRequestBase base);
}
