package az.elixir.icurriculum.repositories;

import az.elixir.icurriculum.models.CollectionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<CollectionModel,Integer> {


}
