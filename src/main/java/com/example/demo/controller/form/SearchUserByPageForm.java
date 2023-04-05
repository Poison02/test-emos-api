package com.example.demo.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Zch
 * 这里查询用户分页数据，
 * 需要的参数是页数、每页数据条数 这两条不能为空
 * name, sex, role, dept, status 是可选的
 **/
@Data
public class SearchUserByPageForm {
    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 10, max = 50, message = "length必须在10-5之间")
    private Integer length;

    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "name内容不正确")
    private String name;

    @Pattern(regexp = "^男$|^女$", message = "sex内容不正确")
    private String sex;

    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,10}$", message = "role内容不正确")
    private String role;

    @Min(value = 1, message = "dept不能小于1")
    private Integer deptId;

    @Min(value = 1, message = "status不能小于1")
    private Integer status;
}
