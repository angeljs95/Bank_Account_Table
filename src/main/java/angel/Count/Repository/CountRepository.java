package angel.Count.Repository;

import angel.Count.Entity.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRepository extends JpaRepository<Count,Integer> {
}
