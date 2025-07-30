package com.forgotpassword.controller;

import com.ForgotPassword.Dto.ForgotPasswordDto;
//import com.ForgotPassword.service.PasswordService;
import com.forgotpassword.Dto.ForgotPassword;
import com.forgotpassword.service.ForgotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forgotpassword")
public class PasswordController
{
    @Autowired
    private ForgotService forgotPasswordService;

    @PostMapping("/request")
    public ResponseEntity<String> requestReset(@RequestBody ForgotPassword request) {
        String response = forgotPasswordService.initiateReset(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ForgotPasswordDto request) {
        String response = forgotPasswordService.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}
