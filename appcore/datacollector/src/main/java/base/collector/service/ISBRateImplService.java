package base.collector.service;

import base.collector.model.ISBRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ISBRateImplService {

    ISBRateImpl save(ISBRateImpl isbRate);

    void delete(ISBRateImpl isbRate);

    ISBRateImpl findOne(String id);

    Iterable<ISBRateImpl> findAll();

    Page<ISBRateImpl> findISBRateImplByBankNameEquals(String bankName, PageRequest pageRequest);
}
