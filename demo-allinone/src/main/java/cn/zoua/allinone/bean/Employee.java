package cn.zoua.allinone.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Employee implements Serializable {

    private Integer id;

    private String lastName;

    private Integer gender;

    private String email;

    private Integer did;

}
