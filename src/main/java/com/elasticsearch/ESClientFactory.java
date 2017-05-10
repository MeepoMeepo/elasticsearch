package com.elasticsearch;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;


public class ESClientFactory {

    private static final String es_server_uris = "http://localhost:9200";

    private final static JestClient client;

    static {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(Arrays.asList(es_server_uris.trim().split(",")))
                .multiThreaded(true)
                .discoveryEnabled(true)
                .discoveryFrequency(1, TimeUnit.SECONDS)
                .build());
        client = factory.getObject();
    }

    private ESClientFactory() {
    }

    public static JestClient getClient() {
        return client;
    }
}
