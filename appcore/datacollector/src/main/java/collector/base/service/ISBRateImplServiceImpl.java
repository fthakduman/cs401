package collector.base.service;

import collector.base.model.ISBRateImpl;
import collector.base.repository.ISBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<ISBRateImpl> findISBRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return isbRepository.findISBRateImplByBankName(bankName,pageRequest);
    }
}
