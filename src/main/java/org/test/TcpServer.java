package org.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpServer {
  public static void main(String[] args) throws IOException {
    String GET_REQUEST = "GET / HTTP/1.1\r\nHost:google.com\r\n\r\n";
    Socket socket = new Socket("google.com", 80);
    InputStream is = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    new Thread(
            () -> {
              try {
                os.write(GET_REQUEST.getBytes());
                socket.shutdownOutput();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            })
        .start();

    new Thread(
            () -> {
              int b;
              while (true) {
                try {
                  if ((b = is.read()) == -1) {
                    break;
                  }
                  System.out.print((char) b);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              }
            })
        .start();
  }
}
