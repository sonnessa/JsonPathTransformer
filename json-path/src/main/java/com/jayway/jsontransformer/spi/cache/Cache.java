package com.jayway.jsontransformer.spi.cache;

import com.jayway.jsontransformer.InvalidJsonException;
import com.jayway.jsontransformer.JsonPath;

public interface Cache {

	/**
     * Get the Cached JsonPath
     * @param key cache key to lookup the JsonPath
     * @return JsonPath
     */
	 JsonPath get(String key);
	
	/**
     * Add JsonPath to the cache
     * @param key cache key to store the JsonPath
     * @param value JsonPath to be cached
     * @throws InvalidJsonException
     */
	 void put(String key, JsonPath value);
}
