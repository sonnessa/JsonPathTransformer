package com.jayway.jsontransformer.spi.transformer;

import com.jayway.jsontransformer.JsonPathException;

public class TransformationSpecValidationException extends JsonPathException {

    public TransformationSpecValidationException(Throwable cause) {
        super(cause);
    }

    public TransformationSpecValidationException(String message) {
        super(message);
    }
}
