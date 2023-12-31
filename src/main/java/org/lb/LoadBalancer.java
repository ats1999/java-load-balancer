package org.lb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoadBalancer {
  private static void processRequests(ServerSocket serverSocket) throws IOException {
    while (true) {
      Socket clientSocket = serverSocket.accept();
      ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
      connectionHandler.handle();
    }
  }

  private static void startProxyServer() throws IOException {
    try (ServerSocket serverSocket = new ServerSocket(Config.PORT)) {
      processRequests(serverSocket);
    }
  }

  public static void main(String[] args) {
    while (true) {
      try {
        startProxyServer();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
