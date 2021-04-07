package ir.piana.dev.pianaspacy.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service("rootHandler")
public class RootHandler extends CommonHandler {
    @Override
    public void doGet(HttpExchange httpExchange) throws IOException {
        String response = "Hello this is HTTP Server";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
