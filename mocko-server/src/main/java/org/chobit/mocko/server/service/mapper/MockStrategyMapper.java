package org.chobit.mocko.server.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author robin
 */
@Mapper
public interface MockStrategyMapper {

    @Insert({
            "insert into m_mock_strategy (expression, response, operator)",
            "values",
            "(#{expression}, #{response}, #{operator})"
    })
    @Options(useGeneratedKeys = true)
    void add(@Param("expression") String expression, @Param("response") String response, @Param("operator") int operator);

}
