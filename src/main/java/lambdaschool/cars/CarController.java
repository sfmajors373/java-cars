package lambdaschool.cars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CarController
{
    private final CarRepository carrepos;
    private final RabbitTemplate rt;

    public CarController(CarRepository carrepos, RabbitTemplate rt)
    {
        this.carrepos = carrepos;
        this.rt = rt;
    }

    //GET
    //cars/id/{id} - returns the car
    @GetMapping("/cars/id/{id}")
    public Car findanID(@PathVariable Long id)
    {
        return carrepos.findOneById(id);
    }
    //cars/year/{year}
    @GetMapping("/cars/year/{year}")
    public List<Car> findlistByYear(@PathVariable int year)
    {
        return carrepos.findByYear(year);
    }
    //cars/brand/{brand}
    @GetMapping("/cars/brand/{brand}")
    public List<Car> findListByBrand(@PathVariable String brand)
    {
        return carrepos.findByBrandIgnoreCase(brand);
    }
    //POST
    //cars/upload - loads multiple sets of data from RequestBody
    @PostMapping("/cars/upload")
    public List<Car> newCar(@RequestBody List<Car> newCars)
    {
        return carrepos.saveAll(newCars);
    }
    //DELETE
    //cars/delete/{id} - logged with message of "{id} Data deleted."
    @DeleteMapping("/cars/delete/{id}")
    public Car deleteId(@PathVariable Long id)
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode deleted = mapper.createObjectNode();
        String mes = id + " Data deleted";
        deleted.put("message", mes);

       return carrepos.deleteOneById(id);
    }
}
