package springBoot.repos;

import org.springframework.data.repository.CrudRepository;
import springBoot.domain.Car;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Long> {
}

