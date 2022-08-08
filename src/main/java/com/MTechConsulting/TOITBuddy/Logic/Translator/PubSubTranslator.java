package com.MTechConsulting.TOITBuddy.Logic.Translator;

import com.MTechConsulting.TOITBuddy.GRPC.PubSubClient;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;

@Component
public class PubSubTranslator {
    private final PubSubClient client;

    public PubSubTranslator() throws SSLException {
        this.client = new PubSubClient();
    }
}
