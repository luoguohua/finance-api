package E:\genrater.domain.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @Version 1.0
* @Author: luoguohua
* @Date: 2024年7月16日 10:27:47
* Content:
* 用户信息
*/

@Entity
@Table(name = "t_sys_user")
@Data
@Schema(description = "用户信息")
public class SysUser {

    /**
    * 主键
    */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "snowIdGenerator")
        @GenericGenerator(name = "snowIdGenerator",type = SnowIdGenerator.class)
    private Long id;



    /**
    * 用户名
    */
        @Schema(description = "用户名",example = "")
    private String username;



    /**
    * 密码
    */
        @Schema(description = "密码",example = "")
    private String passwd;


}
