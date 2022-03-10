package sysc4806.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import sysc4806.models.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
