package org.everyagile.everyagile.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.domain.response.CommonResult;
import org.everyagile.everyagile.dto.BacklogRequestDto;
import org.everyagile.everyagile.dto.responseDto.BacklogResponseDto;
import org.everyagile.everyagile.service.BacklogService;
import org.everyagile.everyagile.service.ResponseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"5. 백로그 컨트롤러"})
@RequestMapping(value = "/backlogs")
public class BacklogController {

    private final ResponseService responseService;
    private final BacklogService backlogService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "백로그 생성", notes = "백로그를 생성한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("")
    public CommonResult createBacklog(@RequestBody @ApiParam(value = "생성할 백로그 정보", required = true) BacklogRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(backlogService.createBacklog(email, requestDto));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @ApiOperation(value = "모든 백로그 조회", notes = "모든 백로그를 조회한다.")
    @GetMapping("")
    public CommonResult getALlBacklogs() {
        return responseService.getListResult(backlogService.getAllBacklogs());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "백로그 정보 조회", notes = "백로그 정보를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{backlogId}")
    public CommonResult getBacklog(@PathVariable Long backlogId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        BacklogResponseDto requestDto = backlogService.getBacklog(email, backlogId);
        return responseService.getSingleResult(requestDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "스프린트별 백로그 목록 조회", notes = "스프린트별 백로그 목록 정보를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/sprint/{sprintId}")
    public CommonResult getBacklogBySprint(@PathVariable Long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getListResult(backlogService.getAllBacklog(sprintId, email));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "백로그 완료 작성", notes = "백로그 완료 여부를 작성한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @PutMapping("/{backlogId}")
    public CommonResult setBacklogStatus(@PathVariable Long backlogId, @RequestParam boolean status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(backlogService.setBacklogStatus(backlogId, status, email));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "백로그 삭제", notes = "백로그를 삭제한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @DeleteMapping("/{backlogId}")
    public CommonResult deleteBacklog(@PathVariable Long backlogId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        backlogService.deleteBacklog(backlogId, email);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "백로그 담당자 조회", notes = "백로그 담당자를 조회한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 400, message = "BAD REQUEST !!"),
            @ApiResponse(code = 404, message = "NOT FOUND !!"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{backlogId}/members")
    public CommonResult getAllUser(@PathVariable Long backlogId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getListResult(backlogService.getAllUser(backlogId, email));
    }
}
