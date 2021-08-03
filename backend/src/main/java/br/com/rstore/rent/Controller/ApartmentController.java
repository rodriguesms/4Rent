package br.com.rstore.rent.Controller;


import br.com.rstore.rent.Controller.DTO.ApartmentDTO;
import br.com.rstore.rent.Controller.Form.ApartmentForm;
import br.com.rstore.rent.Models.Apartment;
import br.com.rstore.rent.Repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    ApartmentRepository apartmentRepository;

    @GetMapping
    public List<ApartmentDTO> listApartments(Boolean forRent){
        List<Apartment> apartments;
        if(forRent != null){ // If user specifies the type, filter results
            apartments = apartmentRepository.findByForRent(forRent);
        } else { // If user doesn't specifies type
            apartments = apartmentRepository.findAll();
        }
        return ApartmentDTO.Convert(apartments);
    }

    @PostMapping
    public ResponseEntity<ApartmentDTO> registerApartment(@RequestBody @Valid ApartmentForm apartmentForm, UriComponentsBuilder uriBuilder){
        Apartment apartment = apartmentForm.Convert();
        apartmentRepository.save(apartment);

        URI uri = uriBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApartmentDTO(apartment));
    }
}
