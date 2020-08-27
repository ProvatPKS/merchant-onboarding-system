package com.mc.program.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.Response;
import com.mc.program.vo.ResponseEnum;
import com.mc.program.service.MerchantService;
import com.mc.program.service.MerchantServiceImpl;
import com.mc.program.utility.ResponseBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class GetAllMerchantsHandler implements RequestHandler<Map<String, Object>, ResponseBuilder> {

	MerchantService merchantService;

	public GetAllMerchantsHandler(){
		merchantService = new MerchantServiceImpl();
	}

	@Override
	public ResponseBuilder handleRequest(Map<String, Object> input, Context context) {

		Response response = new Response();
		List<Merchant> merchants = null;

		try {
			// get all merchants
			merchants = merchantService.getMerchants();

		} catch (MosException ex) {
			response.setStatus("Failed");
			response.setResponseCode(ex.getErrorCode());
			response.setResponseDesc(ResponseEnum.fromValue(ex.getErrorCode()));
			response.setResponseMessage(ex.getMessage());
		}catch (Exception ex) {
			response.setStatus("Failed");
			response.setResponseCode(ResponseEnum.GENERIC_ERROR.getCode());
			response.setResponseDesc(ResponseEnum.GENERIC_ERROR.getDesc());
			response.setResponseMessage(ex.getMessage());
		}

		// send the response back
		if (merchants != null && !merchants.isEmpty()){
			return ResponseBuilder.builder()
					.setStatusCode(200)
					.setObjectBody(merchants)
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
