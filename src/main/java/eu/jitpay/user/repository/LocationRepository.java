package eu.jitpay.user.repository;

import eu.jitpay.user.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findFirstByUser_UserIdOrderByCreatedOnDesc(String id);
    Page<Location> findByUser_UserIdAndCreatedOnBetween(String userId,LocalDateTime start
            ,LocalDateTime end, Pageable pageable);
}
