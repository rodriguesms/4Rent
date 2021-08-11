package br.com.rstore.rent.Controller;


import br.com.rstore.rent.Controller.DTO.ApartmentDTO;
import br.com.rstore.rent.Controller.DTO.ApartmentDetailsDTO;
import br.com.rstore.rent.Controller.Form.ApartmentForm;
import br.com.rstore.rent.Models.Apartment;
import br.com.rstore.rent.Repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    ApartmentRepository apartmentRepository;

    @GetMapping
    @Cacheable(value = "ApartmentsList")
    public Page<ApartmentDTO> listApartments(@RequestParam(required = false) Boolean forRent,
                                             @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0,
                                                     size=10) Pageable pagination){

        Page<Apartment> apartments;

        if(forRent != null){ // If user specifies the type, filter results
            apartments = apartmentRepository.findByForRent(forRent, pagination);
        } else { // If user doesn't specifies type
            apartments = apartmentRepository.findAll(pagination);
        }
        return ApartmentDTO.Convert(apartments);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "ApartmentsList")
    public ResponseEntity<ApartmentDTO> registerApartment(@RequestBody @Valid ApartmentForm apartmentForm,
                                                          UriComponentsBuilder uriBuilder){
        Apartment apartment = apartmentForm.Convert();
        apartmentRepository.save(apartment);

        URI uri = uriBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApartmentDTO(apartment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDetailsDTO> apartmentDetail(@PathVariable Long id){
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if(optionalApartment.isPresent()){
            return ResponseEntity.ok(new ApartmentDetailsDTO(optionalApartment.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ApartmentsList", allEntries = true)
    public ResponseEntity<ApartmentDTO> updateApartment(@PathVariable Long id,
                                                        @RequestBody @Valid ApartmentForm apartmentForm){
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if(optionalApartment.isPresent()){
            Apartment apartment = apartmentForm.Update(id, apartmentRepository);
            return ResponseEntity.ok(new ApartmentDTO(apartment));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changeStatus?q={id}")
    @Transactional
    @CacheEvict(value = "ApartmentsList", allEntries = true)
    public ResponseEntity<ApartmentDTO> changeApartmentStatus(@PathVariable Long id,
                                                              @RequestBody @Valid ApartmentForm apartmentForm){
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if(optionalApartment.isPresent()){
            Apartment apartment = apartmentForm.changeAdStatus(id, apartmentRepository);
            return ResponseEntity.ok(new ApartmentDTO(apartment));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ApartmentsList", allEntries = true)
    public ResponseEntity<?> removeApartment(@PathVariable Long id){
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if(optionalApartment.isPresent()){
            apartmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
