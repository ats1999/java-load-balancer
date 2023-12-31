package org.lb.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResponseForwarder implements Runnable {
  private final OutputStream clientOs;
  private final InputStream upStreamIs;

  ResponseForwarder(OutputStream clientOs, InputStream upStreamIs) {
    this.clientOs = clientOs;
    this.upStreamIs = upStreamIs;
  }

  @Override
  public void run() {
    int inputByte;
    while (true) {
      try {
        if ((inputByte = upStreamIs.read()) == -1) {
          break;
        }

        clientOs.write(inputByte);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
