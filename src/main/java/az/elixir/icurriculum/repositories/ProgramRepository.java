package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.ProgramsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository<ProgramsModel,Integer> {
    List<ProgramsModel> findAll();

}
