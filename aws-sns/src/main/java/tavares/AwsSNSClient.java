package tavares;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import java.util.HashMap;
import java.util.Map;

public class AwsSNSClient {

//    public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
//    public static final String AWS_SECRET_KEY = "aws.secretKey";

    static {
//        System.setProperty(AWS_ACCESS_KEY_ID, "AKIAY77DV5PRSGVASPE5");
//        System.setProperty(AWS_SECRET_KEY, "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");
    }

    public static void main(String[] args) {
        AwsSNSClient myClient = new AwsSNSClient();
//        myClient.sendSingleSMS("This is a brand new Message...", "+5565999820837");
        myClient.sendToMultipleSubs("This is a brand new Message...");
    }

    public void sendToMultipleSubs(String message) {
        AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();

        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("MyWebsite").withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));

        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withTopicArn("arn:aws:sns:us-east-1:618415909859:MyWebsite")
                .withMessageAttributes(smsAttributes));

        System.out.println("Message sent successfully to topic--" + result.getMessageId());

    }

    public void sendSingleSMS(String message, String phoneNumber) {
        AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_WEST_1).build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();

        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("MyWebsite").withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));

        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));

        System.out.println("Message sent successfully--" + result.getMessageId());
    }
}
