package com.winning.finance.apitool.service.codegroup;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.winning.finance.apitool.base.BusinessException;
import com.winning.finance.apitool.contant.Constant;
import com.winning.finance.apitool.entity.CodeRepositoryGroupPO;
import com.winning.finance.apitool.repository.CodeRepositoryGroupRepository;
import com.winning.finance.apitool.vo.coderepositorygroup.add.AddGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.add.AddGroupOutVO;
import com.winning.finance.apitool.vo.coderepositorygroup.delete.DeleteGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.edit.EditGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.edit.EditGroupOutVO;
import com.winning.finance.apitool.vo.coderepositorygroup.search.GroupInfo;
import com.winning.finance.apitool.vo.coderepositorygroup.search.GroupInfoUtil;
import com.winning.finance.apitool.vo.coderepositorygroup.search.SearchGroupInputVO;
import com.winning.finance.apitool.vo.coderepositorygroup.search.SearchGroupOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.winning.finance.apitool.base.IdWorker.getSnowflakeId;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/7/28 13:49
 */
@Service
public class CodeGroupServiceImpl implements CodeGroupService {

    @Autowired
    private CodeRepositoryGroupRepository codeRepositoryGroupRepository;

    @Autowired
    private GroupInfoUtil groupInfoUtil;

    @Override
    public AddGroupOutVO add(AddGroupInputVO inputVO) {
        checkByParentGroupId(inputVO);
        //生成主键id
        long id = getSnowflakeId();
        CodeRepositoryGroupPO po =new  CodeRepositoryGroupPO();
        BeanUtil.copyProperties(inputVO, po);
        po.setGroupId(id);
        po.setIsDel(Constant.IS_DEL_YES);
        if(Objects.isNull(inputVO.getParentGroupId())){
            po.setParentGroupId(0L);
        }
        //插入数据
        codeRepositoryGroupRepository.save(po);
        AddGroupOutVO outVO=new AddGroupOutVO();
        outVO.setGroupId(id);
        return outVO;
    }

    private void checkByParentGroupId(AddGroupInputVO inputVO){

        Long parentGroupId= MoreObjects.firstNonNull(inputVO.getParentGroupId(),0L);

        //根据【分组名称】查询【父级分组标识】下是否已存在同名分组，如果存在，则不予新增
        int count=codeRepositoryGroupRepository.getByParentIdAndName(inputVO.getCodeRepositoryId(),inputVO.getGroupName(),parentGroupId,Constant.IS_DEL_YES);
        if(count>0){
            throw  new BusinessException("已存在【分组名称】:【"+inputVO.getGroupName()+"】在【父级分组标识】："+parentGroupId);
        }

    }

    private void checkByParentGroupId(EditGroupInputVO inputVO){

        Long parentGroupId= MoreObjects.firstNonNull(inputVO.getParentGroupId(),0L);
        Optional<CodeRepositoryGroupPO> optional=codeRepositoryGroupRepository.findById(inputVO.getGroupId());
        if(!optional.isPresent()){
            throw  new BusinessException("无效的分组标识【"+inputVO.getGroupId()+"】");
        }
        //根据【分组名称】查询【父级分组标识】下是否已存在同名分组，如果存在，则不予新增
        // 通过分组标识查询代码仓库标识
        int count=codeRepositoryGroupRepository.getByParentIdAndName(optional.get().getCodeRepositoryId(),inputVO.getGroupName(),parentGroupId,Constant.IS_DEL_YES);
        if(count>0){
            throw  new BusinessException("【分组名称】:【"+inputVO.getGroupName()+"】已存在【父级分组标识】："+parentGroupId+"中");
        }

    }

    @Override
    public EditGroupOutVO edit(EditGroupInputVO inputVO) {

        checkByParentGroupId(inputVO);
        //修改数据
        codeRepositoryGroupRepository.updatePO(inputVO.getGroupId(),inputVO.getGroupName(),inputVO.getParentGroupId());
        EditGroupOutVO outVO = new EditGroupOutVO();
        outVO.setGroupId(inputVO.getGroupId());
        return outVO;
    }

    @Override
    public void delete(DeleteGroupInputVO inputVO) {

        // todo 如果分组下包含API接口信息，则不允许删除
        codeRepositoryGroupRepository.deleteByGroupId(inputVO.getGroupId(),Constant.IS_DEL_NO);
    }

    @Override
    public SearchGroupOutVO search(SearchGroupInputVO inputVO) {
        SearchGroupOutVO outVO=new SearchGroupOutVO();
        // 通过代码仓库标识 查询分组信息
        List<CodeRepositoryGroupPO> list=codeRepositoryGroupRepository.listByCodeRepositoryId(inputVO.getCodeRepositoryId(),Constant.IS_DEL_YES);
        List<GroupInfo> data = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(list)){
         List<GroupInfo> groupInfos = Lists.newArrayList();
            list.forEach(e->{
                GroupInfo info=new GroupInfo();

                info.setGroupId(e.getGroupId());
                info.setGroupName(e.getGroupName());
                info.setParentGroupId(e.getParentGroupId());
                groupInfos.add(info);
            });
            data=  groupInfoUtil.getGroupInfo(groupInfos);
            outVO.setData(data);
        }

        return outVO;
    }
}
