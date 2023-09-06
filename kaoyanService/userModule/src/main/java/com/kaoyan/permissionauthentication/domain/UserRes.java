package com.kaoyan.permissionauthentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    private Integer userId;

    private String userNickname;

    private String userSignature;

    private String userAvatar;
}
