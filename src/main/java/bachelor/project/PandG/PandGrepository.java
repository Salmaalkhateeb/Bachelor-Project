package bachelor.project.PandG;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PandGrepository extends MongoRepository<PGusers, String> {
}