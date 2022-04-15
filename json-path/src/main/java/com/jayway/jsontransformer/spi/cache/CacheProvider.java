package com.jayway.jsontransformer.spi.cache;

import com.jayway.jsontransformer.JsonPathException;

import static com.jayway.jsontransformer.internal.Utils.notNull;

public class CacheProvider {
    private static Cache cache;

    public static void setCache(Cache cache){
        notNull(cache, "Cache may not be null");
        synchronized (CacheProvider.class){
            if(CacheProvider.cache != null){
                throw new JsonPathException("Cache provider must be configured before cache is accessed.");
            } else {
                CacheProvider.cache = cache;
            }
        }
    }

    public static Cache getCache() {
        if(CacheProvider.cache == null){
            synchronized (CacheProvider.class){
                if(CacheProvider.cache == null){
                    CacheProvider.cache = getDefaultCache();
                }
            }
        }
        return CacheProvider.cache;
    }


    private static Cache getDefaultCache(){
        return new LRUCache(400);
        //return new NOOPCache();
    }
}
