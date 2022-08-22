package com.MTechConsulting.TOITBuddy.Logic.Translator;

import com.MTechConsulting.TOITBuddy.Domain.DTO.DataMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.DataSubscriptionDTO;
import com.MTechConsulting.TOITBuddy.GRPC.DataClient;
import io.toit.proto.toit.api.DataProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataTranslator {
    private final DataClient client;

    @Autowired
    public DataTranslator(DataClient client) {
        this.client = client;
    }

    public List<DataProto.Subscription> getSubscriptions(DataProto.DataType dataType){
        return client.getSubscriptions(dataType);
    }

    public List<DataSubscriptionDTO> getSubscriptionDTOs(DataProto.DataType dataType){
        return this.getSubscriptions(dataType).stream().map(this::modalToDTO).collect(Collectors.toList());
    }

    public DataSubscriptionDTO modalToDTO(DataProto.Subscription sub){
        return new DataSubscriptionDTO(sub.getName(), sub.getType());
    }

    public List<DataProto.Message> getMessages(DataProto.Subscription sub){
        return client.getSubscriptionMessages(sub);
    }

    public List<DataMessageDTO> getMessageDTOs(DataProto.Subscription sub){
        return this.getMessages(sub).stream().map(this::modalToDTO).collect(Collectors.toList());
    }

    public DataMessageDTO modalToDTO(DataProto.Message modal){
        return new DataMessageDTO(
                modal.getId().toString(StandardCharsets.UTF_8),
                modal.getMessage().getData().toString(StandardCharsets.UTF_8),
                modal.getMessage().getType(),
                Instant.ofEpochSecond(modal.getMessage().getCreated().getSeconds(), modal.getMessage().getCreated().getNanos()),
                modal.getMessage().getDeviceId().toString(StandardCharsets.UTF_8)
        );
    }
}
