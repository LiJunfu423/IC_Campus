package com.ic.intelligentcampus.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ic.intelligentcampus.Common.R;
import com.ic.intelligentcampus.Entity.User;
import com.ic.intelligentcampus.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private UserService userService;

  @PostMapping("/add_user")//增
  public R<String> save(@RequestBody User user){
    user.setPassword(md5DigestAsHex("123456".getBytes()));
//    user.setStatus(0);
    userService.save(user);
    return R.success("新增用户成功");
  }

  @PutMapping//改
  public R<String> update(@RequestBody User user){
    userService.updateById(user);
    return R.success("修改成功");
  }

  @DeleteMapping("/{id}")//删
  public R<String> delete(@PathVariable int id){
    userService.removeById(id);
    return R.success("已删除该用户");
  }

  @GetMapping("/{id}")//查
  public R<User> getById(@PathVariable String id){
    log.info("查询用户");
    User user = userService.getById(id);
    if(user!=null){
      return R.success(user);
    }
    return R.error("未查询到该用户");
  }


  @PostMapping("/login_check")
  public R<User> adminLogin(HttpServletRequest request, @RequestBody User user){
    String password = user.getPassword();
    String pwd= DigestUtils.md5DigestAsHex(password.getBytes());
    System.out.println("密码是"+password);
    LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getId,user.getId());
    User u=userService.getOne(queryWrapper);
    if(u==null||u.getIdentity()!=0||u.getStatus()!=0){
      return R.error("该管理员不存在");
    }
    if(!u.getPassword().equals(pwd)){
      return R.error("账号密码错误，请重新输入");
    }
    log.info("{}管理员成功登陆",u.getName());
    request.getSession().setAttribute("user",u.getId());
    return R.success(u);
  }

  @PostMapping("/logout")
  public R<String> adminLogout(HttpServletRequest request){
    request.getSession().removeAttribute("user");
    return R.success("退出成功");
  }
  @PostMapping("/get_user_list")
  public R<Page> page(@RequestBody Map map){
    System.out.println(map);
    int page = (int)map.get("page");
    int limit =(int)map.get("limit");
    int total=userService.count();
    Page pageInfo=new Page<>(page,limit,total);
    userService.page(pageInfo);
    return R.success(pageInfo);
  }
}
