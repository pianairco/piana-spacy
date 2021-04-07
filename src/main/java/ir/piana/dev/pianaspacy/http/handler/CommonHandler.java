package ir.piana.dev.pianaspacy.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public abstract class CommonHandler implements HttpHandler {
    protected static final Map<String, HttpExchange> httpExchangeMap = new LinkedHashMap<>();
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Override
    public final void handle(HttpExchange httpExchange) {
        String uuid = UUID.randomUUID().toString();
        httpExchangeMap.put(uuid, httpExchange);
        jmsTemplate.convertAndSend(queue, uuid);
        System.out.println("send");
    }

//    @JmsListener(destination = "local.inmemory.queue")
//    public void onMessage(String content){
//        logger.info("Message received : "+content);
//    }

    @JmsListener(destination = "local.inmemory.queue")
    public void dispatch(String uuid) throws IOException {
        HttpExchange httpExchange = httpExchangeMap.remove(uuid);
        log.info("Message received : " + httpExchange.getRequestURI().getPath());
        switch (httpExchange.getRequestMethod()) {
            case "GET":
                doGet(httpExchange);
                return;
            case "POST":
                doPost(httpExchange);
                return;
            case "PUT":
                doPut(httpExchange);
                return;
            case "DELETE":
                doDelete(httpExchange);
                return;
        }
    }

    public void doGet(HttpExchange httpExchange) throws IOException {}

    public void doPost(HttpExchange httpExchange) {}

    public void doPut(HttpExchange httpExchange) {}

    public void doDelete(HttpExchange httpExchange) {}
}
