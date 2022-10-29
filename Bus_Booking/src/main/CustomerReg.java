package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CustomerReg {

	private JFrame frame;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField userField;
	private JPasswordField passwordField;
	private JPasswordField rePassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReg window = new CustomerReg();
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
	public CustomerReg() {
		initialize();
	}
	
	public final class LengthRestrictedDocument extends PlainDocument {
		private final int limit;
		public LengthRestrictedDocument(int limit) {
			this.limit = limit;
		 }
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null) {
				return;
			}
			if ((getLength() + str.length()) <= limit) {
				super.insertString(offs, str, a);
			}	
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginAsCustomer lc = new LoginAsCustomer();
		frame = new JFrame();
		frame.setBounds(100, 100, 638, 472);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng ký tài khoản");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(197, 10, 255, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và Tên Khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 70, 191, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		nameField = new JTextField();
		nameField.setDocument(new LengthRestrictedDocument(50));
		nameField.setBounds(211, 70, 403, 35);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 115, 191, 35);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		phoneField = new JTextField();
		phoneField.setDocument(new LengthRestrictedDocument(10));
		phoneField.setBounds(211, 115, 403, 35);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 158, 56, 35);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		emailField = new JTextField();
		emailField.setDocument(new LengthRestrictedDocument(50));
		emailField.setColumns(10);
		emailField.setBounds(211, 158, 403, 35);
		frame.getContentPane().add(emailField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tên tài khoản:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(10, 207, 117, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		userField = new JTextField();
		userField.setDocument(new LengthRestrictedDocument(50));
		userField.setColumns(10);
		userField.setBounds(211, 207, 403, 33);
		frame.getContentPane().add(userField);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Mật khẩu:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1.setBounds(10, 258, 117, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Nhập lại mật khẩu:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 310, 191, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát không?","Đăng nhập",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					frame.dispose();
				}
			}
		});
		
		JButton btnngK = new JButton("Đăng ký");
		btnngK.setBackground(new Color(255, 99, 71));
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				String username = userField.getText();
				String password = passwordField.getText();
				String rePass = rePassField.getText();
				if(name.equals("") || phone.equals("") || email.equals("") || username.equals("") || password.equals("") || rePass.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi đăng ký",JOptionPane.ERROR_MESSAGE);
				}
				else if(!password.equals(rePass)) {
					JOptionPane.showMessageDialog(null, "Mật khẩu và nhập lại mật khẩu không khớp","Lỗi đăng ký",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý đăng ký tài khoản hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0){
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					CallableStatement cStmt = null;
					Statement stmt = null;
					ResultSet rs = null;
					
					try {
						stmt = conn.createStatement();
						String sql = "SELECT * FROM khach_hang where username_kh = '"+username+"'";
						rs = stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Tài khoản này đã tồn tại!","Lỗi đăng ký",JOptionPane.ERROR_MESSAGE);
						}
						else {
							cStmt = conn.prepareCall("{call them_kh(?,?,?,?,?)}");
							cStmt.setString(1, name);
							cStmt.setString(2, phone);
							cStmt.setString(3, email);
							cStmt.setString(4, username);
							cStmt.setString(5, password);
							
							cStmt.executeUpdate();
							cStmt.close();
							JOptionPane.showMessageDialog(null, "Đăng ký thành công");
							frame.dispose();
						}
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null,"SQLException: " + ex.getMessage(),"Lỗi đăng ký",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(168, 380, 104, 42);
		frame.getContentPane().add(btnExit);
		
		btnngK.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnngK.setBounds(348, 380, 104, 42);
		frame.getContentPane().add(btnngK);
		
		passwordField = new JPasswordField();
		passwordField.setDocument(new LengthRestrictedDocument(50));
		passwordField.setBounds(211, 258, 403, 33);
		frame.getContentPane().add(passwordField);
		
		rePassField = new JPasswordField();
		rePassField.setDocument(new LengthRestrictedDocument(50));
		rePassField.setBounds(211, 310, 403, 33);
		frame.getContentPane().add(rePassField);
	}
}
