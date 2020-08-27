package com.mc.program.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.program.constant.CommonConstant;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;
import com.mc.program.vo.Response;
import com.mc.program.vo.ResponseEnum;
import com.mc.program.service.*;
import com.mc.program.utility.ResponseBuilder;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class SubmitMerchantHandler implements RequestHandler<Map<String, Object>, ResponseBuilder> {

    private Logger logger = Logger.getLogger(this.getClass());

    MerchantService merchantService;
    MerchantValidator merchantValidator;
    MerchantEmailService merchantEmailService;

    public SubmitMerchantHandler(){
        merchantService = new MerchantServiceImpl();
        merchantValidator = new MerchantValidatorImpl();
        merchantEmailService = new MerchantEmailServiceImpl();
    }

    @Override
    public ResponseBuilder handleRequest(Map<String, Object> input, Context context) {

        Response response = new Response();
        Merchant merchant = null;
        MerchantCredential merchantCredential = null;

        try {
            merchant = new ObjectMapper().readValue((String) input.get(CommonConstant.BODY), Merchant.class);

            merchantValidator.validateMerchant(merchant);

            merchantService.saveMerchant(merchant);

            merchantCredential = merchantService.generateMerchantCredentials(merchant);

            merchantEmailService.sendEmail(true, merchant, merchantCredential, null);

            response.setStatus(CommonConstant.SUCCESS);
            response.setResponseCode(ResponseEnum.MERCHANT_CREATED.getCode());
            response.setResponseDesc(ResponseEnum.MERCHANT_CREATED.getDesc());
            response.setResponseMessage(ResponseEnum.MERCHANT_CREATED.getDesc());
        } catch (MosException ex) {
            response.setStatus(CommonConstant.FAILED);
            response.setResponseCode(ex.getErrorCode());
            response.setResponseDesc(ResponseEnum.fromValue(ex.getErrorCode()));
            response.setResponseMessage(ex.getMessage());

        }catch (Exception ex){
            response.setStatus(CommonConstant.FAILED);
            response.setResponseCode(ResponseEnum.GENERIC_ERROR.getCode());
            response.setResponseDesc(ResponseEnum.GENERIC_ERROR.getDesc());
            response.setResponseMessage(ex.getMessage());
        }

        logger.debug("Building response for merchant");
        // send the response back
        return ResponseBuilder.builder()
                .setStatusCode(200)
                .setObjectBody(response)
                .setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
                .build();
    }

}