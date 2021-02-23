package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutSoftDialog extends JDialog implements ActionListener, MouseListener {
    private ComponentTools componentTools = new ComponentTools();
    private JPanel aboutSoftPanel;
    private Box totalHBox, leftHBox, rightVBox;
    private JLabel iconLabel, systemLabel, editionLabel, hyperlinkLabel;
    private JButton closeButton;

    AboutSoftDialog() {
        // 设置Dialog的相关属性
        this.setTitle("关于软件");
        this.setBounds(400, 400, 500, 300);

        this.setContentPane(this.createAboutSoftPanel());
        this.setVisible(false);

        // 为按钮注册事件监听器
        closeButton.addActionListener(this);
        // 为标签注册鼠标事件监听器
        hyperlinkLabel.addMouseListener(this);
    }

    /**
     * 创建Dialog的内容面板
     *
     * @return 返回一个JPanel
     */
    private JPanel createAboutSoftPanel() {
        aboutSoftPanel = new JPanel();
        aboutSoftPanel.setLayout(new BorderLayout());

        totalHBox = Box.createHorizontalBox();

        leftHBox = Box.createHorizontalBox();
        iconLabel = new JLabel();
        iconLabel.setIcon(componentTools.iconSize(new ImageIcon("src/bookManageSystem/images/panda.png"), 160, 160));
        leftHBox.add(iconLabel);
        totalHBox.add(leftHBox);

        rightVBox = Box.createVerticalBox();
        systemLabel = new JLabel("惰惰龟图书管理系统");
        systemLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        editionLabel = new JLabel("版本 1.0");
        editionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        hyperlinkLabel = new JLabel("<html><u>相关GitHub链接</u></html>");
        hyperlinkLabel.setForeground(new Color(0, 149, 200));
        hyperlinkLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        rightVBox.add(systemLabel);
        rightVBox.add(Box.createVerticalStrut(50));
        rightVBox.add(editionLabel);
        rightVBox.add(Box.createVerticalStrut(50));
        rightVBox.add(hyperlinkLabel);
        totalHBox.add(Box.createHorizontalStrut(20));
        totalHBox.add(rightVBox);

        aboutSoftPanel.add(totalHBox, BorderLayout.NORTH);

        closeButton = new JButton("关闭");
        Box buttonHBox = Box.createHorizontalBox();
        buttonHBox.add(closeButton);
        aboutSoftPanel.add(buttonHBox, BorderLayout.EAST);

        return aboutSoftPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // “关闭”按钮的事件处理
        if (e.getSource() == closeButton) {
            // 设置该Dialog不显示即可
            this.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 鼠标点击事件
        // 通过电脑本地打开默认浏览器然后再打开URI所指向的位置
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://github.com/lck100/JavaExerciseProject/tree/master/1" +
                    ".%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F/%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F%EF%BC%88JavaFX%E7%89%88%EF%BC%89"));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 鼠标按下事件
        hyperlinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlinkLabel.setForeground(new Color(0, 0, 0));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
