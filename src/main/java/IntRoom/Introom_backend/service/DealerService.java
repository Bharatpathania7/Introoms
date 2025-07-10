//package IntRoom.Introom_backend.service;
//
//import IntRoom.Introom_backend.dto.DealerResponse;
//import IntRoom.Introom_backend.dto.UpdateDealerRequest;
//import IntRoom.Introom_backend.model.Dealer;
//import IntRoom.Introom_backend.repository.DealerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//public class DealerService {
//
//    @Autowired
//    private DealerRepository dealerRepository;
//
//    public DealerResponse getProfile(String email){
//        Optional<Dealer> DealerOtp = dealerRepository.findById(email);
//        if (DealerOtp.isEmpty()) return null;
//        Dealer dealer = DealerOtp.get();
//        DealerResponse  response = new DealerResponse();
//        response.setFullName(dealer.getFullname());
//        response.setEmail(dealer.getEmail());
//        response.setCity(dealer.getCity());
//        response.setPhoneNumber(dealer.getNumber());
//
//        return  response;
//
//    }
//       public UpdateDealerRequest updateprofile( String email , UpdateDealerRequest request){
//           Optional<Dealer> DealerOtp = dealerRepository.findById(email);
//           if ((DealerOtp.isEmpty())) return null;
//
//               Dealer dealer = DealerOtp.get();
//               dealer.setFullname(request.getFullName());
//               dealer.set;
//       }
//}
