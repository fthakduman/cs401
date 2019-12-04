package collector.base.service;

import collector.base.model.ISBRateImpl;
import collector.base.repository.ISBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;

@Service
@Qualifier("isbservice")
public class ISBRateImplServiceImpl implements ISBRateImplService {

    @Autowired
    private ISBRepository isbRepository;


    @Autowired
    public void setIsbRepository(ISBRepository isbRepository) {
        this.isbRepository = isbRepository;
    }


    @Override
    public ISBRateImpl save(ISBRateImpl isbRate) {

        return isbRepository.save(isbRate);

    }

    public void delete(ISBRateImpl ykbRate) {
        isbRepository.delete(ykbRate);
    }

    public ISBRateImpl findOne(String id) {
        return isbRepository.findOne(id);
    }

    public Iterable<ISBRateImpl> findAll() {
        return isbRepository.findAll();
    }

    public Page<ISBRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month, pq);
    }

    public Page<ISBRateImpl> findDaily(int year, Month month, int dayValue, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month, dayValue, pq);
    }

    public Page<ISBRateImpl> findDailyInterval(int year, Month month, int begin, int end, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month, begin, end, pq);
    }

    public Page<ISBRateImpl> findHourly(int year, Month month, int dayValue, int hour, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month, dayValue, hour, pq);
    }

    public Page<ISBRateImpl> findHourlyInterval(int year, Month month, int dayValue, int begin, int end, Pageable pq) {
        return isbRepository.findISBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month, dayValue, begin, end, pq);
    }


    @Override
    public Page<ISBRateImpl> findISBRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return isbRepository.findISBRateImplByBankName(bankName, pageRequest);
    }
}
