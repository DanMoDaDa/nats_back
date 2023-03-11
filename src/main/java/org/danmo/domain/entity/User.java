package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

@Data
@TableName(value = "t_user")
public class User extends BaseEntity {

    private String nickname;

    private String username;

    private String password;

    private String headImg_url;

    private String email;

    private String phone;

    private String weixin;

}
