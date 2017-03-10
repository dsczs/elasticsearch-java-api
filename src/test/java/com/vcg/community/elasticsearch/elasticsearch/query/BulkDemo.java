package com.vcg.community.elasticsearch.elasticsearch.query;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.model.px500.Photo;
import org.apache.commons.lang.math.RandomUtils;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/28
 */
public class BulkDemo extends ElasticDemo {

    @Test
    public void test(){
        SearchQuery searchQuery = new NativeSearchQuery(
                QueryBuilders.matchQuery("id","81fc3be9e85d4f5b8d4a3910b96dad21")
        );
      List<Photo> photoList =   elasticsearchTemplate.queryForList(searchQuery,Photo.class);
      List<Photo> photos = new ArrayList<>();
      for (Photo photo : photoList){
          photo.setDescription("香蕉水"+ RandomUtils.nextInt());
          photos.add(photo);
      }

        List<UpdateQuery> queries = new ArrayList<>();
        for (Photo photo : photos){
            UpdateQuery updateQuery = new UpdateQuery();
            updateQuery.setClazz(Photo.class);
            updateQuery.setId(photo.getId());
            updateQuery.setDoUpsert(true);
            updateQuery.setIndexName("index_v1");
            updateQuery.setType("resource");
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.doc(JSON.toJSONString(photo));
            updateRequest.id(photo.getId());
            updateRequest.index("index_v1");
            updateRequest.type("resource");
            updateQuery.setUpdateRequest(updateRequest);
            queries.add(updateQuery);
        }
        elasticsearchTemplate.bulkUpdate(queries);
    }

    @Test
    public void testBulkIndex(){
        List<IndexQuery> indexQueries = new ArrayList<>();
        IndexQuery indexQuery1 = new IndexQuery();
        Photo photo1 = new Photo();
        photo1.setId("000001");
        photo1.setDescription("photo000001");
        indexQuery1.setId("000001");
        indexQuery1.setObject(photo1);
        indexQueries.add(indexQuery1);

        IndexQuery indexQuery2 = new IndexQuery();
        Photo photo2 = new Photo();
        photo2.setId("000002");
        photo2.setDescription("photo000002");
        indexQuery2.setId("000002");
        indexQuery2.setObject(photo2);
        indexQueries.add(indexQuery2);

        elasticsearchTemplate.bulkIndex(indexQueries);

    }

    @Test
    public void testDelete(){
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(QueryBuilders.termQuery("id","000001"));
        elasticsearchTemplate.delete(deleteQuery,Photo.class);
    }

    @Test
    public void testFilter(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withFilter(FilterBuilders.boolFilter().must(FilterBuilders.termFilter("id","000002")))
                .build();
        List<Photo> photos =  elasticsearchTemplate.queryForList(searchQuery,Photo.class);
        System.out.println("photos:"+photos);
    }


}
