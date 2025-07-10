package IntRoom.Introom_backend.repository;

import IntRoom.Introom_backend.model.Dealer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DealerRepository  extends MongoRepository<Dealer , String> {
    @Override
    Optional<Dealer> findById(String email);
}
