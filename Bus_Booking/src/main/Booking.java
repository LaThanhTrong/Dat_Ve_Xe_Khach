package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Booking {
	private static String id_lt;
	private static float price;
	private static int seat;
	private boolean k = false;
	private JFrame frame;
	private JTable table;
	private JTextField seatField;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	public String getID_Schedule() {
		return id_lt;
	}
	public int getSeat() {
		return seat;
	}
	public float getPrice() {
		return price;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking window = new Booking();
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
	public Booking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		LoginAsCustomer lc = new LoginAsCustomer();
		frame.setBounds(100, 100, 926, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đặt vé xe khách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(306, 10, 210, 63);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản ID:");
		lblNewLabel_1.setBounds(10, 10, 74, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(lc.getID()));
		lblNewLabel_2.setBounds(88, 16, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Thành phố nơi đi");
		lblNewLabel_3.setBounds(10, 78, 100, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Thành phố nơi đến");
		lblNewLabel_4.setBounds(230, 78, 109, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ngày đi");
		lblNewLabel_5.setBounds(471, 78, 45, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JDateChooser dateField = new JDateChooser();
		dateField.setBounds(519, 78, 109, 19);
		frame.getContentPane().add(dateField);
		
		JButton btnNewButton = new JButton("Tìm vé");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox1.getSelectedIndex() == -1 || comboBox2.getSelectedIndex() == -1 || dateField.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi tìm vé",JOptionPane.ERROR_MESSAGE);
				}
				else if(comboBox1.getSelectedIndex() == comboBox2.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "Nơi đi và nơi đến không được trùng!","Lỗi tìm vé",JOptionPane.ERROR_MESSAGE);
				}
				else {
					table.setModel(new DefaultTableModel());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String date = null;
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					DefaultTableModel model = null;
					ResultSetMetaData rsmd = null;
					int cols = 0;
					String[] colName;
					String[] row;
					String id,xk,tx,dateArrive,time,timeArrive,price,address,destination;
					conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					try {
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM lich_trinh where dcd_lt = '"+comboBox1.getSelectedItem()+"' and dcc_lt = '"+comboBox2.getSelectedItem()+"' and ngaykh_lt = '"+dateFormat.format(dateField.getDate())+"'");
						rsmd = rs.getMetaData();
						model = (DefaultTableModel) table.getModel();
						cols = rsmd.getColumnCount();
						colName = new String[cols];
						model.setColumnIdentifiers(colName);
						while(rs.next()) {
							id = rs.getString(1);
							xk = rs.getString(2);
							tx = rs.getString(3);
							address = rs.getString(4);
							destination = rs.getString(5);
							date = rs.getString(6);
							dateArrive = rs.getString(7);
							time = rs.getString(8);
							timeArrive = rs.getString(9);
							price = rs.getString(10);
							row = new String[]{id,xk,tx,address,destination,date,dateArrive,time,timeArrive,price};
							model.addRow(row);
						}
						table.getColumnModel().getColumn(0).setHeaderValue("ID");
						table.getColumnModel().getColumn(1).setHeaderValue("ID Xe khách");
						table.getColumnModel().getColumn(2).setHeaderValue("ID Tài xế");
						table.getColumnModel().getColumn(3).setHeaderValue("Điểm đầu");
						table.getColumnModel().getColumn(4).setHeaderValue("Điểm đến");
						table.getColumnModel().getColumn(5).setHeaderValue("Ngày khởi hành");
						table.getColumnModel().getColumn(6).setHeaderValue("Ngày đến");
						table.getColumnModel().getColumn(7).setHeaderValue("Giờ khởi hành");
						table.getColumnModel().getColumn(8).setHeaderValue("Giờ đến");
						table.getColumnModel().getColumn(9).setHeaderValue("Giá chỗ ngồi");
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
		btnNewButton.setBounds(678, 76, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lịch trình");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k = true;
				table.setModel(new DefaultTableModel());
				String date = null;
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				DefaultTableModel model = null;
				ResultSetMetaData rsmd = null;
				int cols = 0;
				String[] colName;
				String[] row;
				String id,xk,tx,dateArrive,time,timeArrive,address,destination,price;
				conn = MySQLConnect.getConnect("dat_ve_xe_khach");
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM lich_trinh");
					rsmd = rs.getMetaData();
					model = (DefaultTableModel) table.getModel();
					cols = rsmd.getColumnCount();
					colName = new String[cols];
					model.setColumnIdentifiers(colName);
					while(rs.next()) {
						id = rs.getString(1);
						xk = rs.getString(2);
						tx = rs.getString(3);
						address = rs.getString(4);
						destination = rs.getString(5);
						date = rs.getString(6);
						dateArrive = rs.getString(7);
						time = rs.getString(8);
						timeArrive = rs.getString(9);
						price = rs.getString(10);
						row = new String[]{id,xk,tx,address,destination,date,dateArrive,time,timeArrive,price};
						model.addRow(row);
					}
					table.getColumnModel().getColumn(0).setHeaderValue("ID");
					table.getColumnModel().getColumn(1).setHeaderValue("ID Xe khách");
					table.getColumnModel().getColumn(2).setHeaderValue("ID Tài xế");
					table.getColumnModel().getColumn(3).setHeaderValue("Điểm đầu");
					table.getColumnModel().getColumn(4).setHeaderValue("Điểm đến");
					table.getColumnModel().getColumn(5).setHeaderValue("Ngày khởi hành");
					table.getColumnModel().getColumn(6).setHeaderValue("Ngày đến");
					table.getColumnModel().getColumn(7).setHeaderValue("Giờ khởi hành");
					table.getColumnModel().getColumn(8).setHeaderValue("Giờ đến");
					table.getColumnModel().getColumn(9).setHeaderValue("Giá chỗ ngồi");
					
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
		});
		btnNewButton_1.setBounds(805, 76, 97, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 892, 420);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Đặt vé");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seatField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng đặt số chỗ ngồi!","Lỗi tìm vé",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						seat = Integer.parseInt(seatField.getText());
						try {
							int row = table.getSelectedRow();
							id_lt = (table.getModel().getValueAt(row, 0).toString());
							price = Float.parseFloat(table.getModel().getValueAt(row, 9).toString()) * seat;
							try {
								AddPayment.main(null);
							}catch(Exception ex) {
								System.out.println("SQLException: " + ex.getMessage());
							}
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu!", "Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Chỗ ngồi không hợp lệ!","Lỗi tìm vé",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnNewButton_2.setBounds(732, 544, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Thông tin cá nhân");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfo.main(null);
			}
		});
		btnNewButton_3.setBounds(763, 25, 139, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_6 = new JLabel("Nhập số chỗ ngồi cần đặt");
		lblNewLabel_6.setBounds(10, 548, 162, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		seatField = new JTextField();
		seatField.setBounds(172, 545, 96, 19);
		frame.getContentPane().add(seatField);
		seatField.setColumns(10);
		
		String[] items = {"Cần Thơ","Hà Nội","Quảng Bình","Ninh Bình","Đà Nẵng","Sài Gòn"};
		comboBox1 = new JComboBox(items);
		comboBox1.setSelectedIndex(-1);
		comboBox1.setBounds(111, 76, 116, 21);
		frame.getContentPane().add(comboBox1);
		
		comboBox2 = new JComboBox(items);
		comboBox2.setSelectedIndex(-1);
		comboBox2.setBounds(345, 76, 116, 21);
		frame.getContentPane().add(comboBox2);
	}
}
