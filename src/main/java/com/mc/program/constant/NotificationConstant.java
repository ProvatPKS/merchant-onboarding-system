package com.mc.program.constant;

public class NotificationConstant {

    public static String SENDER = "subhojit31071989@gmail.com";

    // The subject line for the email.
    public static String SUBJECT_SUCCESS = "merchant registration successfully completed";

    // The subject line for the email.
    public static String SUBJECT_FAILED = "merchant registration failed";

    // The email body for recipients with non-HTML email clients.
    public static String BODY_TEXT_SUCCESS = "Hi %s,\n\n" +
            "Welcome to Merchant Created System.\n" +
            "Login using below credential,\n" +
            "UserId : %s\n" +
            "Password : %s\n" +
            "\n\n" +
            "Regards,\n" +
            "Admin";

    // The email body for recipients with non-HTML email clients.
    public static String BODY_TEXT_FAILED = "Hi %s,\n\n" +
            "Merchant onboarding has failed due to below reason.\n" +
            "%s\n" +
            "Please try again after sometime.\n" +
            "\n\n" +
            "Regards,\n" +
            "Admin";


}
