package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.JwtLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository  extends JpaRepository<JwtLog, Long> {

}
