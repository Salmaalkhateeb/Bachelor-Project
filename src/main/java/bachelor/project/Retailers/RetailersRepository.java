package bachelor.project.Retailers;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailersRepository extends MongoRepository<Retailer, String> {
}
