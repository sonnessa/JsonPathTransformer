package com.jayway.jsontransformer.internal.function.json;

import com.jayway.jsontransformer.internal.EvaluationContext;
import com.jayway.jsontransformer.internal.PathRef;
import com.jayway.jsontransformer.internal.function.Parameter;
import com.jayway.jsontransformer.internal.function.PathFunction;

import java.util.List;

/**
 * Author: Sergey Saiyan sergey.sova42@gmail.com
 * Created at 21/02/2018.
 */
public class KeySetFunction implements PathFunction {

    @Override
    public Object invoke(String currentPath, PathRef parent, Object model, EvaluationContext ctx, List<Parameter> parameters) {
        if (ctx.configuration().jsonProvider().isMap(model)) {
            return ctx.configuration().jsonProvider().getPropertyKeys(model);
        }
        return null;
    }
}
