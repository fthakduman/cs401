package collector.base.repository;

import collector.base.model.DNZRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.Month;
import java.util.List;

public interface DNZRepository extends ElasticsearchRepository<DNZRateImpl, String> {

    Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, Pageable pageable);

    List<DNZRateImpl> findDNZRateImplByMajorCurrencyEquals(String majorCurrency);

    List<DNZRateImpl> findDNZRateImplByMinorCurrencyEquals(String minorCurrency);

    Page<DNZRateImpl> findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(int year, Month month, Pageable pageable);

    Page<DNZRateImpl> findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(int year,Month month,int dayValue, Pageable pageable);

    Page<DNZRateImpl> findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(int year,Month month,int beginday,int endDay, Pageable pageable);

    Page<DNZRateImpl> findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(int year,Month month,int dayValue,int hour, Pageable pageable);

    Page<DNZRateImpl> findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(int year,Month month,int dayValue,int beginHour, int endHour, Pageable pageable);


}