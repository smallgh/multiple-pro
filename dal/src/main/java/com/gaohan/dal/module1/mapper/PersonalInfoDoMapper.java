package com.gaohan.dal.module1.mapper;

import com.gaohan.dal.module1.dataobject.PersonalInfoDo;
import com.gaohan.dal.module1.dataobject.PersonalInfoDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonalInfoDoMapper {
    int countByExample(PersonalInfoDoExample example);

    int deleteByExample(PersonalInfoDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonalInfoDo record);

    int insertSelective(PersonalInfoDo record);

    List<PersonalInfoDo> selectByExample(PersonalInfoDoExample example);

    PersonalInfoDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PersonalInfoDo record, @Param("example") PersonalInfoDoExample example);

    int updateByExample(@Param("record") PersonalInfoDo record, @Param("example") PersonalInfoDoExample example);

    int updateByPrimaryKeySelective(PersonalInfoDo record);

    int updateByPrimaryKey(PersonalInfoDo record);
}