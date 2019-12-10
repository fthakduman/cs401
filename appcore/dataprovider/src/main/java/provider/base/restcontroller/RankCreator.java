package provider.base.restcontroller;

import collector.base.model.DNZRateImpl;
import collector.base.model.ISBRateImpl;
import collector.base.model.YKBRateImpl;
import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import provider.base.model.RankResponse;
import provider.base.model.RateImpl;
import provider.base.util.CommonUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
public class RankCreator implements Rank {

    @Autowired
    RateImpl rate; //Service which will do all data retrieval/manipulation work

    private DoubleRange A = new DoubleRange(0, 0.05);

    private DoubleRange Aminus = new DoubleRange(0.05, 0.10);

    private DoubleRange Bplus = new DoubleRange(0.10, 0.15);

    private DoubleRange B = new DoubleRange(0.15, 0.20);

    private DoubleRange Bminus = new DoubleRange(0.20, 0.30);

    private DoubleRange Cplus = new DoubleRange(0.30, 0.50);

    private DoubleRange C = new DoubleRange(0.50, 0.70);

    private DoubleRange Cminus = new DoubleRange(0.70, 0.90);

    private DoubleRange D = new DoubleRange(0.90, 1);

    private DoubleRange F = new DoubleRange(1, 10);


    StandardDeviation sd = new StandardDeviation(false);

    HashSet<Double> spreads = new HashSet<Double>();
    HashMap<String, DoubleRange> ranges = new HashMap<String, DoubleRange>();

    private List<String> bakNames = new ArrayList<String>();

    public List<String> getBakNames() {
        return bakNames;
    }

    public void setBankNames(List<String> bankNames) {
        this.bakNames = bankNames;
    }

    public RankCreator() {
        ranges.put("A", A);
        ranges.put("A-", Aminus);
        ranges.put("B+", Bplus);
        ranges.put("B", B);
        ranges.put("B-", Bminus);
        ranges.put("C", C);
        ranges.put("C-", Cminus);
        ranges.put("D", D);
        ranges.put("F", F);
    }

    public List<RankResponse> getRank(int year, Month month) {
        List<RankResponse> responses = new ArrayList<RankResponse>();
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        for (String bankName : bakNames) {
            if (bankName.equals(CommonUtils.ykb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.ykb);
                Page<YKBRateImpl> rates = rate.getYKBRates(year, month,pq);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                while(rates.hasNext()){
                     rates = rate.getYKBRates(year, month,rates.nextPageable());
                    rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                }
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.isb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.isb);
                Page<ISBRateImpl> rates = rate.getISBRates(year, month);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.dnz)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.dnz);
                Page<DNZRateImpl> rates = rate.getDNZRates(year, month);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }

        }
        return responses;

    }

    public List<RankResponse> getRank(int year, Month month, int currencyDayOfMonthValue) {
        List<RankResponse> responses = new ArrayList<RankResponse>();
        for (String bankName : bakNames) {
            if (bankName.equals(CommonUtils.ykb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.ykb);
                Page<YKBRateImpl> rates = rate.getYKBRates(year, month,currencyDayOfMonthValue);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.isb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.isb);
                Page<ISBRateImpl> rates = rate.getISBRates(year, month,currencyDayOfMonthValue);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.dnz)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.dnz);
                Page<DNZRateImpl> rates = rate.getDNZRates(year, month,currencyDayOfMonthValue);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }

        }
        return responses;


    }
    public List<RankResponse> getRank(int year, Month month, int currencyDayOfMonthValue,int hour) {
        List<RankResponse> responses = new ArrayList<RankResponse>();
        for (String bankName : bakNames) {
            if (bankName.equals(CommonUtils.ykb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.ykb);
                Page<YKBRateImpl> rates = rate.getYKBRates(year, month,currencyDayOfMonthValue,hour);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.isb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.isb);
                Page<ISBRateImpl> rates = rate.getISBRates(year, month,currencyDayOfMonthValue,hour);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }
            else if (bankName.equals(CommonUtils.dnz)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.dnz);
                Page<DNZRateImpl> rates = rate.getDNZRates(year, month,currencyDayOfMonthValue,hour);
                rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                getStandartDev(month, responses, response);
            }

        }
        return responses;
    }
    public List<RankResponse> getRankByHours(int hours) {
        LocalDateTime now = LocalDateTime.now();
        List<RankResponse> responses = new ArrayList<RankResponse>();
        for (String bankName : bakNames) {
            if (bankName.equals(CommonUtils.ykb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.ykb);
                long remaining = 0;
                while(hours - remaining > 0){
                    Page<YKBRateImpl> rates = rate.getYKBRates(now.getYear(), now.getMonth(),now.getDayOfMonth(),now.minusHours(remaining).getHour());
                    rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                    remaining++;
                }

                getStandartDev(now.getMonth(), responses, response);
            }
            else if (bankName.equals(CommonUtils.isb)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.isb);
                long remaining = 0;
                while(hours - remaining > 0){
                    Page<ISBRateImpl> rates = rate.getISBRates(now.getYear(), now.getMonth(),now.getDayOfMonth(),now.minusHours(remaining).getHour());
                    rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                    remaining++;
                }
                getStandartDev(now.getMonth(), responses, response);
            }
            else if (bankName.equals(CommonUtils.dnz)) {
                RankResponse response = new RankResponse();
                response.setBankName(CommonUtils.dnz);
                long remaining = 0;
                while(hours - remaining > 0){
                    Page<DNZRateImpl> rates = rate.getDNZRates(now.getYear(), now.getMonth(),now.getDayOfMonth(),now.minusHours(remaining).getHour());
                    rates.iterator().forEachRemaining(rate -> spreads.add(rate.getSellRate() - rate.getBuyRate()));
                    remaining++;
                }
                getStandartDev(now.getMonth(), responses, response);
            }
        }
        return responses;
    }

    private void getStandartDev(Month month, List<RankResponse> responses, RankResponse response) {
        double std = Statistic.getMeanAndStdDev(spreads.iterator(), new DoubleExtractor())[2];
        ranges.entrySet().forEach(range -> {
            if (range.getValue().containsNumber(std)) {
                response.setRank(range.getKey());
            }
        });
        response.setRankTimeFor(month.name());
        responses.add(response);
        spreads.clear();
    }

}