package com.mc.program.service;

import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;

public interface MerchantEmailService {

    void sendEmail(final boolean status, final Merchant merchant, final MerchantCredential merchantCredential, final String errorMsg);

}
