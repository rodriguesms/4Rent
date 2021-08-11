package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Page<Apartment> findByForRent(Boolean forRent, Pageable pagination);
}
