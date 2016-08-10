package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.amdev.aktiesnackserverdata.model.StockData;

public interface StockRepository extends PagingAndSortingRepository<StockData, Long> {
	StockData findByStockName(String stockName);
	Collection<StockData> findAll();
	Page<StockData> findAll(Pageable pageRequest);
}
