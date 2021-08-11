package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
    Page<House> findByForRent(Boolean forRent, Pageable pagination);
}
