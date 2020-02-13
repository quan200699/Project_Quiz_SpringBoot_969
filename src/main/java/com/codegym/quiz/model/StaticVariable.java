package com.codegym.quiz.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticVariable {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_TUTOR = "TUTOR";
    public static final String TEXT_REGISTER = "Để xác thực tài khoản xin hãy nhấn vào đường dẫn này :" ;
    public static final String TEXT_PASSWORD_FORGOT = "Nhấn vào đường dẫn sau để đổi mật khẩu :" ;
    public static final String SUBJECT_REGISTER = "Đăng ký thành công!";
    public static final String SUBJECT_PASSWORD_FORGOT = "Đổi mật khẩu";
    public static final String LOGIN = "/login";
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
