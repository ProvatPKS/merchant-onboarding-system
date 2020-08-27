package com.mc.program.dao;

import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;

import java.util.List;

public interface MerchantDao {

    void saveMerchant(final Merchant merchant);

    Merchant getMerchantInOrgByName(final String merchantName, final String organizationName) throws MosException;

    void saveMerchantCredentials(final MerchantCredential merchantCredential);

    Merchant getMerchantById(final String merchantId) throws MosException;

    List<Merchant> getMerchants() throws MosException;

    Merchant getMerchantByFileKey(final String kycId) throws MosException;

}