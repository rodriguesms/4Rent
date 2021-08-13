package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.Land;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Long> {
    Page<Land> findByForRent(Boolean forRent, Pageable pagination);
}
