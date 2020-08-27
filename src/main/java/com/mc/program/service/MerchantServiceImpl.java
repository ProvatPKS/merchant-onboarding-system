package com.mc.program.service;

import com.mc.program.dao.MerchantDao;
import com.mc.program.dao.MerchantDaoImpl;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;
import com.mc.program.utility.CommonsUtility;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MerchantServiceImpl implements MerchantService {

    MerchantDao merchantDao;
    private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis() / 1000);

    public MerchantServiceImpl(){
        merchantDao = new MerchantDaoImpl();
    }

    public static long getNext() {
        return sequence.incrementAndGet();
    }

    @Override
    public void saveMerchant(Merchant merchant) {

        merchant.setMerchantId(getNext());
        merchant.setCreateDate(new Date());
        merchant.setActivationDate(new Date());

        merchantDao.saveMerchant(merchant);
    }

    @Override
    public MerchantCredential generateMerchantCredentials(Merchant merchant) {

        MerchantCredential merchantCredential = new MerchantCredential();
        merchantCredential.setMerchantId(merchant.getMerchantId());
        merchantCredential.setUserId(CommonsUtility.generateUserIdGeneration(merchant));
        merchantCredential.setPwd(CommonsUtility.generatePassword(8).toString());

        merchantDao.saveMerchantCredentials(merchantCredential);

        return merchantCredential;

    }

    @Override
    public Merchant getMerchant(final String merchantId) throws MosException {
        return merchantDao.getMerchantById(merchantId);
    }

    @Override
    public List<Merchant> getMerchants() throws MosException {
        return merchantDao.getMerchants();
    }
}
