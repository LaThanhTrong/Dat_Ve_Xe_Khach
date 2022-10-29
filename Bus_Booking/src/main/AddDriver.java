package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class AddDriver {

	private JFrame frame;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDriver window = new AddDriver();
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
	public AddDriver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 264);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tài xế");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(174, 10, 95, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài xế");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 53, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Số điện thoại");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 93, 117, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Địa chỉ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 130, 100, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		nameField = new JTextField();
		nameField.setBounds(137, 53, 289, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(137, 94, 289, 26);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(137, 132, 289, 27);
		frame.getContentPane().add(addressField);
		addressField.setColumns(10);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.setBackground(new Color(255, 99, 71));
		btnConfirm.setForeground(new Color(0, 0, 0));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals("") || phoneField.getText().equals("") || addressField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý thêm tài xế hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					String sql = "INSERT INTO tai_xe(ten_tx,sdt_tx,dchi_tx) values ('"+nameField.getText()+"','"+phoneField.getText()+"','"+addressField.getText()+"')";
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Thêm tài xế thành công!","Tài xế",JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"SQLException: " + ex.getMessage(),"Lỗi thêm tài xế!",JOptionPane.ERROR_MESSAGE);
					}
					finally {
						//giải phóng tài nguyên khi không sử dụng nữa
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
		btnConfirm.setBounds(159, 176, 110, 40);
		frame.getContentPane().add(btnConfirm);
	}
}
