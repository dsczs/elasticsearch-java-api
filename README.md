

## 当前版本

 [版本](https://github.com/elasticsearch-cn/elasticsearch-definitive-guide) Elasticsearch 5.5.0

## 说明

    关于elasticsearch自身提供的client,elasticsearchTemplate,restClient相关的java-api例子

## 例子,进行一个match_all查询

###  使用client

~~~java
   QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
          SearchResponse searchResponse = client.prepareSearch()
                  .setIndices("indexName")
                  .setTypes("typeName")
                  .setQuery(queryBuilder)
                  .execute().actionGet();
~~~

### 使用elasticsearchTemplate

~~~java
 QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery  = new NativeSearchQuery(
                QueryBuilders.matchAllQuery()
        );
        elasticsearchTemplate.queryForList(searchQuery, XXXXX.class);
~~~

###  使用 RestClient

~~~java
 String method = "POST";
         String endpoint = "/index/type/_search";
         HttpEntity entity = new NStringEntity("{\n" +
                 "  \"query\": {\n" +
                 "    \"match_all\": {}\n" +
                 "  }\n" +
                 "}", ContentType.APPLICATION_JSON);

         Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
         System.out.println(EntityUtils.toString(response.getEntity()));
~~~

## 下载

    git clone git@github.com:felayman/elasticsearch-java-api.git


## 参考

    - [spring-data-elasticsearch](https://github.com/spring-projects/spring-data-elasticsearch)
    - [Java REST Client [5.5]](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/_example_requests.html)



