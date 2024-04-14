package springBoot.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import springBoot.domain.Car;

import java.util.List;

public interface CarRepo extends PagingAndSortingRepository<Car, Long> {
}

