package collector.base.collectortask;

import adaptedservices.AdaptedDNZService;
import adaptedservices.AdaptedISBService;
import adaptedservices.AdaptedYKBService;
import collector.base.model.DNZRateImpl;
import collector.base.model.ISBRateImpl;
import collector.base.service.DNZRateImplServiceImpl;
import collector.base.service.ISBRateImplServiceImpl;
import collector.base.service.YKBRateImplServiceImpl;
import collector.base.model.YKBRateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CurrencyCollectorTask {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyCollectorTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    @Qualifier("ykbservice")
    private YKBRateImplServiceImpl ykbRateImplService;

    @Autowired
    @Qualifier("dnzservice")
    private DNZRateImplServiceImpl dnzRateImplService;

    @Autowired
    @Qualifier("isbservice")
    private ISBRateImplServiceImpl isbRateImplService;

    @Scheduled(cron = "0 * * * * ?")
    public void scheduleDataCollector() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        executeYKBCollector();
        executeDNZCollector();
        executeISBCollector();
    }

    private void executeYKBCollector(){
        YKBRateImpl ykbRate = new YKBRateImpl( "USD", "TL");
        AdaptedYKBService adapter = AdaptedYKBService.getInstance( "USD", "TL");
        adapter.makeRequest();
        ykbRate.setSellRate(adapter.getSellRate());
        ykbRate.setBuyRate(adapter.getBuyRate());
        ykbRate.setCurrencyYear(adapter.getRequestTime().getYear());
        ykbRate.setCurrencyMonth(adapter.getRequestTime().getMonth());
        ykbRate.setCurrencyMonthValue(adapter.getRequestTime().getMonthValue());
        ykbRate.setCurrencyDayOfWeek(adapter.getRequestTime().getDayOfWeek());
        ykbRate.setCurrencyDayOfMonthValue(adapter.getRequestTime().getMonthValue());
        ykbRate.setCurrencyHour(adapter.getRequestTime().getHour());
        ykbRate.setMinute(adapter.getRequestTime().getMinute());
        ykbRateImplService.save(ykbRate);
        adapter.refresh();
    }
    private void executeDNZCollector(){
        DNZRateImpl dnzRate = new DNZRateImpl( "USD", "TL");
        AdaptedDNZService adapter = AdaptedDNZService.getInstance( "USD", "TL");
        adapter.makeRequest();
        dnzRate.setSellRate(adapter.getSellRate());
        dnzRate.setBuyRate(adapter.getBuyRate());
        dnzRate.setCurrencyYear(adapter.getRequestTime().getYear());
        dnzRate.setCurrencyMonth(adapter.getRequestTime().getMonth());
        dnzRate.setCurrencyMonthValue(adapter.getRequestTime().getMonthValue());
        dnzRate.setCurrencyDayOfWeek(adapter.getRequestTime().getDayOfWeek());
        dnzRate.setCurrencyDayOfMonthValue(adapter.getRequestTime().getMonthValue());
        dnzRate.setCurrencyHour(adapter.getRequestTime().getHour());
        dnzRate.setCurrencyMinute(adapter.getRequestTime().getMinute());
        dnzRateImplService.save(dnzRate);
        adapter.refresh();
    }
    private void executeISBCollector(){
        ISBRateImpl isbRate = new ISBRateImpl( "USD", "TL");
        AdaptedISBService adapter = AdaptedISBService.getInstance( "USD", "TL");
        adapter.makeRequest();
        isbRate.setSellRate(adapter.getSellRate());
        isbRate.setBuyRate(adapter.getBuyRate());
        isbRate.setCurrencyYear(adapter.getRequestTime().getYear());
        isbRate.setCurrencyMonth(adapter.getRequestTime().getMonth());
        isbRate.setCurrencyMonthValue(adapter.getRequestTime().getMonthValue());
        isbRate.setCurrencyDayOfWeek(adapter.getRequestTime().getDayOfWeek());
        isbRate.setCurrencyDayOfMonthValue(adapter.getRequestTime().getMonthValue());
        isbRate.setCurrencyHour(adapter.getRequestTime().getHour());
        isbRate.setCurrencyMinute(adapter.getRequestTime().getMinute());
        isbRateImplService.save(isbRate);
        adapter.refresh();
    }
}