package com.meinil.system.service.impl;

import com.meinil.common.core.utlis.CollectionUtil;
import com.meinil.common.core.utlis.StringUtil;
import com.meinil.common.web.utils.WebUtil;
import com.meinil.system.convert.SysMenuConvert;
import com.meinil.system.domain.entity.SysMenu;
import com.meinil.system.domain.vo.SysMenuVO;
import com.meinil.system.mapper.SysMenuMapper;
import com.meinil.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Meinil
 * @date 2025/3/1
 * @description
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuMapper baseMapper;

    private final SysMenuConvert sysMenuConvert;

    public SysMenuServiceImpl(SysMenuMapper baseMapper, SysMenuConvert sysMenuConvert) {
        this.baseMapper = baseMapper;
        this.sysMenuConvert = sysMenuConvert;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = baseMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtil.isNotEmpty(perm)) {
                permsSet.add(perm);
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenuVO> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (WebUtil.isSuperAdmin(userId)) {
            menus = baseMapper.selectMenuTreeAll();
        } else {
            menus = baseMapper.selectMenuTreeByUserId(userId);
        }

        List<SysMenuVO> sysMenuVOS = sysMenuConvert.sysMenuToSysMenuVO(menus);
        return getChildPerms(sysMenuVOS, 0L);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, Long parentId) {
        List<SysMenuVO> returnList = new ArrayList<>();
        for (Iterator<SysMenuVO> iterator = list.iterator(); iterator.hasNext();) {
            SysMenuVO t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (Objects.equals(t.getParentId(), parentId)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<SysMenuVO> list, SysMenuVO t) {
        // 得到子节点列表
        List<SysMenuVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t) {
        List<SysMenuVO> tlist = new ArrayList<>();
        Iterator<SysMenuVO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVO n = (SysMenuVO) it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> list, SysMenuVO t) {
        return CollectionUtil.isNotEmpty(getChildList(list, t));
    }
}
