package dataprovidertests;

import nz.ac.waikato.modeljunit.FsmModel;

public class UserModel implements FsmModel {

    public enum State {
        INITIAL, ADMIN, STANDART, PREMIUM, SUCCESS, FAIL,NOT_EXIST,WRONG_PASSWORD,WRONG_ROLE,WRONG_BANKNAME
    }


    @Override
    public Object getState() {
        return null;
    }

    @Override
    public void reset(boolean b) {

    }


}
