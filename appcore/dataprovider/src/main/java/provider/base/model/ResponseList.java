package provider.base.model;

import java.util.List;

public class ResponseList {

    private List<RankResponse> rankResponses;

    public ResponseList(List<RankResponse> rankResponses) {
        this.rankResponses = rankResponses;
    }

    public List<RankResponse> getRankResponses() {
        return rankResponses;
    }

    public void setRankResponses(List<RankResponse> rankResponses) {
        this.rankResponses = rankResponses;
    }
}
