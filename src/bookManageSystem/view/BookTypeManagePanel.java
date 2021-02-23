package bookManageSystem.view;

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

public class BookTypeManagePanel extends JPanel implements ActionListener, ListSelectionListener {
    private ComponentTools componentTools = new ComponentTools();

    private Box totalVBox, funcationHBox, bookTypeCheckHBox, tableHBox, idAndTypeHBox, descriptionHBox, buttonHBox;
    private JButton checkButton, alterButton, deleteButton;
    private JLabel bookTypeManageLabel, bookTypeNameLabel1, bookTypeNameLabel2, idLabel, descriptionLabel;
    private JTextField bookTypeNameTextField1, bookTypeNameTextField2, idTextField;
    private JTextArea descriptionTextArea;
    private JScrollPane tableScrollPanel;
    private JTable table;
    private DefaultTableModel tableModel;

    BookTypeManagePanel() {
        // 添加控件到图书类别维护面板中
        this.add(createBookTypeManageVBox());
        // 为按钮批量设置图标
        componentTools.setIcons(new JButton[]{alterButton, deleteButton}, new String[]{"src/bookManageSystem/images/edit.png", "src/bookManageSystem/images/delete.png"});
        // 为按钮注册事件监听器
        checkButton.addActionListener(this);
        alterButton.addActionListener(this);
        deleteButton.addActionListener(this);
        // 为表格注册监听器
        table.getSelectionModel().addListSelectionListener(this);
    }

    /**
     * 图书类别维护面板中的控件内容
     *
     * @return 返回一个Box
     */
    private Box createBookTypeManageVBox() {
        totalVBox = Box.createVerticalBox();

        funcationHBox = Box.createHorizontalBox();
        bookTypeManageLabel = new JLabel("图书类别维护功能");
        bookTypeManageLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        funcationHBox.add(bookTypeManageLabel);
        totalVBox.add(funcationHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        bookTypeCheckHBox = Box.createHorizontalBox();
        bookTypeNameLabel1 = new JLabel("图书类别名称：");
        bookTypeNameTextField1 = new JTextField(10);
        checkButton = new JButton("查询");
        bookTypeCheckHBox.add(bookTypeNameLabel1);
        bookTypeCheckHBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckHBox.add(bookTypeNameTextField1);
        bookTypeCheckHBox.add(Box.createHorizontalStrut(30));
        bookTypeCheckHBox.add(checkButton);
        totalVBox.add(bookTypeCheckHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        tableHBox = Box.createHorizontalBox();
        // 实例化一个滚动面板
        tableScrollPanel = new JScrollPane();
        // 将表格设置为滚动面板内容视图
        tableScrollPanel.setViewportView(this.createTable("select btId,btName,btDescription from tb_booktype"));
        // 设置滚动面板预定义尺寸
        tableScrollPanel.setPreferredSize(new Dimension(700, 250));
        // 将滚动面板添加到HBox中
        tableHBox.add(tableScrollPanel);
        totalVBox.add(tableHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        idAndTypeHBox = Box.createHorizontalBox();
        idLabel = new JLabel("编号：");
        idTextField = new JTextField(10);
        idTextField.setEnabled(false);
        bookTypeNameLabel2 = new JLabel("图书类别名称：");
        bookTypeNameTextField2 = new JTextField(10);
        idAndTypeHBox.add(idLabel);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(idTextField);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(bookTypeNameLabel2);
        idAndTypeHBox.add(Box.createHorizontalStrut(20));
        idAndTypeHBox.add(bookTypeNameTextField2);
        totalVBox.add(idAndTypeHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        descriptionHBox = Box.createHorizontalBox();
        descriptionLabel = new JLabel("描述：");
        descriptionTextArea = new JTextArea(5, 40);
        descriptionTextArea.setLineWrap(true);
        descriptionHBox.add(descriptionLabel);
        descriptionHBox.add(Box.createHorizontalStrut(20));
        descriptionHBox.add(descriptionTextArea);
        totalVBox.add(descriptionHBox);
        totalVBox.add(Box.createVerticalStrut(20));

        buttonHBox = Box.createHorizontalBox();
        alterButton = new JButton("修改");
        deleteButton = new JButton("删除");
        buttonHBox.add(alterButton);
        buttonHBox.add(Box.createHorizontalStrut(80));
        buttonHBox.add(deleteButton);
        totalVBox.add(buttonHBox);

        return totalVBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // “查询”按钮的事件处理
        if (e.getSource() == checkButton) {
            String sql = "";
            // 判断图书类别输入框是否为空并做处理
            if (!new SimpleTools().isEmpty(bookTypeNameTextField1.getText())) {
                // 为空即查询所有图书类别
                sql = "select * from tb_booktype;";
            } else {
                // 不为空即按条件查询图书类别
                sql = "select * from tb_booktype where btName='" + bookTypeNameTextField1.getText() + "';";
            }
            // 查询结果转换成二维数组
            String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
            // 表头数据
            String[] headers = {"编号", "图书类别名称", "图书类别描述"};
            // 填充表格模型，刷新表格
            tableModel.setDataVector(rowdatas, headers);
        }
        // “修改”按钮的事件处理
        if (e.getSource() == alterButton) {
            // 获取各输入框的内容
            String id = idTextField.getText();
            String name = bookTypeNameTextField2.getText();
            String description = descriptionTextArea.getText();
            // 组装修改的SQL语句
            String alterSQL = "update tb_booktype set btName='" + name + "',btDescription='" + description + "' where" +
                    " btId=" + id + ";";
            // 执行修改操作并返回结果
            boolean isOK = new BookTypeDao().dataChange(alterSQL);
            // 对修改结果进行判定
            if (isOK) {
                // 修改成功则刷新表格
                refreshTable();
                // 重置各输入框
                new ComponentTools().reset(idTextField, bookTypeNameTextField2, descriptionTextArea);
                // 弹出提示框
                JOptionPane.showMessageDialog(null, "修改成功！");
            } else {
                // 修改失败也弹出提示框
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        }
        // “删除”按钮的事件处理
        if (e.getSource() == deleteButton) {
            // 获取要删除的id
            String id = idTextField.getText();
            // 组装删除SQL并且删除后对主键进行重置
            String sql1 = "set FOREIGN_KEY_CHECKS=0;";
            String deleteSQL = "delete from tb_booktype where btId=" + id + ";";
            String sql2 = "set FOREIGN_KEY_CHECKS=1;";
            // 弹出确认框
            int isOK = JOptionPane.showConfirmDialog(null, "是否确认删除？");
            // 点击确定按钮则继续操作
            if (isOK == JOptionPane.OK_OPTION) {
                // 执行SQL语句
                new BookTypeDao().dataChange(sql1);
                boolean is = new BookTypeDao().dataChange(deleteSQL);
                new BookTypeDao().dataChange(sql2);
                // 对删除结果进行判定
                if (is) {
                    // 删除成功则删除表格并弹出提示框
                    refreshTable();
                    new ComponentTools().reset(idTextField, bookTypeNameTextField2, descriptionTextArea);
                } else {
                    // 删除失败也弹出提示框
                    JOptionPane.showMessageDialog(null, "删除失败！");
                }
            } else {
                return;
            }
        }
    }

    /**
     * 根据SQL查询得到的数据填充一个表格控件
     *
     * @param sql SQL语句
     * @return 返回一个JTable控件
     */
    private JTable createTable(String sql) {
        // 将表格数据转换成一个二维数组
        String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
        // 表头内容
        String[] headers = {"编号", "图书类别名称", "图书类别描述"};
        // 实例化一个表格
        table = new JTable();
        // 设置行高
        table.setRowHeight(30);
        // 将表头数据和表格内容数据填充到默认表格模型中，使用表格模型方便获取表格数据内容和刷新表格
        tableModel = new DefaultTableModel(rowdatas, headers);
        // 设置表格的模型
        table.setModel(tableModel);
        // 返回表格控件
        return table;
    }

    /**
     * 刷新表格数据
     */
    private void refreshTable() {
        // 查询所有的图书类别数据信息
        String sql = "select * from tb_booktype;";
        // 将获取的表格数据转换成二维数组
        String[][] rowdatas = new BookTypeDao().ListToArray(new BookTypeDao().getRecordsDataBySql(sql));
        // 表头数据
        String[] headers = {"编号", "图书类别名称", "图书类别描述"};
        // 重新填充表格模型，即刷新表格数据
        tableModel.setDataVector(rowdatas, headers);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // 获取表格被选中行
        int getSelectedRowIndex = table.getSelectedRow();
        // 判断是否未选中行
        if (getSelectedRowIndex == -1) {
            refreshTable();
        } else {
            // 将选中的表格行数据依次填充到下面的控件中
            idTextField.setText((String) table.getValueAt(getSelectedRowIndex, 0));
            bookTypeNameTextField2.setText((String) table.getValueAt(getSelectedRowIndex, 1));
            descriptionTextArea.setText((String) table.getValueAt(getSelectedRowIndex, 2));
        }
    }
}
