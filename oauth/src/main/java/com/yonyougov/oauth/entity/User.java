package com.yonyougov.oauth.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/4/26
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -5013089537208360499L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String level;
    private int age;
}
