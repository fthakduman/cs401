package dataprovidertests;

import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import provider.base.model.RankRequest;
import provider.base.model.RankResponse;

import java.net.URI;
import java.util.List;

public class Test {
    public static void main (String args []){
        RestTemplate  rankRequest = new RestTemplate();
        RankRequest request = new RankRequest();
        request.setUserName("mustafas");
        request.setPassword("xyz");
        request.setBankNames(TestUtils.getBankNames());

        String urlToken = "http://localhost:8092/dataprovider/api/v1/rank/last6h";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        headers.add("Accept", "application/json");
        ParameterizedTypeReference<List<RankResponse>> responseType = new ParameterizedTypeReference<List<RankResponse>>() {};
        RequestEntity<RankRequest> entity = new RequestEntity<RankRequest>(request, headers, HttpMethod.POST,URI.create(urlToken) );
        ResponseEntity<List<RankResponse>> result = null;
        try{
            result  = rankRequest.exchange(entity,responseType );
            if(result.getStatusCode().is2xxSuccessful()){
                result.getBody().forEach( res -> {
                    System.out.println(res.getBankName() + " " + res.getRank()+ " " + res.getRankTimeFor());
                });
            }
            if(result.getStatusCode().is4xxClientError()){
                System.out.println("is4xxClientError(");
            }
        }
        catch (HttpClientErrorException h){
            System.out.println(h.getLocalizedMessage());
        }

    }
}
