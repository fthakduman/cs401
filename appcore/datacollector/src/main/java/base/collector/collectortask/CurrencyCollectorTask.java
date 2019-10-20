package base.collector.collectortask;

import adaptedservices.AdaptedYKBService;
import base.collector.service.YKBRateImplServiceImpl;
import base.collector.ykbmodel.YKBRateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class CurrencyCollectorTask {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyCollectorTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private YKBRateImplServiceImpl ykbRateImplService;


    @Scheduled(cron = "0 * * * * ?")
    public void scheduleYKBDataCollector() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        YKBRateImpl ykbRate = new YKBRateImpl("YKB", "USD", "TL");
        AdaptedYKBService adapter = AdaptedYKBService.getInstance("YKB", "USD", "TL");
        adapter.makeRequest();
        ykbRate.setSellRate(adapter.getSellRate());
        ykbRate.setBuyRate(adapter.getBuyRate());
        ykbRate.setCurrencyYear(adapter.getRequestTime().getYear());
        ykbRateImplService.save(ykbRate);
    }
}