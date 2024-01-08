package kr.hs.gbsw.tree.auth.controller;

import kr.hs.gbsw.tree.auth.dto.LoginDto;
import kr.hs.gbsw.tree.auth.dto.LoginResultDto;
import kr.hs.gbsw.tree.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/login/old")
//    public LoginResultDto login(
//            @RequestBody LoginDto dto
//    ) {
//        return authService.login(dto);
//    }

    @PostMapping("/login")
    public LoginResultDto loginWithAuthenticationManager(
            @RequestBody LoginDto dto
    ) {
        return authService.loginWithAuthenticationManager(dto);
    }
}
