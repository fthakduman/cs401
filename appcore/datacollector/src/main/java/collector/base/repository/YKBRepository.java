package collector.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import collector.base.model.YKBRateImpl;

import java.util.List;

public interface YKBRepository extends ElasticsearchRepository<YKBRateImpl, String> {

    Page<YKBRateImpl> findYKBRateImplByBankNameEquals(String bankName,Pageable pageable);

    List<YKBRateImpl> findYKBRateImplByMajorCurrencyEquals(String majorCurrency);

    List<YKBRateImpl> findYKBRateImplByMinorCurrencyEquals(String minorCUrrency);

    List<YKBRateImpl> findYKBRateImplBySellRateGreaterThan(double sellAmount);

    List<YKBRateImpl> findYKBRateImplBySellRateIsLessThan(double sellAmount);

}
