package com.patika.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Login {
    private String kimlikNo;
    private String password;

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
