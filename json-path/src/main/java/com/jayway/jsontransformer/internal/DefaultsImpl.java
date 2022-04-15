package com.jayway.jsontransformer.internal;

import com.jayway.jsontransformer.Configuration.Defaults;
import com.jayway.jsontransformer.Option;
import com.jayway.jsontransformer.spi.json.JsonProvider;
import com.jayway.jsontransformer.spi.json.JsonSmartJsonProvider;
import com.jayway.jsontransformer.spi.mapper.JsonSmartMappingProvider;
import com.jayway.jsontransformer.spi.mapper.MappingProvider;
import com.jayway.jsontransformer.spi.transformer.TransformationProvider;
import com.jayway.jsontransformer.spi.transformer.jsonpathtransformer.JsonPathTransformationProvider;

import java.util.EnumSet;
import java.util.Set;

public final class DefaultsImpl implements Defaults {

  public static final DefaultsImpl INSTANCE = new DefaultsImpl();

  private final MappingProvider mappingProvider = new JsonSmartMappingProvider();

  @Override
  public JsonProvider jsonProvider() {
    return new JsonSmartJsonProvider();
  }

  @Override
  public Set<Option> options() {
    return EnumSet.noneOf(Option.class);
  }

  @Override
  public MappingProvider mappingProvider() {
    return mappingProvider;
  }

  @Override
  public TransformationProvider transformationProvider() {
    return new JsonPathTransformationProvider();
  }

  private DefaultsImpl() {
  }

}
