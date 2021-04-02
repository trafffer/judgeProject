package softuni.judgev2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.judgev2.model.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select avg (a.score) from Comment a ")
    double findAverageScore();
}
