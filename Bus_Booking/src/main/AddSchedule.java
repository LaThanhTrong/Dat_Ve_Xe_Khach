package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class AddSchedule {

	private JFrame frame;
	private JTextField idBusField;
	private JTextField idDriverField;
	private JTextField startField;
	private JTextField endField;
	private JTextField priceField;
	private JSpinner timeField;
	private JSpinner timeArriveField;
	private JDateChooser dateField;
	private JDateChooser dateArriveField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSchedule window = new AddSchedule();
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
	public AddSchedule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 437, 530);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lịch trình");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(137, 0, 138, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID Xe khách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 45, 117, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID Tài xế");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 86, 92, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Điểm đầu");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 130, 92, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm đến");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 175, 92, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ngày khởi hành");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 226, 138, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ngày đến");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 271, 81, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Thời gian khởi hành");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7.setBounds(10, 317, 170, 25);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Thời gian đến");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(10, 366, 117, 25);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Giá");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(10, 414, 64, 25);
		frame.getContentPane().add(lblNewLabel_9);
		
		idBusField = new JTextField();
		idBusField.setBounds(171, 45, 241, 28);
		frame.getContentPane().add(idBusField);
		idBusField.setColumns(10);
		
		idDriverField = new JTextField();
		idDriverField.setBounds(171, 83, 241, 28);
		frame.getContentPane().add(idDriverField);
		idDriverField.setColumns(10);
		
		startField = new JTextField();
		startField.setBounds(171, 130, 241, 25);
		frame.getContentPane().add(startField);
		startField.setColumns(10);
		
		endField = new JTextField();
		endField.setBounds(171, 175, 241, 25);
		frame.getContentPane().add(endField);
		endField.setColumns(10);
		
		dateField = new JDateChooser();
		dateField.setBounds(171, 226, 138, 25);
		frame.getContentPane().add(dateField);
		
		dateArriveField = new JDateChooser();
		dateArriveField.setBounds(171, 271, 138, 25);
		frame.getContentPane().add(dateArriveField);
		
		JSpinner spinner = new JSpinner();
		Date d = new Date(0);
		SpinnerDateModel sm = new SpinnerDateModel(d, null, null, Calendar.HOUR_OF_DAY);
		timeField = new javax.swing.JSpinner(sm);
		JSpinner.DateEditor de_timeField = new JSpinner.DateEditor(timeField, "HH:mm:ss");
		timeField.setEditor(de_timeField);
		timeField.setBounds(171, 319, 138, 25);
		frame.getContentPane().add(timeField);
		
		JSpinner spinner_1 = new JSpinner();
		Date d1 = new Date(0);
		SpinnerDateModel sm1 = new SpinnerDateModel(d1, null, null, Calendar.HOUR_OF_DAY);
		timeArriveField = new javax.swing.JSpinner(sm1);
		JSpinner.DateEditor de_timeArriveField = new JSpinner.DateEditor(timeArriveField, "HH:mm:ss");
		timeArriveField.setEditor(de_timeArriveField);
		timeArriveField.setBounds(171, 367, 138, 27);
		frame.getContentPane().add(timeArriveField);
		
		priceField = new JTextField();
		priceField.setBounds(171, 414, 241, 25);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBackground(Color.LIGHT_GRAY);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idBusField.getText().equals("") || idDriverField.getText().equals("") || startField.getText().equals("") || endField.getText().equals("") || dateField.getDate() == null || dateArriveField.getDate() == null || priceField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
				}
				else if(JOptionPane.showConfirmDialog(null, "Bạn có đồng ý thêm lịch trình hay không?","Xác nhận",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE) == 0) {
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String date = null;
					String dateArrive = null;
					String time = new SimpleDateFormat("HH:mm:ss").format(timeField.getValue());
					String timeArrive = new SimpleDateFormat("HH:mm:ss").format(timeArriveField.getValue());
					date = dateFormat.format(dateField.getDate());
					dateArrive = dateFormat.format(dateArriveField.getDate());
					String sql = "INSERT INTO lich_trinh(id_xk,id_tx,dcd_lt,dcc_lt,ngaykh_lt,ngayd_lt,tgkh_lt,tgd_lt,gia_lt) values ('"+idBusField.getText()+"','"+idDriverField.getText()+"','"+startField.getText()+"','"+endField.getText()+"','"+date+"','"+dateArrive+"','"+time+"','"+timeArrive+"','"+priceField.getText()+"')";
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Thêm lịch trình thành công!","Tài xế",JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"SQLException: " + ex.getMessage(),"Lỗi thêm lịch trình!",JOptionPane.ERROR_MESSAGE);
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
		btnConfirm.setBounds(137, 449, 128, 34);
		frame.getContentPane().add(btnConfirm);
	}
}
