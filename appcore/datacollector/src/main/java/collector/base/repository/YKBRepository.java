package collector.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import collector.base.model.YKBRateImpl;

import java.time.Month;
import java.util.Iterator;
import java.util.List;

public interface YKBRepository extends ElasticsearchRepository<YKBRateImpl, String> {

    Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName,Pageable pageable);

    List<YKBRateImpl> findYKBRateImplByMajorCurrencyEquals(String majorCurrency);

    List<YKBRateImpl> findYKBRateImplByMinorCurrencyEquals(String minorCurrency);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(int year,Month month, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(int year,Month month,int dayValue, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(int year,Month month,int beginday,int endDay, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(int year,Month month,int dayValue,int hour, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(int year,Month month,int dayValue,int beginHour, int endHour, Pageable pageable);



}
