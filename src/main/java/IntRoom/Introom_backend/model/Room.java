//package IntRoom.Introom_backend.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Document(collection = "rooms")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Room {
//    @Id
//    private String id;
//
//    private String dealerid;
//
//    public String getDealerid() {
//        return dealerid;
//    }
//
//    public void setDealerid(String dealerid) {
//        this.dealerid = dealerid;
//    }
//
//    private  String title;
//    private  String description;
//    private String roomtype;
//    private String city;
//    private String address;
//    private double latitude;
//    private double longitude;
//    private double price;
//    private  String contactNumber;
//    private List<String> imageurls;
//    private List<String> videourls;
//    private LocalDateTime createdAt;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getRoomtype() {
//        return roomtype;
//    }
//
//    public void setRoomtype(String roomtype) {
//        this.roomtype = roomtype;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getContactNumber() {
//        return contactNumber;
//    }
//
//    public void setContactNumber(String contactNumber) {
//        this.contactNumber = contactNumber;
//    }
//
//    public List<String> getImageurls() {
//        return imageurls;
//    }
//
//    public void setImageurls(List<String> imageurls) {
//        this.imageurls = imageurls;
//    }
//
//    public List<String> getVideourls() {
//        return videourls;
//    }
//
//    public void setVideourls(List<String> videourls) {
//        this.videourls = videourls;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//}

package IntRoom.Introom_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    private String id;

    // ✅ Renamed from dealerid → dealerId (camelCase)

    private String dealerId;

    private String title;
    private String description;
    private String roomtype;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double price;
    private String contactNumber;
    private boolean active = true; // default true


    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    private String locality; // in ludhiana areass
    private List<String> imageurls;
    private List<String> videourls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<String> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<String> imageurls) {
        this.imageurls = imageurls;
    }

    public List<String> getVideourls() {
        return videourls;
    }

    public void setVideourls(List<String> videourls) {
        this.videourls = videourls;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private LocalDateTime createdAt;

    // ✅ You can optionally remove these manual getters/setters if you use Lombok @Data
}
