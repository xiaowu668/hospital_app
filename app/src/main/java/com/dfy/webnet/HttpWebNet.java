package com.dfy.webnet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by Administrator on 2016/5/24.
 */
public class HttpWebNet {
    private URL url;
    private HttpURLConnection conn;
    private InputStream is;
    public String mStrURL;

    public String getData()
    {
        String strData;
        try {
            url = new URL(mStrURL);
            try
            {
                //打开对服务器的连接
                conn=(HttpURLConnection) url.openConnection();
                //连接服务器
                conn.connect();
                /**读入服务器数据的过程**/
                //得到输入流
                is=conn.getInputStream();
                //创建包装流
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                //定义String类型用于储存单行数据
                String line=null;
                //创建StringBuffer对象用于存储所有数据
                StringBuffer sb=new StringBuffer();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }

                strData = sb.toString();
            }
            catch (Exception e)
            {
                strData = e.toString();
            }
        }
        catch (MalformedURLException me)
        {
            strData = me.toString();
        }

        return strData;
    }
}
