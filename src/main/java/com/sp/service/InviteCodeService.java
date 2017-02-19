package com.sp.service;

import com.blade.jdbc.core.Take;
import com.sp.model.InviteCode;

import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
public interface InviteCodeService {

    List<InviteCode> getCodes(Take take);

    void saveCodes(Integer user_id, int num, String prefix);

    InviteCode byCode(String code);

    void delete(Integer id);
}