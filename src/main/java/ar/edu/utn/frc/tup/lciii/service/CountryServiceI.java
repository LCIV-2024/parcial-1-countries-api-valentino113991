package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.AmountBody;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryServiceI {

    List<CountryDTO> getAllPaisesDto(String name, String code);

    List<CountryDTO> getPaisesByContinente(String continent);

    List<CountryDTO> getPaisesByLenguaje(String language);

    List<CountryDTO> postPaises(AmountBody amountBody);

}
