package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private RestTemplate restTemplate;

    private List<CountryDTO> countriesDtos1;
    private CountryDTO countryDTO1;

    private List<CountryDTO> countriesDtos2;
    private CountryDTO countryDTO2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryDTO1 = CountryDTO.builder()
                .code("ARG")
                .name("Argentina")
                .build();

        countryDTO2 = CountryDTO.builder()
                .code("ITA")
                .name("Italia")
                .build();

        countriesDtos1 = new ArrayList<>();
        countriesDtos2 = new ArrayList<>();

        this.countriesDtos1.add(countryDTO1);
        this.countriesDtos2.add(countryDTO2);
    }

    @Test
    void getAllCountriesTest() {

        List<Map<String, Object>> listasApiExterna = List.of(
                Map.of("name", Map.of("common", "Argentina"), "population", 1, "area", 10000, "region", "Sudamerica", "cca3", "ARG"),
                Map.of("name", Map.of("common", "Italia"), "population", 1, "area", 5000, "region", "Europa", "cca3", "ITA")
        );

        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(listasApiExterna);

        List<Country> result = countryService.getAllCountries();

        assertEquals(2, result.size());
        assertEquals("Argentina", result.get(0).getName());
        assertEquals("Italia", result.get(1).getName());
    }

    @Test
    void getAllPaisesDto() {

        Country country1 = Country.builder()
                .code("ARG")
                .name("Argentina")
                .build();

        Country country2 = Country.builder()
                .code("URU")
                .name("Uruguay")
                .build();

        List<Country> countries = new ArrayList<>();
        countries.add(country1);


        when(this.countryService.getAllCountries()).thenReturn(countries);
        List<CountryDTO> result = this.countryService.getAllPaisesDto(null, null);
        assertEquals(2, result.size());
    }

    @Test
    void getPaisesByContinente() {
    }

    @Test
    void postPaises() {
    }
}