package com.jayway.jsontransformer.spi.cache;

import com.jayway.jsontransformer.JsonPath;

public class NOOPCache implements Cache {

    @Override
    public JsonPath get(String key) {
        return null;
    }

    @Override
    public void put(String key, JsonPath value) {
    }
}
