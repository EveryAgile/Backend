package org.everyagile.everyagile.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.domain.response.SingleResult;
import org.everyagile.everyagile.dto.ProjectRequestDto;
import org.everyagile.everyagile.dto.SprintRequestDto;
import org.everyagile.everyagile.dto.responseDto.SprintResponseDto;
import org.everyagile.everyagile.service.ResponseService;
import org.everyagile.everyagile.service.SprintService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"4. 스프린트 컨트롤러"})
@RequestMapping(value = "/sprints")
public class SprintController {

    private final ResponseService responseService;
    private final SprintService sprintService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트 생성", notes = "스프린트를 생성한다 (중요도는 HIGH, DEFAULT, LOW)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("")
    public CommonResult createSprint(@RequestBody @ApiParam(value = "생성할 스프린트 정보", required = true)SprintRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(sprintService.createSprint(email, requestDto));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트 정보 조회", notes = "스프린트 정보를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{sprintId}")
    public CommonResult getSprint(@PathVariable Long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        SprintResponseDto responseDto = sprintService.getSprint(email, sprintId);
        return responseService.getSingleResult(responseDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트별 스프린트 목록 조회", notes = "프로젝트별 스프린트 목록 정보를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/project/{projectId}")
    public CommonResult getSprintByProject(@PathVariable Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getListResult(sprintService.getAllSprint(projectId, email));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트 완료 작성", notes = "스프린트 완료 여부를 작성한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PutMapping("/{sprintId}")
    public CommonResult setSprintStatus(@PathVariable Long sprintId, @RequestParam boolean status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(sprintService.setSprintStatus(sprintId, status, email));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트 삭제", notes = "스프린트를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/{sprintId}")
    public CommonResult deleteSprint(@PathVariable Long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        sprintService.deleteSprint(sprintId, email);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트 담당자 조회", notes = "스프린트 담당자를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{sprintId}/members")
    public CommonResult getAllUser(@PathVariable Long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getListResult(sprintService.getAllUser(sprintId, email));
    }
}
