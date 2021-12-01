package in.rohaan;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class RootController implements HttpHandler {
  private static final Logger LOG = Logger.getLogger(RootController.class.getSimpleName());

  @Override
  public void handle(HttpExchange he) {
    try {
      LOG.info("GET /");
      String response = getGreetingMessage();
      he.sendResponseHeaders(200, response.length());
      OutputStream os = he.getResponseBody();
      os.write(response.getBytes());
      LOG.info("SUCCESS");
      os.close();
    } catch (IOException exception) {
      exception.printStackTrace();
      LOG.severe("FAILURE : " + exception.getMessage());
    }
  }

  public String getGreetingMessage() throws IOException {
    InputStream inputStream = RootController.class.getResourceAsStream("/index.html");
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    for (int length; (length = inputStream.read(buffer)) != -1; ) {
      result.write(buffer, 0, length);
    }

    return result.toString(StandardCharsets.UTF_8);
  }
}
