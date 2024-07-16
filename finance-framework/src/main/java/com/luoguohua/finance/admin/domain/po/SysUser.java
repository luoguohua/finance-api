package com.luoguohua.finance.admin.domain.po;


import com.luoguohua.finance.framework.generator.SnowIdGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 16:49
 * Content:
 */
@Entity
@Table(name="t_sys_user")
@Data
@Schema(description = "系统用户")
public class SysUser implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "snowIdGenerator")
    @GenericGenerator(name = "snowIdGenerator",type = SnowIdGenerator.class)
    private Long id;

    @Schema(description = "用户名",example = "admin")
    private String username;

    @Schema(description = "密码",example = "123456")
    private String passwd;
}
