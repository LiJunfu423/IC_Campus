package com.ic.intelligentcampus.Controller;

import com.ic.intelligentcampus.Common.R;
import com.ic.intelligentcampus.Entity.Camera;
import com.ic.intelligentcampus.Service.CameraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/camera")
public class CameraController {
  @Autowired
  private CameraService cameraService;

  @PostMapping("/add_camera")
  public R<String> addCamera(HttpServletRequest request, @RequestBody Camera camera){

    return R.success(camera.getId()+"摄像头部署成功");

  }
  @DeleteMapping("/delete{/id}")
  public R<String> deleteCamera(HttpServletRequest request,@PathVariable Integer id){
    cameraService.removeById(id);
    return R.success("删除"+id+"成功");
  }

}
