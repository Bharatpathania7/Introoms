

package IntRoom.Introom_backend.service;

//public class FileuploadService {
//}
//package IntRoom.Introom_backend.service;

import IntRoom.Introom_backend.model.Room;
import IntRoom.Introom_backend.repository.RoomsRepository;
import IntRoom.Introom_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileUploadService {

    private final S3Client s3Client;
    @Autowired
    private RoomsRepository roomsRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public FileUploadService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file , String roomType , String locality, String description , String ContactNumber) throws IOException {
        String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Save temp file
        var tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile.toFile());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, tempFile);

        String fileurl =  "https://" + bucketName + ".s3.amazonaws.com/" + key;
        Room media = new Room();
        media.setRoomtype(roomType);
        media.setDealerId(media.getDealerId());
        media.setLocality(locality);
        media.setDescription(description);
        media.setContactNumber(ContactNumber);

        roomsRepository.save(media);
        return fileurl;
    }
}
