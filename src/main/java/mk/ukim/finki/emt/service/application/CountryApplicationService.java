package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.CreateCountryDto;
import mk.ukim.finki.emt.dto.UpdateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<UpdateCountryDto> findAll();
    Optional<UpdateCountryDto> findById(Long id);
    Optional<UpdateCountryDto> save(CreateCountryDto countryDto);
    Optional<UpdateCountryDto> update(Long id, CreateCountryDto countryDto);
    Optional<UpdateCountryDto> delete(Long id);
}
