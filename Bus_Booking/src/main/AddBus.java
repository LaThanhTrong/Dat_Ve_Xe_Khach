package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AddBus {
	private JFrame frame;
	private int bed;
	private int tivi;
	private int wifi;
	private JTextField nameField;
	private JTextField plateField;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBus window = new AddBus();
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
	public AddBus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 356);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Xe khách");
		lblNewLabel.setBounds(187, 10, 71, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên xe khách");
		lblNewLabel_1.setBounds(10, 37, 77, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Biển số");
		lblNewLabel_2.setBounds(10, 71, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Chỗ ngồi");
		lblNewLabel_3.setBounds(10, 107, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Giường");
		lblNewLabel_4.setBounds(10, 148, 45, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tivi");
		lblNewLabel_5.setBounds(10, 190, 45, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Wifi");
		lblNewLabel_6.setBounds(10, 229, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		JRadioButton btnY1 = new JRadioButton("Có");
		btnY1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bed = 1;
			}
		});
		btnY1.setBounds(61, 144, 61, 21);
		frame.getContentPane().add(btnY1);
		
		JRadioButton btnN1 = new JRadioButton("Không");
		btnN1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bed = 0;
			}
		});
		btnN1.setBounds(124, 144, 103, 21);
		frame.getContentPane().add(btnN1);
		
		ButtonGroup g1 = new ButtonGroup();
		g1.add(btnY1);
		g1.add(btnN1);
		
		
		JRadioButton btnY2 = new JRadioButton("Có");
		btnY2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tivi = 1;
			}
		});
		btnY2.setBounds(61, 186, 61, 21);
		frame.getContentPane().add(btnY2);
		
		JRadioButton btnN2 = new JRadioButton("Không");
		btnN2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tivi = 0;
			}
		});
		btnN2.setBounds(124, 186, 103, 21);
		frame.getContentPane().add(btnN2);
		
		ButtonGroup g2 = new ButtonGroup();
		g2.add(btnY2);
		g2.add(btnN2);
		
		JRadioButton btnY3 = new JRadioButton("Có");
		btnY3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifi = 1;
			}
		});
		btnY3.setBounds(61, 225, 61, 21);
		frame.getContentPane().add(btnY3);
		
		JRadioButton btnN3 = new JRadioButton("Không");
		btnN3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wifi = 0;
			}
		});
		btnN3.setBounds(124, 225, 103, 21);
		frame.getContentPane().add(btnN3);
		
		ButtonGroup g3 = new ButtonGroup();
		g3.add(btnY3);
		g3.add(btnN3);
		
		nameField = new JTextField();
		nameField.setBounds(107, 34, 264, 19);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		plateField = new JTextField();
		plateField.setBounds(107, 68, 264, 19);
		frame.getContentPane().add(plateField);
		plateField.setColumns(10);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText().equals("") || plateField.getText().equals("") || comboBox.getSelectedIndex() == -1 || g1.isSelected(null) || g2.isSelected(null) || g3.isSelected(null)){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý thêm xe khách hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					String sql = "INSERT INTO xe_khach(ten_xk,bs_xk,cn_xk,giuong_xk,tivi_xk,wifi_xk) values ('"+nameField.getText()+"','"+plateField.getText()+"','"+comboBox.getSelectedItem()+"','"+bed+"','"+tivi+"','"+wifi+"')";
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Thêm xe khách thành công!","Xe khách",JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"SQLException: " + ex.getMessage(),"Lỗi thêm xe khách!",JOptionPane.ERROR_MESSAGE);
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
		btnConfirm.setBounds(315, 272, 85, 21);
		frame.getContentPane().add(btnConfirm);
		
		String[] items = {"16","29","34"}; 
		comboBox = new JComboBox(items);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(107, 103, 264, 21);
		frame.getContentPane().add(comboBox);
	}
}
