package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.dto.CreateCountryDto;
import mk.ukim.finki.emt.dto.UpdateCountryDto;
import mk.ukim.finki.emt.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<UpdateCountryDto> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateCountryDto> findById(@PathVariable Long id) {
        return countryService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<UpdateCountryDto> deleteAuthor(@PathVariable Long id) {
        return countryService.delete(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<UpdateCountryDto> addCountry(@RequestBody CreateCountryDto countryDto) {
        return countryService.save(countryDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<UpdateCountryDto> editCountry(@PathVariable Long id, @RequestBody CreateCountryDto countryDto) {
        return countryService.update(id, countryDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
