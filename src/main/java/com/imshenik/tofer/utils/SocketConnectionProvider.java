package com.imshenik.tofer.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketConnectionProvider {

    private String ip;
    private Integer port;
    private Socket socket;

    public SocketConnectionProvider() {
    }

    public Socket getConnection() {
        connect();
        return socket;
    }

    private void connect() {
        try {
            System.out.println("connecting to " + this.ip + ":" + this.port);
            this.socket = new Socket(this.ip, this.port);
            System.out.println("connected");
        } catch (UnknownHostException e) {
            System.out.println("NOT connected");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SocketConnectionProvider [ip=" + ip + ", port=" + port + "]";
    }
}
