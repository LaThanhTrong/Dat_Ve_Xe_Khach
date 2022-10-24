package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class LoginAsManager {

	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAsManager window = new LoginAsManager();
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
	public LoginAsManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí đăng nhập");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel.setBounds(107, 10, 274, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(41, 83, 112, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(41, 149, 112, 32);
		frame.getContentPane().add(lblNewLabel_2);
		
		userField = new JTextField();
		userField.setBounds(153, 85, 274, 30);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(153, 151, 274, 30);
		frame.getContentPane().add(passwordField);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = passwordField.getText();
				String username = userField.getText();
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tài khoản hoặc mật khẩu!","Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					Statement stmt = null;
					ResultSet rs = null;
					try {
						stmt = conn.createStatement();
						String sql = "SELECT * FROM nhan_vien where username_nv = '"+username+"' and password_nv = '"+password+"'";
						rs = stmt.executeQuery(sql);
						if(rs.next()) {
							frame.dispose();
							ManagePage.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ!","Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
							passwordField.setText(null);
							userField.setText(null);
						}
					} catch(Exception ex) {
						System.out.println("SQLException: " + ex.getMessage());
					}	
					finally {
						//giải phóng tài nguyên khi không sử dụng nữa
						if (rs != null) {
							try {
								rs.close();
							} catch (SQLException sqlEx) { } //đoạn mã xử lý ng/lệ
							rs = null;
						}
						if (stmt != null) {
							try {
								stmt.close();
							} catch (SQLException sqlEx) { } //đoạn mã xử lý ng/lệ
							stmt = null;
						}
					}
				}
			}
		});
		
		btnConfirm.setBounds(333, 210, 94, 32);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnReset = new JButton("Đặt lại");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(192, 210, 94, 32);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Thoát");
				if(JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát chương trình không?","Đăng nhập",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(49, 210, 94, 32);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 196, 418, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(31, 66, 418, 2);
		frame.getContentPane().add(separator_1);
	}
}
