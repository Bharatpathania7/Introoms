//package IntRoom.Introom_backend.service;
//
//import IntRoom.Introom_backend.dto.ForgetPasswordRequest;
//import IntRoom.Introom_backend.dto.LoginRequest;
//import IntRoom.Introom_backend.dto.SignupRequest;
//import IntRoom.Introom_backend.model.User;
//import IntRoom.Introom_backend.repository.UserRepository;
//import IntRoom.Introom_backend.utils.JwtUtil;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Random;
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository userrepo;
//    @Autowired
//    private JavaMailSender mailsender;
//    @Autowired
//    private JwtUtil jwtUtill;
//
//  private final Map<String , SignupRequest> pendinguser = new HashMap<>();//user waiting for otp verification
//  private final Map<String,String> otpMap = new HashMap<>(); // otp mappped by email
//                    // signuppp
//    public String requestSignup(SignupRequest req){
//        if (userrepo.findByEmail(req.getEmail()).isPresent())return "user alerady exists";
//        String Otp  = generateOtp();
//        otpMap.put(req.getEmail(),Otp);
//        pendinguser.put(req.getEmail(),req);
//        sendEmail(req.getEmail(),"IntRooms Signup OTP", Otp);
//        return  "Otp sent to" + req.getEmail();
//    }                 // verify asnd complete signupp
//    public  String VerifYAndCompleteSignup(String email , String otp){
//        if(!otp.equals(otpMap.get(email))) return "Invalid or Expired OTP";
//        SignupRequest req = pendinguser.get(email);
//        if (req == null) return "Signup Session Expired";
//        User user = new User();
//        user.setEmail(email);
//        user.setName(req.getName());
//        user.setPassword(req.getPassword());
//        user.setRole(req.getRole());
//        user.setEmailVerified(true);
//        user.setCounty(req.getCountry());
//        user.setCity(req.getCity());
//        user.setLatitude(req.getLatitude());
//        user.setLongitude(req.getLongitude());
//
//        userrepo.save(user);
//        otpMap.remove(email);
//        pendinguser.remove(email);
//
//        return "Signup Complete";
//
//    }
//                   // loginnnnn
//    public String login(LoginRequest reql){
//        Optional<User> userOpt = userrepo.findByEmail(reql.getEmail());
//        if (userOpt.isEmpty()) return "User not found";
//
//        User user = userOpt.get();
//        if (!user.isEmailVerified()) return "Email not verified";
//        if (!user.getPassword().equals(reql.getPassword())) return "Incorrect password";
//        return jwtUtill.generateToken(user.getEmail());
//
//    }                    // otp for forget reset password //
//    public String sendOtpForReset(String email) {
//        if (!userrepo.findByEmail(email).isPresent()) return "User not found";
//        String otp = generateOtp();
//        otpMap.put(email, otp);
//        sendEmail(email, "IntRooms Password Reset OTP", otp);
//        return "OTP sent to email";
//    }
//    public String resetPassword(ForgetPasswordRequest req) {
//        String validOtp = otpMap.get(req.getEmail());
//        if (validOtp == null || !validOtp.equals(req.getOtp())) return "Invalid OTP";
//        if (!req.getNewPassword().equals(req.getConfirmPassword())) return "Passwords don't match";
//
//        User user = userrepo.findByEmail(req.getEmail()).orElseThrow();
//        user.setPassword(req.getNewPassword());
//        userrepo.save(user);
//        otpMap.remove(req.getEmail());
//        return "Password reset successful";
//    }
//    private void sendEmail(String to, String subject, String otp) {
//        try {
//            MimeMessage message = mailsender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            String htmlContent = """
//                    <html>
//                      <body style=\"font-family: Arial, sans-serif; padding: 20px; background: #f4f4f4;\">
//                        <div style=\"max-width: 500px; margin: auto; background: white; padding: 30px; border-radius: 10px;\">
//                          <h2 style=\"color: #4CAF50; text-align: center;\">IntRooms - Email Verification</h2>
//                          <p>Thank you for registering with <strong>IntRooms</strong>.</p>
//                          <p style=\"font-size: 18px;\">🔐 <strong>Your OTP is:</strong></p>
//                          <h1 style=\\"text-align: center; background: #e8f5e9; color: #388e3c; padding: 10px; border-radius: 5px;\\">""\" + otp + \"</h1>
//                          <p>This OTP is valid for 10 minutes. Please do not share it.</p>
//                          <p style=\"font-size: 12px; color: #999; text-align: center;\">© 2025 IntRooms. All rights reserved.</p>
//                        </div>
//                      </body>
//                    </html>
//                    """;
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(htmlContent, true);
//            mailsender.send(message);
//        } catch (Exception e) {
//            System.out.println("Failed to send email: " + e.getMessage());
//        }
//    }
//    private String generateOtp() {
//        return String.valueOf(new Random().nextInt(900000) + 100000);
//    }
//}
//
//
package IntRoom.Introom_backend.service;

import IntRoom.Introom_backend.dto.ForgetPasswordRequest;
import IntRoom.Introom_backend.dto.LoginRequest;
import IntRoom.Introom_backend.dto.SignupRequest;
import IntRoom.Introom_backend.model.User;
import IntRoom.Introom_backend.repository.UserRepository;
import IntRoom.Introom_backend.utils.JwtUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private JavaMailSender mailsender;

    @Autowired
    private JwtUtil jwtUtill;

    private final Map<String, SignupRequest> pendinguser = new HashMap<>(); // user waiting for otp verification
    private final Map<String, String> otpMap = new HashMap<>(); // otp mapped by email

    // ✅ STEP 1: Signup request → Generate OTP + Email it
    public String requestSignup(SignupRequest req) {
        if (userrepo.findByEmail(req.getEmail()).isPresent())
            return "User already exists";

        String otp = generateOtp();
        otpMap.put(req.getEmail(), otp);
        pendinguser.put(req.getEmail(), req);
        sendEmail(req.getEmail(), "IntRooms Signup OTP", otp);

        return "OTP sent to " + req.getEmail();
    }

    // ✅ STEP 2: Verify OTP & complete signup
    public String VerifYAndCompleteSignup(String email, String otp) {
        if (!otp.equals(otpMap.get(email))) return "Invalid or expired OTP";

        SignupRequest req = pendinguser.get(email);
        if (req == null) return "Signup session expired";

        User user = new User();
        user.setEmail(email);
        user.setName(req.getName());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole());
        user.setEmailVerified(true);
        user.setCounty(req.getCountry());
        user.setCity(req.getCity());
        user.setLatitude(req.getLatitude());
        user.setLongitude(req.getLongitude());

        userrepo.save(user);
        otpMap.remove(email);
        pendinguser.remove(email);

        return "Signup complete";
    }

    // ✅ Login with email & password
    public String login(LoginRequest reql) {
        Optional<User> userOpt = userrepo.findByEmail(reql.getEmail());
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        if (!user.isEmailVerified()) return "Email not verified";
        if (!user.getPassword().equals(reql.getPassword())) return "Incorrect password";

        return jwtUtill.generateToken(user.getEmail());
    }

    // ✅ Send OTP for password reset
    public String sendOtpForReset(String email) {
        if (!userrepo.findByEmail(email).isPresent()) return "User not found";

        String otp = generateOtp();
        otpMap.put(email, otp);
        sendEmail(email, "IntRooms Password Reset OTP", otp);
        return "OTP sent to email";
    }

    // ✅ Reset password after OTP verification
    public String resetPassword(ForgetPasswordRequest req) {
        String validOtp = otpMap.get(req.getEmail());
        if (validOtp == null || !validOtp.equals(req.getOtp())) return "Invalid OTP";
        if (!req.getNewPassword().equals(req.getConfirmPassword())) return "Passwords don't match";

        User user = userrepo.findByEmail(req.getEmail()).orElseThrow();
        user.setPassword(req.getNewPassword());
        userrepo.save(user);
        otpMap.remove(req.getEmail());

        return "Password reset successful";
    }

    // ✅ Send OTP as beautiful HTML Email
    private void sendEmail(String to, String subject, String otp) {
        try {
            MimeMessage message = mailsender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent = """
                <html>
                  <body style="font-family: Arial, sans-serif; padding: 20px; background: #f4f4f4;">
                    <div style="max-width: 500px; margin: auto; background: white; padding: 30px; border-radius: 10px;">
                      <h2 style="color: #4CAF50; text-align: center;">IntRooms - Email Verification</h2>
                      <p>Thank you for registering with <strong>IntRooms</strong>.</p>
                      <p style="font-size: 18px;">🔐 <strong>Your OTP is:</strong></p>
                      <h1 style="text-align: center; background: #e8f5e9; color: #388e3c; padding: 10px; border-radius: 5px;">%s</h1>
                      <p>This OTP is valid for 10 minutes. Please do not share it.</p>
                      <p style="font-size: 12px; color: #999; text-align: center;">© 2025 IntRooms. All rights reserved.</p>
                    </div>
                  </body>
                </html>
                """.formatted(otp);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailsender.send(message);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    // ✅ Generate 6-digit OTP
    private String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
}
