package com.MTechConsulting.TOITBuddy.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.MTechConsulting.TOITBuddy.Presentation",
        "com.MTechConsulting.TOITBuddy.Logic",
        "com.MTechConsulting.TOITBuddy.GRPC",
        "com.MTechConsulting.TOITBuddy.Domain"})
public class WebConfig {
}
