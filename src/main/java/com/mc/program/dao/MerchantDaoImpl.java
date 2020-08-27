package com.mc.program.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;
import com.mc.program.vo.ResponseEnum;
import com.mc.program.utility.DynamoDBAdapter;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class MerchantDaoImpl implements MerchantDao {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void saveMerchant(final Merchant merchant) {
        DynamoDBAdapter.getMapper("MERCHANTS_TABLE_NAME").save(merchant);
    }

    @Override
    public Merchant getMerchantInOrgByName(final String merchantName, final String organizationName) throws MosException {

        Merchant merchant = null;

        try{
            HashMap<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
            attributeValues.put(":var1", new AttributeValue().withS(merchantName));
            attributeValues.put(":var2", new AttributeValue().withS(organizationName));

            DynamoDBScanExpression queryExp = new DynamoDBScanExpression()
                    .withFilterExpression("merchantName = :var1 and organizationName = :var2")
                    .withExpressionAttributeValues(attributeValues);

            PaginatedScanList<Merchant> result = DynamoDBAdapter.getMapper("MERCHANTS_TABLE_NAME").scan(Merchant.class, queryExp);
            if (result.size() > 0) {
                merchant = result.stream()
                        .findFirst()
                        .get();
                logger.info("MerchantDaoImpl - getMerchantInOrgByName(): merchant - " + merchant.toString());
            } else {
                logger.info("MerchantDaoImpl - getMerchantInOrgByName(): merchant - Not Found.");
            }
        }catch (Exception ex){
            throw new MosException("Exception occurred while fetching merchant data ", ResponseEnum.DATABASE_EXCEPTION);
        }
        return merchant;
    }

    @Override
    public void saveMerchantCredentials(MerchantCredential merchantCredential) {
        DynamoDBAdapter.getMapper("MERCHANT_CREDENTIAL_TABLE_NAME").save(merchantCredential);
    }

    @Override
    public Merchant getMerchantById(final String merchantId) throws MosException {
        Merchant merchant = null;

        try {
            HashMap<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
            attributeValues.put(":var1", new AttributeValue().withN(merchantId));

            DynamoDBScanExpression queryExp = new DynamoDBScanExpression()
                    .withFilterExpression("merchantId = :var1 ")
                    .withExpressionAttributeValues(attributeValues);

            PaginatedScanList<Merchant> result = DynamoDBAdapter.getMapper("MERCHANTS_TABLE_NAME").scan(Merchant.class, queryExp);
            if (result.size() > 0) {
                merchant = result.stream()
                        .findFirst()
                        .get();
                logger.info("MerchantDaoImpl - getMerchantById(): merchant - " + merchant.toString());
            } else {
                throw new MosException(merchant.getMerchantId() + "is is not exists ", ResponseEnum.MERCHANT_NOT_FOUND);
            }
        }catch (Exception ex){
            throw new MosException("Exception occurred while fetching merchant data ", ResponseEnum.DATABASE_EXCEPTION);
        }
        return merchant;
    }

    @Override
    public List<Merchant> getMerchants() throws MosException {

        List<Merchant> results = null;
        try{
            DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
            results = DynamoDBAdapter.getMapper("MERCHANTS_TABLE_NAME").scan(Merchant.class, scanExp);

            if(results != null && results.isEmpty()){
                throw new MosException("No merchant found.", ResponseEnum.MERCHANT_NOT_FOUND);
            }
        }catch (Exception ex){
            throw new MosException("Exception occurred while fetching merchant data ", ResponseEnum.DATABASE_EXCEPTION);
        }

        return results;
    }

    @Override
    public Merchant getMerchantByFileKey(String fileKey) throws MosException {
        Merchant merchant = null;

        try {
            HashMap<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
            attributeValues.put(":var1", new AttributeValue().withS(fileKey));

            DynamoDBScanExpression queryExp = new DynamoDBScanExpression()
                    .withFilterExpression("fileKey = :var1 ")
                    .withExpressionAttributeValues(attributeValues);

            PaginatedScanList<Merchant> result = DynamoDBAdapter.getMapper("MERCHANTS_TABLE_NAME").scan(Merchant.class, queryExp);
            if (result.size() > 0) {
                merchant = result.stream()
                        .findFirst()
                        .get();
                logger.info("MerchantDaoImpl - getMerchantByFileKey(): merchant - " + merchant.toString());
            }
        }catch (Exception ex){
            throw new MosException("Exception occurred while fetching merchant data ", ResponseEnum.DATABASE_EXCEPTION);
        }
        return merchant;
    }


}
