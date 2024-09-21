package com.example.file.courseapp.auth;

import com.example.file.courseapp.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
