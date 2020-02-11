package com.imshenik.tofer.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.imshenik.tofer.utils.SocketConnectionProvider;

public class ToferService {

    private String tcid;
    private int firstTktNumber;
    private int lastTktNumber;
    private static final String FSs = "\u001C";
    private static final String RSs = "\u001E";
    private static final String GSs = "\u001D";
    private static final String USs = "\u001F";
    private static final String reqInfo = "<FS>333<US>00<GS>%s#INFO<FS>";
    private static final String reqTktByNumber = "<FS>333<US>00<GS>%s#%d<FS>";
    private static final String reqCnvByNumber = "<FS>333<US>00<GS>%s#%dC<FS>";
    private SocketConnectionProvider connectionProvider;

    public ToferService() {
    }

    private String request(String tofClientRequest) {
        tofClientRequest = tofClientRequest.replaceAll("<FS>", FSs);
        tofClientRequest = tofClientRequest.replaceAll("<RS>", RSs);
        tofClientRequest = tofClientRequest.replaceAll("<GS>", GSs);
        tofClientRequest = tofClientRequest.replaceAll("<US>", USs);
        StringBuilder tofServerResponse = new StringBuilder();
        try (Socket socket = connectionProvider.getConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            out.write(tofClientRequest);
            out.flush();
            int c;
            int fsCounter = 0;
            while (fsCounter != 2) {
                c = in.read();
                switch ((char) c) {
                    case '\u001C':
                        fsCounter++;
                        tofServerResponse.append("<FS>");
                        break;
                    case '\u001E':
                        tofServerResponse.append("\r\n<RS>");
                        break;
                    case '\u001D':
                        tofServerResponse.append("<GS>");
                        break;
                    case '\u001F':
                        tofServerResponse.append("<US>");
                        break;
                    default:
                        tofServerResponse.append((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tofServerResponse.toString();
    }

    public String requestStatus() {
        return request(String.format(reqInfo, tcid));
    }

    public String requestTktByNumber(int number) {
        return request(String.format(reqTktByNumber, tcid, number));
    }

    public String requestCnvByNumber(int number) {
        return request(String.format(reqCnvByNumber, tcid, number));
    }

    public String requestFirstTkt() {
        fillFirstLast();
        return requestTktByNumber(firstTktNumber);
    }

    public String requestLastTkt() {
        fillFirstLast();
        return requestTktByNumber(lastTktNumber);
    }

    public String requestFirstCnv() {
        fillFirstLast();
        return requestCnvByNumber(firstTktNumber);
    }

    public String requestLastCnv() {
        fillFirstLast();
        return requestCnvByNumber(lastTktNumber);
    }

    private void fillFirstLast() {
        String currentState = requestStatus();
        System.out.println(currentState);
        Pattern first = Pattern.compile("<RS>533<US>[a-zA-Z]{4}#([0-9]+)");
        Pattern last = Pattern.compile("<RS>536<US>[a-zA-Z]{4}#([0-9]+)");
        Matcher m = first.matcher(currentState);
        m.find();
        firstTktNumber = Integer.parseInt(m.toMatchResult().group(1));
        m = last.matcher(currentState);
        m.find();
        lastTktNumber = Integer.parseInt(m.toMatchResult().group(1));
    }

    public int getFirstTktNumber() {
        fillFirstLast();
        return firstTktNumber;
    }

    public int getLastTktNumber() {
        fillFirstLast();
        return lastTktNumber;
    }

    public String getTcid() {
        return tcid;
    }

    public void setTcid(String tcid) {
        this.tcid = tcid;
    }

    public SocketConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    public void setConnectionProvider(SocketConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
}
