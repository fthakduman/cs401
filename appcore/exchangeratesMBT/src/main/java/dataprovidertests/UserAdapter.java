package dataprovidertests;

import nz.ac.waikato.modeljunit.Action;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import provider.base.model.RankRequest;

public class UserAdapter {
    RestTemplate register;
    RestTemplate update;
    String userName;
    String password;
    String userRole;
    boolean isRegistered =false;

    @Action
    public void register(){
        if(isRegistered == false){
            randomizeRegister();
            isRegistered=true;
        }

    }
    @Action
    public void update(){
        if(isRegistered == true){
            randomizeUpdate();
        }
    }
    @Action
    public void delete(){

    }
    @Action
    public void changePassword(){

    }
    @Action
    public void changeRole(){

    }
    @Action
    public void changeName(){

    }
    @Action
    public void makeRequest(){

    }
    @Action
    public void changeBankName(){

    }
    /// randomize just user info for userapi executed once
    private void randomizeRegister(){
        register = new RestTemplate();
        String urlToken = TestUtils.REGISTER_USER;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("CONTENT_TYPE", String.valueOf(MediaType.APPLICATION_JSON));

        headers.add("Accept","application/json");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        userName = TestUtils.getUsername();
        password = TestUtils.getPassword();
        userRole = TestUtils.getRole();
        body.add("userName", userName);
        body.add("password", password);
        body.add("userRole", userRole);
        HttpEntity<?> entity= new HttpEntity<Object>(body, headers);
        ResponseEntity<String> result = register.exchange(urlToken, HttpMethod.POST, entity, String.class);
    }
    /// randomize just userRole
    private void randomizeUpdate(){
        update = new RestTemplate();
        String urlToken = "localhost:8092/userapi//user/changeRole/"+userName;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("CONTENT_TYPE", String.valueOf(MediaType.APPLICATION_JSON));
        headers.add("Accept","application/json");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        userRole = TestUtils.getRole();
        body.add("userName", userName);
        body.add("password", password);
        body.add("userRole", userRole);

        HttpEntity<?> entity= new HttpEntity<Object>(body, headers);
        ResponseEntity<String> result = update.exchange(urlToken, HttpMethod.POST, entity, String.class);
    }
    private void randomizeRankRequest(){

    }

}
