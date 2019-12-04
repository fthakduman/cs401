package provider.base.model;

import java.util.HashMap;
import java.util.Map;

public class RankResponse {

    Map<String, String> ranks = new HashMap<String, String>();

    public RankResponse() {

    }

    public Map<String, String> getRanks() {
        return ranks;
    }

    public void setRanks(Map<String, String> ranks) {
        this.ranks = ranks;
    }

    public void addRanks(String bankName, String rank) {
        this.ranks.put(bankName,rank);
    }
}
