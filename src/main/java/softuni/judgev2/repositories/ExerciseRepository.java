package softuni.judgev2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.judgev2.model.entities.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    @Query("SELECT e.name FROM Exercise e " +
            "order by e.name")
    List<String> findAllExNames();


    Optional<Exercise> findByName(String name);
}
