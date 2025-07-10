package IntRoom.Introom_backend.repository;

import IntRoom.Introom_backend.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomsRepository extends MongoRepository<Room, String> {
    List<Room> findDealerId(String dealerId);

}
