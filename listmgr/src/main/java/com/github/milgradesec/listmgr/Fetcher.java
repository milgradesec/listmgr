package com.github.milgradesec.listmgr;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Fetcher {

    private static CloseableHttpClient client = HttpClients.createDefault();

    public static String fetch(String url) {
        HttpGet request = new HttpGet(URI.create(url));

        try (CloseableHttpResponse response = client.execute(request)) {
            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.printf("error: failed to fetch [%s]: got status code != 200: %d\n", url, status);
                return "";
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            System.out.printf("error: failed to fetch [%s]: %s\n", url, e.toString());
        }

        return "";
    }
}
