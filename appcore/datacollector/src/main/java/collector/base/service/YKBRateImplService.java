package collector.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import collector.base.model.YKBRateImpl;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface YKBRateImplService {
    YKBRateImpl save(YKBRateImpl book);

    void delete(YKBRateImpl ykbRate);

    YKBRateImpl findOne(String id);

    Iterable<YKBRateImpl> findAll();

    public Page<YKBRateImpl> findMonthly(int year, Month month, Pageable pq);

    public Page<YKBRateImpl> findDaily(int year, Month month,int dayValue, Pageable pq);

    public Page<YKBRateImpl> findDailyInterval(int year, Month month,int begin,int end, Pageable pq);

    public Page<YKBRateImpl> findHourly(int year, Month month,int dayValue,int hour, Pageable pq);

    public Page<YKBRateImpl> findHourlyInterval(int year, Month month,int dayValue,int begin,int end, Pageable pq);


    public Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName, PageRequest pageRequest);

}
