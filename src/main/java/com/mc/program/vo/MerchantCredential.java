package com.mc.program.vo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.StringJoiner;

@DynamoDBTable(tableName = "PLACEHOLDER_MERCHANT_CREDENTIAL_TABLE_NAME")
public class MerchantCredential {


    @DynamoDBHashKey(attributeName = "merchantId")
    private Long merchantId;

    @DynamoDBAttribute(attributeName = "userId")
    private String userId;

    @DynamoDBAttribute(attributeName = "pwd")
    private String pwd;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MerchantCredential.class.getSimpleName() + "[", "]")
                .add("merchantId='" + merchantId + "'")
                .add("userId='" + userId + "'")
                .add("pwd='" + pwd + "'")
                .toString();
    }
}
