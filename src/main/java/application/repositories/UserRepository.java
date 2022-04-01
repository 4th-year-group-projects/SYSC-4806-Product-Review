package application.repositories;

import application.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String name);
    User findUserById(long id);
    Set<User> findAll();
    List<User> findAllByIdIsNotNull();

}
