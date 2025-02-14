package com.jayway.jsontransformer;

import com.jayway.jsontransformer.spi.json.GsonJsonProvider;
import com.jayway.jsontransformer.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsontransformer.spi.json.JacksonJsonProvider;
import com.jayway.jsontransformer.spi.json.JsonOrgJsonProvider;
import com.jayway.jsontransformer.spi.json.JsonSmartJsonProvider;
import com.jayway.jsontransformer.spi.mapper.GsonMappingProvider;
import com.jayway.jsontransformer.spi.mapper.JacksonMappingProvider;
import com.jayway.jsontransformer.spi.mapper.JsonOrgMappingProvider;
import com.jayway.jsontransformer.spi.mapper.JsonSmartMappingProvider;

import java.util.Arrays;

public class Configurations {

    public static final Configuration JSON_ORG_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new JsonOrgMappingProvider())
            .jsonProvider(new JsonOrgJsonProvider())
            .build();

    public static final Configuration GSON_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new GsonMappingProvider())
            .jsonProvider(new GsonJsonProvider())
            .build();

    public static final Configuration JACKSON_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new JacksonMappingProvider())
            .jsonProvider(new JacksonJsonProvider())
            .build();

    public static final Configuration JACKSON_JSON_NODE_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new JacksonMappingProvider())
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .build();

    public static final Configuration JSON_SMART_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new JsonSmartMappingProvider())
            .jsonProvider(new JsonSmartJsonProvider())
            .build();

    public static Iterable<Configuration> configurations() {
        return Arrays.asList(
               JSON_SMART_CONFIGURATION
               ,GSON_CONFIGURATION
               ,JACKSON_CONFIGURATION
               ,JACKSON_JSON_NODE_CONFIGURATION
               ,JSON_ORG_CONFIGURATION
        );
    }

    public static Iterable<Configuration> objectMappingConfigurations() {
        return Arrays.asList(
                 GSON_CONFIGURATION
                ,JACKSON_CONFIGURATION
                ,JACKSON_JSON_NODE_CONFIGURATION
        );
    }
}
