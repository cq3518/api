package com.winning.finance.apitool.vo.coderepositorygroup.search;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>api-tool</p>
 * 处理树形结构
 * @author cq
 * @Description
 * @date 2020/7/28 15:53
 */
@Component
public class GroupInfoUtil {

    public  List<GroupInfo> getGroupInfo(List<GroupInfo> allList){
        List<GroupInfo> baseLists = Lists.newArrayList();
        // 总菜单，出一级菜单，一级菜单没有父id
        for (GroupInfo e : allList) {

            if (Objects.equals(e.getParentGroupId(),0L)) {
                baseLists.add(e);
            }
        }
        // 遍历一级菜单
        for (GroupInfo e : baseLists) {
            // 将子元素 set进一级菜单里面   getChild()方法在下方
            e.setChildGroupList(getChild(e.getGroupId(), allList));
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

    private List<GroupInfo> getChild(Long pid, List<GroupInfo> beanList) {
        List<GroupInfo> childs = Lists.newArrayList();
        for (GroupInfo e : beanList) {

            if (!Objects.equals(e.getParentGroupId(),0L)) {
                if (e.getParentGroupId().equals(pid)) {
                    // 子菜单的下级菜单
                    childs.add(e);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (GroupInfo e : childs) {
            // 继续添加子元素
            e.setChildGroupList(getChild(e.getGroupId(), beanList));
        }

        //停下来的条件，如果 没有子元素了，则停下来
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }
}
