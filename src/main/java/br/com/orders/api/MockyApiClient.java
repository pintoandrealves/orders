package br.com.orders.api;

import br.com.orders.configuration.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "MockyApiClient", name = "MockyApiClient", url = "${spring.cloud.openfeign.client.config.feignName.url}", configuration = ClientConfiguration.class)
public interface MockyApiClient extends MockyApi {
}
