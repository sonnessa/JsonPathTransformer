package com.jayway.jsontransformer.internal.function;

import com.jayway.jsontransformer.internal.EvaluationContext;
import com.jayway.jsontransformer.internal.PathRef;

import java.util.List;

/**
 * Defines the default behavior which is to return the model that is provided as input as output
 *
 * Created by mattg on 6/26/15.
 */
public class PassthruPathFunction implements PathFunction {

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        return model;
    }
}
