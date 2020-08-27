package com.mc.program.utility;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import com.amazonaws.regions.Regions;

public class DynamoDBAdapter {

    private static DynamoDBAdapter db_adapter = null;
    private final AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    private DynamoDBAdapter() {
        // create the client
        this.client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .build();
    }

    public static DynamoDBAdapter getInstance() {
        if (db_adapter == null)
          db_adapter = new DynamoDBAdapter();

        return db_adapter;
    }

    public AmazonDynamoDB getDbClient() {
        return this.client;
    }

    public DynamoDBMapper createDbMapper(DynamoDBMapperConfig mapperConfig) {
        // create the validator with the validator config
        if (this.client != null)
            mapper = new DynamoDBMapper(this.client, mapperConfig);

        return this.mapper;
    }

    public static DynamoDBMapper getMapper(String key){

        // build the valiator config
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(System.getenv(key)))
                .build();

        // get the db adapter
        DynamoDBAdapter db_adapter = DynamoDBAdapter.getInstance();
        // create the valiator with config
        DynamoDBMapper mapper = db_adapter.createDbMapper(mapperConfig);

        return mapper;
    }

}
