package com.MTechConsulting.TOITBuddy.GRPC;

import com.google.protobuf.ByteString;
import io.toit.proto.toit.api.DeviceProto;
import io.toit.proto.toit.api.DeviceServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceClient {
    private final DeviceServiceGrpc.DeviceServiceBlockingStub client;

    @Autowired
    public DeviceClient(ClientManager clientManager) {
        client = clientManager.getDeviceServiceGrpc();
    }
    
    public List<DeviceProto.Device> getDevices(){
        DeviceProto.ListDevicesRequest request = DeviceProto.ListDevicesRequest.newBuilder().build();
        DeviceProto.ListDevicesResponse response = client.listDevices(request);
        return response.getDevicesList();
    }

    public DeviceProto.Device getDevice(DeviceProto.GetDeviceRequest request){
        DeviceProto.GetDeviceResponse response = client.getDevice(request);
        return response.getDevice();
    }
    
    public List<DeviceProto.DeviceLog> readDeviceLogs(ByteString deviceID){
        DeviceProto.ReadDeviceLogsRequest request = DeviceProto.ReadDeviceLogsRequest.newBuilder().setDeviceId(deviceID).build();
        DeviceProto.ReadDeviceLogsResponse response = client.readDeviceLogs(request);
        return response.getLogsList();
    }
}
