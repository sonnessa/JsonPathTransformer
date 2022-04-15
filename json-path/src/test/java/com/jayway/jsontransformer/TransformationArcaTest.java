package com.jayway.jsontransformer;

import static org.junit.Assert.assertEquals;

import com.jayway.jsontransformer.spi.transformer.TransformationSpec;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;


public class TransformationArcaTest {

    InputStream sourceStream;
    InputStream transformSpec;
    Configuration configuration;
    TransformationSpec spec;
    Object sourceJson;
    DocumentContext jsonContext;
    Map<String, String> kssProps;

    @Before
    public void setup() {

        configuration = Configuration.builder()
               .options(Option.CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH).build();
        sourceStream = this.getClass().getClassLoader().getResourceAsStream("transforms/arca_example.json");
        sourceJson = configuration.jsonProvider().parse(sourceStream, Charset.defaultCharset().name());

        jsonContext = JsonPath.parse(sourceJson);
        System.out.println("Document Input :" + jsonContext.jsonString());

        transformSpec = this.getClass().getClassLoader().getResourceAsStream("transforms/arca_example_spec.json");

        spec = configuration.transformationProvider().spec(transformSpec, configuration);

    }

    @Test
    public void simple_transform_spec_test() {
        Object transformed = configuration.transformationProvider().transform(sourceJson,spec, configuration);
        DocumentContext tgtJsonContext = JsonPath.parse(transformed);
        System.out.println("Document Created by Transformation:" + tgtJsonContext.jsonString());

        /*//Assertions about correctness of transformations
        //$.earliestStartTime +  $.plannedDriveDurationSeconds == $.testingAdditionalTransform.destinationSTAComputed
        long earliestStartTime = jsonContext.read("$.earliestStartTime");
        int plannedDriveDurationSeconds = jsonContext.read( "$.plannedDriveDurationSeconds");
        long destinationSTAComputed = tgtJsonContext.read("$.testingAdditionalTransform.destinationSTAComputed");
        assertEquals((earliestStartTime + plannedDriveDurationSeconds), destinationSTAComputed);

        //! $.isTPLManaged == $.testingAdditionalTransform.isNotTPLManaged
        boolean isTPLManaged = jsonContext.read("$.isTPLManaged");
        boolean isNotTPLManaged = tgtJsonContext.read("$.testingAdditionalTransform.isNotTPLManaged");
        assertEquals(!isTPLManaged,isNotTPLManaged);

        //$.cost + 100 == $.testingAdditionalTransform.totalCost
        double cost = jsonContext.read("$.cost");
        double totalCost = tgtJsonContext.read("$.testingAdditionalTransform.totalCost");
        assertEquals(cost + 100, totalCost, 0);

        //$.weight / 1000 == $.testingAdditionalTransform.weightKGS
        double weight = jsonContext.read("$.weight ");
        double weightKGS = tgtJsonContext.read("$.testingAdditionalTransform.weightKGS");
        assertEquals(weight/1000, weightKGS, 0.01);

        //1 - $.weightUtilization == $.testingAdditionalTransform.unUtilizedWeight
        double weightUtilization = jsonContext.read("$.weightUtilization");
        double unUtilizedWeight = tgtJsonContext.read("$.testingAdditionalTransform.unUtilizedWeight");
        assertEquals(1-weightUtilization, unUtilizedWeight, 0.01);*/
    }

}
