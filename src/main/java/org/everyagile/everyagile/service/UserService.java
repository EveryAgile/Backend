package org.everyagile.everyagile.service;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CAccountExistedException;
import org.everyagile.everyagile.advice.CRefreshTokenException;
import org.everyagile.everyagile.advice.CSigninFailedException;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.domain.*;
import org.everyagile.everyagile.dto.SignUpRequestDto;
import org.everyagile.everyagile.dto.TokenDto;
import org.everyagile.everyagile.dto.TokenRequestDto;
import org.everyagile.everyagile.dto.UserResponseDto;
import org.everyagile.everyagile.repository.RefreshTokenRepository;
import org.everyagile.everyagile.repository.UserRepository;
import org.everyagile.everyagile.security.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 로그인 로직
    @Transactional
    public TokenDto signin(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(CSigninFailedException::new);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new CSigninFailedException();
        }
        TokenDto tokenDto = jwtTokenProvider.createToken(user.getEmail(), user.getRole());

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(user.getId())
                .token(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }

    // 회원 가입 로직
    @Transactional
    public User singup(SignUpRequestDto requestDto, boolean isAdmin) {
        Optional<User> foundUser = userRepository.findByEmail(requestDto.getEmail());
        if (foundUser.isPresent()) {
            throw new CAccountExistedException();
        }
        String endcodedPw = passwordEncoder.encode(requestDto.getPassword());
        Role role = Role.USER;
        if (isAdmin) {
            role = Role.AMIN;
        }
        User user = new User(requestDto.getName(), requestDto.getEmail() ,endcodedPw, role);
        return userRepository.save(user);
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto requestDto) {
        if(!jwtTokenProvider.validateToken(requestDto.getRefreshToken())) {
            throw new CRefreshTokenException();
        }

        String accessToken = requestDto.getAccessToken();
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(CUsernameNotFoundException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId()).orElseThrow(CRefreshTokenException::new);

        if (!refreshToken.getToken().equals(refreshToken.getToken()))
            throw new CRefreshTokenException();

        TokenDto newCreatedToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        RefreshToken updatedRefreshToken = refreshToken.updateToken(newCreatedToken.getRefreshToken());
        refreshTokenRepository.save(updatedRefreshToken);

        return newCreatedToken;
    }

    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public List<Project> getUserProject (String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        return user.getProjects();
    }

    public List<Sprint> getUserSprint (String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        return user.getSprints();
    }




}
