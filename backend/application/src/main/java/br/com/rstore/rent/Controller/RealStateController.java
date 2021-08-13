package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Controller.DTO.RealStateDTO;
import br.com.rstore.rent.Models.RealState;
import br.com.rstore.rent.Repository.RealStateRepository;
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

}
