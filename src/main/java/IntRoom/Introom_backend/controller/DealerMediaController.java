package IntRoom.Introom_backend.controller;//package IntRoom.Introom_backend.controller;
//
//import IntRoom.Introom_backend.service.FileUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/dealer/media")
//public class DealerMediaController {
//    @Autowired
//    private FileUploadService fileuploadService;
//
//    @PostMapping
//    public ResponseEntity<?> uploadMedia(
//            @RequestParam("file" )MultipartFile file
//            ) {
//        try{
//            String fileUrl = fileuploadService.uploadFile(file);
//            return  ResponseEntity.ok().body("Uploaded:"  + fileUrl);
//        }  catch (IOException e){
//            return  ResponseEntity.internalServerError().body("upload failed :" + e.getMessage());
//        }
//    }
//
//}
import IntRoom.Introom_backend.model.Room;
import IntRoom.Introom_backend.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dealer/media")
public class DealerMediaController {

    @Autowired
    private FileUploadService fileuploadService;

//    @PostMapping
//    public ResponseEntity<?> uploadMedia(
//            @RequestParam("files") List<MultipartFile> files,
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("roomType") String roomType,
//            @RequestParam("locality") String locality,
//            @RequestParam(value = "description", required = false) String description,
//            Principal principal
//    ) {
//        try {
//            String dealerEmail = principal.getName(); // extracted from JWT
//            String fileUrl = fileuploadService.uploadFile(file, roomType, locality, description, dealerEmail);
//            return ResponseEntity.ok().body("Uploaded: " + fileUrl);
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
//        }
//    }
//}
//@RestController
//@RequestMapping("/dealer/media")
//public class DealerMediaController {
//
//    @Autowired
//    private FileUploadService fileuploadService;

//    @PostMapping
//    public ResponseEntity<?> uploadMedia(
//            @RequestParam("files") List<MultipartFile> files,
//            @RequestParam("roomType") String roomType,
//            @RequestParam("locality") String locality,
//            @RequestParam(value = "description", required = false) String description,
//            Principal principal
//    ) {
//        try {
//            String dealerEmail = principal.getName(); // extracted from JWT
//
//            List<String> uploadedUrls = new ArrayList<>();
//
//            for (MultipartFile file : files) {
//                String fileUrl = fileuploadService.uploadFile(file, roomType, locality, description, dealerEmail);
//                uploadedUrls.add(fileUrl);
//            }
//
//            return ResponseEntity.ok().body("Uploaded files: " + uploadedUrls);
//
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
//        }
//    }
//}


    @PostMapping
    public ResponseEntity<?> uploadMultipleMedia(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("roomType") String roomType,
            @RequestParam("locality") String locality,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude,
            Principal principal
    ) {
        try {
            String dealerEmail = principal.getName(); // from JWT
            fileuploadService.saveRoomWithFiles(files, roomType, locality, description, dealerEmail, price , city , latitude, longitude);
            return ResponseEntity.ok("✅ Files uploaded and room saved successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("❌ Upload failed: " + e.getMessage());
        }
    }
    @GetMapping("/history")
    public ResponseEntity<List<Room>> getUploadHistory(Principal principal) {
        String dealerEmail = principal.getName(); // Extracted from JWT
        List<Room> uploads = fileuploadService.getUploadsByDealer(dealerEmail);
        return ResponseEntity.ok(uploads);
    }


}