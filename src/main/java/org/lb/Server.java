package org.lb;

public class Server {
  private String host;
  private int port;

  public Server(final String host, final int port) {
    this.host = host;
    this.port = port;
  }

  public int getPort() {
    return port;
  }

  public String getHost() {
    return host;
  }
}
