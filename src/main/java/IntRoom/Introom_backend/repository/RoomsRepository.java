//package IntRoom.Introom_backend.repository;
//
//import IntRoom.Introom_backend.model.Room;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
//public interface RoomsRepository extends MongoRepository<Room, String> {
//    List<Room> findByDealerId(String dealerId);
//
//}
package IntRoom.Introom_backend.repository;

import IntRoom.Introom_backend.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomsRepository extends MongoRepository<Room, String> {
    // This works now, since dealerId matches the field in Room
    List<Room> findByDealerId(String dealerId);
}
