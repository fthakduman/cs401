package collector.base.service;


import collector.base.model.DNZRateImpl;
import collector.base.repository.DNZRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Qualifier("dnzservice")
public class DNZRateImplServiceImpl implements DNZRateImplService{

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

    @Override
    public Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, PageRequest pageRequest) {
        return dnzRepository.findDNZRateImplByBankNameEquals(bankName,pageRequest);
    }
}
