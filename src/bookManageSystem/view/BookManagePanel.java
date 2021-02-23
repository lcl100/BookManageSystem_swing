package bookManageSystem.view;

import bookManageSystem.bean.BookTypeBean;
import bookManageSystem.dao.BookDao;
import bookManageSystem.dao.BookTypeDao;
import bookManageSystem.tools.ComponentTools;
import bookManageSystem.tools.SimpleTools;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookManagePanel extends JPanel implements ActionListener, ListSelectionListener {
    private ComponentTools componentTools = new ComponentTools();
    private SimpleTools simpleTools = new SimpleTools();

    private Box totalVBox, funcationHBox, checkHBox, tableHBox, sexRadioButtonHBox, descriptionHBox, buttonHBox,
            showContentHBox1, showContentHBox2;
    private JComboBox bookTypeComboBox, bookTypeComboBox2;
    private JLabel bookManageFuncationLabel, bookNameLabel, bookTypeLabel, idLabel, priceLabel, bookNameLabel2,
            bookAuthorLabel, bookTypeLabel2, bookAuthorLabel2, authorSexLabel, bookDescriptionLabel;
    private JTextField bookNameTextField, bookNameTextField2, bookAuthorTextField, bookAuthorTextField2, idTextField,
            priceTextField;
    private JTextArea bookDescriptionTextArea;
    private JButton checkButton, resetButton, resetButton2, alterButton, deleteButton;
    private ButtonGroup sexButtonGroup;
    private JScrollPane tableScrollPanel;
    private JRadioButton femaleRadioButton, maleRadioButton;
    private JTable table;
    private DefaultTableModel tableModel;


    BookManagePanel() {
        // 为图书维护面板添加控件内容
        this.add(this.createBookManageBox());
        // 图书类别查询SQL
        String getBookTypeSQL = "select * from tb_booktype;";
        // 获取所有的图书类别数据
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        // 提取所有的图书类别名称信息
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        // 初始化下拉列表框中的图书类别信息
        componentTools.addComboBoxItems(bookTypeComboBox, typeNames);
        componentTools.addComboBoxItems(bookTypeComboBox2, typeNames);
        // 为按钮注册事件监听器
        checkButton.addActionListener(this);
        resetButton.addActionListener(this);
        alterButton.addActionListener(this);
        deleteButton.addActionListener(this);
        resetButton2.addActionListener(this);
        // 为表格注册事件监听器
        table.getSelectionModel().addListSelectionListener(this);
    }

    /**
     * 创建图书维护面板的控件内容
     *
     * @return 返回一个Box
     */
    private Box createBookManageBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookManageFuncationLabel = new JLabel("图书维护功能");
        bookManageFuncationLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookManageFuncationLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        checkHBox = Box.createHorizontalBox();
        bookNameLabel = new JLabel("图书名称：");
        bookNameTextField = new JTextField();
        bookAuthorLabel = new JLabel("图书作者：");
        bookAuthorTextField = new JTextField();
        bookTypeLabel = new JLabel("图书类别：");
        bookTypeComboBox = new JComboBox();
        checkButton = new JButton("查询");
        resetButton = new JButton("重置");
        checkHBox.add(bookNameLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookNameTextField);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookAuthorLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookAuthorTextField);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookTypeLabel);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(bookTypeComboBox);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(checkButton);
        checkHBox.add(Box.createHorizontalStrut(10));
        checkHBox.add(resetButton);
        totalVBox.add(checkHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        tableHBox = Box.createHorizontalBox();
        // 实例化一个滚动面板
        tableScrollPanel = new JScrollPane();
        // 将表格添加到滚动面板中
        tableScrollPanel.setViewportView(this.createTable("select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription," +
                "btName from tb_book,tb_booktype where tb_book.btId=tb_booktype.btId;"));
        // 设置预定义大小
        tableScrollPanel.setPreferredSize(new Dimension(700, 250));
        tableHBox.add(tableScrollPanel);
        totalVBox.add(tableHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        showContentHBox1 = Box.createHorizontalBox();
        idLabel = new JLabel("编号：");
        idTextField = new JTextField(5);
        idTextField.setEnabled(false);
        bookNameLabel2 = new JLabel("图书名称：");
        bookNameTextField2 = new JTextField(5);
        authorSexLabel = new JLabel("作者性别：");
        sexRadioButtonHBox = Box.createHorizontalBox();
        femaleRadioButton = new JRadioButton("女");
        maleRadioButton = new JRadioButton("男");
        sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(femaleRadioButton);
        sexButtonGroup.add(maleRadioButton);
        sexRadioButtonHBox.add(femaleRadioButton);
        sexRadioButtonHBox.add(maleRadioButton);
        showContentHBox1.add(idLabel);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(idTextField);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(bookNameLabel2);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(bookNameTextField2);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(authorSexLabel);
        showContentHBox1.add(Box.createHorizontalStrut(10));
        showContentHBox1.add(sexRadioButtonHBox);
        totalVBox.add(showContentHBox1);
        totalVBox.add(Box.createVerticalStrut(15));

        showContentHBox2 = Box.createHorizontalBox();
        priceLabel = new JLabel("价格：");
        priceTextField = new JTextField(5);
        bookAuthorLabel2 = new JLabel("图书作者：");
        bookAuthorTextField2 = new JTextField(5);
        bookTypeLabel2 = new JLabel("图书类别：");
        bookTypeComboBox2 = new JComboBox();
        showContentHBox2.add(priceLabel);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(priceTextField);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookAuthorLabel2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookAuthorTextField2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookTypeLabel2);
        showContentHBox2.add(Box.createHorizontalStrut(10));
        showContentHBox2.add(bookTypeComboBox2);
        totalVBox.add(showContentHBox2);
        totalVBox.add(Box.createVerticalStrut(15));

        descriptionHBox = Box.createHorizontalBox();
        bookDescriptionLabel = new JLabel("图书描述：");
        bookDescriptionTextArea = new JTextArea(3, 10);
        descriptionHBox.add(bookDescriptionLabel);
        descriptionHBox.add(bookDescriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(15));

        buttonHBox = Box.createHorizontalBox();
        alterButton = new JButton("修改");
        deleteButton = new JButton("删除");
        resetButton2 = new JButton("重置");
        buttonHBox.add(alterButton);
        buttonHBox.add(Box.createHorizontalStrut(100));
        buttonHBox.add(deleteButton);
        buttonHBox.add(Box.createHorizontalStrut(100));
        buttonHBox.add(resetButton2);
        totalVBox.add(buttonHBox);

        // 批量为各个按钮设置图标
        componentTools.setIcons(new JButton[]{alterButton, deleteButton, resetButton2}, new String[]{"src/bookManageSystem" +
                "/images/edit.png", "src/bookManageSystem/images/delete.png", "src/bookManageSystem/images/reset.png"});

        return totalVBox;
    }

    /**
     * 根据SQL查询得到的表格数据填充创建表格
     *
     * @param sql SQL语句
     * @return 返回填充完成的JTable控件
     */
    private JTable createTable(String sql) {
        // 获取所有的图书记录并将结果转换成一个二维数组
        String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
        // 表头
        String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
        // 实例化表格控件
        table = new JTable();
        // 设置行高
        table.setRowHeight(30);
        // 将表头和表格内容数据填充到表格数据模型中
        tableModel = new DefaultTableModel(rowdatas, headers);
        //为表格设置数据模型
        table.setModel(tableModel);
        // 返回填充完成的表格控件
        return table;
    }

    /**
     * 刷新表格
     */
    private void refreshTable() {
        // 条件查询SQL
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype " +
                "where tb_book.btId=tb_booktype.btId;";
        String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
        String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
        // 重新填充表格数据，刷新表格
        tableModel.setDataVector(rowdatas, headers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // “查询”按钮的事件处理
        if (e.getSource() == checkButton) {
            // 查询SQL
            String sql =
                    "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where "
                            + "tb_book.btId=tb_booktype.btId ";
            // 判断输入是否为空
            if (simpleTools.isEmpty(bookNameTextField.getText()) && simpleTools.isEmpty(bookAuthorTextField.getText()) && bookTypeComboBox.getSelectedIndex() < 0) {
                sql += " and 1=1;";
            } else {
                // 用户输入的图书名称
                if (bookNameTextField.getText().length() > 0) {
                    sql += " and bBookName like '%" + bookNameTextField.getText() + "%'";
                }
                // 用户输入的图书作者
                if (bookAuthorTextField.getText().length() > 0) {
                    sql += " and bAuthor like '%" + bookAuthorTextField.getText() + "%'";
                }
                // 用户选择的图书类别
                String booktype = (String) bookTypeComboBox.getModel().getSelectedItem();
                if (simpleTools.isEmpty(booktype)) {
                    sql += " and btName='" + booktype + "';";
                }
            }
            // 根据组装的SQL获取表格数据并转换成二维数组
            String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
            // 表头
            String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
            // 重新填充表格数据模型，刷新表格
            tableModel.setDataVector(rowdatas, headers);
        }
        // “重置”按钮的事件处理
        if (e.getSource() == resetButton) {
            // 查询SQL
            String sql =
                    "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where "
                            + "tb_book.btId=tb_booktype.btId ";
            String[][] rowdatas = new BookDao().ListToArray(new BookDao().getRecordsDataBySql(sql));
            String[] headers = {"编号", "图书名称", "图书作者", "作者性别", "图书价格", "图书描述", "图书类别"};
            tableModel.setDataVector(rowdatas, headers);
            componentTools.reset(bookNameTextField, bookAuthorTextField);
            componentTools.reset(bookTypeComboBox);
        }
        // “修改”按钮的事件处理
        if (e.getSource() == alterButton) {
            String id = idTextField.getText();
            String bookName = bookNameTextField2.getText();
            String sex = "";
            if (femaleRadioButton.isSelected()) {
                sex = femaleRadioButton.getText();
            } else {
                sex = maleRadioButton.getText();
            }
            Float price = Float.parseFloat(priceTextField.getText());
            String bookAuthor = bookAuthorTextField2.getText();
            String bookType = (String) bookTypeComboBox2.getModel().getSelectedItem();
            String bookDescription = bookDescriptionTextArea.getText();
            // 组装修改SQL
            String alterSQL =
                    "update tb_booktype,tb_book set bBookName='" + bookName + "',bAuthor='" + bookAuthor + "',bSex='" + sex + "'," + "bPrice" + "=" + price + "," + "tb_booktype.btName='" + bookType + "',bBookDescription='" + bookDescription + "' where " + "tb_book.btId=tb_booktype" + ".btId " + "and" + " bId=" + id + ";";
            // 执行修改操作并返回操作结果
            boolean isOK = new BookTypeDao().dataChange(alterSQL);
            // 对修改结果进行处理
            if (isOK) {
                // 修改成功则刷新表格、重置控件内容、弹出提示框
                refreshTable();
                componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                        bookDescriptionTextArea);
                componentTools.reset(sexButtonGroup);
                componentTools.reset(bookTypeComboBox2);
                JOptionPane.showMessageDialog(null, "修改成功！");
            } else {
                // 修改失败则也弹出提示框
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        }
        // “删除”按钮的事件处理
        if (e.getSource() == deleteButton) {
            // 获取要删除的图书id
            String id = idTextField.getText();
            // 删除SQL并重置tb_book的主键
            String sql1 = "set FOREIGN_KEY_CHECKS=0;";
            String deleteSQL = "delete from tb_book where bId=" + id + ";";
            String sql2 = "set FOREIGN_KEY_CHECKS=1;";
            // 弹出确认框来确认用户是否要删除
            int isOK = JOptionPane.showConfirmDialog(null, "是否确认删除？");
            // 如果用户点击了确定按钮
            if (isOK == JOptionPane.OK_OPTION) {
                // 执行删除SQL
                new BookDao().dataChange(sql1);
                boolean is = new BookDao().dataChange(deleteSQL);
                new BookDao().dataChange(sql2);
                // 对删除操作结果进行判断
                if (is) {
                    // 删除成功则刷新表格、重置控件内容
                    refreshTable();
                    componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                            bookDescriptionTextArea);
                    componentTools.reset(sexButtonGroup);
                    componentTools.reset(bookTypeComboBox2);
                } else {
                    // 删除失败也弹出提示框
                    JOptionPane.showMessageDialog(null, "删除失败！");
                }
            } else {
                return;
            }
        }
        // 第二个“重置”按钮的事件处理
        if (e.getSource() == resetButton2) {
            // 重置控件内容
            componentTools.reset(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                    bookDescriptionTextArea);
            componentTools.reset(sexButtonGroup);
            componentTools.reset(bookTypeComboBox2);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // 获取表格所选中行的索引
        int getSelectedRowIndex = table.getSelectedRow();
        // 判断是用户否选中表格中的某一行
        if (getSelectedRowIndex == -1) {
            refreshTable();
        } else {
            // 如果选中表格中的某一行，则将选中行的数据填充到下面的控件中
            idTextField.setText((String) table.getValueAt(getSelectedRowIndex, 0));
            bookNameTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 1));
            boolean b = table.getValueAt(getSelectedRowIndex, 3).equals("男");
            if (b) {
                maleRadioButton.setSelected(true);
            } else {
                femaleRadioButton.setSelected(true);
            }
            priceTextField.setText((String) table.getValueAt(getSelectedRowIndex, 4));
            bookAuthorTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 2));
            bookTypeComboBox2.setSelectedItem(table.getValueAt(getSelectedRowIndex, 6));
            bookDescriptionTextArea.setText((String) table.getValueAt(getSelectedRowIndex, 5));
        }
    }
}
