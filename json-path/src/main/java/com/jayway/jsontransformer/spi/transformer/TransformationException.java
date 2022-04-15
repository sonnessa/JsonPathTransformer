package com.jayway.jsontransformer.spi.transformer;

import com.jayway.jsontransformer.JsonPathException;

public class TransformationException extends JsonPathException {

    public TransformationException(Throwable cause) {
        super(cause);
    }

    public TransformationException(String message) {
        super(message);
    }
}