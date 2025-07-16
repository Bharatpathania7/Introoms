

package IntRoom.Introom_backend.service;

//public class FileuploadService {
//}
//package IntRoom.Introom_backend.service;

import IntRoom.Introom_backend.model.Room;
import IntRoom.Introom_backend.repository.RoomsRepository;
import IntRoom.Introom_backend.repository.UserRepository;
import IntRoom.Introom_backend.utils.LocalityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Service
//public class FileUploadService {
//
//    private final S3Client s3Client;
//    @Autowired
//    private RoomsRepository roomsRepository;
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucketName;
//
//    public FileUploadService(S3Client s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    public String uploadFile(MultipartFile file , String roomtype , String locality, String description , String ContactNumber) throws IOException {
//        String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//        // Save temp file
//        var tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
//        file.transferTo(tempFile.toFile());
//
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .contentType(file.getContentType())
//                .build();
//
//        s3Client.putObject(request, tempFile);
//
//        String fileurl =  "https://" + bucketName + ".s3.amazonaws.com/" + key;
//        Room media = new Room();
//        media.setRoomtype(roomtype);
//        media.setDealerId(media.getDealerId());
//        media.setLocality(locality);
//        media.setDescription(description);
//        media.setContactNumber(ContactNumber);
//
//        roomsRepository.save(media);
//        return fileurl;
//    }
//}
@Service
public class FileUploadService {

    private final S3Client s3Client;
    @Autowired
    private LocalityValidator localityValidator;

    @Autowired
    private RoomsRepository roomsRepository;

    @Value("${upload.max-file-size}")
    private long maxFileSize;

    @Value("#{'${upload.allowed-types}'.split(',')}")
    private List<String> allowedTypes;


    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public FileUploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadSingleFile(MultipartFile file) throws IOException {
        String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        var tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile.toFile());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, tempFile);
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
    public List<Room> getUploadsByDealer(String dealerEmail) {
        return roomsRepository.findByDealerId(dealerEmail);
    }

    public void saveRoomWithFiles(List<MultipartFile> files, String roomType, String locality, String description, String dealerEmail , Double price ,String city , Double latitude , Double longitude ) throws IOException {
        List<String> imageUrls = new ArrayList<>();
        List<String> videoUrls = new ArrayList<>();
        if (!LocalityValidator.isValid(locality)) {
            throw new IllegalArgumentException("❌ Invalid locality: " + locality);
        }


        for (MultipartFile file : files) {
            String fileUrl = uploadSingleFile(file);

            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image")) {
                imageUrls.add(fileUrl);
            } else if (contentType != null && contentType.startsWith("video")) {
                videoUrls.add(fileUrl);
            }
            if (!LocalityValidator.isValid(locality)) {
                throw new IllegalArgumentException("❌ Invalid locality: " + locality);
            }
            if (file.getSize() > maxFileSize) {
                throw new IllegalArgumentException("❌ File size exceeds limit of " + maxFileSize + " bytes");
            }
            if (!allowedTypes.contains(file.getContentType())) {
                throw new IllegalArgumentException("❌ Invalid file type: " + file.getContentType());
            }

        }

        Room room = new Room();
        room.setRoomtype(roomType);
        room.setLocality(locality);
        room.setDescription(description);
        room.setContactNumber(dealerEmail); // optional: use dealerName field if needed
        room.setImageurls(imageUrls);
        room.setVideourls(videoUrls);
        room.setDealerId(dealerEmail);
        

        room.setPrice(price != null ? price : 0);
        room.setCity(city != null ? city : "Ludhiana");
        room.setLatitude(latitude != null ? latitude : 0);
        room.setLongitude(longitude != null ? longitude : 0);


        roomsRepository.save(room);
    }
}
