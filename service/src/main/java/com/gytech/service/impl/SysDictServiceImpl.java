package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Configuration.EnvContext;
import com.gytech.Const;
import com.gytech.LocalEntity.BTree;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.Utils.TreeUtil;
import com.gytech.entity.admin.SysDict;
import com.gytech.entity.admin.SysOrganization;
import com.gytech.entity.admin.SysRole;
import com.gytech.entity.admin.SysUser;
import com.gytech.mapper.admin.SysDictMapper;
import com.gytech.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    private EnvContext envContext;
    @Autowired
    private SysDictMapper sysDictMapper;
    /**
     * containParent参数设置为true，则在按父节点查询的时候把父节点也加入结果集合
     * @param paramMap
     * @return
     */
    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysDict>(curPage,pageSize);
        Long parentId = GU.getMapKeyLong("parentId",paramMap);
        String dictCode =  GU.getMapKeyString("dictCode",paramMap);
        String dictName =  GU.getMapKeyString("dictName",paramMap);
        boolean isContainParent = GU.getMapKeyboolean("containParent",paramMap);
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dictCode)){
            wrapper.eq("dict_code",dictCode);
        }
        if (StringUtils.isNotBlank(dictName)){
            wrapper.eq("dict_name",dictName);
        }
        if (parentId>0){
            wrapper.eq("parent_id",parentId);
            if (isContainParent){
                wrapper.or();
                wrapper.eq("id",parentId);
            }
        }
        pageEntity.setRecords(sysDictMapper.selectList(wrapper));
        return pageEntity;
    }

    /**
     * 必须包含parentid，切挂接不到parent不许插入，根节点只允许一个
     * @param sysDict
     * @return
     */
    @Override
    public Res add(SysDict sysDict) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        Long parentId = sysDict.getParentId();
        if (parentId==null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        SysDict parent = getById(parentId);
        if (parent == null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        if (parentId.equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
        }
        if (dictCodeDump(sysDict.getDictCode(),null)){
            return res.reason("字典代码" +ResultInfo.INFO_ADD_ATTALREADY);
        }
        sysDict.setCreateUserId(currentUser.getId());
        if (save(sysDict)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
    }

    /**
     * 根节点不许删除，包含子节点不许删除
     * @param id
     * @return
     */
    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysDict org = getById(id);
        if (org == null){
            return res.data(false).reason("已不存在id为" + id + "的单位");
        }
        if (org.getId().equals(Const.ROOT_NODE_ID)||org.getParentId().equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_DEL_PARENT);
        }
        QueryWrapper<SysDict> entity = new QueryWrapper<SysDict>();
        entity.eq("parent_id",id);
        int children = baseMapper.selectCount(entity);
        if (children>0){
            return res.reason(ResultInfo.INFO_DEL_HASCHILDREN);
        }
        boolean flag = removeById(id);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    /**
     * 获取树
     * @return
     */
    @Override
    public Res dictTree() {
        Res res = new Res();
        List<SysDict> dicts = baseMapper.selectList(null);
        List<BTree<SysDict>> trees = new ArrayList<BTree<SysDict>>();
        for (SysDict dict:dicts){
            BTree<SysDict> tree = new BTree<SysDict>();
            tree.setId(String.valueOf(dict.getId()));
            tree.setParentId(String.valueOf(dict.getParentId()));
            tree.setTitle(dict.getDictName());
            trees.add(tree);
        }
        BTree<SysDict> orgTree = TreeUtil.build(trees);
        return res.success().data(orgTree);
    }

    @Override
    public Res selectItemsByDictCode(String dictCode) {
        Res res = new Res();
        QueryWrapper<SysDict> ew = new QueryWrapper<>();
        ew.eq("dict_code",dictCode);
        List<SysDict> dicts = baseMapper.selectList(ew);
        if (dicts.size()!=1){
            return res.reason("数据异常：字典查询过程中" + dictCode +"记录为" + dicts.size() + "个");
        }
        SysDict dict = dicts.get(0);
        QueryWrapper<SysDict> ew2 = new QueryWrapper<>();
        ew2.eq("parent_id",dict.getId());
        return res.success().data(baseMapper.selectList(ew2));
    }

    @Override
    public Res selectDictByCode(String dictCode) {
        Res res = new Res();
        QueryWrapper<SysDict> ew = new QueryWrapper<>();
        ew.eq("dict_code",dictCode);
        List<SysDict> dicts = baseMapper.selectList(ew);
        if (dicts.size()!=1){
            return res.reason("数据异常：字典查询过程中" + dictCode +"记录为" + dicts.size() + "个");
        }
        return res.success().data(dicts.get(0));
    }

    @Override
    public Boolean dictCodeDump(String dictCode, Long dictId) {
        QueryWrapper<SysDict> entity = new QueryWrapper<SysDict>();
        if (dictId == null){
            entity.eq("dict_code",dictCode);
            List<SysDict> dicts = baseMapper.selectList(entity);
            if (dicts.size()>0){
                return true;
            }
        }
        entity.eq("dict_code",dictCode);
        entity.ne("id",dictId);
        List<SysDict> dicts = baseMapper.selectList(entity);
        if (dicts.size()>0){
            return true;
        }
        return false;
    }


}
