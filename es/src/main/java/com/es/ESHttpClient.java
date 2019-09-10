package com.es;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/9 0009.
 */
public final class ESHttpClient {
    private static Logger LOOGER = Logger.getLogger(ESHttpClient.class);
    private static CloseableHttpClient httpClient = null;//HttpClients.createDefault();
    static {
        PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
        poolManager.setMaxTotal(500);
        poolManager.setDefaultMaxPerRoute(10);//最高10并发

        httpClient = HttpClients.custom()
                .setConnectionManager(poolManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(RequestConfig.custom().setStaleConnectionCheckEnabled(true).build())
                .build();
    }

    static Pattern pattern = Pattern.compile("^\\{.*\\}$");
    public static String getESResult(String index, String type,String param){
        boolean sigle = pattern.matcher(param).matches();//若是json格式或为空则是查询列表， 若是id则是查询单条
        HttpHost target = new HttpHost("127.0.0.1", 9200);
        HttpRequest request = null;//单条则把id加上

        if (sigle) {//多条则将查询条件放入entity
            request = new HttpPost("http://127.0.0.1:9200/"+index+"/"+type+"/_search");
            HttpEntity entity = new StringEntity(param, "UTF-8");
            ((HttpPost)request).setEntity(entity);
        }else {//查询单条则把id放入url中
            request = new HttpGet("http://127.0.0.1:9200/"+index+"/"+type+"/"+param);
        }
        try {
           return httpClient.execute(target, request, new BasicResponseHandler());//execute中已释放连接
        } catch (IOException e) {
            LOOGER.error("查询es出错：", e);
            return "";
        }
    }

    public static void main(String[] args) {
        saveESData("datas", "tests","", "");//存单条数据
//        bulkOperESData("{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"4\"}}\n{\"name\":\"名字4\",\"age\":\"19\",\"id\":\"4\"}\n" +
//                "{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"5\"}}\n{\"name\":\"名字5\",\"age\":\"20\",\"id\":\"5\"}\n" +
//                "{\"create\":{\"_index\":\"data\",\"_type\":\"test\",\"_id\":\"6\"}}\n{\"name\":\"名字6\",\"age\":\"21\",\"id\":\"6\"}\n");//批量存多条数据
    }

    public static String saveESData(String index, String type,String id, String data){
        HttpHost target = new HttpHost("127.0.0.1", 9200);
        HttpEntity entity = new StringEntity(data, "UTF-8");

        HttpPost post = new HttpPost("http://127.0.0.1:9200/"+index+"/"+type+"/"+(StringUtils.isEmpty(id)?"":id));
        post.setEntity(entity);
        try {
            return httpClient.execute(target, post, new BasicResponseHandler());//execute中已释放连接
        } catch (IOException e) {
            LOOGER.error("保存数据出错：", e);
            return "";
        }
    }

    /**
     * 批量操作数据：即一次请求就可以同时进行增/删/改/查操作
     * @param data
     * @return
     */
    public static String bulkOperESData(String data){
        HttpHost target = new HttpHost("127.0.0.1", 9200);
        HttpEntity entity = new StringEntity(data, "UTF-8");

        HttpPost post = new HttpPost("http://127.0.0.1:9200/_bulk");
        post.setEntity(entity);
        try {
            return httpClient.execute(target, post, new BasicResponseHandler());//execute中已释放连接
        } catch (IOException e) {
            LOOGER.error("查询es出错：", e);
            return "";
        }
    }
}
