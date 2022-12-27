package com.ic.intelligentcampus.Controller;

import com.ic.intelligentcampus.Common.R;
import com.ic.intelligentcampus.Entity.Event;
import com.ic.intelligentcampus.Service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/event")
public class EventController {
  @Autowired
  private EventService eventService;

//  public R<Event> add_event(HttpServletRequest request, @RequestBody Event event){
//
//  }
}
