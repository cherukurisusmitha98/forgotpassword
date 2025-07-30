package com.forgotpassword.service;



import com.forgotpassword.Dto.ForgotPassword;
import com.ForgotPassword.Dto.ForgotPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

    @Service
    public class ForgotService {

        private final Map<String, TokenInfo> tokenStore = new HashMap<>();

        @Autowired
        private JavaMailSender mailSender;

        public String initiateReset(ForgotPassword request) {
            String token = UUID.randomUUID().toString();
            tokenStore.put(token, new TokenInfo(request.getEmail(), LocalDateTime.now().plusMinutes(30)));

            sendResetEmail(request.getEmail(), token);
            return "Reset link sent to email.";
        }

        public String resetPassword(ForgotPasswordDto request) {
            TokenInfo tokenInfo = tokenStore.get(request.getToken());

            if (tokenInfo == null || tokenInfo.expiryTime.isBefore(LocalDateTime.now())) {
                return "Invalid or expired token.";
            }

            // Simulate updating password in Register service
            System.out.println("Reset password for: " + tokenInfo.email);
            System.out.println("New Password: " + request.getResetPassword());

            tokenStore.remove(request.getToken());
            return "Password reset successful.";
        }

        private void sendResetEmail(String to, String token) {
            String link = "http://localhost:8083/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Reset your password");
            message.setText("Click here to reset your password: " + link);

            mailSender.send(message);
        }

        private static class TokenInfo {
            String email;
            LocalDateTime expiryTime;

            public TokenInfo(String email, LocalDateTime expiryTime) {
                this.email = email;
                this.expiryTime = expiryTime;
            }
        }
    }
