package IntRoom.Introom_backend.dto;

import lombok.Data;

@Data
public class OtpVerify {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public OtpVerify(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    private String email;
    private String otp;
}
