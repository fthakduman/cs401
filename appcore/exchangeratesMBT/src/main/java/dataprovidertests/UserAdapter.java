package dataprovidertests;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import provider.base.model.RankRequest;
import provider.base.model.RankResponse;
import provider.base.util.CommonUtils;
import userapi.base.model.UserImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAdapter {
    RestTemplate register;
    RestTemplate update;
    RestTemplate rankRequest;
    UserImpl currentUser;
    List<Map<String, String>> bankNames;
    List<UserImpl> users = new ArrayList<UserImpl>();

    boolean isRegistered = false;

    public UserAdapter(){
        TestUtils.assignValues();
        for(int i=0; i<TestUtils.USER_NAMES.size(); i++ ){
            UserImpl user = new UserImpl();
            user.setUserName(TestUtils.USER_NAMES.get(i));
            user.setPassword(TestUtils.PASSWORDS.get(i));
            user.setUserRole(TestUtils.getRole());
            users.add(user);
        }
        bankNames = TestUtils.getBankNames();
    }


    public void registerOrRandomizeUser() throws Exception{

        if (isRegistered == false) {
            users.forEach(user -> {
                randomizeRegister(user);
                currentUser = user;
            });
            isRegistered = true;
        }
        else {
            currentUser.setUserName(TestUtils.getManipulatedUserName());
            currentUser.setPassword(TestUtils.getManipulatedPassword());
            currentUser.setUserRole(TestUtils.getManipulatedRole());
        }


    }

    public void delete(boolean isSingle) {
        RestTemplate delete = new RestTemplate();
        String urlToken;
        if (isSingle) urlToken = TestUtils.DELETE_USER + currentUser.getUserName();
        else {
            urlToken = TestUtils.DELETE_USER_ALL;
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("Content-Type", String.valueOf(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> result = delete.exchange(urlToken, HttpMethod.DELETE, entity, String.class);
    }

    public void changePassword()  {
        currentUser.setPassword(TestUtils.getManipulatedPassword());
    }

    public void changeRole() {
        currentUser.setUserRole(TestUtils.getManipulatedRole());

    }

    public void changeName()  {
        currentUser.setUserName(TestUtils.getManipulatedUserName());

    }

    public void makeRequest() throws Exception {
        randomizeRankRequest(currentUser);
    }

    public void changeBankName()  {
        bankNames = TestUtils.getBankNames();
    }

    /// randomize just user info for userapi executed once
    private void randomizeRegister(UserImpl body) {
        register = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", "application/json");

        RequestEntity<UserImpl> entity = new RequestEntity<UserImpl>(body, headers, HttpMethod.POST, URI.create(TestUtils.REGISTER_USER));
        ResponseEntity<UserImpl> result = register.exchange(entity, UserImpl.class);

    }


    private void randomizeUpdateUser(UserImpl body,String urlToken) {
        update = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        //headers.add("CONTENT_TYPE", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        headers.add("Accept", "application/json");

        RequestEntity<UserImpl> entity = new RequestEntity<UserImpl>(body, headers, HttpMethod.PUT, URI.create(urlToken));

        ResponseEntity<UserImpl> result = update.exchange(entity, UserImpl.class);

    }

    private void randomizeRankRequest(UserImpl body) throws Exception {
        rankRequest = new RestTemplate();
        RankRequest request = new RankRequest();
        request.setUserName(body.getUserName());
        request.setPassword(body.getPassword());
        request.setBankNames(bankNames);
        String urlToken = TestUtils.getProviderUrl();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        headers.add("Accept", "application/json");
        ParameterizedTypeReference<List<RankResponse>> responseType = new ParameterizedTypeReference<List<RankResponse>>() {
        };
        RequestEntity<RankRequest> entity = new RequestEntity<RankRequest>(request, headers, HttpMethod.POST, URI.create(urlToken));
        ResponseEntity<List<RankResponse>> result = null;
        try {
            result = rankRequest.exchange(entity, responseType);
        } catch (HttpClientErrorException h) {
            checkRankResult(h);
        }


    }

    private void checkRankResult(HttpClientErrorException result) throws Exception {
        if (result.getStatusCode() != HttpStatus.OK) {
            if (result.getResponseBodyAsString().contentEquals("No such User")) {
                throw new Exception(result.getResponseBodyAsString());
            } else if (result.getResponseBodyAsString().contentEquals("Wrong Password for the user")) {
                throw new Exception(result.getResponseBodyAsString());
            } else if (result.getResponseBodyAsString().contentEquals("STANDART users cannot request for more than 2 banks")) {
                throw new Exception(result.getResponseBodyAsString());
            } else if (result.getResponseBodyAsString().contentEquals(
                    "Choose among these bank Names:"
                            + CommonUtils.ykb + " " + CommonUtils.isb + " " + CommonUtils.dnz
            )) {
                throw new Exception(result.getResponseBodyAsString());
            }

        }
    }
}
