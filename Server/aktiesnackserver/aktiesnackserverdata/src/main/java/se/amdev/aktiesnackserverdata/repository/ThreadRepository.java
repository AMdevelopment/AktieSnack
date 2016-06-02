package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.amdev.aktiesnackserverdata.model.ThreadData;

public interface ThreadRepository extends PagingAndSortingRepository<ThreadData, Long> {
	ThreadData findByThreadName(String threadName);
	Page<ThreadData> findAll(Pageable pageRequest);
	Collection<ThreadData> findByDescriptionContaining(String value);
	Collection<ThreadData> findAll();

//	@Query("SELECT w FROM WorkItems w WHERE w.issue IS NOT NULL")
//	Collection<ThreadData> findByIssue();
//
//	@Query("SELECT w FROM WorkItems w JOIN w.workItemTeam t WHERE t.teamName = ?1")
//	Collection<ThreadData> findWorkItemByTeam(String teamName);
//
//	@Query("SELECT w FROM WorkItems w JOIN w.workItemUser u WHERE u.username = ?1")
//	Collection<ThreadData> findByUsername(String username);
//
//	@Query("SELECT w FROM WorkItems w WHERE creation_time >= ?1 AND creation_time <= ?2 AND w.status = ?3")
//	Collection<ThreadData> findByCreationTime(String start, String end, String status);
//
	@Query("SELECT t FROM Threads t ORDER BY last_updated_time DESC")
	Collection<ThreadData> findByLastUpdated();
}
