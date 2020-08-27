package com.mc.program.service;

import com.mc.program.dao.MerchantDao;
import com.mc.program.dao.MerchantDaoImpl;
import com.mc.program.exception.MosException;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.ResponseEnum;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MerchantValidatorImpl implements MerchantValidator {

    private final Logger logger = Logger.getLogger(this.getClass());

    MerchantDao merchantDao;
    public MerchantValidatorImpl() {
        merchantDao = new MerchantDaoImpl();
    }

    @Override
    public void validateMerchant(final Merchant merchant) throws MosException {
        isRequestValid(merchant);
        isDuplicateMerchant(merchant);
    }


    private void isDuplicateMerchant(final Merchant merchant) throws MosException {
        Merchant existingMerchant = merchantDao.getMerchantInOrgByName(merchant.getMerchantName(), merchant.getOrganizationName());
        if(existingMerchant != null ){
            throw new MosException(merchant.getMerchantName() + "is already exists under organization " + merchant.getOrganizationName(), ResponseEnum.DUPLICATE_MERCHANT_NAME);
        }
    }

    private void isRequestValid(final Merchant merchant) throws MosException {
        if(merchant.getMerchantName() == null || merchant.getMerchantName().isEmpty()){
            throw new MosException("Merchant Name should not be blank", ResponseEnum.ORGANIZATION_NAME_BLANK);
        }

        if(merchant.getOrganizationName() == null || merchant.getOrganizationName().isEmpty()){
            throw new MosException("Organization should Should not be blank", ResponseEnum.MERCHANT_NAME_BLANK);
        }

        if(merchant.getLegalName() == null || merchant.getLegalName().isEmpty()){
            throw new MosException("Legal Name should not be blank", ResponseEnum.LEGAL_NAME_BLANK);
        }

        if(merchant.getState() == null || merchant.getState().isEmpty()){
            throw new MosException("State Name should not be blank", ResponseEnum.STATE_NAME_BLANK);
        }

        if(merchant.getCountry() == null || merchant.getCountry().isEmpty()){
            throw new MosException("Country Name should not be blank", ResponseEnum.COUNTRY_NAME_BLANK);
        }

        if(merchant.getCity() == null || merchant.getCity().isEmpty()){
            throw new MosException("City Name should not be blank", ResponseEnum.CITY_NAME_BLANK);
        }

        if(merchant.getStreetAddress() == null || merchant.getStreetAddress().isEmpty()){
            throw new MosException("Street Address should not be blank", ResponseEnum.STREET_ADDRESS_BLANK);
        }

        if(merchant.getEmailId() == null || merchant.getEmailId().isEmpty()){
            throw new MosException("Email Address should not be blank", ResponseEnum.EMAIL_ADDRESS_BLANK);
        }
        if(!(emailValidator(merchant.getEmailId()))){
            throw new MosException(merchant.getEmailId() +" is invalid Email Address", ResponseEnum.EMAIL_ADDRESS_INVALID);
        }

    }

    private boolean emailValidator(String email){
        final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        // static Pattern object, since pattern is fixed
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        // non-static Matcher object because it's created from the input String
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
