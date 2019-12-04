package collector.base.repository;

import collector.base.model.ISBRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.Month;
import java.util.List;

public interface ISBRepository  extends ElasticsearchRepository<ISBRateImpl, String> {

    Page<ISBRateImpl> findISBRateImplByBankName(String bankName, Pageable pageable);

    List<ISBRateImpl> findISBRateImplByMajorCurrency(String majorCurrency);

    List<ISBRateImpl> findISBRateImplByMinorCurrency(String minorCurrency);

    Page<ISBRateImpl> findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(int year, Month month, Pageable pageable);

    Page<ISBRateImpl> findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(int year,Month month,int dayValue, Pageable pageable);

    Page<ISBRateImpl> findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(int year,Month month,int beginday,int endDay, Pageable pageable);

    Page<ISBRateImpl> findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(int year,Month month,int dayValue,int hour, Pageable pageable);

    Page<ISBRateImpl> findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(int year,Month month,int dayValue,int beginHour, int endHour, Pageable pageable);

}
