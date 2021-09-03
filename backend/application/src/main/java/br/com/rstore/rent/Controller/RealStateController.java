package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.RealStateDTO;
import br.com.rstore.rent.Controller.Form.OwnerForm;
import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Models.RealState;
import br.com.rstore.rent.Repository.OwnerRepository;
import br.com.rstore.rent.Repository.RealStateRepository;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realstates")
public class RealStateController {

    @Autowired
    private RealStateRepository realStateRepository;

    @Autowired
    private OwnerRepository ownerRepository;

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
            realStates = realStateRepository.findAll(pagination);
        }
        return RealStateDTO.Convert(realStates);
    }

    @GetMapping("/user-realstates/{id}")
    public Page<RealStateDTO> userRealStates(@PathVariable Long id,
                                            @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0,
                                                    size=10) Pageable pagination){

        Optional<Owner> owner = ownerRepository.findById(id);

        Page<RealState> realStates = realStateRepository.findByOwner(owner.get(), pagination);
        
        return RealStateDTO.Convert(realStates);
    }

}
