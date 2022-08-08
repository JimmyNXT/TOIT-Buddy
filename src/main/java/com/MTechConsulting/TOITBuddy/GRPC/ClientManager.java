package com.MTechConsulting.TOITBuddy.GRPC;

import com.MTechConsulting.TOITBuddy.Domain.Modal.AuthCredentials;
import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContextBuilder;
import io.toit.proto.toit.api.*;
import io.toit.proto.toit.api.pubsub.SubscribeGrpc;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.SSLException;

public class ClientManager {
    public static String host = "api.toit.io";
    public static int port = 443;
    private static final SslContextBuilder builder = GrpcSslContexts.forClient();

    @Value("${com.mtechconsulting.toit-buddy.username}")
    private String username;

    @Value("${com.mtechconsulting.toit-buddy.password}")
    private String password;
    private ManagedChannel channel;
    private static AuthCredentials credentials;

    public ClientManager(String username, String password, ManagedChannel channel) {
        this.username = username;
        this.password = password;
        this.channel = channel;
    }

    public ClientManager(String username, String password) throws SSLException {
        this.username = username;
        this.password = password;
        this.channel = CreateChannel();
    }

    public ClientManager() throws SSLException {
        this.channel = CreateChannel();
    }

    public void Authenticate() {
        AuthGrpc.AuthBlockingStub client = AuthGrpc.newBlockingStub(channel);

        AuthProto.LoginRequest req = AuthProto.LoginRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        credentials = new AuthCredentials(client.login(req));
    }

    public static ManagedChannel CreateChannel() throws SSLException {
        return CreateChannel(host, port);
    }

    public static ManagedChannel CreateChannel(String host, int port) throws SSLException {
        return NettyChannelBuilder.forAddress(host, port).sslContext(builder.build()).build();
    }

    public AppServiceGrpc.AppServiceBlockingStub getAppServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return AppServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public DataServiceGrpc.DataServiceBlockingStub getDataServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return DataServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public DeviceServiceGrpc.DeviceServiceBlockingStub getDeviceServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return DeviceServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public DoctorServiceGrpc.DoctorServiceBlockingStub getDoctorServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return DoctorServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public HardwareServiceGrpc.HardwareServiceBlockingStub getHardwareServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return HardwareServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public OrganizationServiceGrpc.OrganizationServiceBlockingStub getOrganizationServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return OrganizationServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public ProgramServiceGrpc.ProgramServiceBlockingStub getProgramServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return ProgramServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public SDKServiceGrpc.SDKServiceBlockingStub getSDKServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return SDKServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public SimulatorServiceGrpc.SimulatorServiceBlockingStub getSimulatorServiceGrpc() {
        if (credentials == null) {
            Authenticate();
        }

        return SimulatorServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }

    public SubscribeGrpc.SubscribeBlockingStub getSubscribeGrpc(){
        if (credentials == null) {
            Authenticate();
        }

        return SubscribeGrpc.newBlockingStub(channel).withCallCredentials(credentials);
    }
}
