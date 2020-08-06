package com.winning.finance.apitool.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.vo.export.ExportApiParameter;
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
public class ExportApiParamInfoUtil {

    public List<ExportApiParameter> getApiParameter(List<ExportApiParameter> allList){


        List<ExportApiParameter> baseLists = Lists.newArrayList();
        // 总菜单，出一级菜单，一级菜单没有父id
        for (ExportApiParameter e : allList) {

            if (Objects.equals(e.getParentParameterId(),0L)) {
                baseLists.add(e);
            }
        }
        // 遍历一级菜单
        for (ExportApiParameter e : baseLists) {
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

    private List<ExportApiParameter> getChild(Long pid, List<ExportApiParameter> beanList) {
        List<ExportApiParameter> childs = Lists.newArrayList();
        for (ExportApiParameter e : beanList) {

            if (!Objects.equals(e.getParentParameterId(),0L)) {
                if (e.getParentParameterId().equals(pid)) {
                    // 子菜单的下级菜单
                    childs.add(e);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (ExportApiParameter e : childs) {
            // 继续添加子元素
            e.setParameterVOS(getChild(e.getApiParameterId(), beanList));
        }

        //停下来的条件，如果 没有子元素了，则停下来
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }

    /**
     * 重新组装数据
     */

    public List<ExportApiParameter> getData(List<ExportApiParameter> list,
                                            List<ExportApiParameter> result){

        return getExportApi(list, result);
    }

    private List<ExportApiParameter> getExportApi(List<ExportApiParameter> list,
                                                  List<ExportApiParameter> result) {
        for (ExportApiParameter ex : list) {

                ExportApiParameter exportApiParameter=new ExportApiParameter();
                BeanUtil.copyProperties(ex,exportApiParameter);
                exportApiParameter.setParameterVOS(null);
                result.add(exportApiParameter);

                if(CollectionUtil.isNotEmpty(ex.getParameterVOS())){
                    ExportApiParameter apiParameter=new ExportApiParameter();
                    apiParameter.setParameterName("start====="+ex.getParameterNo());
                    result.add(apiParameter);

                    getExportApi(ex.getParameterVOS(),result);
                    ExportApiParameter parameter=new ExportApiParameter();
                    parameter.setParameterName("end====="+ex.getParameterNo());
                    result.add(parameter);
                }else{
                    continue;
                }

        }
        return  result;
    }

}
