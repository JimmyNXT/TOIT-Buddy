package com.MTechConsulting.TOITBuddy.GRPC;

import io.toit.proto.toit.api.DataProto;
import io.toit.proto.toit.api.DataServiceGrpc;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.util.List;

@Component
public class DataClient {
    private final DataServiceGrpc.DataServiceBlockingStub client;

    public DataClient() throws SSLException {
        client = (new ClientManager()).getDataServiceGrpc();
    }

    public List<DataProto.Subscription> getSubscriptions(DataProto.DataType dataType){
        DataProto.ListSubscriptionsRequest request = DataProto.ListSubscriptionsRequest.newBuilder().setType(dataType).build();
        DataProto.ListSubscriptionsResponse response =  client.listSubscriptions(request);
        return response.getSubscriptionsList();
    }

    public List<DataProto.Message> getSubscriptionMessages(DataProto.Subscription subscription){
        DataProto.FetchRequest request = DataProto.FetchRequest.newBuilder().setSubscription(subscription).build();
        DataProto.FetchResponse response = client.fetch(request);
        return response.getMessagesList();
    }
}
