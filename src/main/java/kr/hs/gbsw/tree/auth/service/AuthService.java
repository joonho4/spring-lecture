package kr.hs.gbsw.tree.auth.service;

import kr.hs.gbsw.tree.auth.dto.LoginDto;
import kr.hs.gbsw.tree.auth.dto.LoginResultDto;
import kr.hs.gbsw.tree.user.model.User;
import kr.hs.gbsw.tree.user.repository.UserRepository;
import kr.hs.gbsw.tree.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResultDto login(LoginDto dto) {
        // 1. dto.id 로 사용자를 불러와야 한다.
        User user = userRepository.findById(dto.getId()).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        // 2. 비밀번호 동일 여부 확인
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return new LoginResultDto(jwtUtil.generateToken(user));
    }

    // AuthenticationToken -> AuthenticationManager -> AuthenticationProvider
    public LoginResultDto loginWithAuthenticationManager(LoginDto dto) {
        var request = new UsernamePasswordAuthenticationToken(
                dto.getId(), dto.getPassword()
        );

        var result = authenticationManager.authenticate(request);
        return new LoginResultDto(jwtUtil.generateToken((User) result.getPrincipal()));
    }
}
