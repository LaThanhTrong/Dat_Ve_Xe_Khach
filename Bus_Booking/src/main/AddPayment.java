package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class AddPayment {

	private JFrame frame;
	private JTextField priceField;
	private JTextField discountField;
	private Booking b = new Booking();
	private JLabel priceLable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPayment window = new AddPayment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddPayment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		HashMap<String,Integer> m = new HashMap<String,Integer>();
		m.put("mysql", 10);
		m.put("aile", 5);
		m.put("database", 15);
		
		LoginAsCustomer lc = new LoginAsCustomer();
		frame.setBounds(100, 100, 457, 255);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thanh toán");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(146, 10, 140, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập số tiền");
		lblNewLabel_1.setBounds(10, 108, 81, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		priceField = new JTextField();
		priceField.setBounds(217, 105, 194, 19);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập mã giảm giá (Không bắc buộc)");
		lblNewLabel_2.setBounds(10, 148, 209, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		discountField = new JTextField();
		discountField.setBounds(217, 145, 194, 19);
		frame.getContentPane().add(discountField);
		discountField.setColumns(10);
		
		JButton btnReturn = new JButton("Trở về");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnReturn.setBounds(10, 187, 85, 21);
		frame.getContentPane().add(btnReturn);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(priceField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền cần thanh toán!","Lỗi thanh toán",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý thanh toán cho hóa đơn này hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0){
					String id_lt = b.getID_Schedule();
					int id_dv = 0;
					int id_kh = lc.getID();
					int seat = b.getSeat();
					float price = b.getPrice() - (b.getPrice()*m.getOrDefault(discountField.getText(), 0)/100);
					//System.out.println(id_lt+", "+seat+", "+price);
					if(Float.parseFloat(priceField.getText())  < price) {
						JOptionPane.showMessageDialog(null, "Số dư thanh toán của bạn không đủ!","Lỗi thanh toán",JOptionPane.ERROR_MESSAGE);
					}
					else {
						Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
						PreparedStatement pStmt = null;
						CallableStatement cStmt = null;
						Statement stmt = null;
						ResultSet rs = null;
						try {
							//Insert to dat_ve
							pStmt = conn.prepareStatement("INSERT INTO dat_ve (id_lt,id_kh,cn_dv,ngay_dv) VALUES (?,?,?,curdate())");
							pStmt.setString(1, id_lt);
							pStmt.setInt(2, id_kh);
							pStmt.setInt(3, seat);
							pStmt.executeUpdate();
							
							//Retrieve dat_ve ID which was previously created
							try {
								stmt = conn.createStatement();
								rs = stmt.executeQuery("SELECT max(id_dv) from dat_ve where id_lt='"+id_lt+"' and id_kh='"+id_kh+"' and cn_dv='"+seat+"' and ngay_dv=curdate()");
								if(rs.next()) {
									id_dv = rs.getInt(1);
								}

							}catch(Exception ex) {
								System.out.println("SQLException: " + ex.getMessage());
							}
							
							//Insert to thanh_toan
							pStmt = conn.prepareStatement("INSERT INTO thanh_toan (id_dv,t_tt,giamgia_tt,tht_tt) values (?,?,?,?)");
							pStmt.setInt(1, id_dv);
							pStmt.setString(2, priceField.getText());
							pStmt.setInt(3, m.getOrDefault(discountField.getText(), 0));
							pStmt.setFloat(4, price);
							pStmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Thanh toán thành công!","Thanh toán",JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}catch(Exception ex) {
							System.out.println("SQLException: " + ex.getMessage());
						}
					}
				}
			}
		});
		btnConfirm.setBounds(303, 187, 108, 21);
		frame.getContentPane().add(btnConfirm);
		
		JLabel lblNewLabel_3 = new JLabel("Số tiền cần thanh toán");
		lblNewLabel_3.setBounds(10, 64, 151, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		priceLable = new JLabel(String.valueOf(b.getPrice()));
		priceLable.setBounds(217, 63, 116, 13);
		frame.getContentPane().add(priceLable);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m.containsKey(discountField.getText())) {
					priceLable.setText(String.valueOf(b.getPrice() - (b.getPrice()*m.get(discountField.getText())/100)));
				}
				else {
					priceLable.setText(String.valueOf(b.getPrice()));
				}
			}
		});
		btnNewButton.setBounds(326, 60, 85, 21);
		frame.getContentPane().add(btnNewButton);
	}

}
