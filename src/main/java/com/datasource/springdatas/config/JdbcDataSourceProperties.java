package com.datasource.springdatas.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * @author xuechaofu
 * @date 2019/5/31 14:05
 */
@Data
public class JdbcDataSourceProperties extends DataSourceProperties {
    public static final String READ_ACCESS_TYPE = "read";

    public static final String WRITE_ACCESS_TYPE = "write";

    //确保唯一
    private String datasourceId;

    //read 为读库，write 为写库，不配置为其他用途，比如生产环境配置相应的压力测试库等，
    // 该类型的数据源不参与正常的业务处理，除非程序主动选择
    private String accessType;
}
