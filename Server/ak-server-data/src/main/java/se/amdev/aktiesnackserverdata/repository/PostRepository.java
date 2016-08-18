package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.amdev.aktiesnackserverdata.model.PostData;

public interface PostRepository extends PagingAndSortingRepository<PostData, Long> {
	PostData findByPostNumber(String postNumber);
	PostData findByPostId(String postId);
	Page<PostData> findAll(Pageable pageRequest);
	Collection<PostData> findAll();
	
	@Query("SELECT p FROM Posts p WHERE p.thread.id = :id")
	Collection<PostData> findPostByThread(@Param("id") Long threadId);
}
