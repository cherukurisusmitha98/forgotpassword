package com.ForgotPassword.Dto;

public class ForgotPasswordDto {
    private String token;
    private String resetPassword;

    public ForgotPasswordDto(String token, String resetPassword) {
        this.token = token;
        this.resetPassword = resetPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public ForgotPasswordDto() {
    }
}


