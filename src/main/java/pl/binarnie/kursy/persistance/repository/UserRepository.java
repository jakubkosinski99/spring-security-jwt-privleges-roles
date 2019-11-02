package pl.binarnie.kursy.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.binarnie.kursy.persistance.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public Boolean existsByEmail(String email);

}
