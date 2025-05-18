package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.CreateCountryDto;
import mk.ukim.finki.emt.dto.UpdateCountryDto;
import mk.ukim.finki.emt.service.application.CountryApplicationService;
import mk.ukim.finki.emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<UpdateCountryDto> findAll() {
        return countryService.findAll().stream().map( UpdateCountryDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<UpdateCountryDto> findById(Long id) {
        return countryService.findById(id).map(UpdateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> save(CreateCountryDto countryDto) {
        return countryService.save(countryDto.toCountry()).map(UpdateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id, countryDto.toCountry()).map(UpdateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> delete(Long id) {
        return countryService.delete(id).map(UpdateCountryDto::from);
    }
}
