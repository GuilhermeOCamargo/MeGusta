package br.com.megusta.userservice.util.restTemplate;

import br.com.megusta.userservice.exceptions.ServiceRequestFailedException;
import br.com.megusta.userservice.model.dto.StandardResponse;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Guilherme Camargo
 * */
@Component
public class RestTemplateBuilder {
    RestTemplate restTemplate = new RestTemplate();

    @Value("${default.url.service}")
    private String BASE_URL;


    public StandardResponse doGet(String serviceResource, Object param){
        ResponseEntity<StandardResponse> response = restTemplate.exchange(getCompleteUrl(serviceResource),
                HttpMethod.GET,createHeader(), StandardResponse.class);
        validateResponse(response);
        return response.getBody();
    }

    private HttpEntity<String> createHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
    }
    private void validateResponse(ResponseEntity<StandardResponse> response)throws ServiceRequestFailedException{
        if(!response.getStatusCode().is2xxSuccessful()) throw new ServiceRequestFailedException(response.getBody().getMsg());
    }
    private String getCompleteUrl(String resource){
        return BASE_URL+resource;
    }
}
