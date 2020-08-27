package com.mc.program.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mc.program.constant.CommonConstant;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.Response;
import com.mc.program.vo.ResponseEnum;
import com.mc.program.service.MerchantService;
import com.mc.program.service.MerchantServiceImpl;
import com.mc.program.utility.ResponseBuilder;

import java.util.Collections;
import java.util.Map;


public class GetMerchantHandler implements RequestHandler<Map<String, Object>, ResponseBuilder> {

	MerchantService merchantService;

	public GetMerchantHandler(){
		merchantService = new MerchantServiceImpl();
	}

	@Override
	public ResponseBuilder handleRequest(Map<String, Object> input, Context context) {

		Response response = new Response();
		Merchant merchant = null;

		try {
			// get the 'pathParameters' from input
			Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
			String merchantId = pathParameters.get("id");

			// get the merchant by id
			merchant = merchantService.getMerchant(merchantId);

			response.setStatus(CommonConstant.SUCCESS);
			response.setResponseCode(ResponseEnum.MERCHANT_CREATED.getCode());
			response.setResponseDesc(ResponseEnum.MERCHANT_CREATED.getDesc());
			response.setResponseMessage(ResponseEnum.MERCHANT_CREATED.getDesc());
		} catch (MosException ex) {
			response.setStatus(CommonConstant.FAILED);
			response.setResponseCode(ex.getErrorCode());
			response.setResponseDesc(ResponseEnum.fromValue(ex.getErrorCode()));
			response.setResponseMessage(ex.getMessage());
		}catch (Exception ex) {
			response.setStatus(CommonConstant.FAILED);
			response.setResponseCode(ResponseEnum.GENERIC_ERROR.getCode());
			response.setResponseDesc(ResponseEnum.GENERIC_ERROR.getDesc());
			response.setResponseMessage(ex.getMessage());
		}

		// send the response back
		if (merchant != null){
			return ResponseBuilder.builder()
					.setStatusCode(200)
					.setObjectBody(merchant)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
					.build();
		}else{
			return ResponseBuilder.builder()
					.setStatusCode(200)
					.setObjectBody(response)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
					.build();
		}

	}
}
