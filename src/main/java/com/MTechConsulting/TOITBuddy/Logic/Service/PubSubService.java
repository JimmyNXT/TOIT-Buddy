package com.MTechConsulting.TOITBuddy.Logic.Service;

import com.MTechConsulting.TOITBuddy.Domain.DTO.PubSubMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.SubscriptionDTO;
import com.MTechConsulting.TOITBuddy.Logic.Translator.PubSubTranslator;
import io.toit.proto.toit.api.pubsub.SubscribeProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PubSubService {
    private final PubSubTranslator translator;

    @Autowired
    public PubSubService(PubSubTranslator translator) {
        this.translator = translator;
    }

    public List<SubscriptionDTO> getSubscriptions(){
        return translator.getSubscriptionDTOs();
    }

    public List<PubSubMessageDTO> getMessagesByName(String name){
        List<SubscribeProto.Subscription> subs = translator.getSubscriptions();

        for(SubscribeProto.Subscription sub : subs){
            if(sub.getName().equals(name)){
                return  translator.getSubscriptionMessageDTOs(sub);
            }
        }
        throw new EntityNotFoundException();
    }

    public List<PubSubMessageDTO> getMessagesByTopic(String topic){
        List<SubscribeProto.Subscription> subs = translator.getSubscriptions();

        for(SubscribeProto.Subscription sub : subs){
            if(sub.getTopic().equals(topic)){
                return  translator.getSubscriptionMessageDTOs(sub);
            }
        }
        throw new EntityNotFoundException();
    }
}
