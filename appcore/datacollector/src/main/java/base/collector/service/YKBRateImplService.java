package base.collector.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import base.collector.ykbmodel.YKBRateImpl;

public interface YKBRateImplService {
    YKBRateImpl save(YKBRateImpl book);

    void delete(YKBRateImpl ykbRate);

    YKBRateImpl findOne(String id);

    Iterable<YKBRateImpl> findAll();

    Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName, PageRequest pageRequest);

}
