package org.visualchina.elasticsearch.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-10
 */
public final class ResponseUtil {

        public static String parse(SearchResponse searchResponse){
            if (searchResponse == null){
                return null;
            }

            SearchHit[] searchHits = searchResponse.getHits().getHits();
            if (searchHits == null || searchHits.length  < 1){
                return null;
            }
            JSONArray result = new JSONArray();
            for (SearchHit searchHit : searchHits){
                result.add(JSONObject.parseObject(JSON.toJSONString(searchHit.getSource())));
            }
            return result.toJSONString();
        }

}
