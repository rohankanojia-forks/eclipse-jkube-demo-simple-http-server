package in.rohaan;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServerApplication {
  private static final int PORT = 8080;
  private static final Logger LOG = Logger.getLogger(HttpServerApplication.class.getSimpleName());

  public static void main(String[] args) {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
      LOG.info("Server started at " + PORT);
      server.createContext("/", new RootController());
      server.setExecutor(null);
      server.start();
    } catch (IOException e) {
      LOG.log(Level.SEVERE, String.format("Error encountered while starting server: %s",  e.getMessage()));
    }
  }
}
