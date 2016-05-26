package com.dfy.user;

import java.io.Serializable;
import android.annotation.SuppressLint;

/**
 * Created by Administrator on 2016/5/23.
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -5683263669918171030L;

    private String userName;
    // 原始密码

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private String password;

    private boolean isRember;
    public  boolean getIsRember(){return isRember;}
    public void setIsRember(boolean bIsRember){isRember = bIsRember;}
}
