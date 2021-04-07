package ir.piana.dev.pianaspacy.cfg;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ir.piana.dev.pianaspacy.http.HttpAdapterServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.Queue;
import java.io.IOException;
import java.net.InetSocketAddress;

@Service
@Slf4j
public class HttpServerConfiguration {
//    @Value( "${piana.spacy.port}" )
//    private int port;
    private HttpServer server;

    @Autowired
    private PianaSpacyModel pianaSpacyModel;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() throws IOException {
        server = HttpServer.create(new InetSocketAddress(pianaSpacyModel.getPort()), 0);
        log.info("Server [HTTP] Ready On 127.0.0.1:" + pianaSpacyModel.getPort());

        for (String path : pianaSpacyModel.getRoutes().keySet()) {
            server.createContext(path, (HttpHandler)context.getBean(pianaSpacyModel.getRoutes().get(path)));
        }

//        server.createContext("/", new HttpAdapterServer.RootHandler());
//        server.createContext("/test", new HttpAdapterServer.TestHandler());
        server.setExecutor(null);
        server.start();
    }
}
