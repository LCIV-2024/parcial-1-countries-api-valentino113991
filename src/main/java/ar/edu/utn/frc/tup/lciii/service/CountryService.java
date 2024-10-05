package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.AmountBody;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.exception.CountryNotFoundException;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService implements CountryServiceI{

        @Autowired
        private CountryRepository countryRepository;

        @Autowired
        private RestTemplate restTemplate;


        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .borders((List<String>) countryData.get("borders"))
                        .code(String. valueOf(countryData.get("cca3")))
                        .build();
        }


        private CountryDTO mapToDTO(Country country) {
                return new CountryDTO(country.getCode(), country.getName());
        }


        @Override
        public List<CountryDTO> getAllPaisesDto(String name, String code) {
                if(name == null && code == null){
                        List<Country> countriesApi = this.getAllCountries();
                        List<CountryDTO> result = new ArrayList<>();
                        for(Country c : countriesApi){
                                CountryDTO dto = this.mapToDTO(c);
                                result.add(dto);
                        }
                        return result;
                }else if(name != null && code == null){
                        List<Country> countriesApi = this.getAllCountries();
                        List<CountryDTO> result = new ArrayList<>();
                        for(Country c : countriesApi){
                                CountryDTO dto = this.mapToDTO(c);
                                name = name.toLowerCase();
                                if(name.equals(dto.getName().toLowerCase())){
                                        result.add(dto);
                                }
                        }
                        return result;
                }else{
                        List<Country> countriesApi = this.getAllCountries();
                        List<CountryDTO> result = new ArrayList<>();
                        for(Country c : countriesApi){
                                CountryDTO dto = this.mapToDTO(c);
                                code = code.toLowerCase();
                                if(code.equals(dto.getCode().toLowerCase())){
                                        result.add(dto);
                                }
                        }
                        return result;
                }
        }

        @Override
        public List<CountryDTO> getPaisesByContinente(String continent) {
                List<Country> countriesApi = this.getAllCountries();
                List<CountryDTO> result = new ArrayList<>();
                for(Country c : countriesApi){
                        continent = continent.toLowerCase();
                        CountryDTO dto = this.mapToDTO(c);
                        if(Objects.equals(c.getRegion().toLowerCase(), continent)){
                                result.add(dto);
                        }
                }
                return result;
        }

        @Override
        public List<CountryDTO> getPaisesByLenguaje(String language) {
//                List<Country> countriesApi = this.getAllCountries();
//                List<String> lenguajes = new ArrayList<>();
//                List<CountryDTO> result = new ArrayList<>();
//                for(Country c : countriesApi){
//                        c.getLanguages().forEach((k,v) -> lenguajes.add(v));
//                        for(String aux : lenguajes){
//                                if(Objects.equals(language, aux)){
//                                         result.add(this.mapToDTO(c));
//                                        lenguajes.clear();
//                                        break;
//                                }
//                        }
//                        lenguajes.clear();
//                }
//
//                return result;
                return null;
        }

        @Override
        public List<CountryDTO> postPaises(AmountBody amountBody) {
                if(amountBody.getAmountOfCountryToSave() > 10){
                        throw new CountryNotFoundException("Solo se pueden 10 paises");
                }
                List<Country> countriesApi = this.getAllCountries();
                List<Country> countriesApiFiltradas = new ArrayList<>();
                List<CountryDTO> result = new ArrayList<>();
                Collections.shuffle(countriesApi);
                for(int i = 0; i < amountBody.getAmountOfCountryToSave(); i++){
                        countriesApiFiltradas.add(countriesApi.get(i));
                        result.add(this.mapToDTO(countriesApi.get(i)));
                }
                List<CountryEntity> countriesEntities = new ArrayList<>();
                for(Country aux : countriesApiFiltradas){
                        CountryEntity countryEntity = CountryEntity.builder()
                                .area(aux.getArea())
                                .code(aux.getCode())
                                .name(aux.getName())
                                .population(aux.getPopulation())
                                .build();
                        countriesEntities.add(countryEntity);
                }
                countriesEntities = this.countryRepository.saveAll(countriesEntities);
                return result;
        }

}