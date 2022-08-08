package com.MTechConsulting.TOITBuddy.GRPC;

import io.toit.proto.toit.api.pubsub.SubscribeGrpc;
import io.toit.proto.toit.api.pubsub.SubscribeProto;
import io.toit.proto.toit.model.pubsub.MessageProto;

import javax.net.ssl.SSLException;
import java.util.List;

public class PubSubClient {
    private final SubscribeGrpc.SubscribeBlockingStub client;

    public PubSubClient() throws SSLException {
        this.client = (new ClientManager()).getSubscribeGrpc();
    }

    public List<SubscribeProto.Subscription> getSubscriptionList(){
        SubscribeProto.ListSubscriptionsRequest request = SubscribeProto.ListSubscriptionsRequest.newBuilder().build();
        SubscribeProto.ListSubscriptionsResponse response = client.listSubscriptions(request);
        return response.getSubscriptionsList();
    }

    public List<MessageProto.Envelope> fetch(SubscribeProto.Subscription subscription){
        SubscribeProto.FetchRequest request = SubscribeProto.FetchRequest.newBuilder()
                .setSubscription(subscription)
                .build();
        SubscribeProto.FetchResponse response = client.fetch(request);
        return response.getMessagesList();
    }
}
