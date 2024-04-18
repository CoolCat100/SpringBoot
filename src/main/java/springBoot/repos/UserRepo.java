package springBoot.repos;

import org.springframework.data.repository.CrudRepository;
import springBoot.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {
}
