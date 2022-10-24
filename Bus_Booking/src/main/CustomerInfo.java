package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerInfo {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInfo window = new CustomerInfo();
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
	public CustomerInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginAsCustomer lc = new LoginAsCustomer();
		frame = new JFrame();
		frame.setBounds(100, 100, 433, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(86, 0, 245, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản ID");
		lblNewLabel_1.setBounds(10, 35, 81, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tên tài khoản");
		lblNewLabel_2.setBounds(10, 58, 81, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setBounds(10, 81, 81, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setText(lc.getPassword());
		passwordField.setBounds(155, 71, 176, 26);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Tên khách hàng");
		lblNewLabel_4.setBounds(10, 107, 108, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		nameField = new JTextField();
		nameField.setText(lc.getName());
		nameField.setBounds(155, 101, 176, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Số điện thoại");
		lblNewLabel_5.setBounds(10, 137, 81, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		phoneField = new JTextField();
		phoneField.setText(lc.getPhoneNumber());
		phoneField.setBounds(155, 131, 176, 26);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setBounds(10, 170, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		emailField = new JTextField();
		emailField.setText(lc.getEmail());
		emailField.setBounds(155, 164, 176, 26);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hiển thị");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					passwordField.setEchoChar((char) 0);
				}
				else {
					passwordField.setEchoChar('\u2022');
				}
			}
		});
		chckbxNewCheckBox.setBounds(337, 77, 93, 21);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPass f = new ForgotPass();
				f.setUsername(lc.getUsername());
				ChangePass.main(null);
			}
		});
		btnNewButton.setBounds(286, 196, 108, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnName = new JButton("Sửa");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống!","Lỗi sửa",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý sửa tên khách hàng hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					PreparedStatement pStmt = null;
					try {
						pStmt = conn.prepareStatement("UPDATE khach_hang set ten_kh=? where id_kh=?");
						pStmt.setString(1, nameField.getText());
						pStmt.setInt(2, lc.getID());
						pStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex) {
						System.out.println("SQLException: " + ex.getMessage());
					}
				}
			}
		});
		btnName.setBounds(341, 103, 72, 21);
		frame.getContentPane().add(btnName);
		
		JButton btnPhone = new JButton("Sửa");
		btnPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(phoneField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống!","Lỗi sửa",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý sửa số điện thoại hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					PreparedStatement pStmt = null;
					try {
						pStmt = conn.prepareStatement("UPDATE khach_hang set sdt_kh=? where id_kh=?");
						pStmt.setString(1, phoneField.getText());
						pStmt.setInt(2, lc.getID());
						pStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex) {
						System.out.println("SQLException: " + ex.getMessage());
					}
				}
			}
		});
		btnPhone.setBounds(341, 133, 72, 21);
		frame.getContentPane().add(btnPhone);
		
		JButton btnEmail = new JButton("Sửa");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(emailField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Email không được để trống!","Lỗi sửa",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý sửa email hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					PreparedStatement pStmt = null;
					try {
						pStmt = conn.prepareStatement("UPDATE khach_hang set email_kh=? where id_kh=?");
						pStmt.setString(1, emailField.getText());
						pStmt.setInt(2, lc.getID());
						pStmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex) {
						System.out.println("SQLException: " + ex.getMessage());
					}
				}
			}
		});
		btnEmail.setBounds(341, 166, 72, 21);
		frame.getContentPane().add(btnEmail);
		
		JLabel idLabel = new JLabel(String.valueOf(lc.getID()));
		idLabel.setBounds(155, 35, 176, 13);
		frame.getContentPane().add(idLabel);
		
		JLabel userLabel = new JLabel(lc.getUsername());
		userLabel.setBounds(155, 58, 176, 13);
		frame.getContentPane().add(userLabel);
		
		JButton btnNewButton_2 = new JButton("Trở về");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(10, 196, 85, 32);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Làm mới");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
				PreparedStatement pStmt = null;
				ResultSet rs = null;
				try {
					pStmt = conn.prepareStatement("SELECT password_kh, email_kh, sdt_kh, ten_kh FROM khach_hang where id_kh=?");
					pStmt.setInt(1,lc.getID());
					rs = pStmt.executeQuery();
					if(rs.next()) {
						lc.setPassword(rs.getString("password_kh"));
						lc.setEmail(rs.getString("email_kh"));
						lc.setPhoneNumber(rs.getString("sdt_kh"));
						lc.setName(rs.getString("ten_kh"));
					}
				}catch(Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
				passwordField.setText(lc.getPassword());
				nameField.setText(lc.getName());
				phoneField.setText(lc.getPhoneNumber());
				emailField.setText(lc.getEmail());
			}
		});
		btnNewButton_1.setBounds(155, 196, 85, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Tra cứu lịch sử đặt vé");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderHistory.main(null);
			}
		});
		btnNewButton_3.setBounds(223, 31, 171, 21);
		frame.getContentPane().add(btnNewButton_3);
	}
}
