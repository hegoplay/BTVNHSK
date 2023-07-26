package qlSPham;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
public class DOMvsGUI extends JFrame implements ActionListener, MouseListener {
    private static final long serialVersionUID = 6736610855443618564L;
    private DefaultTableModel model;
    private JTable table;
    private ManageProduct dom;
    private JButton btnDelete;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnAdd;
    private TableRowSorter<TableModel> sorter;
    private JButton btnFilter;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtManufacture;
    private JTextField txtDes;
    private JTextField txtSname;
    private JTextField txtCountry;
    private JTextField txtWeb;
    private JTextField txtPrice;
    private JCheckBox chkPrice;
    public DOMvsGUI(){
        setTitle("Dom parser");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        String[] headers = {"productID", "name", "manufacture", "description", "sname", "country", "web", "price"};
        add(new JScrollPane(table = new JTable(
            model = new DefaultTableModel(headers , 0){
                @Override
                public Class<?> getColumnClass(int columnIndex){
                    // TODO Auto-generated method stub
                    if(columnIndex == 7){
                        return Double.class;
                    }
                    return super.getColumnClass(columnIndex);
                }
            }
        )), BorderLayout.CENTER);
        dom = new ManageProduct();
        updateTableData();
        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        JPanel pnlSouth;
        add(pnlSouth = new JPanel(), BorderLayout.SOUTH);
        pnlSouth.setLayout(new BorderLayout());
        JPanel pnlSSouth;
        pnlSouth.add(pnlSSouth = new JPanel(),BorderLayout.SOUTH);
        pnlSSouth.add(btnAdd = new JButton("Add"));
        pnlSSouth.add(btnDelete = new JButton("Delete"));
        pnlSSouth.add(btnUpdate = new JButton("Update"));
        pnlSSouth.add(btnSave = new JButton("Save"));
        pnlSSouth.add(btnFilter = new JButton("Filter"));
        pnlSSouth.add(chkPrice = new JCheckBox("Gia Giam dan"));
        JPanel pnlSCenter;
        pnlSouth.add(pnlSCenter = new JPanel(),BorderLayout.CENTER);
        pnlSCenter.setLayout(new GridLayout(4, 2, 10, 10));
        JPanel pnl1;
        pnlSCenter.add(pnl1 = new JPanel());
        pnl1.add(new JLabel("ID"));
        pnl1.add(txtID = new JTextField(10));
        txtID.setEditable(false);
        JPanel pnl2;
        pnlSCenter.add(pnl2 = new JPanel());
        pnl2.add(new JLabel("name"));
        pnl2.add(txtName = new JTextField(10));
        JPanel pnl3;
        pnlSCenter.add(pnl3 = new JPanel());
        pnl3.add(new JLabel("manufacture"));
        pnl3.add(txtManufacture = new JTextField(10));
        JPanel pnl4;
        pnlSCenter.add(pnl4 = new JPanel());
        pnl4.add(new JLabel("description"));
        pnl4.add(txtDes = new JTextField(10));
        JPanel pnl5;
        pnlSCenter.add(pnl5 = new JPanel());
        pnl5.add(new JLabel("supply name"));
        pnl5.add(txtSname = new JTextField(10));
        JPanel pnl6;
        pnlSCenter.add(pnl6 = new JPanel());
        pnl6.add(new JLabel("country"));
        pnl6.add(txtCountry = new JTextField(10));
        JPanel pnl7;
        pnlSCenter.add(pnl7 = new JPanel());
        pnl7.add(new JLabel("Website"));
        pnl7.add(txtWeb = new JTextField(10));
        JPanel pnl8;
        pnlSCenter.add(pnl8 = new JPanel());
        pnl8.add(new JLabel("Price"));
        pnl8.add(txtPrice = new JTextField(10));
        table.addMouseListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnSave.addActionListener(this);
        btnFilter.addActionListener(this);
        btnAdd.addActionListener(this);
    }
    private void updateTableData(){
        //xoa du lieu tren table
        while(table.getRowCount() != 0)
            model.removeRow(0);
        ArrayList<Product> list = dom.getAllProducts();
        for(Product p : list){
            Object rowData[] = {
                p.getId(), p.getProductName(), p.getManufacture(), p.getDescription(), 
                p.getSuplier().getSuplierName(), p.getSuplier().getCountry(), p.getSuplier().getWebsite(), p.getPrice()
            };
            model.addRow(rowData);
        }
    }
    public static void main(String[] args){
        new DOMvsGUI().setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object o = e.getSource();
        if(o.equals(btnAdd)){
            String id = JOptionPane.showInputDialog("Nhap vao masp");
            String name = JOptionPane.showInputDialog("Nhap vao ten sp");
            String manufacture = JOptionPane.showInputDialog("Nhap vao nha san xuat");
            String description = JOptionPane.showInputDialog("Nhap mo ta");
            String suplierName = JOptionPane.showInputDialog("Nhap vao ten nha cung cap");
            String country = JOptionPane.showInputDialog("Nhap vao quoc gia");
            String website = JOptionPane.showInputDialog("Nhap vao ten website");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Nhap gia san pham"));
            if(id.trim().equals("")){
                JOptionPane.showMessageDialog(this, "Failed");
                return;
            }
            Supplier s  = new Supplier(suplierName,country,website);
            Product p = new Product(id, name, manufacture, description, s, price);
            dom.addProduct(p);
            JOptionPane.showMessageDialog(this, "Them thanh cong");
            updateTableData();
            dom.writeXMLFile();
        }
        if(o.equals(btnDelete)){
            int a[] = table.getSelectedRows();
            for(int i = a.length-1;i >=0;i--){
                dom.deleteProduct((String)table.getValueAt(a[i], 0));
            }
            updateTableData();
        }
        if(o.equals(btnUpdate)){
            dom.deleteProduct(txtID.getText());
            dom.addProduct(getProductField());
            updateTableData();
        }
        if(o.equals(btnSave)){
            dom.writeXMLFile();
        }
        if(o.equals(btnFilter)){
            sorter.setRowFilter(new RowFilter(){
                @Override
                public boolean include(Entry entry){
                    // TODO Auto-generated method stub
                    String name  = entry.getValue(1).toString();
                    String searchText = txtName.getText();
                    String country  = entry.getValue(5).toString();
                    String search = txtName.getText();
                    return name.startsWith(searchText) && country.startsWith(search);
                }
            });
        }       
    }
    @Override
    public void mouseClicked(MouseEvent e){
        getDataFromField();
    }
    @Override
    public void mousePressed(MouseEvent e){
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e){
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e){
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e){
        // TODO Auto-generated method stub
    }
    public void getDataFromField(){
        int row = table.getSelectedRow();
        txtID.setText((String)table.getValueAt(row, 0));
        txtName.setText((String)table.getValueAt(row, 1));
        txtManufacture.setText((String)table.getValueAt(row, 2));
        txtDes.setText((String)table.getValueAt(row, 3));
        txtSname.setText((String)table.getValueAt(row, 4));
        txtCountry.setText((String)table.getValueAt(row, 5));
        txtWeb.setText((String)table.getValueAt(row, 6));
        txtPrice.setText(table.getValueAt(row, 7)+"");
    }
    public Product getProductField(){
        return new Product(
            txtID.getText(), 
            txtName.getText(), 
            txtManufacture.getText(), 
            txtDes.getText(), 
            new Supplier(txtSname.getText(), 
            txtCountry.getText(), 
            txtWeb.getText()), 
            Double.parseDouble(txtPrice.getText())
        );
    }
}