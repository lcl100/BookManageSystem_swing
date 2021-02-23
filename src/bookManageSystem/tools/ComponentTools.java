package bookManageSystem.tools;

import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class ComponentTools {

    /**
     * 操作结果：自定义图标大小的方法
     * @param imageIcon 图标
     * @param width 图片设置宽度
     * @param height 图片设置高度
     * @return ImageIcon 图标
     */
    public ImageIcon iconSize(ImageIcon imageIcon,int width,int height) {
        Image img = imageIcon.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        imageIcon.setImage(img);
        return imageIcon;
    }

    /**
     * 操作结果：设置标签的图标
     * @param labels 标签数组
     * @param pathnames 图标文件路径数组
     */
    public void setIcons(JLabel[] labels, String[] pathnames){
        if (labels.length != pathnames.length) {
            return;
        } else {
            for (int i = 0; i < labels.length; i++) {
                labels[i].setIcon(new ImageIcon(pathnames[i]));
            }
        }
    }

    /**
     * 操作结果：设置按钮的图标
     * @param buttons 按钮数组
     * @param pathnames 图标文件路径数组
     */
    public void setIcons(JButton[] buttons,String[] pathnames){
        if (buttons.length != pathnames.length) {
            return;
        } else {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setIcon(new ImageIcon(pathnames[i]));
            }
        }
    }

    /**
     * 操作结果：设置菜单的图标
     * @param menus 菜单数组
     * @param pathnames 图标文件路径数组
     */
    public void setIcons(JMenu[] menus,String[] pathnames){
        if (menus.length != pathnames.length) {
            return;
        } else {
            for (int i = 0; i < menus.length; i++) {
                menus[i].setIcon(new ImageIcon(pathnames[i]));
            }
        }
    }

    /**
     * 操作结果：设置菜单项的图标
     * @param menuItems 菜单项数组
     * @param pathnames 图标文件路径数组
     */
    public void setIcons(JMenuItem[] menuItems,String[] pathnames){
        if (menuItems.length != pathnames.length) {
            return;
        } else {
            for (int i = 0; i < menuItems.length; i++) {
                menuItems[i].setIcon(new ImageIcon(pathnames[i]));
            }
        }
    }

    /**
     * 操作结果：重置文本框，密码框的内容
     * @param textComponents 文本框，密码框组
     */
    public void reset(JTextComponent...textComponents){
        for(JTextComponent textComponent:textComponents){
            textComponent.setText("");
        }
    }

    /**
     * 操作结果：重置单选框选择
     * @param radioButtons 单选按钮组
     */
    public void reset(JRadioButton...radioButtons){
        for(JRadioButton radioButton:radioButtons){
           if(radioButton.isSelected()){
               radioButton.setSelected(false);
           }
        }
    }

    /**
     * 操作结果：重置单选按钮，这是在单选按钮组存在的情况下
     * @param buttonGroup 按钮组
     */
    public void reset(ButtonGroup buttonGroup){
        buttonGroup.clearSelection();
    }

    /**
     * 操作结果：重置复选框选择
     * @param checkBoxes 复选框组
     */
    public void reset(JCheckBox...checkBoxes){
        for(JCheckBox checkBox:checkBoxes){
            checkBox.setSelected(false);
        }
    }

    /**
     * 操作结果：重置下拉列表框选择
     * @param comboBoxes 下拉列表框组
     */
    public void reset(JComboBox...comboBoxes){
        for(JComboBox comboBox:comboBoxes){
            comboBox.setSelectedIndex(-1);
        }
    }

    /**
     * 操作结果：向下拉列表框中添加列表项
     *
     * @param comboBox 下拉列表框
     * @param items    列表项
     */
    public void addComboBoxItems(JComboBox comboBox, Object[] items) {
        for(Object obj:items){
            comboBox.addItem(obj);
        }
    }
}
