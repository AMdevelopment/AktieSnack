package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.amdev.aktiesnackserverdata.model.PostData;

public interface PostRepository extends PagingAndSortingRepository<PostData, Long> {
	PostData findByPostNumber(String postNumber);
	Page<PostData> findAll(Pageable pageRequest);
	Collection<PostData> findAll();
}
