package com.ic.intelligentcampus.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ic.intelligentcampus.Entity.Event;
import com.ic.intelligentcampus.Mapper.EventMapper;
import com.ic.intelligentcampus.Service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
}
