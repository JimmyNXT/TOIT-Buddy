package com.MTechConsulting.TOITBuddy.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PubSubSubscriptionDTO {
    private String name;
    private String topic;
}
