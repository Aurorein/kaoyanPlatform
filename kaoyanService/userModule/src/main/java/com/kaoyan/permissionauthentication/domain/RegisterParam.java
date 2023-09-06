package com.kaoyan.permissionauthentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {

    private String username;

    private String password;

    private String personalSignature;

    private String email;

    private String phone;
}
