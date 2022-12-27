package com.ic.intelligentcampus.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ic.intelligentcampus.Entity.Camera;
import com.ic.intelligentcampus.Mapper.CameraMapper;
import com.ic.intelligentcampus.Service.CameraService;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl extends ServiceImpl<CameraMapper,Camera> implements CameraService{
}
