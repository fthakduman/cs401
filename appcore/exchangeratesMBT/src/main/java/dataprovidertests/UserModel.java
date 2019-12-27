package dataprovidertests;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import userapi.base.model.UserRole;

import java.util.ArrayList;
import java.util.Random;

public class UserModel implements FsmModel {

    public enum State {
        INITIAL, ADMIN, STANDART, PREMIUM, SUCCESS, FAIL,NOT_EXIST,WRONG_PASSWORD,WRONG_ROLE,WRONG_BANKNAME
    }
    private State currentState = State.INITIAL;
    private UserAdapter userAdapter = new UserAdapter();
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
    public void registerOrUpdate(){
        ArrayList<State> states = new ArrayList<>();
        states.add(State.ADMIN);
        states.add(State.STANDART);
        states.add(State.PREMIUM);
        if(registerOrUpdateGuard()){
            currentState = states.get(random.nextInt(states.size()));
        }

    }
    public boolean registerOrUpdateGuard(){
        if(currentState == State.INITIAL){
            return true;
        }
        return false;
    }
    @Action
    public void changeRole(){
        if(changeRoleGuard()){
            currentState = State.INITIAL;
        }

    }
    public boolean changeRoleGuard(){
        if(currentState == State.WRONG_ROLE){
            return true;
        }
    return false;
    }
    @Action
    public void makeRequest(){
        ArrayList<State> states = new ArrayList<>();
        states.add(State.SUCCESS);
        states.add(State.FAIL);
        if(makeRequestGuard()){
            currentState = states.get(random.nextInt(states.size()));
        }

    }
    public boolean makeRequestGuard(){

        if(currentState == State.ADMIN || currentState == State.PREMIUM || currentState == State.STANDART){
            return true;
        }
        return false;
    }
    @Action
    public void changeUserName(){
        if(changeRoleGuard()){
            currentState = State.INITIAL;
        }
    }
    public boolean changeUserNameGuard(){

        if(currentState == State.NOT_EXIST){
            return true;
        }
        return false;
    }
    @Action
    public void changePassword(){
        if(changePasswordGuard()){
            currentState = State.INITIAL;
        }
    }
    public boolean changePasswordGuard(){

        if(currentState == State.WRONG_PASSWORD){
            return true;
        }
        return false;
    }
    @Action
    public void changeBankNames(){
        if(changeBankNamesGuard()){
            currentState = State.INITIAL;
        }
    }
    public boolean changeBankNamesGuard(){

        if(currentState == State.WRONG_BANKNAME){
            return true;
        }
        return false;
    }
    @Action
    public void deleteUser(){
        if(deleteUserGuard()){
            currentState = State.INITIAL;
        }
    }
    public boolean deleteUserGuard(){

        if(currentState == State.SUCCESS){
            return true;
        }
        return false;
    }


}
