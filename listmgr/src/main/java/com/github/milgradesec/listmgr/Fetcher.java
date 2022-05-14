package com.github.milgradesec.listmgr;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Fetcher {
    private static final Logger logger = LogManager.getLogger();

    private static final CloseableHttpClient client = HttpClients.createDefault();

    public static String fetch(String url) {
        HttpGet request = new HttpGet(URI.create(url));

        try (CloseableHttpResponse response = client.execute(request)) {
            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                logger.error("failed to fetch '{}': got status code != 200: {}",
                        url, status);
                return "";
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            logger.error("failed to fetch '{}': {}", url, e.toString());
        }
        return "";
    }
}
