package IntRoom.Introom_backend.controller;

import IntRoom.Introom_backend.dto.ForgetPasswordRequest;
import IntRoom.Introom_backend.dto.LoginRequest;
import IntRoom.Introom_backend.dto.OtpVerify;
import IntRoom.Introom_backend.dto.SignupRequest;
import IntRoom.Introom_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
    @Autowired
    private AuthService authService;


    @GetMapping("/user/profile")
    public String getProfile(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return "❌ Not authenticated";
        }
        return "✅ Logged in as: " + authentication.getPrincipal().toString();
    }




    @PostMapping("/signup/request")
    public String requestsignup(@RequestBody SignupRequest request) {
        return authService.requestSignup(request);

    }

    @PostMapping("/signup/verify")
    public String verifySignup(@RequestBody OtpVerify request) {
        return authService.VerifYAndCompleteSignup(request.getEmail(), request.getOtp());
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/forget/send-otp")
    public String Sendotp(@RequestParam String email) {
        return authService.sendOtpForReset(email);

    }

    @PostMapping("/forgot/rest")
    public String resetPassword(@RequestBody ForgetPasswordRequest request) {
        return authService.resetPassword(request);
    }
}

