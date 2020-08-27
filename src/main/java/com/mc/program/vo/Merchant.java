package com.mc.program.vo;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Date;
import java.util.StringJoiner;

@DynamoDBTable(tableName = "PLACEHOLDER_MERCHANTS_TABLE_NAME")
public class Merchant {


    @DynamoDBHashKey(attributeName = "merchantId")
    private Long merchantId;

    @DynamoDBRangeKey(attributeName = "merchantName")
    private String merchantName;

    @DynamoDBAttribute(attributeName = "organizationName")
    private String organizationName;

    @DynamoDBAttribute(attributeName = "merchantShortName")
    private String merchantShortName;

    @DynamoDBAttribute(attributeName = "legalName")
    private String legalName;

    @DynamoDBAttribute(attributeName = "state")
    private String state;

    @DynamoDBAttribute(attributeName = "country")
    private String country;

    @DynamoDBAttribute(attributeName = "city")
    private String city;

    @DynamoDBAttribute(attributeName = "streetAddress")
    private String streetAddress;

    @DynamoDBAttribute(attributeName = "streetAddress2")
    private String streetAddress2;

    @DynamoDBAttribute(attributeName = "postalCode")
    private long postalCode;

    @DynamoDBAttribute(attributeName = "emailId")
    private String emailId;

    @DynamoDBAttribute(attributeName = "telephoneNumber")
    private String telephoneNumber;

    @DynamoDBAttribute(attributeName = "fax")
    private String fax;

    @DynamoDBAttribute(attributeName = "merchantBusinessType")
    private String merchantBusinessType;

    @DynamoDBAttribute(attributeName = "maxTransactionAmount")
    private long maxTransactionAmount;

    @DynamoDBAttribute(attributeName = "merchantStatus")
    private boolean merchantStatus;

    @DynamoDBAttribute(attributeName = "activationDate")
    private Date activationDate;

    @DynamoDBAttribute(attributeName = "deactivationDate")
    private Date deactivationDate;

    @DynamoDBAttribute(attributeName = "website")
    private String website;

    @DynamoDBAttribute(attributeName = "createDate")
    private Date createDate;

    @DynamoDBAttribute(attributeName = "updateDate")
    private Date updateDate;

    @DynamoDBAttribute(attributeName = "createdBy")
    private String createdBy;

    @DynamoDBAttribute(attributeName = "updateBy")
    private String updateBy;


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getMerchantShortName() {
        return merchantShortName;
    }

    public void setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(long postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMerchantBusinessType() {
        return merchantBusinessType;
    }

    public void setMerchantBusinessType(String merchantBusinessType) {
        this.merchantBusinessType = merchantBusinessType;
    }

    public long getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    public void setMaxTransactionAmount(long maxTransactionAmount) {
        this.maxTransactionAmount = maxTransactionAmount;
    }

    public boolean isMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(boolean merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Merchant.class.getSimpleName() + "[", "]")
                .add("merchantId=" + merchantId)
                .add("merchantName='" + merchantName + "'")
                .add("organizationName='" + organizationName + "'")
                .add("merchantShortName='" + merchantShortName + "'")
                .add("legalName='" + legalName + "'")
                .add("state='" + state + "'")
                .add("country='" + country + "'")
                .add("city='" + city + "'")
                .add("streetAddress='" + streetAddress + "'")
                .add("streetAddress2='" + streetAddress2 + "'")
                .add("postalCode=" + postalCode)
                .add("emailId='" + emailId + "'")
                .add("telephoneNumber='" + telephoneNumber + "'")
                .add("fax='" + fax + "'")
                .add("merchantBusinessType='" + merchantBusinessType + "'")
                .add("maxTransactionAmount=" + maxTransactionAmount)
                .add("merchantStatus=" + merchantStatus)
                .add("activationDate=" + activationDate)
                .add("deactivationDate=" + deactivationDate)
                .add("website='" + website + "'")
                .add("createDate=" + createDate)
                .add("updateDate=" + updateDate)
                .add("createdBy='" + createdBy + "'")
                .add("updateBy='" + updateBy + "'")
                .toString();
    }
}
