package com.mc.program.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.mc.program.constant.NotificationConstant;
import com.mc.program.vo.Merchant;
import com.mc.program.vo.MerchantCredential;
import org.apache.log4j.Logger;

public class MerchantEmailServiceImpl implements MerchantEmailService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void sendEmail(final boolean status, final Merchant merchant, final MerchantCredential merchantCredential, final String errorMsg) {
        try {

            // Try to send the email.
            try {
                logger.info("Attempting to send an merchant creation email through Amazon SES.");

                String bodyText = "";
                if(status){
                    bodyText = String.format(NotificationConstant.BODY_TEXT_SUCCESS, merchant.getLegalName(), merchantCredential.getUserId(), merchantCredential.getPwd());
                }else{
                    bodyText = String.format(NotificationConstant.BODY_TEXT_FAILED, merchant.getLegalName(), errorMsg);
                }

                String subject = "";
                if(status){
                    subject = NotificationConstant.SUBJECT_SUCCESS;
                }else{
                    subject = NotificationConstant.SUBJECT_FAILED;
                }

                // Instantiate an Amazon SES client, which will make the service
                // call with the supplied AWS credentials.
                AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                        .withRegion(Regions.US_EAST_1).build();

                SendEmailRequest request = new SendEmailRequest()
                        .withDestination( new Destination().
                                withToAddresses(merchant.getEmailId()))
                        .withMessage(new Message()
                                .withBody(new Body().
                                        withText(new Content().
                                                withCharset("UTF-8").
                                                withData(bodyText)))
                                .withSubject(new Content().
                                        withCharset("UTF-8").
                                        withData(subject)))
                        .withSource(NotificationConstant.SENDER);

                client.sendEmail(request);

                logger.info("Email sent!");
                // Display an error if something goes wrong.
            } catch (Exception ex) {
                logger.error("Email Failed");
                logger.error("Error message: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            logger.error("Email Failed");
            logger.error("Error message: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
