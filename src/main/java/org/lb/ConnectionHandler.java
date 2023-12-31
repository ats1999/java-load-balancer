package org.lb;

import org.lb.proxy.Proxy;

import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler {
  private final Socket clientSocket;

  ConnectionHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void handle() throws IOException {
    Proxy proxy = new Proxy(clientSocket);

    try {
      proxy.make();
    } catch (IOException e) {
      // TODO: terminate threads involved into proxy
      e.printStackTrace();
      this.clientSocket.close();
    }

  }
}
