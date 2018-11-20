package cn.shovi.edu.dao.mapper;

import cn.shovi.edu.common.bean.Tbuser;
import cn.shovi.edu.common.bean.TbuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbuserMapper {
    int countByExample(TbuserExample example);

    int deleteByExample(TbuserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Tbuser record);

    int insertSelective(Tbuser record);

    List<Tbuser> selectByExample(TbuserExample example);

    Tbuser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Tbuser record, @Param("example") TbuserExample example);

    int updateByExample(@Param("record") Tbuser record, @Param("example") TbuserExample example);

    int updateByPrimaryKeySelective(Tbuser record);

    int updateByPrimaryKey(Tbuser record);
}