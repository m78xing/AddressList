package com.example.mapper;

import com.example.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Han
 * @since 2023-04-20
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
