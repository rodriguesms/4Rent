package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByForRent(Boolean forRent);
}
