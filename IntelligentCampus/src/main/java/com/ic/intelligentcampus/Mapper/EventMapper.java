package com.ic.intelligentcampus.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ic.intelligentcampus.Entity.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper extends BaseMapper<Event> {
}
