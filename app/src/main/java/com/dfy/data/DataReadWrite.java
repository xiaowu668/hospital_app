package com.dfy.data;

import java.io.IOException;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.dfy.serialize.SerializableUtil;
import com.dfy.user.UserEntity;
/**
 * Created by Administrator on 2016/5/23.
 */
public class DataReadWrite {

    // 用户名key
    public final static String KEY_NAME = "KEY_NAME";

    public final static String KEY_LEVEL = "KEY_LEVEL";


    private static DataReadWrite s_DataReadWrite;

    private static UserEntity s_User = null;

    private SharedPreferences msp;

    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context)
    {
        if (s_DataReadWrite == null)
        {
            s_DataReadWrite = new DataReadWrite(context);
        }
    }

    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized DataReadWrite getInstance()
    {
        return s_DataReadWrite;
    }

    public DataReadWrite(Context context)
    {
        msp = context.getSharedPreferences("DataReadWrite",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }

    public SharedPreferences getSharedPref()
    {
        return msp;
    }


    public synchronized void putUser(UserEntity user)
    {

        Editor editor = msp.edit();

        String str="";
        try {
            str = SerializableUtil.obj2Str(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();

        s_User = user;
    }

    public synchronized UserEntity getUser()
    {

        if (s_User == null)
        {
            s_User = new UserEntity();


            //获取序列化的数据
            String str = msp.getString(DataReadWrite.KEY_NAME, "");

            try {
                Object obj = SerializableUtil.str2Obj(str);
                if(obj != null){
                    s_User = (UserEntity)obj;
                }

            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return s_User;
    }

    public synchronized void DeleteUser()
    {
        Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");

        editor.commit();
        s_User = null;
    }

}
