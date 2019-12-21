package dataprovidertests;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import userapi.base.model.UserRole;

public class UserModel implements FsmModel {

    public enum State {
        INITIAL, ADMIN, STANDART, PREMIUM, SUCCESS, FAIL,NOT_EXIST,WRONG_PASSWORD,WRONG_ROLE,WRONG_BANKNAME
    }
    private State currentState = State.INITIAL;
    private UserAdapter userAdapter = new UserAdapter();

    @Override
    public Object getState() {
        return currentState;
    }

    @Override
    public void reset(boolean testing) {
        currentState = State.INITIAL;
        userAdapter.delete(false);
    }



    @Action
    public void register(){

        try{
            if(currentState == State.INITIAL){
                userAdapter.register();
                userAdapter.update();
            }
            if(userAdapter.userRole == UserRole.ADMIN_ROLE){
                currentState = State.ADMIN;
            }
            else if(userAdapter.userRole == UserRole.STANDART_ROLE){
                currentState = State.STANDART;
            }
            else if(userAdapter.userRole == UserRole.PREMIUM_ROLE){
                currentState = State.PREMIUM;
            }
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
    @Action
    public void changeRole(){
        try{
            if(currentState == State.INITIAL){
                userAdapter.changeRole();
                if(userAdapter.userRole == UserRole.ADMIN_ROLE){
                    currentState = State.ADMIN;
                }
                else if(userAdapter.userRole == UserRole.STANDART_ROLE){
                    currentState = State.STANDART;
                }
                else if(userAdapter.userRole == UserRole.PREMIUM_ROLE){
                    currentState = State.PREMIUM;
                }
            }
            else if (currentState == State.WRONG_ROLE){
                userAdapter.changeRole();
                if(userAdapter.userRole == UserRole.ADMIN_ROLE){
                    currentState = State.ADMIN;
                }
                else if(userAdapter.userRole == UserRole.STANDART_ROLE){
                    currentState = State.STANDART;
                }
                else if(userAdapter.userRole == UserRole.PREMIUM_ROLE){
                    currentState = State.PREMIUM;
                }
            }
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
    @Action
    public void makeRequest(){
        try{
            if(currentState == State.ADMIN || currentState == State.PREMIUM || currentState == State.STANDART){
                userAdapter.makeRequest();
                currentState = State.SUCCESS;
            }
        }
        catch (Exception e ){
            currentState = State.FAIL;
            ///////////// set state acordıng to fail type by exception
            System.out.println(e.getMessage());
            currentState = State.FAIL;
        }

    }
    @Action
    public void changeUserName(){
        try{
            if(currentState == State.NOT_EXIST){
                userAdapter.changeName();
                currentState = State.INITIAL;
            }
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
    @Action
    public void changePassword(){
        try{
            if(currentState == State.WRONG_PASSWORD){
                userAdapter.changePassword();
                currentState = State.INITIAL;
            }
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
    @Action
    public void changeBankNames(){
        try{
            if(currentState == State.WRONG_BANKNAME){
                userAdapter.changeBankName();
                currentState = State.INITIAL;
            }
        }
        catch (Exception e ){

        }

    }
    @Action
    public void deleteUser(){
        try{
            if(currentState == State.SUCCESS){
                userAdapter.delete(true);
                currentState = State.INITIAL;
            }
        }
        catch (Exception e ){

        }

    }


}
