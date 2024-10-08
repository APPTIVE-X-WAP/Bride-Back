package com.pinu.familing.domain.family.controller;

import com.pinu.familing.domain.family.dto.FamilyCode;
import com.pinu.familing.domain.family.dto.FamilyDto;
import com.pinu.familing.domain.family.dto.FamilyName;
import com.pinu.familing.domain.family.service.FamilyService;
import com.pinu.familing.domain.user.service.UserService;
import com.pinu.familing.global.oauth.dto.CustomOAuth2User;
import com.pinu.familing.global.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FamilyController {

    private final FamilyService familyService;
    private final UserService userService;

    /**
     * <가족 생성 로직>
     * 유저 정보 기반 가족 생성
     * 유저에 가족 정보 넣기
     * 가족 코드
     */
    @PostMapping("/family")
    public ApiUtils.ApiResult<?> createFamily(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, @RequestBody FamilyName familyName) {
        Map<String, Object> responseBody = new HashMap<>();
        //가족 코드 생성 로직
        FamilyDto familyDto = familyService.registerNewFamily(customOAuth2User.getName(), familyName.name());
        //유저에 가족을 넣기
        userService.addFamilyToUser(customOAuth2User, familyDto.code());
        responseBody.put("code", familyDto.code());
        return ApiUtils.success(responseBody);
    }

    /**
     * <가족 등록 로직>
     * 유저가 가족을 가지고 있는지 검사
     * 유효한 가족 코드 확인
     * 유저 정보 넣기
     */
    @PostMapping("/family/user")
    public ApiUtils.ApiResult<String> registerFamily(@AuthenticationPrincipal CustomOAuth2User customOAuth2User, @RequestBody FamilyCode familyCode) {
        //유저에 가족을 넣기
        userService.addFamilyToUser(customOAuth2User, familyCode.code());
        return ApiUtils.success("Successful addition of family");
    }

    @GetMapping("/family")
    public ApiUtils.ApiResult<?> getMyFamily(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
        return ApiUtils.success(familyService.getMyFamily(customOAuth2User.getName()));
    }

}
