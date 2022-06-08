package org.everyagile.everyagile.advice;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult();
    }

    @ExceptionHandler(CAccountExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult accountExistedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("중복된 계정 정보가 존재합니다.");
    }

    @ExceptionHandler(CUsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult usernameNotFoundException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("오류가 발생하였습니다. 계정의 Username을 찾을 수 없습니다.");
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 리소스에 접근하기 위한 권한이 없습니다.");
    }

    @ExceptionHandler(CSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult siginFailedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("계정 정보가 옳지 않습니다.");
    }

    @ExceptionHandler(CRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult refreshTokenException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("Refresh Token이 옳지 않습니다.");
    }

    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult accessDeniedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("보유한 권한으로 접근할 수 없습니다.");
    }

    @ExceptionHandler(CPostNotExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult postNotExistedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 게시글이 존재하지 않습니다.");
    }

    @ExceptionHandler(CEmotionResultFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emotionResultFailedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 게시글에 대한 감정 결과가 아직 분석중입니다.");
    }

    @ExceptionHandler(CRegionFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult regionFailedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 경위도에 대한 위치 정보를 가져올 수 없습니다.");
    }

    @ExceptionHandler(CProjectNotExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult projectNotExistedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 프로젝트가 존재하지 않습니다.");
    }

    @ExceptionHandler(CNotMemberException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult notMemberException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("회원은 해당 프로젝트의 멤버가 아닙니다.");
    }

    @ExceptionHandler(CSprintNotExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult sprintNotExistedException(HttpServletRequest request, Exception e){
        return responseService.getFailResultWithMsg("해당 스프린트가 존재하지 않습니다.");
    }
}
