package base;

import adaptedservices.AdaptedYKBService;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import base.collector.service.YKBRateImplServiceImpl;
import base.collector.ykbmodel.YKBRateImpl;

import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private YKBRateImplServiceImpl ykbRateImplService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

        YKBRateImpl ykbRate = new YKBRateImpl("YKB", "USD", "TL");
        AdaptedYKBService adapter = AdaptedYKBService.getInstance("YKB", "USD", "TL");
        adapter.makeRequest();
        ykbRate.setSellRate(adapter.getSellRate());
        ykbRate.setBuyRate(adapter.getBuyRate());
        ykbRate.setCurrencyYear(adapter.getRequestTime().getYear());
        adapter.refresh();
        ykbRateImplService.save(ykbRate);


        //fuzzey search
        Page<YKBRateImpl> books = ykbRateImplService.findYKBRateImplByBankNameEquals("Rambabu", new PageRequest(0, 10));


        //List<Book> books = ykbRateImplService.findByTitle("Elasticsearch Basics");

        books.forEach(x -> System.out.println(x));


    }

    //useful for debug, print elastic search details
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch--");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--ElasticSearch--");
    }

}