package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.RealStateDTO;
import br.com.rstore.rent.Controller.Form.RealStateForm;
import br.com.rstore.rent.Models.RealState;
import br.com.rstore.rent.Repository.RealStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/realstates")
public class RealStateController {

    @Autowired
    private RealStateRepository realStateRepository;

    @GetMapping
    public List<RealStateDTO> listRealState(Boolean forRent){
        List<RealState> realStates;
        if(forRent != null){
            realStates = realStateRepository.findByForRent(forRent);
        }
        else{
            realStates = realStateRepository.findAll();
        }
        return RealStateDTO.convert(realStates);
    }

    @PostMapping
    public ResponseEntity<RealStateDTO> registerRealState(@RequestBody @Valid RealStateForm realStateForm, UriComponentsBuilder uriBuilder){
        RealState realState = realStateForm.Convert();
        realStateRepository.save(realState);
        URI uri = uriBuilder.path("/realstates/{id}").buildAndExpand(realState.getId()).toUri();
        return ResponseEntity.created(uri).body(new RealStateDTO(realState));
    }
}
