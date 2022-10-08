package com.wes.webflux.interceptor;



import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for each restTemplate call, prints the request and response.
 *
 * Needs to be configured as interceptor in class ApplicationConfiguration @{see ApplicationConfiguration}
 */
@Slf4j
@Component
class FullLoggingInterceptor implements ClientHttpRequestInterceptor{

  @Override
public
  ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    traceRequest(request, body);
    ClientHttpResponse response = execution.execute(request, body);
    traceResponse(response);

    return response;
  }

  private static void traceRequest(HttpRequest request, byte[] body) throws IOException {
    log.debug("===========================request begin================================================");
    log.debug("URI         : {}", request.getURI());
    log.debug("Method      : {}", request.getMethod());
    log.debug("Headers     : {}", request.getHeaders() );
    log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
    log.debug("===========================request end================================================");
  }

  private static void traceResponse(ClientHttpResponse response) throws IOException {
    log.debug("===========================response begin==========================================");
    log.debug("Status code  : {}", response.getStatusCode());
    log.debug("Status text  : {}", response.getStatusText());
    log.debug("Headers      : {}", response.getHeaders());
    log.debug("Response body: {}", IOUtils.toString(response.getBody()));
    log.debug("===========================response end=================================================");
  }
}