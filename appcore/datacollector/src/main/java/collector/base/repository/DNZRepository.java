package collector.base.repository;

import collector.base.model.DNZRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DNZRepository extends ElasticsearchRepository<DNZRateImpl, String> {

    Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, Pageable pageable);

    List<DNZRateImpl> findYDNZRateImplByMajorCurrencyEquals(String majorCurrency);

    List<DNZRateImpl> findYDNZRateImplByMinorCurrencyEquals(String minorCurrency);

    List<DNZRateImpl> findDNZRateImplBySellRate(double sellRate);

    List<DNZRateImpl> findDNZRateImplBySellRateGreaterThan(double sellAmount);


}