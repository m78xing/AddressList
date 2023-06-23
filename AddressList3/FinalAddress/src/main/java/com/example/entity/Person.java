package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Han
 * @since 2023-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Person {

    private static final long serialVersionUID = 1L;

    private String name;

    private String gender;

    private Integer age;

    private String tel;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
