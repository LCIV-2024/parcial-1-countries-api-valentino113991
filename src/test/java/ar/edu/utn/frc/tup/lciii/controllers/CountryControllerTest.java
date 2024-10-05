package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.AmountBody;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryServiceI countryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPaises() throws Exception {
        List<CountryDTO> countriesDtos1 = new ArrayList<>();
       CountryDTO countryDTO1;
        CountryDTO countryDTO2;
        countryDTO1 = CountryDTO.builder()
                .code("ARG")
                .name("Argentina")
                .build();

        countryDTO2 = CountryDTO.builder()
                .code("ITA")
                .name("Italia")
                .build();

        countriesDtos1.add(countryDTO1);
        countriesDtos1.add(countryDTO2);

        when(countryService.getAllPaisesDto(any(), any())).thenReturn(countriesDtos1);

        MvcResult mvcResult = mockMvc.perform(get("/api/countries")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPaisesByContinente() throws Exception {
        List<CountryDTO> countriesDtos1 = new ArrayList<>();
        CountryDTO countryDTO1;
        CountryDTO countryDTO2;
        countryDTO1 = CountryDTO.builder()
                .code("HOL")
                .name("Holanda")
                .build();

        countryDTO2 = CountryDTO.builder()
                .code("ITA")
                .name("Italia")
                .build();

        countriesDtos1.add(countryDTO1);
        countriesDtos1.add(countryDTO2);

        when(countryService.getPaisesByContinente("Europe")).thenReturn(countriesDtos1);

        MvcResult mvcResult = mockMvc.perform(get("/api/countries/Europe/continent")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPaisesByLenguaje() {
    }

    @Test
    void postPaises() throws Exception {
        List<CountryDTO> countriesDtos1 = new ArrayList<>();
        CountryDTO countryDTO1;
        CountryDTO countryDTO2;
        countryDTO1 = CountryDTO.builder()
                .code("HOL")
                .name("Holanda")
                .build();

        countryDTO2 = CountryDTO.builder()
                .code("ITA")
                .name("Italia")
                .build();

        countriesDtos1.add(countryDTO1);
        countriesDtos1.add(countryDTO2);

        AmountBody amountBody = AmountBody.builder()
                .amountOfCountryToSave(2)
                .build();

        when(countryService.postPaises(amountBody)).thenReturn(countriesDtos1);

        MvcResult mvcResult = mockMvc.perform(post("/api/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(amountBody)))
                .andExpect(status().isOk())
                .andReturn();
    }
}