//package IntRoom.Introom_backend.model;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data   // it automatic Genrate Getter setter
//@Document("users") // it Make  mongodb collection name user
//public class User {
//    @Id  // unique code
//  private String id;
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public String getEmail() {
//    return email;
//  }
//
//  public void setEmail(String email) {
//    this.email = email;
//  }
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  public String getRole() {
//    return role;
//  }
//
//  public void setRole(String role) {
//    this.role = role;
//  }
//
//  public boolean isEmailVerified() {
//    return emailVerified;
//  }
//
//  public void setEmailVerified(boolean emailVerified) {
//    this.emailVerified = emailVerified;
//  }
//
//  public String getCity() {
//    return city;
//  }
//
//  public void setCity(String city) {
//    this.city = city;
//  }
//
//  public String getCounty() {
//    return county;
//  }
//
//  public void setCounty(String county) {
//    this.county = county;
//  }
//
//  public Double getLatitude() {
//    return latitude;
//  }
//
//  public void setLatitude(Double latitude) {
//    this.latitude = latitude;
//  }
//
//  public Double getLongitude() {
//    return Longitude;
//  }
//
//  public void setLongitude(Double longitude) {
//    Longitude = longitude;
//  }
//
//  public String getId() {
//    return id;
//  }
//
//  public void setId(String id) {
//    this.id = id;
//  }
//
//  private String name;
//    private String email;
//    private String password;
//    private String role; // dealer / student
//    private boolean emailVerified = false;
//    private String city;
//    private String county;
//    private Double latitude;
//    private Double Longitude;
//}
//
//
//


package IntRoom.Introom_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("users")
public class User {
  @Field("fullname")
  private String fullname;

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  @Id
  private String id;

  private String email;
  private String password;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

//  public String getName() {
//    return fullname;
//  }
//
//  public void setName(String name) {
//    this.fullname = name;
//  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  private String role; // "dealer" or "student"
  private boolean emailVerified = false;
  private String city;
  private String county;
  private Double latitude;
  private Double longitude;

  // Nullable dealer-specific fields (only populated if role="dealer")
  private String phoneNumber;
  private String businessName;
  private String licenseNumber; // Example: Real estate license
}