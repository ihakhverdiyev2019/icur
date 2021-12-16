package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.BadgesModel;
import az.elixir.icurriculum.models.ProgramBadge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramBadgeRepository extends CrudRepository<ProgramBadge,Integer> {
    List<ProgramBadge> findAllByBadgesModel(BadgesModel badgesModel);
    ProgramBadge deleteAllByBadgesModel(BadgesModel badgesModel);
}
