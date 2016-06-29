package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.amdev.aktiesnackserverdata.model.InquiryData;

public interface RegisterEmailRepository extends PagingAndSortingRepository<InquiryData, Long> {
	Collection<InquiryData> findAll();
}
