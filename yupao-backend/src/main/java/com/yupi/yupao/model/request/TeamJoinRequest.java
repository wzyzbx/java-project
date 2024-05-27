package com.yupi.yupao.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户加入队伍请求
 * @author zbx
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = 6329406995466340003L;

    /**
     * id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;
}
