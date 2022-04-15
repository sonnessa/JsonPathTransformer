package com.jayway.jsontransformer.matchers;

import com.jayway.jsontransformer.Configuration;
import com.jayway.jsontransformer.matchers.helpers.StrictParsingConfiguration;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.jsontransformer.matchers.JsonPathMatchers.isJsonString;
import static com.jayway.jsontransformer.matchers.helpers.ResourceHelpers.resource;
import static com.jayway.jsontransformer.matchers.helpers.TestingMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class IsJsonStringTest {
    private static final String BOOKS_JSON = resource("books.json");

    @BeforeClass
    public static void setupStrictJsonParsing() {
        Configuration.setDefaults(new StrictParsingConfiguration());
    }

    @AfterClass
    public static void setupDefaultJsonParsing() {
        Configuration.setDefaults(null);
    }

    @Test
    public void shouldMatchJsonStringEvaluatedToTrue() {
        assertThat(BOOKS_JSON, isJsonString(withPathEvaluatedTo(true)));
    }

    @Test
    public void shouldNotMatchJsonStringEvaluatedToFalse() {
        assertThat(BOOKS_JSON, not(isJsonString(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldNotMatchInvalidJsonString() {
        assertThat("invalid-json", not(isJsonString(withPathEvaluatedTo(true))));
        assertThat("invalid-json", not(isJsonString(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldBeDescriptive() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeTo(description);
        assertThat(description.toString(), startsWith("is json"));
        assertThat(description.toString(), containsString(MATCH_TRUE_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfValidJson() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch(BOOKS_JSON, description);
        assertThat(description.toString(), containsString(MISMATCHED_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfInvalidJson() {
        Matcher<String> matcher = isJsonString(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch("invalid-json", description);
        assertThat(description.toString(), containsString("\"invalid-json\""));
    }
}
