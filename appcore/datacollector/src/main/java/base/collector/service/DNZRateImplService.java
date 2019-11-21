package base.collector.service;

import base.collector.model.DNZRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DNZRateImplService {
    DNZRateImpl save(DNZRateImpl book);

    void delete(DNZRateImpl ykbRate);

    DNZRateImpl findOne(String id);

    Iterable<DNZRateImpl> findAll();

    Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, PageRequest pageRequest);
}
