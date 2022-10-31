package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Color;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ManagePage {
	private int key = 0;
	private JFrame frame;
	private JTable table;
	private JTextField nameCustomerField;
	private JTextField phoneCustomerField;
	private JTextField nameDriverField;
	private JTextField phoneDriverField;
	private JTextField plateBusField;
	private JTextField nameBusField;
	private JDateChooser dateBookingField;
	private JTextField namePaymentField;
	private JTextField discountPaymentField;
	private JTextField addressField;
	private JTextField destinationField;
	private JDateChooser dateScheduleField;
	private JTextField nameBookingField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagePage window = new ManagePage();
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
	public ManagePage() {
		initialize();
	}
	
	private void SQLCustomer(String sql) {
		String name = nameCustomerField.getText();
		String phone = phoneCustomerField.getText();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,email,user,pass;
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				phone = rs.getString(3);
				email = rs.getString(4);
				user = rs.getString(5);
				pass = rs.getString(6);
				row = new String[]{id,name,phone,email,user,pass};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID");
			table.getColumnModel().getColumn(1).setHeaderValue("Khách hàng");
			table.getColumnModel().getColumn(2).setHeaderValue("Số điện thoại");
			table.getColumnModel().getColumn(3).setHeaderValue("Email");
			table.getColumnModel().getColumn(4).setHeaderValue("Tên tài khoản");
			table.getColumnModel().getColumn(5).setHeaderValue("Mật khẩu");
			name = nameCustomerField.getText();
			phone = phoneCustomerField.getText();
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
	
	private void SQLSchedule(String sql) {
		String date = null;
		String address = addressField.getText();
		String destination = destinationField.getText();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,xk,tx,dateArrive,time,timeArrive,price,seat;
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
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
			table.getColumnModel().getColumn(1).setHeaderValue("ID Xe khách");
			table.getColumnModel().getColumn(2).setHeaderValue("ID Tài xế");
			table.getColumnModel().getColumn(3).setHeaderValue("Số chỗ ngồi");
			table.getColumnModel().getColumn(4).setHeaderValue("Điểm đầu");
			table.getColumnModel().getColumn(5).setHeaderValue("Điểm đến");
			table.getColumnModel().getColumn(6).setHeaderValue("Ngày khởi hành");
			table.getColumnModel().getColumn(7).setHeaderValue("Ngày đến");
			table.getColumnModel().getColumn(8).setHeaderValue("Giờ khởi hành");
			table.getColumnModel().getColumn(9).setHeaderValue("Giờ đến");
			table.getColumnModel().getColumn(10).setHeaderValue("Giá chỗ ngồi");
			address = addressField.getText();
			destination = destinationField.getText();
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
	
	private void SQLDriver(String sql) {
		String name = nameDriverField.getText();
		String phone = phoneDriverField.getText();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,address;
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				phone = rs.getString(3);
				address = rs.getString(4);
				row = new String[]{id,name,phone,address};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID");
			table.getColumnModel().getColumn(1).setHeaderValue("Tên tài xế");
			table.getColumnModel().getColumn(2).setHeaderValue("Số điện thoại");
			table.getColumnModel().getColumn(3).setHeaderValue("Địa chỉ");
			name = nameDriverField.getText();
			phone = phoneDriverField.getText();
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
	
	private void SQLBus(String sql) {
		String plate = plateBusField.getText();
		String name = nameBusField.getText();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,seat,bed,tivi,wifi;
		String yes = "Có";
		String no = "Không";
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				plate = rs.getString(3);
				seat = rs.getString(4);
				if(rs.getString(5).equals("1")) bed = yes;
				else bed = no;
				if(rs.getString(6).equals("1")) tivi = yes;
				else tivi = no;
				if(rs.getString(7).equals("1")) wifi = yes;
				else wifi = no;
				row = new String[]{id,name,plate,seat,bed,tivi,wifi};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID");
			table.getColumnModel().getColumn(1).setHeaderValue("Tên xe khách");
			table.getColumnModel().getColumn(2).setHeaderValue("Biển số");
			table.getColumnModel().getColumn(3).setHeaderValue("Chỗ ngồi");
			table.getColumnModel().getColumn(4).setHeaderValue("Giường");
			table.getColumnModel().getColumn(5).setHeaderValue("Tivi");
			table.getColumnModel().getColumn(6).setHeaderValue("Wifi");
			plate = plateBusField.getText();
			name = nameBusField.getText();
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
	
	private int NameToID(String name) {
		Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id_kh FROM khach_hang where ten_kh = '"+name+"'");
			if(rs.next()) {
				id = rs.getInt("id_kh");
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
		return id;
	}
	
	private int NameToPayment(String name) {
		Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT dv.id_dv FROM dat_ve dv join khach_hang kh on dv.id_kh = kh.id_kh where ten_kh = '"+name+"'");
			if(rs.next()) {
				id = rs.getInt("id_dv");
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
		return id;
	}
	
	private void SQLBooking(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,lt,kh,seat,ngay;
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
				id = rs.getString(1);
				lt = rs.getString(2);
				kh = rs.getString(3);
				seat = rs.getString(4);
				ngay = rs.getString(5);
				row = new String[]{id,lt,kh,seat,ngay};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID");
			table.getColumnModel().getColumn(1).setHeaderValue("ID lịch trình");
			table.getColumnModel().getColumn(2).setHeaderValue("ID khách hàng");
			table.getColumnModel().getColumn(3).setHeaderValue("Chỗ ngồi");
			table.getColumnModel().getColumn(4).setHeaderValue("Ngày đặt vé");
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
	
	private void SQLPayment(String sql) {
		String discount = discountPaymentField.getText();
		Connection conn = null;
		Statement stmt = null;
		CallableStatement cStmt = null;
		ResultSet rs = null;
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id,dv,price;
		conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		try {
			cStmt = conn.prepareCall("{call capnhat_tt(?)}");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			//Call update 
			while(rs.next()) {
				id = rs.getString(1);
				cStmt.setString(1,id);
				cStmt.executeUpdate();
			}
			rs = stmt.executeQuery(sql);
			cStmt.close();
			while(rs.next()) {
				id = rs.getString(1);
				dv = rs.getString(2);
				discount = rs.getString(3);
				price = rs.getString(4);
				row = new String[]{id,dv,discount,price};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID");
			table.getColumnModel().getColumn(1).setHeaderValue("ID đặt vé");
			table.getColumnModel().getColumn(2).setHeaderValue("Giảm giá");
			table.getColumnModel().getColumn(3).setHeaderValue("Thành tiền");
			discount = discountPaymentField.getText();
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
	
	private void Delete(String procedureName) {
		try {
			int row = table.getSelectedRow();
			String value = (table.getModel().getValueAt(row, 0).toString());
			Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
			CallableStatement cStmt = null;
			try {
				cStmt = conn.prepareCall("{call "+procedureName+"(?)}");
				cStmt.setString(1, value);
				cStmt.executeUpdate();
				cStmt.close();
			}catch(Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.setRowCount(0);
			JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công!");
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu!", "Lỗi xóa",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(120, 15, 1350, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hệ Thống Quản Lý");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 35));
		lblNewLabel.setBounds(20, 10, 308, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCustomer = new JButton("Khách hàng");
		btnCustomer.setBackground(Color.LIGHT_GRAY);
		btnCustomer.setForeground(new Color(0, 0, 0));
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 1;
				String name = nameCustomerField.getText();
				String phone = phoneCustomerField.getText();
				if(name.equals("") && phone.equals("")) {
					SQLCustomer("SELECT * FROM khach_hang");
				}
				else if(!name.equals("") && phone.equals("")) {
					SQLCustomer("SELECT * FROM khach_hang where ten_kh = '"+name+"'");
				}
				else if(name.equals("") && !phone.equals("")) {
					SQLCustomer("SELECT * FROM khach_hang where sdt_kh = '"+phone+"'");
				}
				else{
					SQLCustomer("SELECT * FROM khach_hang where ten_kh = '"+name+"' and sdt_kh = '"+phone+"'");
				}
			}
		});
		btnCustomer.setBounds(144, 64, 114, 35);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnBus = new JButton("Xe khách");
		btnBus.setBackground(Color.LIGHT_GRAY);
		btnBus.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 3;
				String plate = plateBusField.getText();
				String name = nameBusField.getText();
				if(plate.equals("") && name.equals("")) {
					SQLBus("SELECT * FROM xe_khach");
				}
				else if(!plate.equals("") && name.equals("")) {
					SQLBus("SELECT * FROM xe_khach where bs_xk = '"+plate+"'");
				}
				else if(plate.equals("") && !name.equals("")) {
					SQLBus("SELECT * FROM xe_khach where ten_xk = '"+name+"'");
				}
				else{
					SQLBus("SELECT * FROM xe_khach where bs_xk = '"+plate+"' and ten_xk = '"+name+"'");
				}
			}
		});
		btnBus.setBounds(144, 318, 114, 35);
		frame.getContentPane().add(btnBus);
		
		JButton btnSchedule = new JButton("Lịch trình");
		btnSchedule.setBackground(Color.LIGHT_GRAY);
		btnSchedule.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 4;
				String address = addressField.getText();
				String destination = destinationField.getText();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = null;
				if((!address.equals("") && destination.equals("")) || (address.equals("") && !destination.equals(""))){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ đầu và địa chỉ đến!","Lỗi đăng nhập",JOptionPane.ERROR_MESSAGE);
				}
				if(dateScheduleField.getDate() == null && address.equals("") && destination.equals("")) {
					SQLSchedule("SELECT lt.id_lt,id_xk,id_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt");
				}
				if(dateScheduleField.getDate() == null && !address.equals("") && !destination.equals("")) {
					SQLSchedule("SELECT lt.id_lt,id_xk,id_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt where dcd_lt = '"+address+"' and dcc_lt = '"+destination+"'");
				}
				if(dateScheduleField.getDate() != null && address.equals("") && destination.equals("")) {
					try {
						date = dateFormat.format(dateScheduleField.getDate());
						SQLSchedule("SELECT lt.id_lt,id_xk,id_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt where ngaykh_lt = '"+date+"'");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày khởi hành!","Lỗi lịch trình",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex.getMessage());
					}
				}
				if(dateScheduleField.getDate() != null && !address.equals("") && !destination.equals("")) {
					try {
						date = dateFormat.format(dateScheduleField.getDate());
						SQLSchedule("SELECT lt.id_lt,id_xk,id_tx, cnlt.cn_lt, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, tgkh_lt,tgd_lt, gia_lt FROM lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt where ngaykh_lt = '"+date+"' and dcd_lt = '"+address+"' and dcc_lt = '"+destination+"'");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày khởi hành!","Lỗi lịch trình",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		btnSchedule.setBounds(144, 446, 114, 35);
		frame.getContentPane().add(btnSchedule);
		
		JButton btnBooking = new JButton("Đặt vé");
		btnBooking.setBackground(Color.LIGHT_GRAY);
		btnBooking.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 5;
				String name = nameBookingField.getText();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = null;
				if(name.equals("") && dateBookingField.getDate() == null) {
					SQLBooking("SELECT * FROM dat_ve");
				}
				if(!name.equals("") && dateBookingField.getDate() == null) {
					//No id, need convert name to id
					int id = NameToID(name);
					SQLBooking("SELECT * FROM dat_ve where id_kh = '"+id+"'");
				}
				if(name.equals("") && dateBookingField.getDate() != null) {
					try {
						date = dateFormat.format(dateBookingField.getDate());
						SQLBooking("SELECT * FROM dat_ve where ngay_dv = '"+date+"'");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đặt vé!","Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex.getMessage());
					}
				}
				if(!name.equals("") && dateBookingField.getDate() != null) {
					try {
						date = dateFormat.format(dateBookingField.getDate());
						int id = NameToID(name);
						SQLBooking("SELECT * FROM dat_ve where id_kh = '"+id+"' and ngay_dv = '"+date+"'");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đặt vé!","Lỗi đặt vé",JOptionPane.ERROR_MESSAGE);
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		btnBooking.setBounds(144, 626, 114, 36);
		frame.getContentPane().add(btnBooking);
		
		JButton btnDriver = new JButton("Tài xế");
		btnDriver.setBackground(Color.LIGHT_GRAY);
		btnDriver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 2;
				String name = nameDriverField.getText();
				String phone = phoneDriverField.getText();
				if(name.equals("") && phone.equals("")) {
					SQLDriver("SELECT * FROM tai_xe");
				}
				if(!name.equals("") && phone.equals("")) {
					SQLDriver("SELECT * FROM tai_xe where ten_tx = '"+name+"'");
				}
				if(name.equals("") && !phone.equals("")) {
					SQLDriver("SELECT * FROM tai_xe where sdt_tx = '"+phone+"'");
				}
				else {
					SQLDriver("SELECT * FROM tai_xe where ten_tx = '"+name+"' and sdt_tx = '"+phone+"'");
				}
			}
		});
		btnDriver.setBounds(144, 190, 114, 35);
		frame.getContentPane().add(btnDriver);
		
		JButton btnPayment = new JButton("Thanh toán");
		btnPayment.setBackground(Color.LIGHT_GRAY);
		btnPayment.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 6;
				String name = namePaymentField.getText();
				String discount = discountPaymentField.getText();
				if(name.equals("") && discount.equals("")) {
					SQLPayment("SELECT * FROM thanh_toan");
				}
				if(name.equals("") && !discount.equals("")) {
					SQLPayment("SELECT * FROM thanh_toan where giamgia_tt = '"+discount+"'");
				}
				if(!name.equals("") && discount.equals("")) {
					int id = NameToPayment(name);
					SQLPayment("SELECT * FROM thanh_toan where id_dv = '"+id+"'");
				}
				if(!name.equals("") && !discount.equals("")) {
					int id = NameToPayment(name);
					SQLPayment("SELECT * FROM thanh_toan where giamgia_tt = '"+discount+"' and id_dv = '"+id+"'");
				}
			}
		});
		btnPayment.setBounds(482, 626, 114, 36);
		frame.getContentPane().add(btnPayment);
		
		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 109, 135, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Số điện thoại:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 151, 114, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tên tài xế:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 234, 114, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Số điện thoại:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 280, 114, 28);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Biển số:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 363, 85, 28);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tên xe:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 411, 85, 29);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày khởi hành:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7.setBounds(10, 491, 135, 28);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Địa chỉ đầu:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(10, 536, 114, 30);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Địa chỉ đến:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(10, 588, 114, 28);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Tên khách hàng:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_10.setBounds(10, 672, 135, 28);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Ngày đặt vé:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(10, 722, 114, 28);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Tên khách hàng:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_12.setBounds(348, 672, 135, 28);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Giảm giá:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_13.setBounds(348, 722, 85, 25);
		frame.getContentPane().add(lblNewLabel_13);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 10, 978, 606);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setBackground(new Color(255, 99, 71));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(key) {
					case 0:
						JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng!","Lỗi thêm",JOptionPane.ERROR_MESSAGE);
						break;
					case 1:
						CustomerReg.main(null);
						break;
					case 2:
						AddDriver.main(null);
						break;
					case 3:
						AddBus.main(null);
						break;
					case 4:
						AddSchedule.main(null);
						break;
				}
			}
		});
		btnAdd.setBounds(808, 626, 93, 47);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setBackground(new Color(255, 99, 71));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(key == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng!","Lỗi xóa",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý xóa dữ liệu hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					switch(key) {
						case 1:
							Delete("xoa_kh");
							break;
						case 2:
							Delete("xoa_tx");
							break;
						case 3:
							Delete("xoa_xk");
							break;
						case 4:
							Delete("xoa_lt");
							break;
						case 5:
							Delete("xoa_dv");
							break;
						case 6:
							Delete("xoa_tt");
							break;
					}
				}
			}
		});
		btnDelete.setBounds(1208, 687, 85, 42);
		frame.getContentPane().add(btnDelete);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setBackground(new Color(255, 99, 71));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(key == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn bảng!","Lỗi sửa",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showInternalConfirmDialog(null, "Bạn có đồng ý thay đổi dữ liệu hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					if(key == 1) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String name = model.getValueAt(r, 1).toString();
								String sdt = model.getValueAt(r, 2).toString();
								String email = model.getValueAt(r, 3).toString();
								String username = model.getValueAt(r, 4).toString();
								String password = model.getValueAt(r, 5).toString();
								String sql = "UPDATE khach_hang set ten_kh = '"+name+"', sdt_kh = '"+sdt+"', email_kh = '"+email+"', username_kh = '"+username+"', password_kh = '"+password+"' where id_kh = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLCustomer("SELECT * FROM khach_hang");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(key == 2) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String name = model.getValueAt(r, 1).toString();
								String sdt = model.getValueAt(r, 2).toString();
								String address = model.getValueAt(r, 3).toString();
								String sql = "UPDATE tai_xe set ten_tx = '"+name+"', sdt_tx = '"+sdt+"', dchi_tx = '"+address+"' where id_tx = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLDriver("SELECT * FROM tai_xe");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(key == 3) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String name = model.getValueAt(r, 1).toString();
								String plate = model.getValueAt(r, 2).toString();
								String seat = model.getValueAt(r, 3).toString();
								String bed = model.getValueAt(r, 4).toString();
								if (bed.equals("Có")) bed = "1";
								else bed = "0";
								String tivi = model.getValueAt(r, 5).toString();
								if (tivi.equals("Có")) tivi = "1";
								else tivi = "0";
								String wifi = model.getValueAt(r, 6).toString();
								if (wifi.equals("Có")) wifi = "1";
								else wifi = "0";
								String sql = "UPDATE xe_khach set ten_xk = '"+name+"', bs_xk = '"+plate+"', cn_xk = '"+seat+"', giuong_xk = '"+bed+"', tivi_xk = '"+tivi+"', wifi_xk = '"+wifi+"' where id_xk = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLBus("SELECT * FROM xe_khach");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(key == 4) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String xk = model.getValueAt(r, 1).toString();
								String tx = model.getValueAt(r, 2).toString();
								String seat = model.getValueAt(r, 3).toString();
								String address = model.getValueAt(r, 4).toString();
								String destination = model.getValueAt(r, 5).toString();
								String date = model.getValueAt(r, 6).toString();
								String dateArrive = model.getValueAt(r, 7).toString();
								String time = model.getValueAt(r, 8).toString();
								String timeArrive = model.getValueAt(r, 9).toString();
								String price = model.getValueAt(r, 10).toString();
								String sql = "UPDATE lich_trinh lt join chongoi_lichtrinh cnlt on lt.id_lt = cnlt.id_lt set lt.id_xk = '"+xk+"', lt.id_tx = '"+tx+"', cnlt.cn_lt = '"+seat+"', lt.dcd_lt = '"+address+"', lt.dcc_lt = '"+destination+"', lt.ngaykh_lt = '"+date+"', lt.ngayd_lt = '"+dateArrive+"', lt.tgkh_lt = '"+time+"', lt.tgd_lt = '"+timeArrive+"', lt.gia_lt = '"+price+"' where lt.id_lt = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLSchedule("SELECT * FROM lich_trinh");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(key == 5) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String lt = model.getValueAt(r, 1).toString();
								String kh = model.getValueAt(r, 2).toString();
								String seat = model.getValueAt(r, 3).toString();
								String date = model.getValueAt(r, 4).toString();
								String sql = "UPDATE dat_ve set id_lt = '"+lt+"', id_kh = '"+kh+"', cn_dv = '"+seat+"', ngay_dv = '"+date+"' where id_dv = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLBooking("SELECT * FROM dat_ve");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(key == 6) {
						try {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							int rows = model.getRowCount();
							for(int r = 0; r < rows; r++) {
								String id = model.getValueAt(r, 0).toString();
								String dv = model.getValueAt(r, 1).toString();
								String discount = model.getValueAt(r, 2).toString();
								String date = model.getValueAt(r, 3).toString();
								String price = model.getValueAt(r, 4).toString();
								String sql = "UPDATE thanh_toan set id_dv = '"+dv+"', giamgia_tt = '"+discount+"', ngay_tt = '"+date+"', tht_tt = '"+price+"' where id_tt = '"+id+"'";
								Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
								PreparedStatement pStmt = conn.prepareStatement(sql);
								pStmt.execute();
							}
							table.setModel(new DefaultTableModel());
							model.setRowCount(0);
							//SQLPayment("SELECT * FROM thanh_toan");
							JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công!","Sửa",JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnEdit.setBounds(1094, 687, 85, 42);
		frame.getContentPane().add(btnEdit);
		
		nameCustomerField = new JTextField();
		nameCustomerField.setBounds(144, 109, 195, 28);
		frame.getContentPane().add(nameCustomerField);
		nameCustomerField.setColumns(10);
		
		phoneCustomerField = new JTextField();
		phoneCustomerField.setBounds(144, 152, 194, 28);
		frame.getContentPane().add(phoneCustomerField);
		phoneCustomerField.setColumns(10);
		
		nameDriverField = new JTextField();
		nameDriverField.setBounds(144, 234, 195, 28);
		frame.getContentPane().add(nameDriverField);
		nameDriverField.setColumns(10);
		
		phoneDriverField = new JTextField();
		phoneDriverField.setBounds(144, 280, 195, 28);
		frame.getContentPane().add(phoneDriverField);
		phoneDriverField.setColumns(10);
		
		plateBusField = new JTextField();
		plateBusField.setBounds(144, 363, 194, 28);
		frame.getContentPane().add(plateBusField);
		plateBusField.setColumns(10);
		
		nameBusField = new JTextField();
		nameBusField.setBounds(144, 412, 195, 28);
		frame.getContentPane().add(nameBusField);
		nameBusField.setColumns(10);
		
		dateScheduleField = new JDateChooser();
		dateScheduleField.setBounds(144, 491, 194, 28);
		frame.getContentPane().add(dateScheduleField);
		
		namePaymentField = new JTextField();
		namePaymentField.setBounds(482, 672, 195, 28);
		frame.getContentPane().add(namePaymentField);
		namePaymentField.setColumns(10);
		
		discountPaymentField = new JTextField();
		discountPaymentField.setBounds(482, 722, 195, 26);
		frame.getContentPane().add(discountPaymentField);
		discountPaymentField.setColumns(10);
		
		JButton btnNewButton = new JButton("Dọn bảng");
		btnNewButton.setBackground(new Color(220, 220, 220));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				key = 0;
			}
		});
		btnNewButton.setBounds(745, 686, 114, 45);
		frame.getContentPane().add(btnNewButton);
		
		addressField = new JTextField();
		addressField.setBounds(144, 538, 195, 28);
		frame.getContentPane().add(addressField);
		addressField.setColumns(10);
		
		destinationField = new JTextField();
		destinationField.setBounds(144, 587, 195, 28);
		frame.getContentPane().add(destinationField);
		destinationField.setColumns(10);
		
		JButton btnReset = new JButton("Đặt lại");
		btnReset.setBackground(new Color(220, 220, 220));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameCustomerField.setText(null);
				phoneCustomerField.setText(null);
				nameDriverField.setText(null);
				phoneDriverField.setText(null);
				plateBusField.setText(null);
				nameBusField.setText(null);
				nameBookingField.setText(null);
				dateBookingField.setDate(null);
				namePaymentField.setText(null);
				discountPaymentField.setText(null);
				addressField.setText(null);
				destinationField.setText(null);
				dateScheduleField.setDate(null);
			}
		});
		btnReset.setBounds(705, 626, 93, 51);
		frame.getContentPane().add(btnReset);
		
		dateBookingField = new JDateChooser();
		dateBookingField.setBounds(144, 722, 195, 28);
		frame.getContentPane().add(dateBookingField);
		
		nameBookingField = new JTextField();
		nameBookingField.setBounds(144, 671, 195, 28);
		frame.getContentPane().add(nameBookingField);
		nameBookingField.setColumns(10);
		
		String notice = "- Khách hàng\n- Tài xế\n- Xe khách\n- Lịch trình";
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 228, 225));
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textArea.append(notice);
		textArea.setBounds(911, 626, 135, 121);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("Tra cứu doanh thu");
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfitDetails.main(null);
			}
		});
		btnNewButton_1.setBounds(1094, 622, 199, 44);
		frame.getContentPane().add(btnNewButton_1);
		
		ImageIcon khachhang = new ImageIcon("Images/khachhang.png");
		khachhang.setImage(khachhang.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JLabel customerIcon = new JLabel();
		customerIcon.setIcon(khachhang);
		customerIcon.setBounds(41, 64, 54, 44);
		frame.getContentPane().add(customerIcon);
		
		ImageIcon driver = new ImageIcon("Images/driver.png");
		driver.setImage(driver.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JLabel driverIcon = new JLabel();
		driverIcon.setIcon(driver);
		driverIcon.setBounds(41, 190, 54, 44);
		frame.getContentPane().add(driverIcon);
		
		ImageIcon bus = new ImageIcon("Images/xekhach.png");
		bus.setImage(bus.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JLabel busIcon = new JLabel();
		busIcon.setIcon(bus);
		busIcon.setBounds(41, 318, 45, 44);
		frame.getContentPane().add(busIcon);
		
		ImageIcon schedule = new ImageIcon("Images/schedule.png");
		schedule.setImage(schedule.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		JLabel scheduleIcon = new JLabel();
		scheduleIcon.setIcon(schedule);
		scheduleIcon.setBounds(20, 446, 66, 47);
		frame.getContentPane().add(scheduleIcon);
		
		ImageIcon ticket = new ImageIcon("Images/ticket.png");
		ticket.setImage(ticket.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		JLabel bookingIcon = new JLabel();
		bookingIcon.setIcon(ticket);
		bookingIcon.setBounds(41, 627, 66, 46);
		frame.getContentPane().add(bookingIcon);
		
		ImageIcon payment = new ImageIcon("Images/payment.png");
		payment.setImage(payment.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JLabel paymentIcon = new JLabel();
		paymentIcon.setIcon(payment);
		paymentIcon.setBounds(388, 627, 45, 44);
		frame.getContentPane().add(paymentIcon);
		
		ImageIcon backgroundIcon = new ImageIcon("Images/login.jpg");
		backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(1500, 800, Image.SCALE_SMOOTH));
		JLabel background = new JLabel();
		background.setIcon(backgroundIcon);
		background.setBounds(0, 0, 1336, 763);
		frame.getContentPane().add(background);
		
	}
}
