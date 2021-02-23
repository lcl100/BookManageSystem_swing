package bookManageSystem.view;

import bookManageSystem.bean.BookTypeBean;
import bookManageSystem.dao.BookDao;
import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookAddPanel extends JPanel implements ActionListener {
    private ComponentTools componentTools = new ComponentTools();

    private Box totalVBox, funcationHBox, nameAndAuthorHBox, sexAndPriceHBox, typeHBox, descriptionHBox, buttonHBox;
    private JLabel bookAddFuncationLabel, bookNameLabel, bookAuthorLabel, authorSexLabel, bookTypeLabel,
            descriptionLabel, bookPriceLabel;
    private JTextField bookNameTextField, bookAuthorTextField, bookPriceTextField;
    private JTextArea descriptionTextArea;
    private JRadioButton femaleRadioButton, maleRadioButton;
    private ButtonGroup radioButtonGroup;
    private JComboBox bookTypeComboBox;
    private JButton addButton, resetButton;

    BookAddPanel() {
        // 为图书添加面板添加控件内容
        this.add(createBookAddBox());
        // 批量为按钮设置图标
        componentTools.setIcons(new JButton[]{addButton, resetButton}, new String[]{"src/bookManageSystem/images/add" +
                ".png", "src/bookManageSystem/images/reset.png"});
        // 图书类别查询SQL
        String getBookTypeSQL = "select * from tb_booktype";
        // 获取所有的图书类别数据
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        // 获取所有的图书类别名称
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        // 初始化下拉列表框中的选项
        componentTools.addComboBoxItems(bookTypeComboBox, typeNames);
        // 为按钮注册事件监听器
        addButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    /**
     * 图书添加面板的内容控件
     *
     * @return 返回一个Box
     */
    private Box createBookAddBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookAddFuncationLabel = new JLabel("图书添加功能");
        bookAddFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookAddFuncationLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        nameAndAuthorHBox = Box.createHorizontalBox();
        bookNameLabel = new JLabel("图书名称：");
        bookNameTextField = new JTextField(10);
        bookAuthorLabel = new JLabel("图书作者：");
        bookAuthorTextField = new JTextField(10);
        nameAndAuthorHBox.add(bookNameLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookNameTextField);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookAuthorLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(40));
        nameAndAuthorHBox.add(bookAuthorTextField);
        totalVBox.add(nameAndAuthorHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        sexAndPriceHBox = Box.createHorizontalBox();
        authorSexLabel = new JLabel("作者性别：");
        Box sexHBox = Box.createHorizontalBox();
        femaleRadioButton = new JRadioButton("女");
        maleRadioButton = new JRadioButton("男");
        sexHBox.add(femaleRadioButton);
        sexHBox.add(maleRadioButton);
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(femaleRadioButton);
        radioButtonGroup.add(maleRadioButton);
        bookPriceLabel = new JLabel("图书价格：");
        bookPriceTextField = new JTextField(5);
        sexAndPriceHBox.add(authorSexLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(50));
        sexAndPriceHBox.add(sexHBox);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(100));
        sexAndPriceHBox.add(bookPriceLabel);
        nameAndAuthorHBox.add(Box.createHorizontalStrut(10));
        sexAndPriceHBox.add(bookPriceTextField);
        totalVBox.add(sexAndPriceHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        typeHBox = Box.createHorizontalBox();
        bookTypeLabel = new JLabel("图书类别：");
        // 实例化下拉列表框控件
        bookTypeComboBox = new JComboBox();
        typeHBox.add(bookTypeLabel);
        typeHBox.add(Box.createHorizontalStrut(40));
        typeHBox.add(bookTypeComboBox);
        totalVBox.add(typeHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        descriptionHBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("图书描述：");
        descriptionTextArea = new JTextArea(10, 40);
        descriptionHBox.add(descriptionLabel);
        descriptionHBox.add(Box.createHorizontalStrut(40));
        descriptionHBox.add(descriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(30));

        buttonHBox = Box.createHorizontalBox();
        addButton = new JButton("添加");
        resetButton = new JButton("重置");
        buttonHBox.add(addButton);
        buttonHBox.add(Box.createHorizontalStrut(80));
        buttonHBox.add(resetButton);
        totalVBox.add(buttonHBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // “添加”按钮的事件处理
        if (e.getSource() == addButton) {
            // 获取用户输入的图书名称
            String name = bookNameTextField.getText();
            // 获取用户输入的图书作者名字
            String author = bookAuthorTextField.getText();
            // 获取用户选择的图书作者的性别
            String sex = "";
            if (maleRadioButton.isSelected()) {
                sex = maleRadioButton.getText();
            } else if (femaleRadioButton.isSelected()) {
                sex = femaleRadioButton.getText();
            }
            // 获取用户输入的图书价格
            String price = bookPriceTextField.getText();
            // 获取用户选择的图书类别
            String type = (String) bookTypeComboBox.getModel().getSelectedItem();
            // 获取用户输入的图书描述
            String description = descriptionTextArea.getText();

            // 组装图书类别SQL
            String bookTypeSQL = "select * from tb_booktype where btName='" + type + "';";
            // 根据用户选择的图书类别进行查询
            List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSQL);
            // 获取查询结果
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
            // 获取用户选择的图书类别的id号
            int bookTypeId = bookTypeBean.getBookTypeId();
            // 组装新增SQL语句
            String sql =
                    "insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values('" + name + "'," +
                            "'" + author + "','" + sex + "'," + price + ",'" + description + "'," + bookTypeId + ");";
            // 执行SQL并获取操作结果
            boolean isOK = new BookDao().dataChange(sql);
            // 对添加结果进行判断
            if (isOK) {
                // 添加成功则重置用户输入，并弹出提示框
                componentTools.reset(bookNameTextField, bookAuthorTextField, bookPriceTextField, descriptionTextArea);
                componentTools.reset(radioButtonGroup);
                componentTools.reset(bookTypeComboBox);
                JOptionPane.showMessageDialog(null, "添加成功！");
            } else {
                // 添加失败也弹出提示框
                JOptionPane.showMessageDialog(null, "添加失败！");
            }
        }
        // “重置”按钮的事件处理
        if (e.getSource() == resetButton) {
            // 重置用户输入和选择
            componentTools.reset(bookNameTextField, bookAuthorTextField, bookPriceTextField, descriptionTextArea);
            componentTools.reset(radioButtonGroup);
            componentTools.reset(bookTypeComboBox);
        }
    }
}
