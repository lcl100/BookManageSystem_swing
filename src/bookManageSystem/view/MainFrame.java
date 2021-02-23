package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private ComponentTools componentTools = new ComponentTools();

    private JMenuBar menuBar;
    private JMenu bookTypeManageMenu, bookManageMenu, otherMenu;
    private JMenuItem bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem,
            aboutSoftMenuItem;

    MainFrame() {
        this.setTitle("惰惰龟图书管理系统");
        this.setBounds(400, 400, 800, 700);

        // 为主界面添加菜单条
        this.setJMenuBar(this.createMenuBar());
        // 设置主界面的内容面板
        this.setContentPane(createMainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置默认不显示
        this.setVisible(false);

        // 为菜单条中的菜单项注册事件
        bookTypeAddMenuItem.addActionListener(this);
        bookTypeManageMenuItem.addActionListener(this);
        bookAddMenuItem.addActionListener(this);
        bookManageMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        aboutSoftMenuItem.addActionListener(this);
    }

    /**
     * 为主界面创建一个菜单条
     *
     * @return 返回菜单条
     */
    private JMenuBar createMenuBar() {
        // 实例化一个菜单条对象
        menuBar = new JMenuBar();

        // 【图书类别管理】菜单
        bookTypeManageMenu = new JMenu("图书类别管理");
        bookTypeAddMenuItem = new JMenuItem("图书类别添加");
        bookTypeManageMenuItem = new JMenuItem("图书类别维护");
        bookTypeManageMenu.add(bookTypeAddMenuItem);
        bookTypeManageMenu.add(bookTypeManageMenuItem);
        menuBar.add(bookTypeManageMenu);

        // 【图书管理】菜单
        bookManageMenu = new JMenu("图书管理");
        bookAddMenuItem = new JMenuItem("图书添加");
        bookManageMenuItem = new JMenuItem("图书维护");
        bookManageMenu.add(bookAddMenuItem);
        bookManageMenu.add(bookManageMenuItem);
        menuBar.add(bookManageMenu);

        // 【其他】菜单
        otherMenu = new JMenu("其他");
        exitMenuItem = new JMenuItem("退出");
        aboutSoftMenuItem = new JMenuItem("关于软件");
        otherMenu.add(exitMenuItem);
        otherMenu.add(aboutSoftMenuItem);
        menuBar.add(otherMenu);

        // 批量为菜单项设置图标
        componentTools.setIcons(
                new JMenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem, bookManageMenuItem, exitMenuItem, aboutSoftMenuItem},
                new String[]{"src/bookManageSystem/images/add.png", "src/bookManageSystem/images/edit.png", "src" +
                        "/bookManageSystem/images/add.png", "src/bookManageSystem/images/edit.png", "src" +
                        "/bookManageSystem/images/exit.png", "src/bookManageSystem/images/about.png"}
        );

        return menuBar;
    }

    /**
     * 为主界面创建内容面板
     *
     * @return 返回一个面板
     */
    private JPanel createMainPanel() {
        // 实例化一个面板
        JPanel panel = new JPanel();
        // 设置面板中的布局方式
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon ii = new ComponentTools().iconSize(new ImageIcon("src/bookManageSystem/images/bookmanagesystem.png"), 600, 400);
        // 设置一个图片标签
        label.setIcon(ii);
        // 将标签添加到面板中
        panel.add(label);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // “图书类别添加”菜单项事件处理
        if (e.getSource() == bookTypeAddMenuItem) {
            // 将主界面的内容面板替换成图书类别添加面板
            this.setContentPane(new BookTypeAddPanel());
            // 并设置该面板显示
            this.setVisible(true);
        }
        // “图书类别维护”菜单项事件处理
        if (e.getSource() == bookTypeManageMenuItem) {
            // 将主界面的内容面板替换成图书类别维护面板
            this.setContentPane(new BookTypeManagePanel());
            // 并设置该面板显示
            this.setVisible(true);
        }
        // “图书添加”菜单项事件处理
        if (e.getSource() == bookAddMenuItem) {
            // 将主界面的内容面板替换成图书添加面板
            this.setContentPane(new BookAddPanel());
            // 并设置该面板显示
            this.setVisible(true);
        }
        // “图书维护”菜单项事件处理
        if (e.getSource() == bookManageMenuItem) {
            // 将主界面的内容面板替换成图书维护面板
            this.setContentPane(new BookManagePanel());
            // 并设置该面板显示
            this.setVisible(true);
        }
        // “退出”菜单项事件处理
        if (e.getSource() == exitMenuItem) {
            // 结束整个程序
            System.exit(0);
        }
        // “关于软件”菜单项事件处理
        if (e.getSource() == aboutSoftMenuItem) {
            // 设置关于软件提示框显示
            new AboutSoftDialog().setVisible(true);
        }
    }
}
