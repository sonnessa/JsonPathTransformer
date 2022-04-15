package com.jayway.jsontransformer.spi.transformer.jsonpathtransformer;

import com.jayway.jsontransformer.spi.transformer.TransformationSpecValidationException;

public class UnsupportedWildcardPathException  extends TransformationSpecValidationException {
    public UnsupportedWildcardPathException(Throwable cause) {
        super(cause);
    }
    public UnsupportedWildcardPathException(String message) {
        super(message);
    }

}
