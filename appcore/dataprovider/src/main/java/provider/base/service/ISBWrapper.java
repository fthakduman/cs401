package provider.base.service;

import collector.base.model.ISBRateImpl;
import collector.base.repository.ISBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;

@Service
@Qualifier("isbwrapper")
public class ISBWrapper implements ISBWrap {

    @Autowired
    private ISBRepository isbRepository;

    @Override
    public Page<ISBRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month, pq);
    }

    @Override
    public Page<ISBRateImpl> findDaily(int year, Month month, int dayValue, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month, dayValue, pq);

    }

    @Override
    public Page<ISBRateImpl> findDailyInterval(int year, Month month, int begin, int end, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month, begin, end, pq);
    }

    @Override
    public Page<ISBRateImpl> findHourly(int year, Month month, int dayValue, int hour, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month, dayValue, hour, pq);
    }

    @Override
    public Page<ISBRateImpl> findHourlyInterval(int year, Month month, int dayValue, int begin, int end, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month, dayValue, begin, end, pq);
    }
}
