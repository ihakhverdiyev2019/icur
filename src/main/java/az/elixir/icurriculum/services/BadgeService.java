package az.elixir.icurriculum.services;

import az.elixir.icurriculum.models.BadgesModel;
import az.elixir.icurriculum.models.CollectionModel;
import az.elixir.icurriculum.models.StudentCollections;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface BadgeService {

    BadgesModel saveBadge(BadgesModel badgesModel);

    List<BadgesModel> getAllBadge();

    List<BadgesModel> getAllBadgeOfStudents(String id);

    BadgesModel getAllBadgeOfStudentsAndBadge(String sid, String bid);


    BadgesModel getById(String id);

    void removeBadge(int id);


    void enableTheCollection(String studentId,  String  programId) throws IOException, WriterException;
    void enableAllCollection( String  programId);


    void disableTheCollection(String studentId,  String  programId);
    void disableAllCollection(String programId);

    CollectionModel saveCollections(CollectionModel collectionModel);
}
