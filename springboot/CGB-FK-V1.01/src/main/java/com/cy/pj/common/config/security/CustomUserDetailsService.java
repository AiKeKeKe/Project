package com.cy.pj.common.config.security;

import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysRoleMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义 UserDetailsService ，将用户信息和权限注入进来。
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SysUser user = sysUserDao.findUserByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //获取权限
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleIds)){
            roleIds.stream().forEach(roleId -> {
                SysRoleMenuVo sysRoleMenuVo = sysRoleDao.findObjectById(roleId);
                authorities.add(new SimpleGrantedAuthority(sysRoleMenuVo.getName()));
            });
        }
        //返回UserDetails实现类
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
