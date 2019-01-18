package cn.zoua.allinone.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@ApiModel
public class Employee implements Serializable {

    @ApiModelProperty(value="ID",dataType="Integer",name="ID",example="1")
    private Integer id;

    @ApiModelProperty(value="姓名",dataType="String",name="lastName",example="张三")
    private String lastName;

    @ApiModelProperty(value="姓别",dataType="Integer",name="gender",example="1")
    private Integer gender;

    @ApiModelProperty(value="邮箱",dataType="String",name="email",example="zhangsan@email.com")
    private String email;

    @ApiModelProperty(value="did",dataType="Integer",name="did",example="1")
    private Integer did;

}
