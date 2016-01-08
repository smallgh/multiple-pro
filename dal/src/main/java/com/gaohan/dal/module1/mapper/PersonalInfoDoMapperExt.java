package com.gaohan.dal.module1.mapper;

import com.gaohan.biz.module1.vo.PersonalInfoVo;

/**
 * Created by gaohan on 2016/1/7.
 */
public interface PersonalInfoDoMapperExt extends PersonalInfoDoMapper {
    PersonalInfoVo selectVoByPrimaryKey(Long id);
}
