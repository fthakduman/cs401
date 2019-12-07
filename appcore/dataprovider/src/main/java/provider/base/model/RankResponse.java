package provider.base.model;

import java.util.HashMap;
import java.util.Map;

public class RankResponse {

    private String bankName;

    private String rank;

    private String rankTimeFor;


    public RankResponse() {

    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankTimeFor() {
        return rankTimeFor;
    }

    public void setRankTimeFor(String rankTimeFor) {
        this.rankTimeFor = rankTimeFor;
    }
}
