package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.LandDTO;
import br.com.rstore.rent.Controller.DTO.LandDetailsDTO;
import br.com.rstore.rent.Controller.Form.LandForm;
import br.com.rstore.rent.Models.Land;
import br.com.rstore.rent.Repository.LandRepository;
import br.com.rstore.rent.Repository.OwnerRepository;
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
@RequestMapping("/lands")
public class LandController {

    @Autowired
    private LandRepository landRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    @Cacheable(value = "LandList")
    public Page<LandDTO> listLand(@RequestParam(required = false) Boolean forRent,
                                  @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0,
                                          size=10) Pageable pagination){
        Page<Land> lands;

        if(forRent != null){
            lands = landRepository.findByForRent(forRent, pagination);
        }else{
            lands = landRepository.findAll(pagination);
        }
        return LandDTO.Convert(lands);
    }

    @PostMapping
    @CacheEvict(value = {"LandList", "RealStatesList"}, allEntries = true)
    public ResponseEntity<LandDTO> registerLand(@RequestBody @Valid LandForm landForm,
                                                UriComponentsBuilder uriBuilder){
        Land land = landForm.Convert(ownerRepository);
        landRepository.save(land);
        URI uri = uriBuilder.path("/lands/{id}").buildAndExpand(land.getId()).toUri();
        return ResponseEntity.created(uri).body(new LandDTO(land));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandDetailsDTO> landDetails(@PathVariable Long id){
        Optional<Land> land = landRepository.findById(id);
        if(land.isPresent()) {
            return ResponseEntity.ok(new LandDetailsDTO(land.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = {"LandList", "RealStatesList"}, allEntries = true)
    public ResponseEntity<LandDTO> updateLand(@PathVariable Long id,
                                                        @RequestBody @Valid LandForm landForm){
        Optional<Land> optionalLand = landRepository.findById(id);
        if(optionalLand.isPresent()) {
            Land land = landForm.Update(id, landRepository);
            return ResponseEntity.ok(new LandDTO(land));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changeStatus?q={id}")
    @Transactional
    @CacheEvict(value = {"LandList", "RealStatesList"}, allEntries = true)
    public ResponseEntity<LandDTO> pauseLandAd(@PathVariable Long id,
                                                         @RequestBody @Valid LandForm landForm){
        Optional<Land> landOptional = landRepository.findById(id);
        if(landOptional.isPresent()){
            Land land = landForm.changeAdStatus(id, landRepository);
            return ResponseEntity.ok(new LandDTO(land));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = {"LandList", "RealStatesList"}, allEntries = true)
    public ResponseEntity<?> removeLand(@PathVariable Long id){
        Optional<Land> optionalLand = landRepository.findById(id);
        if(optionalLand.isPresent()){
            landRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
