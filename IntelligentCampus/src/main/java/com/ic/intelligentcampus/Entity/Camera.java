package com.ic.intelligentcampus.Entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ic.intelligentcampus.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class Camera implements Serializable {
  private Long id;

  private String name;

  private String location;

  private Long user_id;

  private Boolean is_safe;

  private void findDanger(){
    setIs_safe(false);
    sendMessage(this.getName()+"find danger in"+this.getLocation());
  }

  private void outOfDanger(){
    setIs_safe(true);
    sendMessage("The danger in"+this.getLocation()+"is solved");
  }

  private void sendMessage(String s){
  }

}
