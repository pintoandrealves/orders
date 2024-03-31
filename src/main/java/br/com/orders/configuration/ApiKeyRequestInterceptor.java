package br.com.orders.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Util;


public class ApiKeyRequestInterceptor implements RequestInterceptor {
    private final String location;
    private final String name;
    private String value;

    public ApiKeyRequestInterceptor(String location, String name, String value) {
        Util.checkNotNull(location, "location");
        Util.checkNotNull(name, "name");
        Util.checkNotNull(value, "value");
        this.location = location;
        this.name = name;
        this.value = value;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (location.equals("header")) {
            requestTemplate.header(name, value);
        } else if (location.equals("query")) {
            requestTemplate.query(name, value);
        }
    }

}
