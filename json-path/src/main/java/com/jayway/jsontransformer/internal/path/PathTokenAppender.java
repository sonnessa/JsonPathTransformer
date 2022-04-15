package com.jayway.jsontransformer.internal.path;

public interface PathTokenAppender {
    PathTokenAppender appendPathToken(PathToken next);
}
