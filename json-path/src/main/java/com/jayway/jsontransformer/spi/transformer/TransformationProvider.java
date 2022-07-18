package com.jayway.jsontransformer.spi.transformer;

import com.jayway.jsontransformer.Configuration;

import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;

public interface TransformationProvider<T extends TransformationSpec> {

    /**
     * Create the TransformationSpec from the given Json String
     * @param input, the source string describing the Transformation
     * @return The Transformation Specification
     * @throws TransformationSpecValidationException
     */
    TransformationSpec spec(String input, Configuration configuration);

    /**
     * Create the TransformationSpec from the given InputStream
     * @param input
     * @return The Transformation Specification
     * @throws TransformationSpecValidationException
     */
    TransformationSpec spec(InputStream input, Configuration configuration);

    /**
     *
     * @param source the source
     * @param spec the transformation spec
     * @param configuration
     * @return return the transformed JSON object. The Object structure is whatever is
     * returned by the underlying JsonProvider.
     */
     Object transform(Object source, T spec, Configuration configuration);

    /**
     *
     * @param source the source
     * @param spec the transformation spec
     * @param configuration
     * @param customTransformations
     * @return return the transformed JSON object. The Object structure is whatever is
     * returned by the underlying JsonProvider.
     */
    Object transform(Object source, T spec, Configuration configuration, Map<String, Function<Object, String>> customTransformations);
}
