package provider.base.service;

import collector.base.model.ISBRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface ISBWrap {

    public Page<ISBRateImpl> findMonthly(int year, Month month, Pageable pq);

    public Page<ISBRateImpl> findDaily(int year, Month month,int dayValue, Pageable pq);

    public Page<ISBRateImpl> findDailyInterval(int year, Month month,int begin,int end, Pageable pq);

    public Page<ISBRateImpl> findHourly(int year, Month month,int dayValue,int hour, Pageable pq);

    public Page<ISBRateImpl> findHourlyInterval(int year, Month month,int dayValue,int begin,int end, Pageable pq);
}
