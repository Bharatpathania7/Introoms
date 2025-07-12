//package IntRoom.Introom_backend.service;
//
//import IntRoom.Introom_backend.dto.DealerResponse;
//import IntRoom.Introom_backend.dto.UpdateDealerRequest;
//import IntRoom.Introom_backend.model.Dealer;
//import IntRoom.Introom_backend.repository.DealerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Optional;
//@Service
////package IntRoom.Introom_backend.service;
////
////import IntRoom.Introom_backend.dto.DealerResponse;
////import IntRoom.Introom_backend.dto.UpdateDealerRequest;
////import IntRoom.Introom_backend.model.Dealer;
////import IntRoom.Introom_backend.repository.DealerRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////
////import java.util.Optional;
////
////public class DealerService {
////
////    @Autowired
////    private DealerRepository dealerRepository;
////
////    public DealerResponse getProfile(String email){
////        Optional<Dealer> DealerOtp = dealerRepository.findById(email);
////        if (DealerOtp.isEmpty()) return null;
////        Dealer dealer = DealerOtp.get();
////        DealerResponse  response = new DealerResponse();
////        response.setFullName(dealer.getFullname());
////        response.setEmail(dealer.getEmail());
////        response.setCity(dealer.getCity());
////        response.setPhoneNumber(dealer.getNumber());
////
////        return  response;
////
////    }
////       public UpdateDealerRequest updateprofile( String email , UpdateDealerRequest request){
////           Optional<Dealer> DealerOtp = dealerRepository.findById(email);
////           if ((DealerOtp.isEmpty())) return null;
////
////               Dealer dealer = DealerOtp.get();
////               dealer.setFullname(request.getFullName());
////               dealer.set;
////       }
////}
//       public class DealerService {
//
//            @Autowired
//            private DealerRepository dealerRepository;
////            public DealerResponse getprofile(String email){
////                Optional<Dealer> DealerOtp = dealerRepository.findByemail(email);
////                if(DealerOtp.isEmpty()) return  null;
////                Dealer dealer = DealerOtp.get();
//          public DealerResponse getprofile(String email) {
//               Optional<Dealer> dealerOpt = dealerRepository.findByemail(email);
//             if(dealerOpt.isEmpty()) {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dealer not found");
//    }
//                             Dealer dealer = dealerOpt.get();
//         // rest of your mapping code
//
//                DealerResponse response = new DealerResponse();
//                response.setFullName(dealer.getFullname());
//                response.setEmail(dealer.getEmail());
//                response.setCity(dealer.getCity());
//                response.setPhoneNumber(dealer.getNumber());
//
//                return  response;
//            }
//
//             public DealerResponse updateProfile(String email , UpdateDealerRequest request){
//                Optional<Dealer> DealerOtp = dealerRepository.findByemail(email);
//                if(DealerOtp.isEmpty()) return null;
//                 Dealer dealer = DealerOtp.get();
//                dealer.setFullname(request.getFullName());
//                dealer.setCity(request.getCity());
//                dealer.setNumber(request.getPhoneNumber());
//
//                dealerRepository.save(dealer);
//
//
//                return  getprofile(email);
//             }
//
//}

package IntRoom.Introom_backend.service;

import IntRoom.Introom_backend.dto.DealerResponse;
import IntRoom.Introom_backend.dto.UpdateDealerRequest;
import IntRoom.Introom_backend.model.User;
import IntRoom.Introom_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DealerService {

    @Autowired
    private UserRepository userRepository;  // Changed from DealerRepository to UserRepository

    public DealerResponse getprofile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Verify user is actually a dealer
        if (!"dealer".equals(user.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not registered as a dealer");
        }

        // Map User to DealerResponse
        DealerResponse response = new DealerResponse();
        response.setFullName(user.getFullname());
        response.setEmail(user.getEmail());
        response.setCity(user.getCity());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setBusinessName(user.getBusinessName());
        response.setLicenseNumber(user.getLicenseNumber());

        return response;
    }

    public DealerResponse updateProfile(String email, UpdateDealerRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Verify user is a dealer
        if (!"dealer".equals(user.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only dealers can update dealer profiles");
        }

        // Update dealer-specific fields
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBusinessName(request.getBusinessName());
        user.setLicenseNumber(request.getLicenseNumber());
        user.setCity(request.getCity());

        userRepository.save(user);

        return getprofile(email);  // Return updated profile
    }
}