package com.jayway.jsontransformer.internal.function.text;

import com.jayway.jsontransformer.internal.EvaluationContext;
import com.jayway.jsontransformer.internal.PathRef;
import com.jayway.jsontransformer.internal.function.Parameter;
import com.jayway.jsontransformer.internal.function.PathFunction;

import java.util.List;

/**
 * Provides the length of a JSONArray Object
 *
 * Created by mattg on 6/26/15.
 */
public class Length implements PathFunction {

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        if(ctx.configuration().jsonProvider().isArray(model)){
            return ctx.configuration().jsonProvider().length(model);
        } else if(ctx.configuration().jsonProvider().isMap(model)){
            return ctx.configuration().jsonProvider().length(model);
        }
        return null;
    }
}