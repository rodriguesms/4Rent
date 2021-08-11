package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.HouseDTO;
import br.com.rstore.rent.Controller.DTO.HouseDetailsDTO;
import br.com.rstore.rent.Controller.Form.HouseForm;
import br.com.rstore.rent.Models.House;
import br.com.rstore.rent.Repository.HouseRepository;
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
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    @Cacheable(value = "HousesList")
    public Page<HouseDTO> listHouses(@RequestParam(required=false)Boolean forRent,
                                     @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0,
                                             size=10) Pageable pagination) {

        Page<House> houses;

        if (forRent != null) {
            houses = houseRepository.findByForRent(forRent, pagination);
        } else {
            houses = houseRepository.findAll(pagination);
        }
        return HouseDTO.Convert(houses);
    }

    @PostMapping
    @CacheEvict(value = "HousesList", allEntries = true)
    public ResponseEntity<HouseDTO> registerHouse(@RequestBody @Valid HouseForm houseForm,
                                                  UriComponentsBuilder uriBuilder) {
        House house = houseForm.Convert();
        houseRepository.save(house);
        URI uri = uriBuilder.path("/houses/{id}").buildAndExpand(house.getId()).toUri();
        return ResponseEntity.created(uri).body(new HouseDTO(house));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDetailsDTO> houseDetails(@PathVariable Long id){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            return ResponseEntity.ok(new HouseDetailsDTO(optionalHouse.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "HousesList", allEntries = true)
    public ResponseEntity<HouseDTO> updateHouse(@PathVariable Long id, @RequestBody @Valid HouseForm houseForm){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = houseForm.Update(id, houseRepository);
            return ResponseEntity.ok(new HouseDTO(house));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changeStatus?q={id}")
    @Transactional
    @CacheEvict(value = "HousesList", allEntries = true)
    public ResponseEntity<HouseDTO> changeHouseStatus(@PathVariable Long id, @RequestBody @Valid HouseForm houseForm){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = houseForm.changeAdStatus(id, houseRepository);
            return ResponseEntity.ok(new HouseDTO(house));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "HousesList", allEntries = true)
    public ResponseEntity<?> removeHouse(@PathVariable Long id){
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            houseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
