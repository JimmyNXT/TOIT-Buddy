package com.MTechConsulting.TOITBuddy.Logic.Translator;

import com.MTechConsulting.TOITBuddy.Domain.DTO.PubSubMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.PubSubSubscriptionDTO;
import com.MTechConsulting.TOITBuddy.GRPC.ClientManager;
import com.MTechConsulting.TOITBuddy.GRPC.PubSubClient;
import io.toit.proto.toit.api.pubsub.SubscribeProto;
import io.toit.proto.toit.model.pubsub.MessageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PubSubTranslator {
    private final PubSubClient client;

    @Autowired
    public PubSubTranslator(PubSubClient client) {
        this.client = client;
    }

    public List<SubscribeProto.Subscription> getSubscriptions(){
        return client.getSubscriptions();
    }

    public List<PubSubSubscriptionDTO> getSubscriptionDTOs(){
        return client.getSubscriptions().stream().map(this::modalToDto).collect(Collectors.toList());
    }

    public PubSubSubscriptionDTO modalToDto(SubscribeProto.Subscription modal){
        return new PubSubSubscriptionDTO(modal.getName(), modal.getTopic());
    }

    public List<MessageProto.Envelope> getSubscriptionMessages(SubscribeProto.Subscription subscription){
        return client.getSubscriptionMessages(subscription);
    }

    public List<PubSubMessageDTO> getSubscriptionMessageDTOs(SubscribeProto.Subscription subscription){
        return  this.getSubscriptionMessages(subscription).stream().map(this::modalToDto).collect(Collectors.toList());
    }

    public PubSubMessageDTO modalToDto(MessageProto.Envelope modal){
        return new PubSubMessageDTO(
                modal.getId().toString(StandardCharsets.UTF_8),
                modal.getMessage().getTopic(),
                modal.getMessage().getData().toString(StandardCharsets.UTF_8),
                Instant.ofEpochSecond(modal.getMessage().getCreatedAt().getSeconds(), modal.getMessage().getCreatedAt().getNanos()),
                modal.getMessage().getPublisher().getDevice().getDeviceId().toString(StandardCharsets.UTF_8)
                );
    }
}
