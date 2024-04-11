package springBoot.repos;

import org.springframework.data.repository.CrudRepository;
import springBoot.domain.Car;

public interface CarRepo extends CrudRepository<Car, Long> {
}

