package com.sp.service;

import com.sp.model.Token;

/**
 * Created by biezhi on 2017/2/20.
 */
public interface TokenService {

    Token byToken(String token);

    String create(Integer id);

}
