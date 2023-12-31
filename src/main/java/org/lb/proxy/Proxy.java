package org.lb.proxy;

import org.lb.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Proxy {
  private Socket upStreamSocket;
  private final Socket clientSocket;
  protected Server upStream;

  public Proxy(final Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public void selectUpStream() {
    this.upStream = new Server("localhost", 3000);
  }

  public void createUpStreamConnection() throws IOException {
    upStreamSocket = new Socket(upStream.getHost(), upStream.getPort());
  }

  public void make() throws IOException {
    selectUpStream();
    createUpStreamConnection();

    OutputStream upStreamOs = upStreamSocket.getOutputStream();
    InputStream upStreamIs = upStreamSocket.getInputStream();

    OutputStream clientOs = clientSocket.getOutputStream();
    InputStream clientIs = clientSocket.getInputStream();

    RequestForwarder requestForwarder = new RequestForwarder(upStreamOs, clientIs);
    new Thread(requestForwarder,"RequestForwarder").start();

    ResponseForwarder responseForwarder = new ResponseForwarder(clientOs, upStreamIs);
    new Thread(responseForwarder,"ResponseForwarder").start();
  }

}
