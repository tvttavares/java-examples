package tavares.sns.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.util.Optional;

public class SNSService {

    static final Logger LOG = LogManager.getLogger();

    private static final String SUBSCRIPTIONS_CONFIRMED = "SubscriptionsConfirmed";

//    public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
//    public static final String AWS_SECRET_KEY = "aws.secretKey";

//    static {
//        System.setProperty(AWS_ACCESS_KEY_ID, "xxx");
//        System.setProperty(AWS_SECRET_KEY, "xxx");
//    }

    public static void main(String[] args) {

        // list all topics on SNS
        listSNSTopics();

//         create new SNS topic
        createSNSTopic("my-new-topic");
        createSNSTopic("test-topic-send-email");

//         delete a topic
        deleteSNSTopic("arn:aws:sns:us-east-1:618415909859:my-new-topic");

//         subscribe email to topic
        subscribeEmail("arn:aws:sns:us-east-1:618415909859:test-topic-send-email", "ttavares@mcfadyen.com");

//         confirm subscription
        confirmSubscription("subToken", "arn:aws:sns:us-east-1:618415909859:test-topic-send-email");

        publishMessageOnTopic("sns email test", "arn:aws:sns:us-east-1:618415909859:test-topic-send-email");
    }

    private static SnsClient getSNSClient() {
        return SnsClient.builder()
                .region(Region.US_EAST_1)
//                .region(Region.of(System.getenv("AWS_REGION")))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    public static void listSNSTopics() {
        SnsClient snsClient = getSNSClient();
        try {
            ListTopicsRequest request = ListTopicsRequest.builder()
                    .build();

            ListTopicsResponse result = snsClient.listTopics(request);

            Optional<Topic> t = snsClient.listTopics().topics().stream()
                    .filter(topic -> topic.topicArn().equals("arn:aws:sns:us-east-1:618415909859:test-topic-send-email")).findFirst();


            if (snsClient.listTopics().topics().stream()
                    .anyMatch(topic -> topic.topicArn().equals("arn:aws:sns:us-east-1:618415909859:test-topic-send-email"))) {
                System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\nTopics\n" + result.topics());
            }

            if (snsClient.getTopicAttributes(GetTopicAttributesRequest.builder()
                    .topicArn("arn:aws:sns:us-east-1:618415909859:test-topic-send-email").build()).attributes().get("SubscriptionsConfirmed").equals("1")) {
                System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\nTopics\n" + result.topics());
            }

            System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\nTopics\n" + result.topics());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public void createAndSubscribeEmailToTopic(String topicName, String email) {
        String topicArn = createSNSTopic(topicName);
        subscribeEmail(topicArn, email);
    }

    public static String createSNSTopic(String topicName) {
        SnsClient snsClient = getSNSClient();
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();
            return snsClient.createTopic(request).topicArn();

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
            return "no topic";
        }
    }

    public static void deleteSNSTopic(String topicArn) {
        SnsClient snsClient = getSNSClient();
        try {
            DeleteTopicRequest request = DeleteTopicRequest.builder()
                    .topicArn(topicArn)
                    .build();

            DeleteTopicResponse result = snsClient.deleteTopic(request);
            System.out.println("\n\nStatus was " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public static void subscribeEmail(String topicArn, String email) {
        SnsClient snsClient = getSNSClient();
        try {
            SubscribeRequest request = SubscribeRequest.builder()
                    .protocol("email")
                    .endpoint(email)
                    .returnSubscriptionArn(true)
                    .topicArn(topicArn)
                    .build();

            SubscribeResponse result = snsClient.subscribe(request);
            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public static void confirmSubscription(String subscriptionToken, String topicArn) {
        SnsClient snsClient = getSNSClient();
        try {
            ConfirmSubscriptionRequest request = ConfirmSubscriptionRequest.builder()
                    .token(subscriptionToken)
                    .topicArn(topicArn)
                    .build();

            ConfirmSubscriptionResponse result = snsClient.confirmSubscription(request);
            System.out.println("\n\nStatus was " + result.sdkHttpResponse().statusCode() + "\nSubscription Arn: \n" + result.subscriptionArn());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public static void publishMessageOnTopic(String message, String topicArn) {
        SnsClient snsClient = getSNSClient();
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public String sendMessage(String pMessage, String pMessageSubject, String pTopicArn) {
        if (pTopicArn != null && pTopicArn.isEmpty()) {
            return "No message was sent. TopicArn is Blank/Empty";
        }

        String region = System.getenv("AWS_REGION");
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("SendProductExportSNSMessage sendMessage >> " + region));
        }

        SnsClient snsClient = getSNSClient();
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("SendProductExportSNSMessage sending msg >> '" + pMessage + "' to topicArn:" + pTopicArn));
        }

        /* Check if topic exists */
        if (snsClient.listTopics().topics().stream().anyMatch(topic -> topic.topicArn().equals(pTopicArn))) {
            /* Check if subscription is confirmed */
            if (snsClient.getTopicAttributes(GetTopicAttributesRequest.builder().topicArn(pTopicArn).build()).attributes().get(SUBSCRIPTIONS_CONFIRMED).equals("1")) {
                try {
                    PublishRequest request = PublishRequest.builder()
                            .message(pMessage)
                            .subject(pMessageSubject)
                            .topicArn(pTopicArn)
                            .build();

                    PublishResponse result = snsClient.publish(request);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
                    }
                    return "SendSNSMessage succeeded with messageId " + result.messageId()
                            + ", sequence number " + result.sequenceNumber() + "\n";
                } catch (SnsException e) {
                    return "SendSNSMessage failed with following error" + e.awsErrorDetails().errorMessage();
                }
            }
            return "Subscription still in pending state";
        }
        return "Topic not created";
    }
}
