package org.everyagile.everyagile.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.domain.response.SingleResult;
import org.everyagile.everyagile.dto.SignInRequestDto;
import org.everyagile.everyagile.dto.SignUpRequestDto;
import org.everyagile.everyagile.dto.TokenDto;
import org.everyagile.everyagile.dto.TokenRequestDto;
import org.everyagile.everyagile.service.ResponseService;
import org.everyagile.everyagile.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"1. 계정 컨트롤러"})
@RequestMapping(value = "/users/auth")
public class SignController {
    private final UserService userService;
    private final ResponseService responseService;

    @ApiOperation(value = "로그인", notes = "로그인을 한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/signin")
    public SingleResult<TokenDto> signIn(@RequestBody @ApiParam(value = "이메일 비밀번호", required = true) SignInRequestDto requestDto) {
        TokenDto tokenDto = userService.signin(requestDto.getEmail(), requestDto.getPassword());
        return responseService.getSingleResult(tokenDto);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/signup")
    public CommonResult signUp(@RequestBody @ApiParam(value = "가입할 회원 정보", required = true) SignUpRequestDto requestDto) {
        userService.singup(requestDto, false);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "토큰 재발급", notes = "토큰 만료시 재발급한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/reissue")
    public CommonResult reissue(@RequestBody @ApiParam(value = "토큰 재발급 요청 DTO", required = true) TokenRequestDto requestDto) {
        return responseService.getSingleResult(userService.reissue(requestDto));
    }
}
