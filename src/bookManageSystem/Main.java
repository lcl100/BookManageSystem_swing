package bookManageSystem;

import bookManageSystem.tools.SimpleTools;
import bookManageSystem.view.LogupFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // 设置全局字体
        new SimpleTools().initGlobalFont(new Font("微软雅黑", Font.PLAIN, 16));
        // 首页界面即登录界面
        new LogupFrame().setVisible(true);
    }
}
