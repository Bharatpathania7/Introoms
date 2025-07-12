package IntRoom.Introom_backend.controller;


import IntRoom.Introom_backend.dto.DealerResponse;
import IntRoom.Introom_backend.dto.UpdateDealerRequest;
import IntRoom.Introom_backend.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    @Autowired
    private DealerService dealerService;
//    @GetMapping("/profile")
//    public ResponseEntity<DealerResponse> getprofile(Authentication authentication){
//        String email = authentication.getName();
//        DealerResponse response =dealerService.getprofile(email);
//        return ResponseEntity.ok(response);
//        }
                @GetMapping("/profile")
                   public ResponseEntity<DealerResponse> getprofile(Authentication authentication) {
        String email = authentication.getName();
        DealerResponse response = dealerService.getprofile(email);
        return ResponseEntity.ok(response);
}

        @PutMapping("/profile")
        public ResponseEntity<DealerResponse> updateProfile(
                Authentication authentication,
                @RequestBody UpdateDealerRequest request) {

            String email = authentication.getName(); // email from token
            DealerResponse response = dealerService.updateProfile(email, request);
            return ResponseEntity.ok(response);
        }
    }