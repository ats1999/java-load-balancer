package org.lb.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestForwarder implements Runnable {
  private final OutputStream upStreamOs;
  private final InputStream clientIs;

  RequestForwarder(OutputStream upStreamOs, InputStream clientIs) {
    this.upStreamOs = upStreamOs;
    this.clientIs = clientIs;
  }

  @Override
  public void run() {
    int inputByte;
    while (true) {
      try {
        if ((inputByte = clientIs.read()) == -1) {
          break;
        }
        upStreamOs.write(inputByte);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
