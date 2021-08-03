package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.ApartmentDTO;
import br.com.rstore.rent.Controller.DTO.HouseDTO;
import br.com.rstore.rent.Controller.Form.HouseForm;
import br.com.rstore.rent.Models.House;
import br.com.rstore.rent.Repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    public List<HouseDTO> listRealState(Boolean forRent) {
        List<House> houses;
        if (forRent != null) {
            houses = houseRepository.findByForRent(forRent);
        } else {
            houses = houseRepository.findAll();
        }
        return HouseDTO.Convert(houses);
    }

    @PostMapping
    public ResponseEntity<HouseDTO> registerHouse(@RequestBody @Valid HouseForm houseForm, UriComponentsBuilder uriBuilder) {
        House house = houseForm.Convert();
        houseRepository.save(house);
        URI uri = uriBuilder.path("/houses/{id}").buildAndExpand(house.getId()).toUri();
        return ResponseEntity.created(uri).body(new HouseDTO(house));
    }
}
