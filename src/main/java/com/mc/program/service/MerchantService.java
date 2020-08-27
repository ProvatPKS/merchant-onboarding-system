package com.mc.program.service;

import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;

import java.util.List;

public interface MerchantService {

    void saveMerchant(final Merchant merchant);

    MerchantCredential generateMerchantCredentials(final Merchant merchant);

    Merchant getMerchant(final String merchantId) throws MosException;

    List<Merchant> getMerchants() throws MosException;

}
