package com.MTechConsulting.TOITBuddy.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PubSubMessageDTO {
    private String id;
    private String topic;
    private String data;
    private Instant timestamp;
    private String deviceId;
}
