package org.everyagile.everyagile.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.domain.response.SingleResult;
import org.everyagile.everyagile.dto.ProjectRequestDto;
import org.everyagile.everyagile.dto.SprintRequestDto;
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
    @ApiOperation(value = "스프린트 생성", notes = "스프린트를 생성한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("")
    public SingleResult<Sprint> createSprint(@RequestBody @ApiParam(value = "생성할 스프린트 정보", required = true)SprintRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Sprint sprint = sprintService.createSprint(email, requestDto);
        return responseService.getSingleResult(sprint);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "프로젝트별 모든 스프린트 조회", notes = "프로젝트에 해당하는 모든 스프린트를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{projectId}")
    public CommonResult getAllSprint(@PathVariable Long projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<Sprint> sprints = sprintService.getAllSprint(projectId, email);
        return responseService.getListResult(sprints);
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
    public SingleResult<Sprint> getSprint(@PathVariable Long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Sprint sprint = sprintService.getSprint(email, sprintId);
        return responseService.getSingleResult(sprint);
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
    public SingleResult<Sprint> setSprintStatus(@PathVariable Long sprintId, @RequestParam boolean status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Sprint sprint = sprintService.setSprintStatus(sprintId, status, email);
        return responseService.getSingleResult(sprint);
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
}
