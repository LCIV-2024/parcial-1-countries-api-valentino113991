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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        this.countriesDtos1.add(countryDTO1);
        this.countriesDtos2.add(countryDTO2);
    }

    @Test
    void getAllCountries() {
    }

    @Test
    void getAllPaisesDto() {
    }

    @Test
    void getPaisesByContinente() {
    }

    @Test
    void postPaises() {
    }
}