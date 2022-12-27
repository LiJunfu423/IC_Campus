package com.ic.intelligentcampus.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Event implements Serializable {

  private Long id;

  private Integer sort;

  private String time;

  private String description;

  private Long camera_id;
}
