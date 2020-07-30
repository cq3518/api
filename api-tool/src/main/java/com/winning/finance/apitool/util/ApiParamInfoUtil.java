package com.winning.finance.apitool.util;

import com.google.common.collect.Lists;
import com.winning.finance.apitool.vo.apiinfo.search.ApiParameter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/30 14:39
 */
@Component
public class ApiParamInfoUtil {

    public List<ApiParameter> getApiParameter(List<ApiParameter> allList){
        List<ApiParameter> baseLists = Lists.newArrayList();
        // 总菜单，出一级菜单，一级菜单没有父id
        for (ApiParameter e : allList) {

            if (Objects.equals(e.getParentParameterId(),0L)) {
                baseLists.add(e);
            }
        }
        // 遍历一级菜单
        for (ApiParameter e : baseLists) {
            // 将子元素 set进一级菜单里面   getChild()方法在下方
            e.setParameterVOS(getChild(e.getApiParameterId(), allList));
        }

        return baseLists;
    }

    /**
     * 获取子节点
     *
     * @param pid
     * @param beanList
     * @return
     */

    private List<ApiParameter> getChild(Long pid, List<ApiParameter> beanList) {
        List<ApiParameter> childs = Lists.newArrayList();
        for (ApiParameter e : beanList) {

            if (!Objects.equals(e.getParentParameterId(),0L)) {
                if (e.getParentParameterId().equals(pid)) {
                    // 子菜单的下级菜单
                    childs.add(e);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (ApiParameter e : childs) {
            // 继续添加子元素
            e.setParameterVOS(getChild(e.getApiParameterId(), beanList));
        }

        //停下来的条件，如果 没有子元素了，则停下来
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }
}
