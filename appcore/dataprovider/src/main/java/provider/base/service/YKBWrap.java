package provider.base.service;

import collector.base.model.YKBRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface YKBWrap {

    public Page<YKBRateImpl> findMonthly(int year, Month month, Pageable pq);

    public Page<YKBRateImpl> findDaily(int year, Month month,int dayValue, Pageable pq);

    public Page<YKBRateImpl> findDailyInterval(int year, Month month,int begin,int end, Pageable pq);

    public Page<YKBRateImpl> findHourly(int year, Month month,int dayValue,int hour, Pageable pq);

    public Page<YKBRateImpl> findHourlyInterval(int year, Month month,int dayValue,int begin,int end, Pageable pq);
}
