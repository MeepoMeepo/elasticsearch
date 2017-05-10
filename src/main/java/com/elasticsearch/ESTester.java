package com.elasticsearch;

import java.io.IOException;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * kcj
 * 2016年8月27日 下午9:48:55
 */
public class ESTester {
	public static void main(String[] args) throws IOException {
    	String query = "{\n" +
				"      \"query\": {\n" +
				"            \"bool\": {\n" +
				"                  \"must\": {\n" +
				"                        \"match\": {\n" +
				"                              \"_id\": {\n" +
				"                                    \"query\": 100000000201696754,\n" +
				"                                    \"type\": \"phrase\"\n" +
				"                              }\n" +
				"                        }\n" +
				"                  }\n" +
				"            }\n" +
				"      }\n" +
				"}";

		Search search = new Search.Builder(query).addIndex("shipping_order_2016-08-22").build();

		SearchResult result = ESClientFactory.getClient().execute(search);
		if (result.isSucceeded()) {

		}
	}
}
