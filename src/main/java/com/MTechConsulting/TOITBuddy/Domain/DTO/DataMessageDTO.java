package com.MTechConsulting.TOITBuddy.Domain.DTO;

import io.toit.proto.toit.api.DataProto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataMessageDTO {
    private String id;
    private String message;
    private DataProto.DataType type;
    private Instant createdAt;
    private String deviceID;
}
