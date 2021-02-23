package bookManageSystem.view;

import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogupFrame extends JFrame implements ActionListener {
    private SimpleTools simpleTools = new SimpleTools();
    private ComponentTools componentTools = new ComponentTools();
    private JPanel panel;
    private Box totalVBox, systemLabelBox, usernameBox, passwordBox, buttonBox;
    private JLabel systemLabel, usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton logupButton, resetButton;

    public LogupFrame() {
        this.setTitle("登录");
        this.setBounds(400, 400, 600, 500);

        this.setContentPane(this.createLogupPane());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        // 为按钮注册事件
        logupButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public JPanel createLogupPane() {
        panel = new JPanel();
        totalVBox = Box.createVerticalBox();

        systemLabelBox = Box.createHorizontalBox();
        systemLabel = new JLabel("图书管理系统");
        systemLabel.setFont(new Font("微软雅黑", Font.BOLD, 40));
        systemLabelBox.add(systemLabel);

        usernameBox = Box.createHorizontalBox();
        usernameLabel = new JLabel("用户名：");
        usernameTextField = new JTextField(20);
        usernameBox.add(usernameLabel);
        usernameBox.add(Box.createHorizontalStrut(20));
        usernameBox.add(usernameTextField);

        passwordBox = Box.createHorizontalBox();
        passwordLabel = new JLabel("密    码：");
        passwordField = new JPasswordField(20);
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createHorizontalStrut(20));
        passwordBox.add(passwordField);

        buttonBox = Box.createHorizontalBox();
        logupButton = new JButton("登录");
        resetButton = new JButton("重置");
        buttonBox.add(logupButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton);

        totalVBox.add(systemLabelBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(usernameBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(passwordBox);
        totalVBox.add(Box.createVerticalStrut(80));
        totalVBox.add(buttonBox);

        panel.add(totalVBox);

        // 为标签设置图标
        new ComponentTools().setIcons(new JLabel[]{systemLabel, usernameLabel, passwordLabel}, new String[]{"src/bookManageSystem/images/logo.png", "src/bookManageSystem/images/userName.png", "src" + "/bookManageSystem/images/password.png"});
        // 为按钮设置图标
        new ComponentTools().setIcons(new JButton[]{logupButton, resetButton}, new String[]{"src/bookManageSystem/images/login.png", "src/bookManageSystem/images/reset.png"});
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 登录按钮的事件处理
        if (e.getSource() == logupButton) {
            // 判断输入框是否为空并做处理
            if (simpleTools.isEmpty(usernameTextField.getText()) && simpleTools.isEmpty(passwordField.getText())) {
                // 登录账户，这里没有调用数据库，而是使用默认唯一账户：（用户名：张三；密码：123456）
                if (usernameTextField.getText().equals("张三") && passwordField.getText().equals("123456")) {
                    // 弹出提示框进行登录成功的提示
                    int isOK = JOptionPane.showConfirmDialog(null, "恭喜您，登录成功！");
                    // 如果用户点击了提示框中的确认按钮则进行下一步操作
                    if (isOK == JOptionPane.OK_OPTION) {
                        // 重置输入框内容
                        componentTools.reset(usernameTextField, passwordField);
                        // 当登录成功后，跳转到主界面，在此时不可取消注释，因为并没有创建主界面故会报错
                         new MainFrame().setVisible(true);
                        // 登录成功后同时使登录界面不可见（即消失）
                        this.setVisible(false);
                    } else {
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "对不起，登录失败！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "不能为空！");
            }
        }
        // 重置按钮的事件处理
        if (e.getSource() == resetButton) {
            // 将输入框的内容置为空
            componentTools.reset(usernameTextField, passwordField);
        }
    }

}
