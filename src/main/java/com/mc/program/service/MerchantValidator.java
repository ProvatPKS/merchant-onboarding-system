package com.mc.program.service;

import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;

public interface MerchantValidator {

    void validateMerchant(final Merchant merchant) throws MosException;
}
