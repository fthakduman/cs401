package provider.base.service;

import collector.base.model.DNZRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Month;

public interface DNZWrap {

    public Page<DNZRateImpl> findMonthly(int year, Month month, Pageable pq);

    public Page<DNZRateImpl> findDaily(int year, Month month,int dayValue, Pageable pq);

    public Page<DNZRateImpl> findDailyInterval(int year, Month month,int begin,int end, Pageable pq);

    public Page<DNZRateImpl> findHourly(int year, Month month,int dayValue,int hour, Pageable pq);

    public Page<DNZRateImpl> findHourlyInterval(int year, Month month,int dayValue,int begin,int end, Pageable pq);
}
