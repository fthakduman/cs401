package base.collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import base.collector.repository.YKBRepository;
import base.collector.model.YKBRateImpl;

@Service
@Qualifier("ykbservice")
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

    @Override
    public Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return ykbRepository.findYKBRateImplByBankNameEquals(bankName,pageRequest);
    }



}
