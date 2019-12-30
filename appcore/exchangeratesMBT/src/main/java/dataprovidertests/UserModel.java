package dataprovidertests;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import provider.base.util.CommonUtils;

import java.util.HashMap;
import java.util.Random;

public class UserModel implements FsmModel {

    public enum State {
        INITIAL, ADMIN, STANDART, PREMIUM, SUCCESS, FAIL, NOT_EXIST, WRONG_PASSWORD, WRONG_ROLE, WRONG_BANKNAME
    }

    private State currentState = State.INITIAL;
    public UserAdapter userAdapter = new UserAdapter();

    Random random = new Random();

    @Override
    public Object getState() {
        return currentState;
    }

    @Override
    public void reset(boolean testing) {
        currentState = State.INITIAL;
    }

    @Action
    public void checkSuccess(){
        if(currentState == State.SUCCESS){
            currentState = State.INITIAL;
        }
    }
    @Action
    public void registerOrUpdate() {
        if (registerOrUpdateGuard()) {
            try {
                userAdapter.registerOrRandomizeUser();
                if (userAdapter.currentUser.getUserRole() == State.ADMIN.name()) {
                    currentState = State.ADMIN;
                } else if (userAdapter.currentUser.getUserRole() == State.PREMIUM.name()) {
                    currentState = State.PREMIUM;
                } else if (userAdapter.currentUser.getUserRole() == State.STANDART.name()) {
                    currentState = State.STANDART;
                }
            } catch (Exception e) {

            }
        }
    }

    public boolean registerOrUpdateGuard() {
        if (currentState == State.INITIAL) {
            return true;
        }
        return false;
    }

    @Action
    public void changeRole() {
        if (changeRoleGuard()) {
            userAdapter.changeRole();
            if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.ADMIN_ROLE)){
                currentState = State.ADMIN;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.PREMIUM_ROLE)){
                currentState = State.PREMIUM;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.STANDART_ROLE)){
                currentState = State.STANDART;
            }
        }

    }

    public boolean changeRoleGuard() {
        if (currentState == State.WRONG_ROLE || currentState == State.SUCCESS) {
            return true;
        }
        return false;
    }

    @Action
    public void makeRequest() throws Exception {
        if (makeRequestGuard()) {
            try {
                userAdapter.makeRequest();
                currentState = State.SUCCESS;
            } catch (Exception e) {
                currentState = State.FAIL;
                if (!isExceptionExpected()) {
                    throw new Exception("Unexpected result with: " + e.getMessage());
                }
                if (e.getMessage().contentEquals("No such User")) {
                    currentState = State.NOT_EXIST;
                } else if (e.getMessage().contentEquals("Wrong Password for the user")) {
                    currentState = State.WRONG_PASSWORD;
                } else if (e.getMessage().contentEquals("STANDART users cannot request for more than 2 banks")) {
                    currentState = State.WRONG_ROLE;
                } else if (e.getMessage().contentEquals("Choose among these bank Names:"
                        + CommonUtils.ykb + " " + CommonUtils.isb + " " + CommonUtils.dnz)) {
                    currentState = State.WRONG_BANKNAME;
                }

            }
        }

    }

    public boolean makeRequestGuard() {

        if (currentState == State.ADMIN || currentState == State.PREMIUM || currentState == State.STANDART) {
            return true;
        }
        return false;
    }

    @Action
    public void changeUserName() {
        if (changeUserNameGuard()) {
            userAdapter.changeName();
            if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.ADMIN_ROLE)){
                currentState = State.ADMIN;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.PREMIUM_ROLE)){
                currentState = State.PREMIUM;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.STANDART_ROLE)){
                currentState = State.STANDART;
            }
        }
    }

    public boolean changeUserNameGuard() {

        if (currentState == State.NOT_EXIST) {
            return true;
        }
        return false;
    }

    @Action
    public void changePassword() {
        if (changePasswordGuard()) {
            userAdapter.changePassword();
            if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.ADMIN_ROLE)){
                currentState = State.ADMIN;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.PREMIUM_ROLE)){
                currentState = State.PREMIUM;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.STANDART_ROLE)){
                currentState = State.STANDART;
            }
        }
    }

    public boolean changePasswordGuard() {

        if (currentState == State.WRONG_PASSWORD) {
            return true;
        }
        return false;
    }

    @Action
    public void changeBankNames() {
        if (changeBankNamesGuard()) {
            userAdapter.changeBankName();
            if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.ADMIN_ROLE)){
                currentState = State.ADMIN;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.PREMIUM_ROLE)){
                currentState = State.PREMIUM;
            }
            else if(userAdapter.currentUser.getUserRole().contentEquals(TestUtils.STANDART_ROLE)){
                currentState = State.STANDART;
            }
        }
    }

    public boolean changeBankNamesGuard() {

        if (currentState == State.WRONG_BANKNAME) {
            return true;
        }
        return false;
    }

    private boolean isExceptionExpected() {
        HashMap<String, String> mockBank = new HashMap<String, String>();
        mockBank.put("bankName", "mockBank");
        if (!userAdapter.users.contains(userAdapter.currentUser) || userAdapter.bankNames.contains(mockBank)) {
            return true;
        }
        String currentRole = userAdapter.currentUser.getUserRole();
        String currentUserName = userAdapter.currentUser.getUserName();
        String currentPassword = userAdapter.currentUser.getPassword();
        if(!TestUtils.USER_NAMES.contains(currentUserName) || !TestUtils.ROLES.contains(currentRole)
        || !TestUtils.PASSWORDS.contains(currentPassword)){
            return true;
        }
        return false;
    }

}
