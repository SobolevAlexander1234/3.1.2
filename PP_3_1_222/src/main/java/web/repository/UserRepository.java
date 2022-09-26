package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import web.model.MyUser;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByUsername(String username);
}