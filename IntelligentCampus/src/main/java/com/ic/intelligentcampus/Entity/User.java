package com.ic.intelligentcampus.Entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
  private Long id;

  private String name;

  private String password;

  private Integer identity;

  private Integer status;

  private String telephone;

  private String department;

}
