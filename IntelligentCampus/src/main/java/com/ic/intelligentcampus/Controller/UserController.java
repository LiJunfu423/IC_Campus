package com.ic.intelligentcampus.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ic.intelligentcampus.Common.R;
import com.ic.intelligentcampus.Entity.User;
import com.ic.intelligentcampus.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")//登陆功能  POST请求(包含请求体）：localhost：8080/user/login
  public R<User> login(HttpServletRequest request, @RequestBody User user){
      String password= user.getPassword();
      String MD5_password= DigestUtils.md5DigestAsHex(password.getBytes());

      LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
      queryWrapper.eq(User::getId,user.getId());
      User u=userService.getOne(queryWrapper);

    if(u==null){
        return R.error("该用户不存在");
      }
      if(!u.getPassword().equals(MD5_password)){
        return R.error("密码错误");

      }
      if(u.getStatus()==0){
        return R.error("该用户被禁用，请联系管理员");

      }
      log.info("账号没问题");
      request.getSession().setAttribute("user",u.getId());
      return R.success(u);
  }

  @GetMapping("/logout{id}")//退出功能  GET请求：localhost：8080/user/login+{id}
  public R<String> logout(HttpServletRequest request,@PathVariable Integer id){
      request.getSession().removeAttribute(Integer.toString(id));
      return R.success("退出成功");
  }


  @PostMapping("/registration")//注册功能   POST请求(包含请求体）：localhost：8080/user/registration
  public R<String> register(@RequestBody User user){
      String password=DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
      user.setPassword(password);
      user.setStatus(0);
      user.setIdentity(1);
      LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
      queryWrapper.eq(User::getName,user.getName());
      User u=userService.getOne(queryWrapper);
    if(u==null) {
      userService.save(user);
      return R.success(user.getName()+"注册成功");
    }else{
      return R.error("该用户已存在");
    }
  }
  @PutMapping("/change_password")//改密码功能  POST请求(包含请求体）：localhost：8080/user/change_password
  public R<String> changePassword(@RequestBody User user){
    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    userService.updateById(user);
    return R.success("密码修改完成");
  }


}
