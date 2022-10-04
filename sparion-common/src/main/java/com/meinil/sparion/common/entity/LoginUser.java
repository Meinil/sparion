package com.meinil.sparion.common.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {
    @ApiModelProperty("用户信息")
    private User user;

    @ApiModelProperty("权限信息")
    private List<Permission> permissions;

    @JSONField(serialize = false, deserialize = false)
    @ApiModelProperty("SpringSecurity权限信息")
    private List<GrantedAuthority> authorities;

    @ApiModelProperty("角色信息")
    private List<String> roles;

    public LoginUser(User user, List<Permission> permissions, List<String> roles) {
        this.user = user;
        this.permissions = permissions;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = permissions.stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getPerms()))
                            .collect(Collectors.toList());
        }
        return authorities;
    }

    @JSONField(serialize = false, deserialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JSONField(serialize = false, deserialize = false)
    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JSONField(serialize = false, deserialize = false)
    public Long getId() {
        return user.getId();
    }
}
