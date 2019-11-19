package base.collector.repository;

import base.collector.model.DNZRate;
import base.collector.model.DNZRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DNZRepository extends ElasticsearchRepository<DNZRateImpl, String> {

    Page<DNZRateImpl> findDNZRateImplByBankNameEquals(String bankName, Pageable pageable);

    List<DNZRateImpl> findYDNZateImplByMajorCurrencyEquals(String majorCurrency);

    List<DNZRateImpl> findYDNZateImplByMinorCurrencyEquals(String minorCUrrency);

    List<DNZRateImpl> findDNZRateImplBySellRateGreaterThan(double sellAmount);

    List<DNZRateImpl> finddnzDNZRateImplBySellRateIsLessThan(double sellAmount);

}