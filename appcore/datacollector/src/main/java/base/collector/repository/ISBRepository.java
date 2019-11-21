package base.collector.repository;

import base.collector.model.ISBRateImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ISBRepository  extends ElasticsearchRepository<ISBRateImpl, String> {

    Page<ISBRateImpl> findISBRateImplByBankName(String bankName, Pageable pageable);

    List<ISBRateImpl> findISBRateImplByMajorCurrency(String majorCurrency);

    List<ISBRateImpl> findISBRateImplByMinorCurrency(String minorCurrency);

    List<ISBRateImpl> findISBRateImplBySellRate(double sellRate);

    List<ISBRateImpl> findISBRateImplByBuyRate(double buyRate);

}
