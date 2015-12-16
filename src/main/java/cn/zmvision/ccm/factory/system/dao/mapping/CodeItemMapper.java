package cn.zmvision.ccm.factory.system.dao.mapping;

import cn.zmvision.ccm.factory.system.dao.model.CodeItem;
import cn.zmvision.ccm.factory.system.dao.model.CodeItemExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeItemMapper {
    int countByExample(CodeItemExample example);

    int deleteByExample(CodeItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CodeItem record);

    int insertSelective(CodeItem record);

    PageList<CodeItem> selectByExample(CodeItemExample example, PageBounds pageBounds);

    List<CodeItem> selectByExample(CodeItemExample example);

    CodeItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CodeItem record, @Param("example") CodeItemExample example);

    int updateByExample(@Param("record") CodeItem record, @Param("example") CodeItemExample example);

    int updateByPrimaryKeySelective(CodeItem record);

    int updateByPrimaryKey(CodeItem record);
}