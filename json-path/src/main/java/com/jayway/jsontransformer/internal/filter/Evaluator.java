package com.jayway.jsontransformer.internal.filter;

import com.jayway.jsontransformer.Predicate;

public interface Evaluator {
    boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx);
}