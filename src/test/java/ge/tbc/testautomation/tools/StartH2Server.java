package ge.tbc.testautomation.tools;

import org.h2.tools.Server;

public class StartH2Server {
    public static void main(String[] args) throws Exception {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();
        System.out.println("H2 TCP server running on port 9092. Leave this running.");
        Thread.sleep(Long.MAX_VALUE);
    }
}