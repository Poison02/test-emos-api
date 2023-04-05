package com.example.demo.controller.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Zch
 **/
@Data
public class DeleteUserByIdForm {

    @NotEmpty(message = "ids不能为空")
    private Integer[] ids;

}
