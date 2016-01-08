package com.gaohan.web.controller;

import com.gaohan.biz.module1.service.PersonalInfoService;
import com.gaohan.biz.module1.vo.PersonalInfoVo;
import com.gaohan.enums.BizResultCodeEnum;
import com.gaohan.exception.BizException;
import com.gaohan.web.result.WebResult;
import com.google.common.base.Optional;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gaohan on 2015/12/29.
 */
@Controller
@RequestMapping("/personal")
public class PersonalInfoController {
    @Autowired
    private PersonalInfoService personalInfoService;

    @RequestMapping(value = "/info/get")
    @ResponseBody
    public WebResult<PersonalInfoVo> getPersonalInfo(@RequestParam("id") Long id){
        Optional<Long> optional = Optional.fromNullable(id);
        if(!optional.isPresent()){
            throw new BizException(BizResultCodeEnum.NULL_ARGUMENT,"id不能为空");
        }
        PersonalInfoVo personalInfoVo = personalInfoService.findById(optional.get());
        return WebResult.success(personalInfoVo);
    }

    @RequestMapping(value="/info/del")
    @ResponseBody
    public WebResult<Boolean> delPersonalInfo(@RequestParam("id") Long id){
        Optional<Long> optional = Optional.fromNullable(id);
        if(!optional.isPresent()){
            throw new BizException(BizResultCodeEnum.NULL_ARGUMENT,"id不能为空");
        }
        boolean opResult = personalInfoService.delById(optional.get());
        return WebResult.success(Boolean.valueOf(opResult));
    }

    @RequestMapping(value="/info/update")
    @ResponseBody
    public WebResult<Boolean> updatePersonalInfo(@RequestBody PersonalInfoVo personalInfoVo){
        Optional<Long> optional = Optional.fromNullable(personalInfoVo.getId());
        if(!optional.isPresent()){
            throw new BizException(BizResultCodeEnum.NULL_ARGUMENT,"id不能为空");
        }
        boolean opResult = personalInfoService.updateSelective(personalInfoVo);
        return WebResult.success(Boolean.valueOf(opResult));
    }

    @RequestMapping(value="/info/insert")
    @ResponseBody
    public WebResult<Boolean> insertPersonalInfo(@RequestBody PersonalInfoVo personalInfoVo){
        PersonalInfoVo opPersonalInfoVo = personalInfoService.insertSelective(personalInfoVo);
        return WebResult.success(opPersonalInfoVo == null ? Boolean.FALSE : Boolean.TRUE);
    }

}
