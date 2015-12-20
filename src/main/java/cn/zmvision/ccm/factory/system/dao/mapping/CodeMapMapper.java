package cn.zmvision.ccm.factory.system.dao.mapping;

import cn.zmvision.ccm.factory.system.dao.model.CodeMap;
import cn.zmvision.ccm.factory.system.dao.model.CodeMapExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeMapMapper {
    int countByExample(CodeMapExample example);

    int deleteByExample(CodeMapExample example);

    int deleteByPrimaryKey(String id);

    int insert(CodeMap record);

    int insertSelective(CodeMap record);

    PageList<CodeMap> selectByExample(CodeMapExample example, PageBounds pageBounds);

    List<CodeMap> selectByExample(CodeMapExample example);

    CodeMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CodeMap record, @Param("example") CodeMapExample example);

    int updateByExample(@Param("record") CodeMap record, @Param("example") CodeMapExample example);

    int updateByPrimaryKeySelective(CodeMap record);

    int updateByPrimaryKey(CodeMap record);
}