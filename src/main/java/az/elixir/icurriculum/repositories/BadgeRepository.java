package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.BadgesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgesModel,Integer> {



    List<BadgesModel> findAll();
}
