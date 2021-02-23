package bookManageSystem.view;

import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookTypeAddPanel extends JPanel implements ActionListener {
    private ComponentTools componentTools = new ComponentTools();

    private Box totalVBox, funcationBox, typeNameBox, typeDescriptionBox, buttonBox;
    private JLabel typeNameLabel, bookTypeAddFuncationLabel, typeDescriptionLabel;
    private JTextArea typeDescriptionTextArea;
    private JTextField typeNameTextField;
    private JButton addButton, resetButton;


    BookTypeAddPanel() {
        // 为图书类型添加面板添加控件
        this.add(this.createBookTypeAddBox());
        // 设置按钮的图标
        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{"src/bookManageSystem/images/add.png", "src/bookManageSystem/images/reset.png"});
        // 为按钮注册事件监听器
        addButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    /**
     * 图书类型添加面板的控件内容
     *
     * @return 返回一个Box
     */
    private Box createBookTypeAddBox() {
        // 创建一个垂直容器盒子
        totalVBox = Box.createVerticalBox();

        // 创建一个水平容器盒子
        funcationBox = Box.createHorizontalBox();
        bookTypeAddFuncationLabel = new JLabel("图书类别添加功能");
        bookTypeAddFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationBox.add(bookTypeAddFuncationLabel);
        totalVBox.add(funcationBox);
        totalVBox.add(Box.createVerticalStrut(60));

        typeNameBox = Box.createHorizontalBox();
        typeNameLabel = new JLabel("图书类别名称：");
        typeNameTextField = new JTextField(20);
        typeNameBox.add(typeNameLabel);
        typeNameBox.add(Box.createHorizontalStrut(50));
        typeNameBox.add(typeNameTextField);
        totalVBox.add(typeNameBox);
        totalVBox.add(Box.createVerticalStrut(60));

        typeDescriptionBox = Box.createHorizontalBox();
        typeDescriptionLabel = new JLabel("图书类别描述：");
        typeDescriptionTextArea = new JTextArea(10, 20);
        typeDescriptionTextArea.setLineWrap(true);
        typeDescriptionBox.add(typeDescriptionLabel);
        typeDescriptionBox.add(Box.createHorizontalStrut(50));
        typeDescriptionBox.add(typeDescriptionTextArea);
        totalVBox.add(typeDescriptionBox);
        totalVBox.add(Box.createVerticalStrut(60));

        buttonBox = Box.createHorizontalBox();
        addButton = new JButton("添加");
        resetButton = new JButton("重置");
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(resetButton);
        totalVBox.add(buttonBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 添加按钮的事件处理
        if (e.getSource() == addButton) {
            // 获取图书分类名称
            String bookTypeName = typeNameTextField.getText();
            // 获取图书分类描述
            String bookTypeDescription = typeDescriptionTextArea.getText();
            // 组装SQL语句
            String sql =
                    "insert into tb_bookType (btName, btDescription) values ('" + bookTypeName + "','" + bookTypeDescription + "');";
            // 执行方法插入一条书籍
            boolean isOK = new BookTypeDao().dataChange(sql);
            // 判断是否操作成功并作出相应的处理
            if (isOK) {
                JOptionPane.showMessageDialog(null, "添加成功！");
                componentTools.reset(typeNameTextField, typeDescriptionTextArea);
            } else {
                JOptionPane.showMessageDialog(null, "添加失败！");
            }
        }
        // 重置按钮的事件处理
        if (e.getSource() == resetButton) {
            // 即清空输入框的内容
            componentTools.reset(typeNameTextField, typeDescriptionTextArea);
        }
    }
}
