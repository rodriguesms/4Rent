package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findByForRent(Boolean forRent);
}
