package provider.base.service;

import collector.base.model.DNZRateImpl;
import collector.base.repository.DNZRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;


@Service
@Qualifier("dnzwrapper")
public class DNZWrapper implements DNZWrap {

    @Autowired
    private DNZRepository dnzRepository;


    @Override
    public Page<DNZRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month, pq);
    }

    @Override
    public Page<DNZRateImpl> findDaily(int year, Month month, int dayValue, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month, dayValue, pq);
    }

    @Override
    public Page<DNZRateImpl> findDailyInterval(int year, Month month, int begin, int end, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month, begin, end, pq);
    }

    @Override
    public Page<DNZRateImpl> findHourly(int year, Month month, int dayValue, int hour, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month, dayValue, hour, pq);
    }

    @Override
    public Page<DNZRateImpl> findHourlyInterval(int year, Month month, int dayValue, int begin, int end, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month, dayValue, begin, end, pq);
    }
}
