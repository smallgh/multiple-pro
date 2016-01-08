package com.gaohan.biz.module1.service;

import com.gaohan.biz.module1.vo.PersonalInfoVo;

/**
 * Created by gaohan on 2015/12/29.
 */
public interface PersonalInfoService {

    PersonalInfoVo findById(Long id);

    boolean delById(Long id);

    boolean updateSelective(PersonalInfoVo personalInfoVo);

    PersonalInfoVo insertSelective(PersonalInfoVo personalInfoVo);
}
