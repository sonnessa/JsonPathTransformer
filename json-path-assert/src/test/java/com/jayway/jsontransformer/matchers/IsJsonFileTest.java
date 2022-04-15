package com.jayway.jsontransformer.matchers;

import com.jayway.jsontransformer.Configuration;
import com.jayway.jsontransformer.matchers.helpers.StrictParsingConfiguration;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static com.jayway.jsontransformer.matchers.JsonPathMatchers.isJsonFile;
import static com.jayway.jsontransformer.matchers.helpers.ResourceHelpers.resourceAsFile;
import static com.jayway.jsontransformer.matchers.helpers.TestingMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class IsJsonFileTest {
    private static final File BOOKS_JSON = resourceAsFile("books.json");
    private static final File INVALID_JSON = resourceAsFile("invalid.json");

    @BeforeClass
    public static void setupStrictJsonParsing() {
        Configuration.setDefaults(new StrictParsingConfiguration());
    }

    @AfterClass
    public static void setupDefaultJsonParsing() {
        Configuration.setDefaults(null);
    }

    @Test
    public void shouldMatchJsonFileEvaluatedToTrue() {
        assertThat(BOOKS_JSON, isJsonFile(withPathEvaluatedTo(true)));
    }

    @Test
    public void shouldNotMatchJsonFileEvaluatedToFalse() {
        assertThat(BOOKS_JSON, not(isJsonFile(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldNotMatchInvalidJsonFile() {
        assertThat(INVALID_JSON, not(isJsonFile(withPathEvaluatedTo(true))));
        assertThat(INVALID_JSON, not(isJsonFile(withPathEvaluatedTo(false))));
    }

    @Test
    public void shouldBeDescriptive() {
        Matcher<File> matcher = isJsonFile(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeTo(description);
        assertThat(description.toString(), startsWith("is json"));
        assertThat(description.toString(), containsString(MATCH_TRUE_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfValidJson() {
        Matcher<File> matcher = isJsonFile(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch(BOOKS_JSON, description);
        assertThat(description.toString(), containsString(MISMATCHED_TEXT));
    }

    @Test
    public void shouldDescribeMismatchOfInvalidJson() {
        Matcher<File> matcher = isJsonFile(withPathEvaluatedTo(true));
        Description description = new StringDescription();
        matcher.describeMismatch(INVALID_JSON, description);
        assertThat(description.toString(), containsString("invalid.json"));
        assertThat(description.toString(), containsString("invalid-json"));
    }
}
