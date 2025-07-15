package IntRoom.Introom_backend.service;

import IntRoom.Introom_backend.model.Room;
import IntRoom.Introom_backend.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentMediaService {
    @Autowired
    private RoomsRepository roomsRepository;

    public List<Room> getfilteredRooms(String roomType,String locality) {
        if (roomType != null && locality != null) {
            return roomsRepository.findByRoomtypeAndLocality(roomType, locality);
        } else if (roomType != null) {
            return roomsRepository.findByRoomtype(roomType);

        } else if (locality != null) {
            return roomsRepository.findByLocality(locality);
        } else {
            return roomsRepository.findAll();
        }



    }
}
