package com.MTechConsulting.TOITBuddy.Logic.Service;

import com.MTechConsulting.TOITBuddy.Domain.DTO.DataMessageDTO;
import com.MTechConsulting.TOITBuddy.Domain.DTO.DataSubscriptionDTO;
import com.MTechConsulting.TOITBuddy.Logic.Translator.DataTranslator;
import io.toit.proto.toit.api.DataProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DataService {
    private final DataTranslator translator;

    @Autowired
    public DataService(DataTranslator translator) {
        this.translator = translator;
    }

    public List<DataSubscriptionDTO> getSubscriptions(DataProto.DataType type){
        return translator.getSubscriptionDTOs(type);
    }

    public List<DataMessageDTO> getMessages(String subName, DataProto.DataType type){
        for(DataProto.Subscription sub : translator.getSubscriptions(type)){
            if(sub.getName().equals(subName)){
                return translator.getMessageDTOs(sub);
            }
        }
        throw new EntityNotFoundException();
    }
}
