package dataprovidertests;

import nz.ac.waikato.modeljunit.Action;
import org.springframework.web.client.RestTemplate;

public class UserAdapter {
    RestTemplate request;
    boolean isRegistered =false;

    @Action
    public void registerOrUpdate(){

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

    private void randomizeRequest(){
        request = new RestTemplate();
    }
}
