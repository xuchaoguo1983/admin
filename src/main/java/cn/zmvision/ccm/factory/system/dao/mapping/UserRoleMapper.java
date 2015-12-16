package cn.zmvision.ccm.factory.system.dao.mapping;

import cn.zmvision.ccm.factory.system.dao.model.UserRoleExample;
import cn.zmvision.ccm.factory.system.dao.model.UserRoleKey;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    PageList<UserRoleKey> selectByExample(UserRoleExample example, PageBounds pageBounds);

    List<UserRoleKey> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRoleKey record, @Param("example") UserRoleExample example);
}