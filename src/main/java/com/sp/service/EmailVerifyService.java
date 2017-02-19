package com.sp.service;

public interface EmailVerifyService {
	
    boolean checkVerifyCode(String email, String verifycode);

    void sendVerification(String email);
}
