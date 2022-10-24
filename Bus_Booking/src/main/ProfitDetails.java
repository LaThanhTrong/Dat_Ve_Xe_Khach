package main;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.*;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ProfitDetails {
	private static String year = "";
	private JFrame frame;
	private JFreeChart barChart;
	private JTextField yearField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfitDetails window = new ProfitDetails();
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
	public ProfitDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1009, 580);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelChart = new JPanel();
		panelChart.setBounds(10, 41, 975, 492);
		frame.getContentPane().add(panelChart);
		panelChart.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Hiện thống kê năm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!yearField.getText().equals("")) {
					DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
					Connection conn = MySQLConnect.getConnect("dat_ve_xe_khach");
					PreparedStatement pStmt = null;
					ResultSet rs = null;
					boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false, check7 = false, check8 = false, check9 = false, check10 = false, check11 = false, check12 = false;
					try {
						pStmt = conn.prepareStatement("select month(dv.ngay_dv), sum(tt.tht_tt) from thanh_toan tt join dat_ve dv on tt.id_dv = dv.id_dv where year(dv.ngay_dv)='"+yearField.getText()+"' group by month(dv.ngay_dv)");
						rs = pStmt.executeQuery();
						while(rs.next()) {
							if(rs.getString(1).equals("1")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 1");
								check1 = true;
							}
							if(!rs.getString(1).equals("1") && check1 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 1");
							}
							if(rs.getString(1).equals("2")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 2");
								check2 = true;
							}
							if(!rs.getString(1).equals("2") && check2 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 2");
							}
							if(rs.getString(1).equals("3")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 3");
								check3 = true;
							}
							if(!rs.getString(1).equals("3") && check3 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 3");
							}
							if(rs.getString(1).equals("4")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 4");
								check4 = true;
							}		
							if(!rs.getString(1).equals("4") && check4 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 4");
							}
							if(rs.getString(1).equals("5")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 5");
								check5 = true;
							}
							if(!rs.getString(1).equals("5") && check5 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 5");
							}
							if(rs.getString(1).equals("6")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 6");
								check6 = true;
							}		
							if(!rs.getString(1).equals("6") && check6 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 6");
							}
							if(rs.getString(1).equals("7")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 7");
								check7 = true;
							}
							if(!rs.getString(1).equals("7") && check7 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 7");
							}
							if(rs.getString(1).equals("8")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 8");
								check8 = true;
							}
							if(!rs.getString(1).equals("8") && check8 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 8");
							}
							if(rs.getString(1).equals("9")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 9");
								check9 = true;
							}
							if(!rs.getString(1).equals("9") && check9 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 9");
							}
							if(rs.getString(1).equals("10")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 10");
								check10 = true;
							}
							if(!rs.getString(1).equals("10") && check10 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 10");
							}
							if(rs.getString(1).equals("11")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 11");
								check11 = true;
							}
							if(!rs.getString(1).equals("11") && check11 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 11");
							}
							if(rs.getString(1).equals("12")) {
								barChartData.addValue(rs.getInt(2), "Doanh Thu", "Tháng 12");
								check12 = true;
							}
							if(!rs.getString(1).equals("12") && check12 == false) {
								barChartData.addValue(0, "Doanh Thu", "Tháng 12");
							}
						}
					}catch(Exception ex) {
						System.out.println("SQLException: " + ex.getMessage());
					}
					/*barChartData.addValue(2000000, "Doanh Thu", "Tháng 1");
					barChartData.addValue(3000000, "Doanh Thu", "Tháng 2");
					barChartData.addValue(1000000, "Doanh Thu", "Tháng 3");
					barChartData.addValue(1500000, "Doanh Thu", "Tháng 4");
					barChartData.addValue(2500000, "Doanh Thu", "Tháng 5");
					barChartData.addValue(5000000, "Doanh Thu", "Tháng 6");
					barChartData.addValue(3200000, "Doanh Thu", "Tháng 7");
					barChartData.addValue(4000000, "Doanh Thu", "Tháng 8");
					barChartData.addValue(4300000, "Doanh Thu", "Tháng 9");
					barChartData.addValue(4100000, "Doanh Thu", "Tháng 10");
					barChartData.addValue(2200000, "Doanh Thu", "Tháng 11");
					barChartData.addValue(1900000, "Doanh Thu", "Tháng 12");*/
					
					year = yearField.getText();
					barChart = ChartFactory.createBarChart("Biểu đồ doanh thu theo tháng, năm "+year, "Tháng", "Doanh Thu", barChartData, PlotOrientation.VERTICAL, false, true, false );
					CategoryPlot barchrt = barChart.getCategoryPlot();
					barchrt.setRangeGridlinePaint(Color.ORANGE);
					
					ChartPanel barPanel = new ChartPanel(barChart);
					panelChart.removeAll();
					panelChart.add(barPanel,BorderLayout.CENTER);
					panelChart.validate();
				}
			}
		});
		btnNewButton.setBounds(10, 10, 152, 21);
		frame.getContentPane().add(btnNewButton);
		
		yearField = new JTextField();
		yearField.setBounds(166, 12, 96, 19);
		frame.getContentPane().add(yearField);
		yearField.setColumns(10);
	}
}
