package bookManageSystem.tools;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

public class SimpleTools {
    /**
     * 操作结果：统一界面字体
     *
     * @param font 字体
     */
    public void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }

    }

    /**
     * 操作结果：判断文本是否为空
     * @param str 字符串
     * @return 如果是非空的返回true，否则返回false
     */
    public boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return false;
        }else{
            return true;
        }
    }
}

