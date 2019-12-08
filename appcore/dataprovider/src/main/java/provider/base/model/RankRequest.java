package provider.base.model;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class RankRequest {

    private String userName;

    private String password;



    private List<Map<String,String>> bankNames ;



    public RankRequest() {
    }
    public List<Map<String, String>> getBankNames() {
        return bankNames;
    }

    public void setBankNames(List<Map<String, String>> bankNames) {
        this.bankNames = bankNames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
