package lambdaschool.cars;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>
{
    Car findOneById(Long id);
    List<Car> findByYear(int year);
    List<Car> findByBrandIgnoreCase(String brand);
    Car deleteOneById(Long id);
}
