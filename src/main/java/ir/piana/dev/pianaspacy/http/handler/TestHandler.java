package ir.piana.dev.pianaspacy.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service("testHandler")
public class TestHandler extends CommonHandler {
    public void doGet(HttpExchange t) throws IOException {
        String response = "Hello this is HTTP Server";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
