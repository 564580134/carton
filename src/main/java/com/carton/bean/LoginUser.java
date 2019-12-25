package com.carton.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Liu Runyong
 * @date 2019/12/25
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUser implements Serializable {
    private String username;
    private String password;
    private Integer rememberMe;
}
