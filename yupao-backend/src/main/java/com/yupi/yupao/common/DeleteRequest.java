package com.yupi.yupao.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 通用的删除请求
 */
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = -6127824771593003128L;

    private long id;
}
