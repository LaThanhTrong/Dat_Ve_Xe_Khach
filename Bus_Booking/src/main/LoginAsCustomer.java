package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginAsCustomer {
	
	private static int id;
	private static String user;
	private static String pass;
	private static String email;
	private static String phoneNumber;
	private static String customerName;
	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;
	
	public int getID() {
		return id;
	}
	public String getUsername() {
		return user;
	}
	public String getPassword() {
		return pass;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getName() {
		return customerName;
	}
	public void setPassword(String pass) {
		this.pass = pass;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAsCustomer window = new LoginAsCustomer();
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
	public LoginAsCustomer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(700, 300, 500, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Khách hàng đăng nhập");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 28));
		lblNewLabel.setBounds(111, 10, 319, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(31, 83, 112, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(31, 149, 112, 32);
		frame.getContentPane().add(lblNewLabel_2);
		
		userField = new JTextField();
		userField.setBounds(136, 85, 313, 30);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
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
							String sql = "SELECT id_kh, username_kh, password_kh, email_kh, sdt_kh, ten_kh FROM khach_hang where username_kh = '"+username+"' and password_kh = '"+password+"'";
							stmt = conn.createStatement();
							rs = stmt.executeQuery(sql);
							if(rs.next()) {
								frame.dispose();
								id = rs.getInt("id_kh");
								user = rs.getString("username_kh");
								pass = rs.getString("password_kh");
								email = rs.getString("email_kh");
								phoneNumber = rs.getString("sdt_kh");
								customerName = rs.getString("ten_kh");
								//System.out.println("Old id: "+id);
								Booking.main(null);
							}
							else {
								JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ!","Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
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
			}
		});
		passwordField.setBounds(136, 151, 313, 30);
		frame.getContentPane().add(passwordField);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBackground(Color.GRAY);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
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
						String sql = "SELECT id_kh, username_kh, password_kh, email_kh, sdt_kh, ten_kh FROM khach_hang where username_kh = '"+username+"' and password_kh = '"+password+"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						if(rs.next()) {
							frame.dispose();
							id = rs.getInt("id_kh");
							user = rs.getString("username_kh");
							pass = rs.getString("password_kh");
							email = rs.getString("email_kh");
							phoneNumber = rs.getString("sdt_kh");
							customerName = rs.getString("ten_kh");
							//System.out.println("Old id: "+id);
							Booking.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ!","Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
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
		
		btnConfirm.setBounds(189, 191, 118, 39);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnReset = new JButton("Đặt lại");
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(170, 295, 94, 32);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Thoát");
				if(JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát chương trình không?","Đăng nhập",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(31, 295, 94, 32);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 283, 418, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(31, 66, 418, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnNewButton = new JButton("Đăng ký");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerReg.main(null);
			}
		});
		btnNewButton.setBounds(337, 240, 112, 33);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Không có tài khoản?");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(31, 244, 291, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Quên mật khẩu?");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPass.main(null);
				
			}
		});
		btnNewButton_1.setBounds(304, 295, 145, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		ImageIcon user = new ImageIcon("Images/user.png");
		user.setImage(user.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		JLabel UserImage = new JLabel();
		UserImage.setIcon(user);
		UserImage.setBounds(58, 20, 55, 30);
		frame.getContentPane().add(UserImage);
		
		ImageIcon customer = new ImageIcon("Images/customer.png");
		customer.setImage(customer.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
		JLabel background = new JLabel();
		background.setIcon(customer);
		background.setBounds(0, 0, 486, 337);
		frame.getContentPane().add(background);
		
	}
}
