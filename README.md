Jayway JsonPath
=====================

**A Java DSL for reading JSON documents.**

[![Build Status](https://travis-ci.org/json-path/JsonPath.svg?branch=master)](https://travis-ci.org/json-path/JsonPath)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/jsontransformer/json-path/badge.svg)](https://maven-badges.herokuapp.com/maven-central/jsontransformer/json-path)
[![Javadoc](https://www.javadoc.io/badge/jsontransformer/json-path.svg)](http://www.javadoc.io/doc/jsontransformer/json-path)

Jayway JsonPath is a Java port of [Stefan Goessner JsonPath implementation](http://goessner.net/articles/JsonPath/). 

News
----
11 Oct 2022 - Released JsonPath 2.7.1:
      New Transformations STRING_TO_LONG, STRING_TO_DOUBLE, STRING_TO_BOOL

28 Jul 2022 - Released JsonPath 2.7.0:
Transform target strategies refactoring:
     CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH,
     FORCE_MISSING_PROPERTIES_ON_TARGET,
     SRC_NULL_ON_TARGET

27 Jul 2022 - Released JsonPath 2.6.1:
Float values are parsed as BigDecimal but it was not considered a scalar value to for json trasformation.
BigDecimal and BigInteger value representation of json documents are now treated as scalar.

18 Jul 2022 - Released JsonPath 2.6.0:
CUSTOM_ functions introduced in additionalTransform: function can be defined at host passing a map of custom function

20 Jun 2022 - Released JsonPath 2.5.9:
New transformation function did not support null values.

14 Jun 2022 - Released JsonPath 2.5.8:
Fix behavior introduced in 2.5.7

13 Jun 2022 - Released JsonPath 2.5.7:
Source path with null values were omitted! Why? Now values are returned as well.

13 Jun 2022 - Released JsonPath 2.5.6:
ISO8601_TO_STR bug fix. Bifunction transformation can be applied to null values.

21 May 2022 - Released JsonPath 2.5.5:
New Transformations STR_TO_ISO8601, ISO8601_TO_STR to transform strings into ISO8601 dates, using custom date format
    and vice-versa.

02 May 2022 - Released JsonPath 2.5.4:
    New Transformations ARRAY_JOIN, STRING_TO_ARRAY to transform array of strings into delimiter concatenated string
    and vice-versa.

01 May 2022 - Released JsonPath 2.5.3:
    New transformation functions BOOL_TO_STRING and NUMBER_TO_STRING now support null values

20 Apr 2022 - Released JsonPath 2.5.2:
    New Option.FORCE_MISSING_PROPERTIES_ON_TARGET provided to force target json to contain missing source fields

20 Apr 2022 - Released JsonPath 2.5.1:
    New Transformation (cast) function NUMBER_TO_STRING provided

10 Dec 2020 - Released JsonPath 2.5.0

05 Jul 2017 - Released JsonPath 2.4.0

26 Jun 2017 - Released JsonPath 2.3.0

29 Feb 2016 - Released JsonPath 2.2.0

22 Nov 2015 - Released JsonPath 2.1.0

19 Mar 2015 - Released JsonPath 2.0.0

11 Nov 2014 - Released JsonPath 1.2.0

01 Oct 2014 - Released JsonPath 1.1.0  

26 Sep 2014 - Released JsonPath 1.0.0 


Getting Started
---------------

JsonPath is available at the Central Maven Repository. Maven users add this to your POM.

```xml
<dependency>
    <groupId>com.jayway.jsontransformercom.jayway.jsontransformer</groupId>
    <artifactId>json-path</artifactId>
    <version>2.5.0</version>
</dependency>
```

If you need help ask questions at [Stack Overflow](http://stackoverflow.com/questions/tagged/jsonpath). Tag the question 'jsonpath' and 'java'.

JsonPath expressions always refer to a JSON structure in the same way as XPath expression are used in combination 
with an XML document. The "root member object" in JsonPath is always referred to as `$` regardless if it is an 
object or array.

JsonPath expressions can use the dot–notation

`$.store.book[0].title`

or the bracket–notation

`$['store']['book'][0]['title']`

Operators
---------

| Operator                  | Description                                                        |
| :------------------------ | :----------------------------------------------------------------- |
| `$`                       | The root element to query. This starts all path expressions.       |
| `@`                       | The current node being processed by a filter predicate.            |
| `*`                       | Wildcard. Available anywhere a name or numeric are required.       |
| `..`                      | Deep scan. Available anywhere a name is required.                  |
| `.<name>`                 | Dot-notated child                                                  |
| `['<name>' (, '<name>')]` | Bracket-notated child or children                                  |
| `[<number> (, <number>)]` | Array index or indexes                                             |
| `[start:end]`             | Array slice operator                                               |
| `[?(<expression>)]`       | Filter expression. Expression must evaluate to a boolean value.    |


Functions
---------

Functions can be invoked at the tail end of a path - the input to a function is the output of the path expression.
The function output is dictated by the function itself.

| Function                  | Description                                                         | Output    |
| :------------------------ | :------------------------------------------------------------------ |-----------|
| min()                     | Provides the min value of an array of numbers                       | Double    |
| max()                     | Provides the max value of an array of numbers                       | Double    |
| avg()                     | Provides the average value of an array of numbers                   | Double    |
| stddev()                  | Provides the standard deviation value of an array of numbers        | Double    |
| length()                  | Provides the length of an array                                     | Integer   |
| sum()                     | Provides the sum value of an array of numbers                       | Double    |


Filter Operators
-----------------

Filters are logical expressions used to filter arrays. A typical filter would be `[?(@.age > 18)]` where `@` represents the current item being processed. More complex filters can be created with logical operators `&&` and `||`. String literals must be enclosed by single or double quotes (`[?(@.color == 'blue')]` or `[?(@.color == "blue")]`).   

| Operator                 | Description                                                           |
| :----------------------- | :-------------------------------------------------------------------- |
| ==                       | left is equal to right (note that 1 is not equal to '1')              |
| !=                       | left is not equal to right                                            |
| <                        | left is less than right                                               |
| <=                       | left is less or equal to right                                        |
| >                        | left is greater than right                                            |
| >=                       | left is greater than or equal to right                                |
| =~                       | left matches regular expression  [?(@.name =~ /foo.*?/i)]             |
| in                       | left exists in right [?(@.size in ['S', 'M'])]                        |
| nin                      | left does not exists in right                                         |
| subsetof                 | left is a subset of right [?(@.sizes subsetof ['S', 'M', 'L'])]       |
| anyof                    | left has an intersection with right [?(@.sizes anyof ['M', 'L'])]     |
| noneof                   | left has no intersection with right [?(@.sizes noneof ['M', 'L'])]    |
| size                     | size of left (array or string) should match right                     |
| empty                    | left (array or string) should be empty                                |


Path Examples
-------------

Given the json

```javascript
{
    "store": {
        "book": [
            {
                "category": "reference",
                "author": "Nigel Rees",
                "title": "Sayings of the Century",
                "price": 8.95
            },
            {
                "category": "fiction",
                "author": "Evelyn Waugh",
                "title": "Sword of Honour",
                "price": 12.99
            },
            {
                "category": "fiction",
                "author": "Herman Melville",
                "title": "Moby Dick",
                "isbn": "0-553-21311-3",
                "price": 8.99
            },
            {
                "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "0-395-19395-8",
                "price": 22.99
            }
        ],
        "bicycle": {
            "color": "red",
            "price": 19.95
        }
    },
    "expensive": 10
}
```

| JsonPath (click link to try)| Result |
| :------- | :----- |
| <a href="http://jsonpath.herokuapp.com/?path=$.store.book[*].author" target="_blank">$.store.book[*].author</a>| The authors of all books     |
| <a href="http://jsonpath.herokuapp.com/?path=$..author" target="_blank">$..author</a>                   | All authors                         |
| <a href="http://jsonpath.herokuapp.com/?path=$.store.*" target="_blank">$.store.*</a>                  | All things, both books and bicycles  |
| <a href="http://jsonpath.herokuapp.com/?path=$.store..price" target="_blank">$.store..price</a>             | The price of everything         |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[2]" target="_blank">$..book[2]</a>                 | The third book                      |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[2]" target="_blank">$..book[-2]</a>                 | The second to last book            |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[0,1]" target="_blank">$..book[0,1]</a>               | The first two books               |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[:2]" target="_blank">$..book[:2]</a>                | All books from index 0 (inclusive) until index 2 (exclusive) |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[1:2]" target="_blank">$..book[1:2]</a>                | All books from index 1 (inclusive) until index 2 (exclusive) |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[-2:]" target="_blank">$..book[-2:]</a>                | Last two books                   |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[2:]" target="_blank">$..book[2:]</a>                | Book number two from tail          |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[?(@.isbn)]" target="_blank">$..book[?(@.isbn)]</a>          | All books with an ISBN number         |
| <a href="http://jsonpath.herokuapp.com/?path=$.store.book[?(@.price < 10)]" target="_blank">$.store.book[?(@.price < 10)]</a> | All books in store cheaper than 10  |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[?(@.price <= $['expensive'])]" target="_blank">$..book[?(@.price <= $['expensive'])]</a> | All books in store that are not "expensive"  |
| <a href="http://jsonpath.herokuapp.com/?path=$..book[?(@.author =~ /.*REES/i)]" target="_blank">$..book[?(@.author =~ /.*REES/i)]</a> | All books matching regex (ignore case)  |
| <a href="http://jsonpath.herokuapp.com/?path=$..*" target="_blank">$..*</a>                        | Give me every thing   
| <a href="http://jsonpath.herokuapp.com/?path=$..book.length()" target="_blank">$..book.length()</a>                 | The number of books                      |

Reading a Document
------------------
The simplest most straight forward way to use JsonPath is via the static read API.

```java
String json = "...";

List<String> authors = JsonPath.read(json, "$.store.book[*].author");
```

If you only want to read once this is OK. In case you need to read an other path as well this is not the way 
to go since the document will be parsed every time you call JsonPath.read(...). To avoid the problem you can 
parse the json first.

```java
String json = "...";
Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

String author0 = JsonPath.read(document, "$.store.book[0].author");
String author1 = JsonPath.read(document, "$.store.book[1].author");
```
JsonPath also provides a fluent API. This is also the most flexible one.

```java
String json = "...";

ReadContext ctx = JsonPath.parse(json);

List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");


List<Map<String, Object>> expensiveBooks = JsonPath
                            .using(configuration)
                            .parse(json)
                            .read("$.store.book[?(@.price > 10)]", List.class);
```

What is Returned When?
----------------------
When using JsonPath in java its important to know what type you expect in your result. JsonPath will automatically 
try to cast the result to the type expected by the invoker.

```java
//Will throw an java.lang.ClassCastException    
List<String> list = JsonPath.parse(json).read("$.store.book[0].author")

//Works fine
String author = JsonPath.parse(json).read("$.store.book[0].author")
```

When evaluating a path you need to understand the concept of when a path is `definite`. A path is `indefinite` if it contains:

* `..` - a deep scan operator
* `?(<expression>)` - an expression
* `[<number>, <number> (, <number>)]` - multiple array indexes

`Indefinite` paths always returns a list (as represented by current JsonProvider). 

By default a simple object mapper is provided by the MappingProvider SPI. This allows you to specify the return type you want and the MappingProvider will
try to perform the mapping. In the example below mapping between `Long` and `Date` is demonstrated. 

```java
String json = "{\"date_as_long\" : 1411455611975}";

Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
```

If you configure JsonPath to use `JacksonMappingProvider` or `GsonMappingProvider` you can even map your JsonPath output directly into POJO's.

```java
Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
```

To obtain full generics type information, use TypeRef.

```java
TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};

List<String> titles = JsonPath.parse(JSON_DOCUMENT).read("$.store.book[*].title", typeRef);
```

Predicates
----------
There are three different ways to create filter predicates in JsonPath.

### Inline Predicates

Inline predicates are the ones defined in the path.

```java
List<Map<String, Object>> books =  JsonPath.parse(json)
                                     .read("$.store.book[?(@.price < 10)]");
```

You can use `&&` and `||` to combine multiple predicates `[?(@.price < 10 && @.category == 'fiction')]` , 
`[?(@.category == 'reference' || @.price > 10)]`.
 
You can use `!` to negate a predicate `[?(!(@.price < 10 && @.category == 'fiction'))]`.

### Filter Predicates
 
Predicates can be built using the Filter API as shown below:

```java
import static com.jayway.jsontransformer.JsonPath.parse;
import static com.jayway.jsontransformer.Criteria.where;
import static com.jayway.jsontransformer.Filter.filter;
...
...

Filter cheapFictionFilter = filter(
   where("category").is("fiction").and("price").lte(10D)
);

List<Map<String, Object>> books =  
   parse(json).read("$.store.book[?]", cheapFictionFilter);

```
Notice the placeholder `?` for the filter in the path. When multiple filters are provided they are applied in order where the number of placeholders must match 
the number of provided filters. You can specify multiple predicate placeholders in one filter operation `[?, ?]`, both predicates must match. 

Filters can also be combined with 'OR' and 'AND'
```java
Filter fooOrBar = filter(
   where("foo").exists(true)).or(where("bar").exists(true)
);
   
Filter fooAndBar = filter(
   where("foo").exists(true)).and(where("bar").exists(true)
);
```

### Roll Your Own
 
Third option is to implement your own predicates
 
```java 
Predicate booksWithISBN = new Predicate() {
    @Override
    public boolean apply(PredicateContext ctx) {
        return ctx.item(Map.class).containsKey("isbn");
    }
};

List<Map<String, Object>> books = 
   reader.read("$.store.book[?].isbn", List.class, booksWithISBN);
```

Path vs Value
-------------
In the Goessner implementation a JsonPath can return either `Path` or `Value`. `Value` is the default and what all the examples above are returning. If you rather have the path of the elements our query is hitting this can be achieved with an option.

```java
Configuration conf = Configuration.builder()
   .options(Option.AS_PATH_LIST).build();

List<String> pathList = using(conf).parse(json).read("$..author");

assertThat(pathList).containsExactly(
    "$['store']['book'][0]['author']",
    "$['store']['book'][1]['author']",
    "$['store']['book'][2]['author']",
    "$['store']['book'][3]['author']");
```

Set a value 
-----------
The library offers the possibility to set a value.

```java
String newJson = JsonPath.parse(json).set("$['store']['book'][0]['author']", "Paul").jsonString();
```



Tweaking Configuration
----------------------

### Options
When creating your Configuration there are a few option flags that can alter the default behaviour.

**DEFAULT_PATH_LEAF_TO_NULL**
<br/>
This option makes JsonPath return null for missing leafs. Consider the following json

```javascript
[
   {
      "name" : "john",
      "gender" : "male"
   },
   {
      "name" : "ben"
   }
]
```

```java
Configuration conf = Configuration.defaultConfiguration();

//Works fine
String gender0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
//PathNotFoundException thrown
String gender1 = JsonPath.using(conf).parse(json).read("$[1]['gender']");

Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

//Works fine
String gender0 = JsonPath.using(conf2).parse(json).read("$[0]['gender']");
//Works fine (null is returned)
String gender1 = JsonPath.using(conf2).parse(json).read("$[1]['gender']");
```
 
**ALWAYS_RETURN_LIST**
<br/>
This option configures JsonPath to return a list even when the path is `definite`. 
 
```java
Configuration conf = Configuration.defaultConfiguration();

//ClassCastException thrown
List<String> genders0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");

Configuration conf2 = conf.addOptions(Option.ALWAYS_RETURN_LIST);

//Works fine
List<String> genders0 = JsonPath.using(conf2).parse(json).read("$[0]['gender']");
``` 
**SUPPRESS_EXCEPTIONS**
<br/>
This option makes sure no exceptions are propagated from path evaluation. It follows these simple rules:

* If option `ALWAYS_RETURN_LIST` is present an empty list will be returned
* If option `ALWAYS_RETURN_LIST` is **NOT** present null returned 

**REQUIRE_PROPERTIES**
</br>
This option configures JsonPath to require properties defined in path when an `indefinite` path is evaluated.

```java
Configuration conf = Configuration.defaultConfiguration();

//Works fine
List<String> genders = JsonPath.using(conf).parse(json).read("$[*]['gender']");

Configuration conf2 = conf.addOptions(Option.REQUIRE_PROPERTIES);

//PathNotFoundException thrown
List<String> genders = JsonPath.using(conf2).parse(json).read("$[*]['gender']");
```

### JsonProvider SPI

JsonPath is shipped with five different JsonProviders:

* [JsonSmartJsonProvider](https://code.google.com/p/json-smart/) (default)
* [JacksonJsonProvider](https://github.com/FasterXML/jackson)
* [JacksonJsonNodeJsonProvider](https://github.com/FasterXML/jackson)
* [GsonJsonProvider](https://code.google.com/p/google-gson/) 
* [JsonOrgJsonProvider](http://www.json.org/java/index.html)

Changing the configuration defaults as demonstrated should only be done when your application is being initialized. Changes during runtime is strongly discouraged, especially in multi threaded applications.
  

```java
Configuration.setDefaults(new Configuration.Defaults() {

    private final JsonProvider jsonProvider = new JacksonJsonProvider();
    private final MappingProvider mappingProvider = new JacksonMappingProvider();
      
    @Override
    public JsonProvider jsonProvider() {
        return jsonProvider;
    }

    @Override
    public MappingProvider mappingProvider() {
        return mappingProvider;
    }
    
    @Override
    public Set<Option> options() {
        return EnumSet.noneOf(Option.class);
    }
});
```

Note that the JacksonJsonProvider requires `com.fasterxml.jackson.core:jackson-databind:2.4.5` and the GsonJsonProvider requires `com.google.code.gson:gson:2.3.1` on your classpath. 

### Cache SPI

In JsonPath 2.1.0 a new Cache SPI was introduced. This allows API consumers to configure path caching in a way that suits their needs. The cache must be configured before it is accesses for the first time or a JsonPathException is thrown. JsonPath ships with two cache implementations

* `com.jayway.jsontransformer.spi.cache.LRUCache` (default, thread safe)
* `com.jayway.jsontransformer.spi.cache.NOOPCache` (no cache)

If you want to implement your own cache the API is simple. 

```java
CacheProvider.setCache(new Cache() {
    //Not thread safe simple cache
    private Map<String, JsonPath> map = new HashMap<String, JsonPath>();

    @Override
    public JsonPath get(String key) {
        return map.get(key);
    }

    @Override
    public void put(String key, JsonPath jsonPath) {
        map.put(key, jsonPath);
    }
});
```

### Generating JSON from JsonPath

JsonPath set/put API can be used to update the value in a document at a given Path. But what if the Path to be updated does not exist.

```java
    String inputObject =  "{ }";
    String path = "$.a.b.c";
    JsonPath compiledPath = JsonPath.compile(path);
    Object output = compiledPath.set(pathConfiguration.jsonProvider().parse(inputObject),
                12345,pathConfiguration);
    Integer result = parse(output).read(path);
    DocumentContext jsonContext = JsonPath.parse(output);
    System.out.println("Document Created by JsonPaths:" + jsonContext.jsonString());
    assertThat(result).isEqualTo(12345);
```
Here path '$.a.b.c' does not exist in the input Object so we would get a PathNotFoundException

```java
com.jayway.jsontransformer.PathNotFoundException: Missing property in path $['a']
	at com.jayway.jsontransformer.internal.path.EvaluationContextImpl.getValue(EvaluationContextImpl.java:133)
	at com.jayway.jsontransformer.JsonPath.read(JsonPath.java:199)
	at com.jayway.jsontransformer.internal.JsonContext.read(JsonContext.java:89)
	at com.jayway.jsontransformer.internal.JsonContext.read(JsonContext.java:78)
```

There are usecases where one would like the above set API to create a JSON of the form below:

```javascript
{"a":{"b":{"c":12345}}}
```
This is now possible by enabling a new Configuration Option named `CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH`. The feature will only work if the 'path' argument represents a Definite Path as per JsonPath definitions.

```java
    Configuration pathConfiguration = Configuration.builder()
                .options(Option.CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH).build();
    String inputObject =  "{ }";
    String path = "$.a.b.c";
    JsonPath compiledPath = JsonPath.compile(path);
    Object output = compiledPath.set(pathConfiguration.jsonProvider().parse(inputObject),
            12345,pathConfiguration);
    Integer result = parse(output).read(path);
    DocumentContext jsonContext = JsonPath.parse(output);
    System.out.println("Document Created by JsonPaths:" + jsonContext.jsonString());
    assertThat(result).isEqualTo(12345);
```

* `NOTE: when the path argument supplied is not definite, one would see things such as classcast exception`

### TransformationProvider SPI

Building on the JSON Generation support one can now perform JSON to JSON transformations using JsonPath Mappings between an existing Source and a desired Traget JSON Document. The Source JSON document could then be transformed into the Target JSON document. This feature is quiet useful in Enterprise Integrations where typically the source and destination JSON Documents tend to have varying structures for the same Object/Concept.

A new `TransformationProvider` SPI has been introduced in JsonPath to support this requirement. There can be multiple implementations of the SPI but there is a default implementation in place which uses a specific form of Mapping Definition also called `TransformationSpec`. Each new implementation of the TransformationProvider can define its own format for the TransformationSpec.

```java
    InputStream sourceStream;
    InputStream transformSpec;
    TransformationSpec spec;
    Object sourceJson;

    //assuming sourceStream, and transformSpec have been initialized
    Configuration configuration = Configuration.builder()
                .options(Option.CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH).build();
    sourceJson = configuration.jsonProvider().parse(sourceStream, Charset.defaultCharset().name());
    spec = configuration.transformationProvider().spec(transformSpec, configuration);
    Object transformed = configuration.transformationProvider().transform(sourceJson,spec, configuration);
    
    //print the transformed document
    DocumentContext jsonContext = JsonPath.parse(transformed);
    System.out.println("Document Created by Transformation:" + jsonContext.jsonString());
```

The above code when supplied with a sample Transformation Sepcification like this:

```javascript
{
  "pathMappings": [{
    "source": "$.store.book[*].price",
    "target": "$.store.novel[*].cost"
  }, {
    "source": "$.store.book[*].title",
    "target": "$.store.novel[*].bookTitle"
  }
  ]
}
```

And an Input Document like this:

```java
{
	"store": {
		"book": [{
			"category": "reference",
			"author": "Nigel Rees",
			"title": "Sayings of the Century",
			"price": 8.95
		}, {
			"category": "fiction",
			"author": "Evelyn Waugh",
			"title": "Sword of Honour",
			"price": 12.99
		}, {
			"category": "fiction",
			"author": "Herman Melville",
			"title": "Moby Dick",
			"isbn": "0-553-21311-3",
			"price": 8.99
		}, {
			"category": "fiction",
			"author": "J. R. R. Tolkien",
			"title": "The Lord of the Rings",
			"isbn": "0-395-19395-8",
			"price": 22.99
		}],
		"bicycle": {
			"color": "red",
			"price": 19.95
		}
	},
	"expensive": 10
}
```

would produce a transformed target JSON Document like this:

```javascript
{
	"store": {
		"novel": [{
			"cost": 8.95,
			"bookTitle": "Sayings of the Century"
		}, {
			"cost": 12.99,
			"bookTitle": "Sword of Honour"
		}, {
			"cost": 8.99,
			"bookTitle": "Moby Dick"
		}, {
			"cost": 22.99,
			"bookTitle": "The Lord of the Rings"
		}]
	}
}
```
* `NOTE: The Transformation Feature requires enabling the Option CREATE_MISSING_PROPERTIES_ON_DEFINITE_PATH explicitly.`
    
This Transformation feature can potentially be used to do things like summarization/aggregation on a given Source document when the Source JsonPath makes use of Filters and Functions (min, max, sum etc).

    
```javascript
{
  "pathMappings": [{
    "source": "$.store.book[*].price.max()",
    "target": "$.storeSummary.maxPrice"
  }, {
    "source": "$.store.book[*].price.sum()",
    "target": "$.storeSummary.totalCostOfBooks"
  }
  ]
}
```
* `NOTE: some of the JsonPath's aggregate functions do not work on master. However there is a Pull Request :https://github.com/json-path/JsonPath/pull/197/files that provides the fixes to make aggregate functions work. This fork also incorporates the changes from the aforementioned Pull request`

The above transformation specification can be used to produce the following summary document 

```javascript
{
  "storeSummary": {
    "maxPrice": 22.99,
    "totalCostOfBooks": 53.92
  }
}
```

The default transformation provider supports a transformation spec which allows for WildCard mappings (currently Single-Level) for array paths. It also provides a notion of LookupTables that support custom mappings of a string/enumeration value in the source document to a value in the corresponding transformed target document. The example shown earlier in this section shows the usage of wildcard (limited to "*" only, slices are not supported currently). The example below shows the use of LookupTables.

Given a Source Document like this:

```javascript
{
  "shipment": {
    "id": 123456,
    "state": "EN_ROUTE",
    "stops" : [
      {
        "name" : "source",
        "sequence" : "0",
        "location" : "-44,123"
      },
      {
        "name" : "destination",
        "sequence" : "1",
        "location" : "-98,225"
      }
    ]
  }
}
```
and a Transformation Specification like this:
```javascript
{
  "lookupTables": [{
    "tableName": "stateMapper",
    "tableData": {
      "EN_ROUTE": "ACTIVE",
      "PLANNED": "DISPATCHED",
      "COMPLETED": "COMPLETED"
    }
  }
  ],
  "pathMappings": [{
    "source": "$.shipment.id",
    "target": "$.shipment.extid"
  }, {
    "source": "$.shipment.state",
    "target": "$.shipment.status",
    "lookupTable": "stateMapper"
  }, {
    "source" : "$.shipment.stops[0].name",
    "target" : "$.shipment.loading.name"
  },{
    "source" : "$.shipment.stops[0].location",
    "target" : "$.shipment.loading.location"
  },
    {
      "source" : "$.shipment.stops[1].name",
      "target" : "$.shipment.unloading.name"
    },{
      "source" : "$.shipment.stops[1].location",
      "target" : "$.shipment.unloading.location"
    }

  ]
}
```
The Transformed Target document would look like this:

```javascript
{
  "shipment": {
    "extid": 123456,
    "status": "ACTIVE",
    "loading": {
      "name": "source",
      "location": "-44,123"
    },
    "unloading": {
      "name": "destination",
      "location": "-98,225"
    }
  }
}
```

Notice that the "state" field in the source document was mapped to a "status" field in target document and the mapping made use of a LookupTable to transform "EN_ROUTE" to "ACTIVE". The use of LookupTables is optional in the transformation specification.

#### Additional Transformations of Source Path before Mapping
The default TransformationSpec and Provider also support the notion of transformations of the Source Path before a mapping is performed to the Target Path. The transformations supported are in the form of 
* Unary operation on the source-path
* Binary operation on the source-path combined with a constant or a secondary source json-path. 
In this case the datatypes of the source-path expression and the validation rules implemented by the provider will govern what is valid for the additional transformation.

For example add two numeric source-paths to produce a value in the target document.
```
{
      "source" : "$.earliestStartTime",
      "additionalTransform" : {
        "operator" : "ADD",
        "sourcePath" : "$.plannedDriveDurationSeconds"
      },
      "target" : "$.destinationETAComputed"
}
```
In the above example 'earliestStartTime' is added to 'plannedDriveDurationSeconds' from the source document to produce 'destinationETAComputed'
in the target document.

```
{
      "source": "$.cost",
      "additionalTransform": {
        "operator" : "ADD",
        "constantSourceValue" : 100
      },
      "target": "$.totalCostWithTax"
}
```
In the above example a constant value 100 is added to 'cost' from the source document to produce 'totalCostWithTax'
in the target document.

```
{
      "source" : "$.stops[-1].estimatedArrivalTime",
      "additionalTransform" : {
        "operator" : "TO_ISO8601"
      },
      "target" : "$.destinationSTA"
}
```
In the above example a unary operator TO_ISO8601 is applied to 'estimatedArrivalTime' to convert the time to ISO8601 format and produce a field 'destinationSTA' in the target document.

It is also possible to produce a brand new value in the target document without reference to an explicit source-path in a Mapping. 
This is achieved by only having an 'additionalTransform' and 'target' elements in the Mapping and omitting the 'source' element.

For example, setting the new target path to a constant :
```
 {
   "target" : "$.needSTD",
   "additionalTransform" : {
       "constantSourceValue" : true
   }
 }
```
the above mapping spec will produce a new field - "needSTD" : true in 
the target document.

For more details refer [[testcase](https://github.com/KumarJayanti/JsonPath/blob/feature/transformation-api/json-path/src/test/java/com/jayway/jsonpath/TransformationAdvancedTest.java)].

##### Supported Set of Operators for Transformations and Extending the supported Operators
A limited set of Binary and Unary operators are currently supported by the Transformation Spec and Provider.
However extending the set of operators is simple enough. 
* A new operator (binary/unary) can be added to com.jayway.jsontransformer.spi.transformer.jsonpathtransformer.model.SourceTransform.AllowedOperation
* Validation Rules for the source and destination operands as applicable would have to implemented in com.jayway.jsontransformer.spi.transformer.jsonpathtransformer.JsonPathTransformationSpec.validate
* The actual operation when the operator is used in the transform spec should be implemented as a new case in com.jayway.jsontransformer.spi.transformer.jsonpathtransformer.JsonPathTransformationProvider.applyAddtionalTransform

The support for adding additional operators can be enhanced to a more sophisticated declarative approach. But its best to wait 
and see sufficient interest in this project to justify the additional efforts.

#### Supporting a new Transformer
The transformer is defined as a new/additional SPI in the JSONPath project.
The features documented above are of the default implementation of the SPI.
Each implementation of the SPI is expected to define its own SPEC (specification DSL) for the transformation
and implement the Provider which transforms JSON documents as per its SPEC.
JSONPath project defines SPI's for the cache, mapper and json provider. The transformer is added as a new SPI on the same lines, hence switching the provider when there are mulitple providers follows the same established pattern in JSONPath project.


[![Analytics](https://ga-beacon.appspot.com/UA-54945131-1/jsonpath/index)](https://github.com/igrigorik/ga-beacon)

