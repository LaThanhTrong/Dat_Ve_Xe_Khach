package main;

import java.awt.EventQueue;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ForgotPass {
	private static String username;
	private JFrame frame;
	private JTextField userField;
	private JTextField otpField;
	private MailAPI m = new MailAPI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPass window = new ForgotPass();
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
	public ForgotPass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 446, 237);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 58, 131, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quên mật khẩu");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_1.setBounds(121, 10, 182, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNhpMOpt = new JLabel("Nhập mã OTP");
		lblNhpMOpt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhpMOpt.setBounds(10, 107, 131, 28);
		frame.getContentPane().add(lblNhpMOpt);
		
		userField = new JTextField();
		userField.setBounds(151, 58, 244, 31);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		otpField = new JTextField();
		otpField.setBounds(151, 107, 244, 28);
		frame.getContentPane().add(otpField);
		otpField.setColumns(10);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userField.getText().equals("") || otpField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(otpField.getText().equals(m.getOTP())) {
						frame.dispose();
						ChangePass.main(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "Mã OTP không hợp lệ!","Lỗi",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnConfirm.setBounds(264, 159, 131, 31);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnReset = new JButton("Đặt lại");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText(null);
				otpField.setText(null);
			}
		});
		btnReset.setBounds(137, 161, 103, 31);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Gửi");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = userField.getText();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tài khoản","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					Statement stmt = null;
					ResultSet rs = null;
					try {
						String sql = "SELECT email_kh FROM khach_hang where username_kh = '"+username+"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()) {
							MailAPI.sendMail(rs.getString(1));
							JOptionPane.showMessageDialog(null, "Đã gửi mã OTP qua email!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception ex) {
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
		btnExit.setBounds(10, 161, 103, 31);
		frame.getContentPane().add(btnExit);
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
