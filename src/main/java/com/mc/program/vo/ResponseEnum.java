package com.mc.program.vo;

import java.util.StringJoiner;

public enum ResponseEnum {

    MERCHANT_CREATED("MOS000", "Merchant is created successfully."),
    GENERIC_ERROR("MOS500", "Merchant is failed to onboard."),
    DUPLICATE_MERCHANT_NAME("MOS501", "Merchant already exists in our system."),
    MERCHANT_NOT_FOUND("MOS502", "Merchant is not exists in our system."),

    MERCHANT_NAME_BLANK("MOS506", "Blank Merchant Name."),
    ORGANIZATION_NAME_BLANK("MOS507", "Blank Organization Name."),
    LEGAL_NAME_BLANK("MOS508", "Blank Legal Name."),
    STATE_NAME_BLANK("MOS509", "Blank State Name."),
    COUNTRY_NAME_BLANK("MOS510", "Blank Country Name."),
    CITY_NAME_BLANK("MOS511", "Blank City Name."),
    STREET_ADDRESS_BLANK("MOS512", "Blank Street Address."),
    EMAIL_ADDRESS_BLANK("MOS513", "Blank Email Address."),
    FILE_KEY_BLANK("MOS514", "Blank File Key."),
    EMAIL_ADDRESS_INVALID("MOS515", "Blank Email Address."),

    DATABASE_EXCEPTION("MOS516", "Generic database exception")
    ;


    private String code;
    private String desc;

    ResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    //From String method will return you the Enum for the provided input string
    public static String fromValue(String parameterName) {
        if (parameterName != null) {
            for (ResponseEnum objType : ResponseEnum.values()) {
                if (parameterName.equalsIgnoreCase(objType.getCode())) {
                    return objType.getDesc();
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseEnum.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("desc='" + desc + "'")
                .toString();
    }
}
