package com.sp.service;

import com.sp.model.PasswordReset;

/**
 * Created by biezhi on 2017/2/19.
 */
public interface PasswordResetService {

    void sendResetEmail(String email);

    PasswordReset byToken(String token);
}
