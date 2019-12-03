package collector.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import collector.base.model.YKBRateImpl;

import java.time.Month;
import java.util.List;

public interface YKBRepository extends ElasticsearchRepository<YKBRateImpl, String> {

    Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName,Pageable pageable);

    List<YKBRateImpl> findYKBRateImplByMajorCurrencyEquals(String majorCurrency);

    List<YKBRateImpl> findYKBRateImplByMinorCurrencyEquals(String minorCUrrency);

    List<YKBRateImpl> findYKBRateImplBySellRateGreaterThan(double sellAmount);

    List<YKBRateImpl> findYKBRateImplBySellRateIsLessThan(double sellAmount);

    Page<YKBRateImpl> findYKBRateImplByCurrencyMonthEquals(String month, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(int year,Month month, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(int year,Month month,int dayofmonth, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(int year,Month month,int dayofmonth,int hour, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyHourIsBetween(int year,Month month,int hourbegin,int hourend, Pageable pageable);


    Page<YKBRateImpl> findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyHourEqualsAndCurrencyMinuteEquals(int year,Month month,int hour,int minute, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyMonthValueEquals(int monthv, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyMonthValue(int monthv, Pageable pageable);

    Page<YKBRateImpl> findYKBRateImplByCurrencyMonth(String month, Pageable pageable);

}
