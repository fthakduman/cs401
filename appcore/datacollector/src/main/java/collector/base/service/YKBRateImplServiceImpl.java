package collector.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import collector.base.repository.YKBRepository;
import collector.base.model.YKBRateImpl;

import java.time.Month;

@Service
public class YKBRateImplServiceImpl implements YKBRateImplService {

    @Autowired
    private YKBRepository ykbRepository;


    @Autowired
    public void setYkbRepository(YKBRepository ykbRepository) {
        this.ykbRepository = ykbRepository;
    }

    public YKBRateImpl save(YKBRateImpl ykbRate) {
        return ykbRepository.save(ykbRate);
    }

    public void delete(YKBRateImpl ykbRate) {
        ykbRepository.delete(ykbRate);
    }

    public YKBRateImpl findOne(String id) {
        return ykbRepository.findOne(id);
    }

    public Iterable<YKBRateImpl> findAll() {
        return ykbRepository.findAll();
    }

    public Page<YKBRateImpl> findMonthly(int year, Month month, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEquals(year, month,pq);
    }
    public Page<YKBRateImpl> findDaily(int year, Month month,int dayValue, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEquals(year, month,dayValue,pq);
    }
    public Page<YKBRateImpl> findDailyInterval(int year, Month month,int begin,int end, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueIsBetween(year, month,begin,end,pq);
    }
    public Page<YKBRateImpl> findHourly(int year, Month month,int dayValue,int hour, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourEquals(year, month,dayValue,hour,pq);
    }
    public Page<YKBRateImpl> findHourlyInterval(int year, Month month,int dayValue,int begin,int end, Pageable pq) {
        return ykbRepository.findYKBRateImplByCurrencyYearEqualsAndCurrencyMonthEqualsAndCurrencyDayOfMonthValueEqualsAndCurrencyHourIsBetween(year, month,dayValue,begin,end,pq);
    }

    @Override
    public Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return ykbRepository.findYKBRateImplByBankNameEquals(bankName,pageRequest);
    }



}
