package cn.zoua.allinone.bean;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private String bookName;

    private String author;


}
