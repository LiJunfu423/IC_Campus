package com.ic.Filter;

import com.ic.intelligentcampus.Common.BaseContext;
import com.ic.intelligentcampus.Common.R;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

  public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();



  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request=(HttpServletRequest) servletRequest;
    HttpServletResponse response=(HttpServletResponse) servletResponse;
    String requestURI = request.getRequestURI();
    String[] urls=new String[]{//白名单
      "/user/login",  //本来就是登陆请求
      "/user/logout"  //退出请求
    };


    boolean checkResult=check(requestURI,urls);
//如果不用处理直接go
    if(checkResult){
//            log.info("本次请求{}不需要处理",requestURI);
      filterChain.doFilter(request,response);
      return;
    }
    if(request.getSession().getAttribute("user")!=null){
//            log.info("用户已登陆，用户id为：{}",request.getSession().getAttribute("user"));
      Long userId = (Long)request.getSession().getAttribute("user");
      BaseContext.setCurrentId(userId);


      filterChain.doFilter(request,response);
      return;
    }
//        log.info("用户未登陆");
    //如果未登录则返回登陆结果
    response.getWriter().write(JSON.toJSONString(R.error("未登录")));
    return;
  }

    public boolean check(String requestURI,String[] urls){
      for (String url : urls) {
        boolean match = PATH_MATCHER.match(url, requestURI);
        if(match){
          return true;
        }
      }
      return false;

    }
  }
