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
        UserAdapter adapter = new UserAdapter();
        adapter.delete(false);
        adapter.register();

        try {
            adapter.makeRequest();
            System.out.println(adapter.userName + " " + adapter.password + " " );
            adapter.makeRequest();
            System.out.println(adapter.userName + " " + adapter.password + " " );
            adapter.changeRole();
            System.out.println(adapter.userName + " " + adapter.password + " " );
            adapter.changeName();
            System.out.println(adapter.userName + " " + adapter.password + " " );
            adapter.changePassword();
            System.out.println(adapter.userName + " " + adapter.password + " " );
            adapter.makeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.delete(false);

    }
}
