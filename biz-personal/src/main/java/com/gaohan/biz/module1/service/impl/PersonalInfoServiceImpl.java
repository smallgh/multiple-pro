package com.gaohan.biz.module1.service.impl;

import com.gaohan.biz.module1.service.PersonalInfoService;
import com.gaohan.biz.module1.vo.PersonalInfoVo;
import com.gaohan.common.service.cache.CacheFetchHelper;
import com.gaohan.common.service.cache.enums.CacheNameEnum;
import com.gaohan.dal.module1.dataobject.PersonalInfoDo;
import com.gaohan.dal.module1.mapper.PersonalInfoDoMapper;
import com.gaohan.dal.module1.mapper.PersonalInfoDoMapperExt;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Created by gaohan on 2015/12/29.
 */
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private Logger logger = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);

    @Autowired
    private PersonalInfoDoMapperExt personalInfoDoMapperExt;

    @Cacheable(key = "'" + CacheFetchHelper.PERSONAL_INFO_PREFIX + "'.concat(#id)", value = CacheNameEnum.REDIS)
    @Override
    public PersonalInfoVo findById(Long id) {
        logger.info("start to query personalInfo from db");

        Optional<PersonalInfoVo> optional = Optional.fromNullable(personalInfoDoMapperExt.selectVoByPrimaryKey(id));
        if (optional.isPresent()) {
            logger.info(MessageFormat.format("query personalInfo successfully,result:{0}", optional.get()));
        }
        return optional.get();
    }

    @CacheEvict(key = "'" + CacheFetchHelper.PERSONAL_INFO_PREFIX + "'.concat(#id)", value = CacheNameEnum.REDIS)
    @Override
    public boolean delById(Long id) {
        logger.info("start to delete personalInfo in db");
        int effectCount = personalInfoDoMapperExt.deleteByPrimaryKey(id);
        logger.info(MessageFormat.format("delete personalInfo,id:{0},effectCount:{1}", id, effectCount));
        return effectCount > 0;
    }

    @CacheEvict(key = "'" + CacheFetchHelper.PERSONAL_INFO_PREFIX
                      + "'.concat(#personalInfoVo.id)", value = CacheNameEnum.REDIS)
    @Override
    public boolean updateSelective(PersonalInfoVo personalInfoVo) {
        logger.info("start to update personalInfo to db");
        int effectCount = personalInfoDoMapperExt.updateByPrimaryKeySelective(bulidPersonInfoDo(personalInfoVo));
        logger.info(MessageFormat.format("update personalInfo,personalInfoVo:{0},effectCount:{1}", personalInfoVo,
            effectCount));
        return effectCount > 0;
    }

    @Override
    public PersonalInfoVo insertSelective(PersonalInfoVo personalInfoVo) {
        logger.info("start to insert personalInfo to db");
        int effectCount = personalInfoDoMapperExt.insertSelective(bulidPersonInfoDo(personalInfoVo));
        logger.info(MessageFormat.format("insert personalInfo,personalInfoVo:{0},effectCount:{1}", personalInfoVo,
            effectCount));
        return effectCount > 0 ? personalInfoVo : null;
    }

    private PersonalInfoDo bulidPersonInfoDo(PersonalInfoVo personalInfoVo) {
        PersonalInfoDo personalInfoDo = new PersonalInfoDo();
        personalInfoDo.setId(personalInfoVo.getId());
        personalInfoDo.setName(personalInfoVo.getName());
        personalInfoDo.setAge(personalInfoVo.getAge());
        personalInfoDo.setSex(personalInfoVo.getSex());
        personalInfoDo.setHobbies(personalInfoVo.getHobbies());
        return personalInfoDo;
    }
}
