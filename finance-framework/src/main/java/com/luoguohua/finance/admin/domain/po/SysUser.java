package com.luoguohua.finance.admin.domain.po;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/5/11 16:49
 * Content:
 */
@Entity
@Data
public class SysUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String passwd;
}
