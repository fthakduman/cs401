package provider.base.service;

import collector.base.model.YKBRateImpl;
import collector.base.repository.YKBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;


@Service
@Qualifier("ykbwrapper")
public class YKBWrapper implements YKBWrap {

    @Autowired
    private YKBRepository ykbRepository;


    @Override
    public Page<YKBRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month,pq);    }

    @Override
    public Page<YKBRateImpl> findDaily(int year, Month month, int dayValue, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month,dayValue,pq);
    }

    @Override
    public Page<YKBRateImpl> findDailyInterval(int year, Month month, int begin, int end, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month,begin,end,pq);
    }

    @Override
    public Page<YKBRateImpl> findHourly(int year, Month month, int dayValue, int hour, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month,dayValue,hour,pq);
    }

    @Override
    public Page<YKBRateImpl> findHourlyInterval(int year, Month month, int dayValue, int begin, int end, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month,dayValue,begin,end,pq);
    }
}
