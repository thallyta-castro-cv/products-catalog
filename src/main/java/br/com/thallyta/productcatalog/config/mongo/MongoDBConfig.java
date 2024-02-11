package br.com.thallyta.productcatalog.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoDatabaseFactory mongoDbFactory(){
        return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/product-catalog");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
    }
}
