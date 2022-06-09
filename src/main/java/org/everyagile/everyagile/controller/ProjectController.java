package org.everyagile.everyagile.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.User;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.domain.response.SingleResult;
import org.everyagile.everyagile.dto.InviteRequestDto;
import org.everyagile.everyagile.dto.ProjectRequestDto;
import org.everyagile.everyagile.dto.SignInRequestDto;
import org.everyagile.everyagile.dto.TokenDto;
import org.everyagile.everyagile.service.ProjectService;
import org.everyagile.everyagile.service.ResponseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3. 프로젝트 컨트롤러"})
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트 생성", notes = "프로젝트를 생성한다 (타입은 DEFAUT or DEVELOP) ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("")
    public CommonResult createProject(@RequestBody @ApiParam(value = "생성할 프로젝트 정보", required = true) ProjectRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(projectService.createProject(email, requestDto));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @ApiOperation(value = "모든 프로젝트 조회", notes = "모든 프로젝트를 조회한다.")
    @GetMapping("")
    public CommonResult getALlProjects() {
        return responseService.getListResult(projectService.getAllProjects());
    }

    @ApiOperation(value = "프로젝트 정보 조회", notes = "프로젝트 정보를 조회한다 (타입은 DEFAUT or DEVELOP)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{projectId}")
    public CommonResult getProject(@PathVariable Long projectId) {
        return responseService.getSingleResult(projectService.getProjectByIdx(projectId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트 회원 초대", notes = "프로젝트에 회원을 초대한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/member")
    public CommonResult invite(@RequestBody @ApiParam(value = "초대할 회원과 프로젝트 정보", required = true) InviteRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        projectService.inviteMember(email, requestDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트 삭제", notes = "프로젝트를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/{projectId}")
    public CommonResult deleteProject(@PathVariable Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        projectService.deleteProjectByIdx(projectId, email);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트 멤버 조회", notes = "프로젝트 멤버를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{projectId}/members")
    public CommonResult getMembers(@PathVariable Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getListResult(projectService.getMembers(projectId, email));
    }
}
