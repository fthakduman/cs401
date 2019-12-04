package provider.base.model;


import collector.base.model.DNZRateImpl;
import collector.base.model.ISBRateImpl;
import collector.base.model.YKBRateImpl;
import collector.base.repository.DNZRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import provider.base.service.DNZWrap;
import provider.base.service.ISBWrap;
import provider.base.service.YKBWrap;
import provider.base.util.CommonUtils;

import java.time.Month;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Component
public class RateImpl implements Rate {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    @Qualifier("ykbwrapper")
    YKBWrap ykbWrap;

    @Autowired
    @Qualifier("dnzwrapper")
    DNZWrap dnzWrap;

    @Autowired
    @Qualifier("isbwrapper")
    ISBWrap isbWrap;

    @Autowired
    DNZRepository dnzRepository;

    public List<YKBRateImpl> getYKBRates(int year) {
        List<YKBRateImpl> result = new ArrayList<YKBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withIndices("rates")
                .withTypes("ykb")
                .build();


        return result;

    }

    public Page<YKBRateImpl> getYKBRates(int year, Month month) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<YKBRateImpl> ykbRates = ykbWrap.findMonthly(year, month, pq);

        return ykbRates;

    }

    public Page<YKBRateImpl> getYKBRates(int year, Month month, int currencyDayofMonthValue) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<YKBRateImpl> ykbRates = ykbWrap.findDaily(year, month, currencyDayofMonthValue, pq);

        return ykbRates;

    }

    public Page<YKBRateImpl> getYKBRates(int year, Month month, int currencyDayofMonthValue, int hour) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_HOURLY);
        Page<YKBRateImpl> ykbRates = ykbWrap.findHourly(year, month, currencyDayofMonthValue, hour, pq);

        return ykbRates;

    }

    public Page<YKBRateImpl> getYKBRatesHInterval(int year, Month month, int currencyDayofMonthValue, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_HOURLY);
        Page<YKBRateImpl> ykbRates = ykbWrap.findHourlyInterval(year, month, currencyDayofMonthValue, begin,end, pq);

        return ykbRates;

    }
    public Page<YKBRateImpl> getYKBRatesDInterval(int year, Month month, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_HOURLY);
        Page<YKBRateImpl> ykbRates = ykbWrap.findDailyInterval(year, month, begin,end, pq);

        return ykbRates;

    }

    public List<DNZRateImpl> getDNZRates(int year) {

        List<DNZRateImpl> result = new ArrayList<DNZRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("dnz")
                .build();


        Iterator<DNZRateImpl> dnzRateIterator = elasticsearchTemplate.stream(build, DNZRateImpl.class);
        while (dnzRateIterator.hasNext()) {
            result.add(dnzRateIterator.next());
        }

        return result;
    }

    public Page<DNZRateImpl> getDNZRates(int year, Month month) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<DNZRateImpl> dnzRates = dnzWrap.findMonthly(year, month, pq);

        return dnzRates;
    }

    public Page<DNZRateImpl> getDNZRates(int year, Month month, int currencyDayofMonthValue) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<DNZRateImpl> dnzRates = dnzWrap.findDaily(year, month, currencyDayofMonthValue, pq);

        return dnzRates;
    }

    public Page<DNZRateImpl> getDNZRates(int year, Month month, int currencyDayofMonthValue, int hour) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_HOURLY);
        Page<DNZRateImpl> dnzRates = dnzWrap.findHourly(year, month, currencyDayofMonthValue, hour, pq);

        return dnzRates;
    }

    public Page<DNZRateImpl> getDNZRatesHInterval(int year, Month month, int currencyDayofMonthValue, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<DNZRateImpl> dnzRates = dnzWrap.findHourlyInterval(year, month, currencyDayofMonthValue, begin,end, pq);

        return dnzRates;

    }
    public Page<DNZRateImpl> getDNZRatesDInterval(int year, Month month, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<DNZRateImpl> dnzRates = dnzWrap.findDailyInterval(year, month, begin,end, pq);

        return dnzRates;

    }

    public List<ISBRateImpl> getISBRates(int year) {

        List<ISBRateImpl> result = new ArrayList<ISBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("isb")
                .build();

        Iterator<ISBRateImpl> isbRateIterator = elasticsearchTemplate.stream(build, ISBRateImpl.class);
        while (isbRateIterator.hasNext()) {
            result.add(isbRateIterator.next());
        }

        return result;
    }

    public Page<ISBRateImpl> getISBRates(int year, Month month) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<ISBRateImpl> isbRates = isbWrap.findMonthly(year, month, pq);

        return isbRates;
    }

    public Page<ISBRateImpl> getISBRates(int year, Month month, int currencyDayofMonthValue) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<ISBRateImpl> isbRates = isbWrap.findDaily(year, month, currencyDayofMonthValue, pq);

        return isbRates;
    }

    public Page<ISBRateImpl> getISBRates(int year, Month month, int currencyDayofMonthValue, int hour) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_HOURLY);
        Page<ISBRateImpl> isbRates = isbWrap.findHourly(year, month, currencyDayofMonthValue, hour, pq);

        return isbRates;
    }

    public Page<ISBRateImpl> getISBRatesHInterval(int year, Month month, int currencyDayofMonthValue, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<ISBRateImpl> isbRates = isbWrap.findHourlyInterval(year, month, currencyDayofMonthValue, begin,end, pq);

        return isbRates;

    }
    public Page<ISBRateImpl> getISBRatesDInterval(int year, Month month, int begin, int end) {
        PageRequest pq = new PageRequest(0, CommonUtils.RATE_SIZE_DAILY);
        Page<ISBRateImpl> isbRates = isbWrap.findDailyInterval(year, month, begin,end, pq);

        return isbRates;

    }

//    {
//        "query": {
//        "bool": {
//            "must":
//      [
//            { "match": { "currencyYear": 2019 }},
//            { "match": { "currencyMonth": "DECEMBER" }}
//          ]
//        }
//    }
//    }

}