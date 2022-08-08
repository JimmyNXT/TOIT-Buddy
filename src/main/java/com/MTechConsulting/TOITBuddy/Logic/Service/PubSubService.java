package com.MTechConsulting.TOITBuddy.Logic.Service;

import com.MTechConsulting.TOITBuddy.Logic.Translator.PubSubTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PubSubService {
    private final PubSubTranslator translator;

    @Autowired
    public PubSubService(PubSubTranslator translator) {
        this.translator = translator;
    }
}
