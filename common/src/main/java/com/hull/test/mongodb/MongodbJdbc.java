package com.hull.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author
 * @create 2018-10-27 下午4:09
 **/

public class MongodbJdbc {

    public static void main(String[] args) {

        //连接 mongodb
        MongoDatabase database = connectDb();
//        connectDbWithPwd();

        //查询
        MongoCollection<Document> collection = database.getCollection("student");

        //根据条件删除
        DeleteResult deleteResult = collection.deleteMany(Filters.gte("age", 18));

        FindIterable result = collection.find();
        MongoCursor cursor = result.iterator();
        while (cursor.hasNext()){
            Document document = (Document) cursor.next();
            System.out.println(document.toJson());
        }


        MongoCollection<Document> teachers = database.getCollection("teacher");
        if(teachers==null){
            //建表
            database.createCollection("teacher");
            System.out.println("创建表成功");
        }

        //按条件查找
        FindIterable missCang = teachers.find(Filters.eq("name","仓老师"));
        if(missCang.iterator().hasNext()){
            //更新
            teachers.updateMany(Filters.eq("name", "仓老师"), new Document("$set",new Document("age",20)));
        }else{
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("id", "101").
                    append("name", "仓老师").
                    append("age", 18);
            Document document2 = new Document("id", "102").
                    append("name", "小泽老师").
                    append("age", 19);
            List<Document> documents = new ArrayList<>();
            documents.add(document);
            documents.add(document2);
            teachers.insertMany(documents);
            System.out.println("文档插入成功");
        }



    }

    /**
     * 没有密码的连接
     */
    private static MongoDatabase connectDb() {
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase db = mongoClient.getDatabase("mydb");

            System.out.println("Connect to database successfully");
            return db;
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }


    /**
     * 有密码的链接
     */
    private static void connectDbWithPwd() {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress("localhost",27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs,credentials);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

}
