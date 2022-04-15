package com.jayway.jsontransformer.web.bench;

import com.jayway.jsontransformer.internal.JsonFormatter;

public class Result {

    public final String provider;
    public final long time;
    public final String result;
    public final String error;

    public Result(String provider, long time, String result, String error) {
        this.provider = provider;
        this.time = time;
        this.result = result != null ? JsonFormatter.prettyPrint(result) : result;
        this.error = error;
    }
}