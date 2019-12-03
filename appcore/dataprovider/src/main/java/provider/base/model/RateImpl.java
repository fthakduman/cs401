package provider.base.model;


import collector.base.model.DNZRateImpl;
import collector.base.model.ISBRateImpl;
import collector.base.model.YKBRateImpl;
import collector.base.repository.YKBRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Component
public class RateImpl implements Rate {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    YKBRepository ykbRepositor;

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
    public List<YKBRateImpl> getYKBRates(int year, Month month) {
        List<YKBRateImpl> result = new ArrayList<YKBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(matchQuery("currencyYear", year));
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withIndices("rates")
                .withTypes("ykb")
                .build();

        PageRequest pq = new PageRequest(0, 1000);

        Page<YKBRateImpl> page = ykbRepositor.findYKBRateImplByBankNameEquals("YapıKrediBank",pq);

        return page.getContent();

    }
    public List<YKBRateImpl> getYKBRates(int year, Month month,int currencyDayofMonthValue) {
        List<YKBRateImpl> result = new ArrayList<YKBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue", currencyDayofMonthValue));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("ykb")
                .build();

        Iterator<YKBRateImpl> ykbRates = elasticsearchTemplate.stream(build, YKBRateImpl.class);
        while(ykbRates.hasNext()){
            result.add(ykbRates.next());
        }

        return result;

    }
    public List<YKBRateImpl> getYKBRates(int year, Month month,int currencyDayofMonthValue, int hour) {
        List<YKBRateImpl> result = new ArrayList<YKBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue", currencyDayofMonthValue))
                .must(termQuery("hour", hour));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("ykb")
                .build();

        Iterator<YKBRateImpl> ykbRates = elasticsearchTemplate.stream(build, YKBRateImpl.class);
        while(ykbRates.hasNext()){
            result.add(ykbRates.next());
        }

        return result;

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
        while(dnzRateIterator.hasNext()){
            result.add(dnzRateIterator.next());
        }

        return result;
    }
    public List<DNZRateImpl> getDNZRates(int year, Month month) {
        List<DNZRateImpl> result = new ArrayList<DNZRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("dnz")
                .build();

        Iterator<DNZRateImpl> dnzRateIterator = elasticsearchTemplate.stream(build, DNZRateImpl.class);
        while(dnzRateIterator.hasNext()){
            result.add(dnzRateIterator.next());
        }

        return result;
    }

    public List<DNZRateImpl> getDNZRates(int year, Month month ,int currencyDayofMonthValue) {
        List<DNZRateImpl> result = new ArrayList<DNZRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue",currencyDayofMonthValue));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("dnz")
                .build();


        Iterator<DNZRateImpl> dnzRateIterator = elasticsearchTemplate.stream(build, DNZRateImpl.class);
        while(dnzRateIterator.hasNext()){
            result.add(dnzRateIterator.next());
        }

        return result;
    }
    public List<DNZRateImpl> getDNZRates(int year, Month month ,int currencyDayofMonthValue,int hour) {
        List<DNZRateImpl> result = new ArrayList<DNZRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue",currencyDayofMonthValue))
                .must(termQuery("hour",hour));


        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("dnz")
                .build();

        Iterator<DNZRateImpl> dnzRateIterator = elasticsearchTemplate.stream(build, DNZRateImpl.class);
        while(dnzRateIterator.hasNext()){
            result.add(dnzRateIterator.next());
        }

        return result;
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
        while(isbRateIterator.hasNext()){
            result.add(isbRateIterator.next());
        }

        return result;
    }

    public List<ISBRateImpl> getISBRates(int year, Month month) {
        List<ISBRateImpl> result = new ArrayList<ISBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("isb")
                .build();

        Iterator<ISBRateImpl> isbRateIterator = elasticsearchTemplate.stream(build, ISBRateImpl.class);
        while(isbRateIterator.hasNext()){
            result.add(isbRateIterator.next());
        }

        return result;
    }

    public List<ISBRateImpl> getISBRates(int year, Month month,int currencyDayofMonthValue) {
        List<ISBRateImpl> result = new ArrayList<ISBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue", currencyDayofMonthValue));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("isb")
                .build();

        Iterator<ISBRateImpl> isbRateIterator = elasticsearchTemplate.stream(build, ISBRateImpl.class);
        while(isbRateIterator.hasNext()){
            result.add(isbRateIterator.next());
        }

        return result;
    }
    public List<ISBRateImpl> getISBRates(int year, Month month,int currencyDayofMonthValue, int hour) {
        List<ISBRateImpl> result = new ArrayList<ISBRateImpl>();
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(termQuery("currencyYear", year))
                .must(termQuery("currencyMonth", month.name()))
                .must(termQuery("currencyDayofMonthValue", currencyDayofMonthValue))
                .must(termQuery("hour", hour));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withTypes("isb")
                .build();

        Iterator<ISBRateImpl> isbRateIterator = elasticsearchTemplate.stream(build, ISBRateImpl.class);
        while(isbRateIterator.hasNext()){
            result.add(isbRateIterator.next());
        }

        return result;
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