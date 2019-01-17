package cn.zoua.allinone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Department implements Serializable {

    private Integer id;

    private String departmentName;

}
