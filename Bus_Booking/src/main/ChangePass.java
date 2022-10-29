package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePass {
	private JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField repasswordField;
	private ForgotPass fp = new ForgotPass();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePass window = new ChangePass();
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
	public ChangePass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 242);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(126, 10, 152, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 56, 104, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 109, 152, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 56, 239, 31);
		frame.getContentPane().add(passwordField);
		
		repasswordField = new JPasswordField();
		repasswordField.setBounds(172, 111, 239, 31);
		frame.getContentPane().add(repasswordField);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBackground(Color.LIGHT_GRAY);
		btnConfirm.setForeground(Color.DARK_GRAY);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals("") || repasswordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(!passwordField.getText().equals(repasswordField.getText())) {
						JOptionPane.showMessageDialog(null, "Mật khẩu và nhập lại mật khẩu không giống!","Lỗi",JOptionPane.ERROR_MESSAGE);
					}
					else {
						Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
						CallableStatement cStmt = null;
						try {
							cStmt = conn.prepareCall("{call sua_mk(?, ?)}");
							cStmt.setString(1, fp.getUsername());
							cStmt.setString(2, repasswordField.getText());
							cStmt.execute();
							JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!","Đổi mật khẩu",JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						} catch (SQLException ex) {
							System.out.println("SQLException: " + ex.getMessage());
						}
					}
				}
			}
		});
		btnConfirm.setBounds(143, 158, 135, 37);
		frame.getContentPane().add(btnConfirm);
	}
}
