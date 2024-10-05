package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.AmountBody;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import ar.edu.utn.frc.tup.lciii.service.CountryServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CountryController {

    @Autowired
    private CountryServiceI countryService;

    @GetMapping("/api/countries")
    public ResponseEntity<?> getAllPaises(@RequestParam(required = false) String name
            ,@RequestParam(required = false) String code){
        return ResponseEntity.ok(this.countryService.getAllPaisesDto(name, code));
    }

    @GetMapping("/api/countries/{continent}/continent")
    public ResponseEntity<?> getPaisesByContinente(@PathVariable String continent){
        return ResponseEntity.ok(this.countryService.getPaisesByContinente(continent));
    }

    @PostMapping("/api/countries")
    public ResponseEntity<?> postPaises(@RequestBody AmountBody amountBody){
        return ResponseEntity.ok(this.countryService.postPaises(amountBody));
    }

}