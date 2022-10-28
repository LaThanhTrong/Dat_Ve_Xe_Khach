package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.ImageIcon;
import java.awt.Color;

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
		frame.setBounds(100, 100, 1046, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon bus = new ImageIcon("Images/bus.png");
		bus.setImage(bus.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		JLabel busIcon = new JLabel();
		busIcon.setIcon(bus);
		busIcon.setBounds(314, 5, 96, 63);
		frame.getContentPane().add(busIcon);
		
		JLabel lblNewLabel = new JLabel("Đặt vé xe khách");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 40));
		lblNewLabel.setBounds(420, 5, 334, 63);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 21, 116, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(lc.getID()));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(127, 21, 45, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Thành phố nơi đi");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 94, 129, 36);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Thành phố nơi đến");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(275, 94, 142, 36);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ngày đi");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(556, 94, 85, 36);
		frame.getContentPane().add(lblNewLabel_5);
		
		JDateChooser dateField = new JDateChooser();
		dateField.setBounds(625, 96, 150, 36);
		frame.getContentPane().add(dateField);
		
		JButton btnNewButton = new JButton("Tìm vé");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
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
					String id,xk,tx,seat,dateArrive,time,timeArrive,price,address,destination;
					conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					try {
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT lt.id_lt,xk.ten_xk,tx.ten_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt \r\n"
								+ "FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt\r\n"
								+ "				   join xe_khach xk on xk.id_xk = lt.id_xk\r\n"
								+ "                   join tai_xe tx on tx.id_tx = lt.id_tx where dcd_lt = '"+comboBox1.getSelectedItem()+"' and dcc_lt = '"+comboBox2.getSelectedItem()+"' and ngaykh_lt = '"+dateFormat.format(dateField.getDate())+"'"
										+ "order by lt.id_lt");
						rsmd = rs.getMetaData();
						model = (DefaultTableModel) table.getModel();
						cols = rsmd.getColumnCount();
						colName = new String[cols];
						model.setColumnIdentifiers(colName);
						while(rs.next()) {
							id = rs.getString(1);
							xk = rs.getString(2);
							tx = rs.getString(3);
							seat = rs.getString(4);
							address = rs.getString(5);
							destination = rs.getString(6);
							date = rs.getString(7);
							dateArrive = rs.getString(8);
							time = rs.getString(9);
							timeArrive = rs.getString(10);
							price = rs.getString(11);
							row = new String[]{id,xk,tx,seat,address,destination,date,dateArrive,time,timeArrive,price};
							model.addRow(row);
						}
						table.getColumnModel().getColumn(0).setHeaderValue("ID");
						table.getColumnModel().getColumn(1).setHeaderValue("Tên xe khách");
						table.getColumnModel().getColumn(2).setHeaderValue("Tên tài xế");
						table.getColumnModel().getColumn(3).setHeaderValue("Số chỗ ngồi");
						table.getColumnModel().getColumn(4).setHeaderValue("Điểm đầu");
						table.getColumnModel().getColumn(5).setHeaderValue("Điểm đến");
						table.getColumnModel().getColumn(6).setHeaderValue("Ngày khởi hành");
						table.getColumnModel().getColumn(7).setHeaderValue("Ngày đến");
						table.getColumnModel().getColumn(8).setHeaderValue("Giờ khởi hành");
						table.getColumnModel().getColumn(9).setHeaderValue("Giờ đến");
						table.getColumnModel().getColumn(10).setHeaderValue("Giá chỗ ngồi");
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
		btnNewButton.setBounds(795, 95, 96, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lịch trình");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
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
				String id,xk,tx,seat,dateArrive,time,timeArrive,address,destination,price;
				conn = MySQLConnect.getConnect("dat_ve_xe_khach");
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT lt.id_lt,xk.ten_xk,tx.ten_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt \r\n"
							+ "FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt\r\n"
							+ "				   join xe_khach xk on xk.id_xk = lt.id_xk\r\n"
							+ "                   join tai_xe tx on tx.id_tx = lt.id_tx order by lt.id_lt");
					rsmd = rs.getMetaData();
					model = (DefaultTableModel) table.getModel();
					cols = rsmd.getColumnCount();
					colName = new String[cols];
					model.setColumnIdentifiers(colName);
					while(rs.next()) {
						id = rs.getString(1);
						xk = rs.getString(2);
						tx = rs.getString(3);
						seat = rs.getString(4);
						address = rs.getString(5);
						destination = rs.getString(6);
						date = rs.getString(7);
						dateArrive = rs.getString(8);
						time = rs.getString(9);
						timeArrive = rs.getString(10);
						price = rs.getString(11);
						row = new String[]{id,xk,tx,seat,address,destination,date,dateArrive,time,timeArrive,price};
						model.addRow(row);
					}
					table.getColumnModel().getColumn(0).setHeaderValue("ID");
					table.getColumnModel().getColumn(1).setHeaderValue("Tên xe khách");
					table.getColumnModel().getColumn(2).setHeaderValue("Tên tài xế");
					table.getColumnModel().getColumn(3).setHeaderValue("Số chỗ ngồi");
					table.getColumnModel().getColumn(4).setHeaderValue("Điểm đầu");
					table.getColumnModel().getColumn(5).setHeaderValue("Điểm đến");
					table.getColumnModel().getColumn(6).setHeaderValue("Ngày khởi hành");
					table.getColumnModel().getColumn(7).setHeaderValue("Ngày đến");
					table.getColumnModel().getColumn(8).setHeaderValue("Giờ khởi hành");
					table.getColumnModel().getColumn(9).setHeaderValue("Giờ đến");
					table.getColumnModel().getColumn(10).setHeaderValue("Giá chỗ ngồi");
					
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
		btnNewButton_1.setBounds(901, 94, 121, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 1012, 415);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Đặt vé");
		btnNewButton_2.setBackground(new Color(255, 182, 193));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seatField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng đặt số chỗ ngồi!","Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
				}
				else if(Integer.parseInt(seatField.getText()) <= 0){
					JOptionPane.showMessageDialog(null, "Số chỗ ngồi không hợp lệ!","Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						seat = Integer.parseInt(seatField.getText());
						int cn_lt = 0;
						try {
							int row = table.getSelectedRow();
							id_lt = (table.getModel().getValueAt(row, 0).toString());
							price = Float.parseFloat(table.getModel().getValueAt(row, 10).toString()) * seat;
							Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
							PreparedStatement pStmt = null;
							ResultSet rs = null;
							pStmt = conn.prepareStatement("select cn_lt from chongoi_lichtrinh where id_lt=?");
							pStmt.setInt(1, Integer.parseInt(id_lt));
							rs = pStmt.executeQuery();
							if(rs.next()) {
								cn_lt = rs.getInt(1);
							}
							if(seat > cn_lt) {
								JOptionPane.showMessageDialog(null, "Lịch trình đã hết chỗ ngồi!", "Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
							}
							else {
								try {
									AddPayment.main(null);
								}catch(Exception ex) {
									System.out.println("SQLException: " + ex.getMessage());
								}
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
		btnNewButton_2.setBounds(453, 593, 139, 41);
		frame.getContentPane().add(btnNewButton_2);
		
		ImageIcon user = new ImageIcon("Images/user.png");
		user.setImage(user.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		JButton btnNewButton_3 = new JButton(/*"Thông tin cá nhân"*/);
		btnNewButton_3.setBackground(new Color(255, 239, 213));
		//btnNewButton_3.setBackground(new Color(255, 235, 205));
		//btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setIcon(user);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfo.main(null);
			}
		});
		btnNewButton_3.setBounds(925, 21, 57, 57);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_6 = new JLabel("Nhập số chỗ ngồi cần đặt");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 606, 215, 24);
		frame.getContentPane().add(lblNewLabel_6);
		
		seatField = new JTextField();
		seatField.setBounds(211, 603, 96, 28);
		frame.getContentPane().add(seatField);
		seatField.setColumns(10);
		
		String[] items = {"Cần Thơ","Hà Nội","Quảng Bình","Ninh Bình","Đà Nẵng","Sài Gòn"};
		comboBox1 = new JComboBox(items);
		comboBox1.setSelectedIndex(-1);
		comboBox1.setBounds(149, 96, 116, 36);
		frame.getContentPane().add(comboBox1);
		
		comboBox2 = new JComboBox(items);
		comboBox2.setSelectedIndex(-1);
		comboBox2.setBounds(427, 95, 116, 36);
		frame.getContentPane().add(comboBox2);
		
		ImageIcon booking = new ImageIcon("Images/booking.png");
		booking.setImage(booking.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT));
		JLabel background = new JLabel();
		background.setIcon(booking);
		background.setBounds(0, 0, 1032, 652);
		frame.getContentPane().add(background);
		
	}
}
