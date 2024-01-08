package kr.hs.gbsw.tree.user.dto;

import lombok.Getter;

@Getter
public class CreateUserDto {
    private String id;
    private String password;
    private String name;
    private String email;
}
