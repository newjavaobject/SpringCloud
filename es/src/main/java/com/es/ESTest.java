package com.es;

import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Administrator on 2019/5/9 0009.
 */
public class ESTest {
    public static void main(String[] args) throws UnknownHostException {
        /*client方式 -- 开始*/
//        Settings settings = Settings.builder()
//                .put("cluster.name", "cluster-name-chenzhiyu")
//                .put("client.transport.sniff", true)
//                .build();

//        TransportClient restClient =
//                TransportClient.builder()
//                .settings(settings).build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));//创建客户端

//        SearchResponse response = restClient.prepareSearch("mappingtest").get();//创建请求

//        StringBuilder json = new StringBuilder("");
//        json.append("\"name\":\"nametest\",");
//        json.append("\"age\":\"agetest\",");
//        json.append("\"id\":\"100\"}");
//

//        CreateIndexRequest indexRequest = new CreateIndexRequest();
//        indexRequest.index("index3");
//        restClient.admin().indices().create(indexRequest).actionGet();
//
//        System.out.println("新创建的索引" + indexResponse.getIndex());
//        System.out.println("索引是否存在：" + restClient.admin().indices().prepareExists("data").get().isExists());

//        System.out.println("总命中数：" + response.getHits().getTotalHits());
//        SearchHit[] hits = response.getHits().getHits();
//        for (SearchHit hit : hits) {
//            System.out.println("结果：" + hit.getSource());
//        }
        //分组统计后，按统计数量排序，，，先按description分桶（统计），再统计每个description下price的数量（统计）
        //可用于统计常用IP、统计TOP10常用应用、统计TOP10常用功能、计算常规工作时间
//        AggregationBuilder priceAggBuilder =  AggregationBuilders.terms("priceCount").field("price").order(Terms.Order.count(false));//order()：按数量排序
//        AggregationBuilder descAggBuilder =  AggregationBuilders.terms("descriptionCount").field("description").subAggregation(priceAggBuilder).order(Terms.Order.count(false));
//        SearchResponse sr = restClient.prepareSearch("mappingtest")
//                .addAggregation(descAggBuilder)
//                .execute().actionGet();
//
//        Iterator<Terms.Bucket> descriptionBuckets = ((StringTerms) sr.getAggregations().asMap().get("descriptionCount")).getBuckets().iterator();
//        while (descriptionBuckets.hasNext()) {
//            Terms.Bucket descriptionBucket = descriptionBuckets.next();
//            System.out.println("描述为【" + descriptionBucket.getKey() + "】的记录共有" + descriptionBucket.getDocCount() +"条。");
//
//            Iterator<Terms.Bucket> priceBuckets = null;
//            Aggregation priceAgg =  descriptionBucket.getAggregations().asMap().get("priceCount");
//            if (priceAgg instanceof StringTerms) {
//                priceBuckets = ((StringTerms)priceAgg).getBuckets().iterator();
//            } else if (priceAgg instanceof DoubleTerms) {
//                priceBuckets = ((DoubleTerms) priceAgg).getBuckets().iterator();
//            }
//
//            while (priceBuckets.hasNext()) {
//                Terms.Bucket priceBucket = priceBuckets.next();
//                System.out.println("   其中价格为【" +priceBucket.getKey()+"】的有" + priceBucket.getDocCount() +"条。");
//            }
//            System.out.println("------------------------------------------------");
//        }


//        restClient.close();//释放连接
        /*client方式 -- 结束*/





        /*RestTemplate方式 -- 开始*/
        /*RestTemplate template = new RestTemplate();
        String response = template.getForObject("http://127.0.0.1:9200/data/test/_search", String.class);
        System.out.println("结果：" + response);*/
        /*RestTemplate方式 -- 结束*/





        /* httpClient方式 -- 开始 */
        /*保存单条数据，参数为：index、type、id、数据，id可传空或自定义，若传空则es会自动为该条数据生成一个id*/
//        ESHttpClient.saveESData("data", "test","3", "{\"name\":\"名字\",\"age\":\"18\",\"id\":\"2\"}");
        /*批量保存多条数据， 注意参数中的\n必须添加的！*/
        ESHttpClient.bulkOperESData("{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"4\"}}\n{\"name\":\"名字4\",\"age\":\"19\",\"id\":\"4\"}\n" +
                "{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"5\"}}\n{\"name\":\"名字5\",\"age\":\"20\",\"id\":\"5\"}\n" +
                "{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"6\"}}\n{\"name\":\"名字6\",\"age\":\"21\",\"id\":\"6\"}\n");

        /*查询单条数据，参数分别为：index、type、id*/
//        String result = ESHttpClient.getESResult("data","test","6");
        /*根据条件查询多条*/
//        String result = ESHttpClient.getESResult("data","test","{\"from\":\"0\",\"size\":\"10\"}"); //from、size用于分页查询，size传0则不会返回记录只有总数
//        String result = ESHttpClient.getESResult("data","test","{\"from\":\"0\",\"size\":\"10\",\"sort\":[{\"age\":\"desc\"}]}"); //排序
//        String result = ESHttpClient.getESResult("data","test","{\"query\":{\"term\":{\"age\":\"20\"}}}");//term：表示精确查询
//        System.out.println(result);
        /* httpClient方式 -- 结束 */
    }


}
