package com.aviralgupta.site.monitoring_system.socket;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SocketEventListeners {

    private final SocketIOServer server;

    public SocketEventListeners(SocketIOServer server) {
        this.server = server;
    }

    @PostConstruct
    public void addListeners() {
        server.addConnectListener(client -> {
            System.out.println("Client connected: " + client.getSessionId());
        });

        server.addDisconnectListener(client -> {
            System.out.println("Client disconnected: " + client.getSessionId());
        });
    }
}
