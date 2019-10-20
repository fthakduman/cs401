package base;

import adaptedservices.AdaptedYKBService;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import base.collector.service.YKBRateImplServiceImpl;
import base.collector.ykbmodel.YKBRateImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class Application  extends SpringBootServletInitializer implements CommandLineRunner{

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
        Page<YKBRateImpl> rates = ykbRateImplService.findYKBRateImplByBankNameEquals("YKB", new PageRequest(0, 10));

        //List<Book> books = ykbRateImplService.findByTitle("Elasticsearch Basics");

        rates.forEach(x -> System.out.println(x));

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
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