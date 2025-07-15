package IntRoom.Introom_backend.controller;

import IntRoom.Introom_backend.model.Room;
import IntRoom.Introom_backend.service.StudentMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/media")
public class StudentMediaController {
            @Autowired
          private StudentMediaService studentMediaService;
          @GetMapping
    public ResponseEntity<List<Room>> getMedia(
            @RequestParam(required = false) String roomtype,
            @RequestParam(required = false) String locality,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
          ) {
              List<Room> results = studentMediaService.getfilteredRooms(roomtype, locality);
              return ResponseEntity.ok(results);
          }
}
