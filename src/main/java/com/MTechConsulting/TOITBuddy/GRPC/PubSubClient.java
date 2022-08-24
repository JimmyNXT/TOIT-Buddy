package com.MTechConsulting.TOITBuddy.GRPC;

import io.toit.proto.toit.api.pubsub.SubscribeGrpc;
import io.toit.proto.toit.api.pubsub.SubscribeProto;
import io.toit.proto.toit.model.pubsub.MessageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.util.List;

@Component
public class PubSubClient {
    private final SubscribeGrpc.SubscribeBlockingStub client;

    @Autowired
    public PubSubClient(ClientManager clientManager){
        this.client = clientManager.getSubscribeGrpc();
    }

    public List<SubscribeProto.Subscription> getSubscriptions(){
        SubscribeProto.ListSubscriptionsRequest request = SubscribeProto.ListSubscriptionsRequest.newBuilder().build();
        SubscribeProto.ListSubscriptionsResponse response = client.listSubscriptions(request);
        return response.getSubscriptionsList();
    }

    public List<MessageProto.Envelope> getSubscriptionMessages(SubscribeProto.Subscription subscription){
        SubscribeProto.FetchRequest request = SubscribeProto.FetchRequest.newBuilder()
                .setSubscription(subscription)
                .build();
        SubscribeProto.FetchResponse response = client.fetch(request);
        return response.getMessagesList();
    }
}
