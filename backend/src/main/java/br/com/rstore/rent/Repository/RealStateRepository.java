package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.RealState;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RealStateRepository extends JpaRepository<RealState, Long> {
    List<RealState> findByForRent(Boolean forRent);
}
