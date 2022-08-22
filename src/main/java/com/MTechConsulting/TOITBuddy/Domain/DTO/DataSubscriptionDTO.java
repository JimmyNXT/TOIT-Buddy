package com.MTechConsulting.TOITBuddy.Domain.DTO;

import io.toit.proto.toit.api.DataProto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataSubscriptionDTO {
    private String name;
    private DataProto.DataType type;
}
