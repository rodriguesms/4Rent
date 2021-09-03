package br.com.rstore.rent.Repository;

import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Models.RealState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealStateRepository extends JpaRepository<RealState, Long> {
    Page<RealState> findByForRent(Boolean forRent, Pageable pagination);
    Page<RealState> findByOwner(Owner owner, Pageable pagination);
    Page<RealState> findByAnnouncementTitle(String announcementTitle, Pageable pagination);
}
