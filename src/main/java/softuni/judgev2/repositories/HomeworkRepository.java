package softuni.judgev2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.judgev2.model.entities.Homework;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {
       Optional<Homework>findTop1ByOrderByComments();
}
