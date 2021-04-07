package ir.piana.dev.pianaspacy.http;

import com.sun.net.httpserver.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpParameterProcessor {
    private HttpExchange httpExchange;
    private Map<String, String> queryParameterMap;

    public static HttpParameterProcessor getProcessor(HttpExchange httpExchange) {
        return new HttpParameterProcessor(httpExchange);
    }

    private HttpParameterProcessor(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    private synchronized void init() {
        if(queryParameterMap == null) {
             queryParameterMap = Arrays.asList(httpExchange.getRequestURI().getQuery().split("&"))
                    .stream().map(q -> q.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        }
    }

    public String getString(String parameterKey) {
        init();
        return queryParameterMap.get(parameterKey);
    }

    public Double getDouble(String parameterKey) {
        init();
        String s = queryParameterMap.get(parameterKey);
        return s == null ? null : Double.valueOf(s);
    }

    public Float getFloat(String parameterKey) {
        init();
        String s = queryParameterMap.get(parameterKey);
        return s == null ? null : Float.valueOf(s);
    }

    public Long getLong(String parameterKey) {
        init();
        String s = queryParameterMap.get(parameterKey);
        return s == null ? null : Long.valueOf(s);
    }

    public Integer getInt(String parameterKey) {
        init();
        String s = queryParameterMap.get(parameterKey);
        return s == null ? null : Integer.valueOf(s);
    }

    public Boolean getBoolean(String parameterKey) {
        init();
        String s = queryParameterMap.get(parameterKey);
        return s == null ? null : Boolean.valueOf(s);
    }
}
