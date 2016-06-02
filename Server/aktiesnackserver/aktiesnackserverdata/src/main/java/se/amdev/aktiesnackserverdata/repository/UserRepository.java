package se.amdev.aktiesnackserverdata.repository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.amdev.aktiesnackserverdata.model.UserData;

public interface UserRepository extends PagingAndSortingRepository<UserData, Long> {
	UserData findByUsername(String username);
	Page<UserData> findAll(Pageable pageRequest);
	Collection<UserData> findAll();
	Collection<UserData> findByFirstName(String firstName);
	Collection<UserData> findByLastName(String lastName);
	Collection<UserData> findByFirstNameAndLastName(String firstName, String lastName);

//	@Query("SELECT u FROM Users u JOIN u.team t WHERE t.teamName = ?1")
//	Collection<UserData> findByTeamName(String teamName);
}
