package provider.base.service;

import provider.base.model.RankRequest;

public interface UserCheck {

    public boolean isUserExist(RankRequest request);
    public boolean isPasswordValid(RankRequest request);
    public boolean isBankNumberValid(RankRequest request);
    public boolean isBankNameValid(RankRequest request);
    public boolean isRequestKeyValid(RankRequest request);
}
