package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderHistory {

	private JFrame frame;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderHistory window = new OrderHistory();
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
	public OrderHistory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1068, 359);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lịch sử thanh toán");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 21, 221, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 1034, 256);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		LoginAsCustomer lc = new LoginAsCustomer();
		table.setModel(new DefaultTableModel());
		
		btnNewButton = new JButton("Trở về");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(293, 21, 85, 21);
		frame.getContentPane().add(btnNewButton);
		DefaultTableModel model = null;
		ResultSetMetaData rsmd = null;
		int cols = 0;
		String[] colName;
		String[] row;
		String id_dv, ngay_dv, ten_xk, cn_dv, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, gia_lt, giamgia_tt, tht_tt;
		Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = conn.prepareStatement("select dv.id_dv, dv.ngay_dv, xk.ten_xk, dv.cn_dv, lt.dcd_lt, lt.dcc_lt, lt.ngaykh_lt, lt.ngayd_lt, lt.gia_lt, tt.giamgia_tt, tt.tht_tt\r\n"
					+ "from lich_trinh lt join dat_ve dv on lt.id_lt = dv.id_lt\r\n"
					+ "				   	  join xe_khach xk on xk.id_xk = lt.id_xk\r\n"
					+ "                   join thanh_toan tt on tt.id_dv = dv.id_dv\r\n"
					+ "where dv.id_kh = ?");
			pStmt.setInt(1, lc.getID());
			rs = pStmt.executeQuery();
			rsmd = rs.getMetaData();
			model = (DefaultTableModel) table.getModel();
			cols = rsmd.getColumnCount();
			colName = new String[cols];
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
				id_dv = rs.getString(1);
				ngay_dv = rs.getString(2);
				ten_xk = rs.getString(3);
				cn_dv = rs.getString(4);
				dcd_lt = rs.getString(5);
				dcc_lt = rs.getString(6);
				ngaykh_lt = rs.getString(7);
				ngayd_lt = rs.getString(8);
				gia_lt = rs.getString(9);
				giamgia_tt = rs.getString(10);
				tht_tt = rs.getString(11);
				row = new String[]{id_dv, ngay_dv, ten_xk, cn_dv, dcd_lt, dcc_lt, ngaykh_lt, ngayd_lt, gia_lt, giamgia_tt, tht_tt};
				model.addRow(row);
			}
			table.getColumnModel().getColumn(0).setHeaderValue("ID đặt vé");
			table.getColumnModel().getColumn(1).setHeaderValue("Ngày đặt vé");
			table.getColumnModel().getColumn(2).setHeaderValue("Tên xe khách");
			table.getColumnModel().getColumn(3).setHeaderValue("Chỗ ngồi");
			table.getColumnModel().getColumn(4).setHeaderValue("Điểm đầu");
			table.getColumnModel().getColumn(5).setHeaderValue("Điểm đến");
			table.getColumnModel().getColumn(6).setHeaderValue("Ngày khởi hành");
			table.getColumnModel().getColumn(7).setHeaderValue("Ngày đến");
			table.getColumnModel().getColumn(8).setHeaderValue("Giá lịch trình");
			table.getColumnModel().getColumn(9).setHeaderValue("Giảm giá");
			table.getColumnModel().getColumn(10).setHeaderValue("Thành tiền");
		}catch(Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		
	}
}
