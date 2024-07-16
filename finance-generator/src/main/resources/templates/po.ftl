package ${packageName}.domain.po;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @Version 1.0
* @Author: ${author}
* @Date: <#assign currentTime = .now?string("yyyy/MM/dd HH:mm")> ${currentTime}
* Content:
* ${tableRemarks}
*/

@Entity
@Table(name = "${tableName}")
@Data
@Schema(description = "${tableRemarks}")
public class ${className} {
<#list columns as column>

    /**
    * ${column.columnDesc}
    */
<#if column.columnName != 'id'>
    @Schema(description = "${column.columnDesc}",example = "")
<#else>
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "snowIdGenerator")
    @GenericGenerator(name = "snowIdGenerator",type = SnowIdGenerator.class)
</#if>
    private ${column.columnType} ${column.columnName};


</#list>
}
