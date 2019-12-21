package dataprovidertests;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import provider.base.model.RankRequest;
import provider.base.model.RankResponse;
import provider.base.util.CommonUtils;
import userapi.base.model.UserImpl;

import java.net.URI;

public class UserAdapter {
    RestTemplate register;
    RestTemplate update;
    RestTemplate rankRequest;
    String userName;
    String password;
    String userRole;
    boolean isRegistered =false;


    public void register(){
        if(isRegistered == false){
            randomizeRegister();
            isRegistered=true;
        }

    }
    public void update(){
        if(isRegistered == true){
            randomizeRole();
        }
    }

    public void delete(boolean isSingle){
        RestTemplate delete = new RestTemplate();
        String urlToken;
        if(isSingle) urlToken = TestUtils.DELETE_USER+userName;
        else {
            urlToken = TestUtils.DELETE_USER_ALL;
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("CONTENT_TYPE", String.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity= new HttpEntity<Object>(headers);
        ResponseEntity<String> result = delete.exchange(urlToken, HttpMethod.DELETE, entity, String.class);
    }

    public void changePassword()throws Exception{
        password = TestUtils.getPassword();
        randomizeRankRequest();
    }

    public void changeRole()throws Exception{
        userRole = TestUtils.getRole();
        randomizeRankRequest();
    }

    public void changeName()throws Exception{
        userName = TestUtils.getUsername();
        randomizeRankRequest();
    }

    public void makeRequest()throws Exception{
        randomizeRankRequest();
    }

    public void changeBankName() throws Exception{
        randomizeRankRequest();
    }
    /// randomize just user info for userapi executed once
    private void randomizeRegister(){
        register = new RestTemplate();
        String urlToken = TestUtils.REGISTER_USER;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        headers.add("Accept","application/json");
        UserImpl body = new UserImpl();
        userName = TestUtils.getUsername();
        password = TestUtils.getPassword();
        userRole = TestUtils.getRole();
        body.setUserName(userName);
        body.setPassword(password);
        body.setUserRole(userRole);
        RequestEntity<UserImpl> entity = new RequestEntity<UserImpl>(body,headers,HttpMethod.POST, URI.create(TestUtils.REGISTER_USER));
        ResponseEntity<UserImpl> result = register.exchange(entity, UserImpl.class);
        System.out.println("randomizeRegister:" + result);
    }
    /// randomize just userRole
    private void randomizeRole(){
        update = new RestTemplate();
        String urlToken = "http://localhost:8093/userapi/user/changeRole/"+userName;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        headers.add("Accept","application/json");
        UserImpl body = new UserImpl();
        userRole = TestUtils.getRole();
        body.setUserName(userName);
        body.setPassword(password);
        body.setUserRole(userRole);

        RequestEntity<UserImpl> entity = new RequestEntity<UserImpl>(body,headers,HttpMethod.PUT, URI.create(TestUtils.REGISTER_USER));
        ResponseEntity<UserImpl> result = register.exchange(entity, UserImpl.class);
    }
    private void randomizeRankRequest() throws Exception{
        rankRequest = new RestTemplate();
        RankRequest request = new RankRequest();
        request.setUserName(userName);
        request.setPassword(password);
        request.setBankNames(TestUtils.getBankNames());
        String urlToken = TestUtils.getProviderUrl();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept","application/json");

        RequestEntity<RankRequest> entity = new RequestEntity<RankRequest>(request,headers,HttpMethod.GET, URI.create(TestUtils.getProviderUrl()));
        ResponseEntity<RankResponse> result = rankRequest.exchange(entity, RankResponse.class);
        checkRankResult(result);

    }
    private void checkRankResult(ResponseEntity<RankResponse> result) throws Exception{
        if(result.getStatusCode() != HttpStatus.OK) {
            if(result.getBody().toString().contentEquals("No such User")){
                throw new Exception(result.getBody().toString());
            }
            else if(result.getBody().toString().contentEquals("Wrong Password for the user")){
                throw new Exception(result.getBody().toString());
            }
            else if(result.getBody().toString().contentEquals("STANDART users cannot request for more than 2 banks")){
                throw new Exception(result.getBody().toString());
            }
            else if(result.getBody().toString().contentEquals(
                    "Choose among these bank Names:"
                            + CommonUtils.ykb + " " + CommonUtils.isb + " " + CommonUtils.dnz
            )){
                throw new Exception(result.getBody().toString());
            }

        }
    }
    private void checkUserResult( ResponseEntity<UserImpl> result) throws Exception {

    }

}
