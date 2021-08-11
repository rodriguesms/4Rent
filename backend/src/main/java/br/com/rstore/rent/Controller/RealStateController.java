package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.RealStateDTO;
import br.com.rstore.rent.Controller.DTO.RealStateDetailsDTO;
import br.com.rstore.rent.Controller.Form.RealStateForm;
import br.com.rstore.rent.Models.RealState;
import br.com.rstore.rent.Repository.RealStateRepository;
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
@RequestMapping("/realstates")
public class RealStateController {

    @Autowired
    private RealStateRepository realStateRepository;

    @GetMapping
    @Cacheable(value = "RealStatesList")
    public Page<RealStateDTO> listRealState(@RequestParam(required = false) Boolean forRent,
                                            @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0,
                                                    size=10) Pageable pagination){


        Page<RealState> realStates;

        if(forRent != null){
            realStates = realStateRepository.findByForRent(forRent, pagination);
        }
        else{
            realStates = realStates = realStateRepository.findAll(pagination);
        }
        return RealStateDTO.Convert(realStates);
    }

    @PostMapping
    @CacheEvict(value = "RealStatesList", allEntries = true)
    public ResponseEntity<RealStateDTO> registerRealState(@RequestBody @Valid RealStateForm realStateForm,
                                                          UriComponentsBuilder uriBuilder){
        RealState realState = realStateForm.Convert();
        realStateRepository.save(realState);
        URI uri = uriBuilder.path("/realstates/{id}").buildAndExpand(realState.getId()).toUri();
        return ResponseEntity.created(uri).body(new RealStateDTO(realState));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealStateDetailsDTO> realStateDetails(@PathVariable Long id){
        Optional<RealState> realState = realStateRepository.findById(id);
        if(realState.isPresent()) {
            return ResponseEntity.ok(new RealStateDetailsDTO(realState.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "RealStatesList", allEntries = true)
    public ResponseEntity<RealStateDTO> updateRealState(@PathVariable Long id,
                                                        @RequestBody @Valid RealStateForm realStateForm){
        Optional<RealState> optionalRealState = realStateRepository.findById(id);
        if(optionalRealState.isPresent()) {
            RealState realState = realStateForm.Update(id, realStateRepository);
            return ResponseEntity.ok(new RealStateDTO(realState));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changeStatus?q={id}")
    @Transactional
    @CacheEvict(value = "RealStatesList", allEntries = true)
    public ResponseEntity<RealStateDTO> pauseRealStateAd(@PathVariable Long id,
                                                         @RequestBody @Valid RealStateForm realStateForm){
        Optional<RealState> optionalRealState = realStateRepository.findById(id);
        if(optionalRealState.isPresent()){
            RealState realState = realStateForm.changeAdStatus(id, realStateRepository);
            return ResponseEntity.ok(new RealStateDTO(realState));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "RealStatesList", allEntries = true)
    public ResponseEntity<?> removeRealState(@PathVariable Long id){
        Optional<RealState> optionalRealState = realStateRepository.findById(id);
        if(optionalRealState.isPresent()){
            realStateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
