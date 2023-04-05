package com.example.demo.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Zch
 **/
@Data
public class UpdatePasswordForm {

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6.20}$", message = "password格式不正确")
    private String password;
}
