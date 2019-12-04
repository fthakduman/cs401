package collector.base.service;


import collector.base.model.DNZRateImpl;
import collector.base.repository.DNZRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;

@Service
@Qualifier("dnzservice")
public class DNZRateImplServiceImpl implements DNZRateImplService {

    @Autowired
    private DNZRepository dnzRepository;


    @Autowired
    public void setDnzRepository(DNZRepository dnzRepository) {
        this.dnzRepository = dnzRepository;
    }

    public DNZRateImpl save(DNZRateImpl dnzRate) {
        return dnzRepository.save(dnzRate);
    }

    public void delete(DNZRateImpl dnzRate) {
        dnzRepository.delete(dnzRate);
    }

    public DNZRateImpl findOne(String id) {
        return dnzRepository.findOne(id);
    }

    public Iterable<DNZRateImpl> findAll() {
        return dnzRepository.findAll();
    }


    public Page<DNZRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month, pq);
    }

    public Page<DNZRateImpl> findDaily(int year, Month month, int dayValue, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month, dayValue, pq);
    }

    public Page<DNZRateImpl> findDailyInterval(int year, Month month, int begin, int end, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month, begin, end, pq);
    }

    public Page<DNZRateImpl> findHourly(int year, Month month, int dayValue, int hour, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month, dayValue, hour, pq);
    }

    public Page<DNZRateImpl> findHourlyInterval(int year, Month month, int dayValue, int begin, int end, Pageable pq) {
        return dnzRepository.findDNZRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month, dayValue, begin, end, pq);
    }


    @Override
    public Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return dnzRepository.findDNZRateImplByBankNameEquals(bankName, pageRequest);
    }
}
