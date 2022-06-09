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
import org.everyagile.everyagile.dto.responseDto.*;
import org.everyagile.everyagile.repository.*;
import org.everyagile.everyagile.security.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserProjectRepository userProjectRepository;
    private final UserSprintRepository userSprintRepository;
    private final UserBacklogRepository userBacklogRepository;
    private final UserTaskRepository userTaskRepository;

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

    // 토큰 재발급
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

    // 회원 정보 조회
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        return new UserResponseDto(user);
    }

    // 모든 회원 조회
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user: users){
            userResponseDtos.add(new UserResponseDto(user));
        }
        return userResponseDtos;
    }

    // 회원별 프로젝트 리스트 조회
    public List<ProjectResponseDto> getUserProject (String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserProject> groups =  userProjectRepository.findAllByUser(user);
        List<ProjectResponseDto> projects = new ArrayList<>();
        for(UserProject group : groups) {
            Project project = group.getProject();
            projects.add(new ProjectResponseDto(project));
        }
        return projects;
    }

    // 회원별 스프린트 리스트 조회
    public List<SprintResponseDto> getUserSprint(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserSprint> userSprints = userSprintRepository.findAllByUser(user);
        List<SprintResponseDto> sprints = new ArrayList<>();
        for(UserSprint userSprint: userSprints){
            sprints.add(new SprintResponseDto(userSprint.getSprint()));
        }
        return sprints;
    }

    // 회원별 백로그 리스트 조회
    public List<BacklogResponseDto> getUserBacklog(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserBacklog> userBacklogs = userBacklogRepository.findAllByUser(user);
        List<BacklogResponseDto> backlogs = new ArrayList<>();
        for(UserBacklog userBacklog : userBacklogs){
            backlogs.add(new BacklogResponseDto(userBacklog.getBacklog()));
        }
        return backlogs;
    }

    // 회원별 할일 리스트 조회
    public List<TaskResponseDto> getUserTask(String email){
        User user = userRepository.findByEmail(email).orElseThrow(CUsernameNotFoundException::new);
        List<UserTask> userTasks = userTaskRepository.findAllByUser(user);
        List<TaskResponseDto> tasks = new ArrayList<>();
        for(UserTask userTask : userTasks) {
            tasks.add(new TaskResponseDto(userTask.getTask()));
        }
        return tasks;
    }

}
