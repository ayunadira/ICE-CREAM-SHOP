package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Receipt extends JFrame {
	
	DecimalFormat priceformatter = new DecimalFormat("#0.00");

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public Receipt(String orderid) {
		//@SuppressWarnings("unlikely-arg-type")
		List<Customerclass> customerdata = Main.getcustomer().stream().filter(custdata -> custdata.getorderid().equals(orderid)).collect(Collectors.toList());
		List<Ordersclass> orderdata = Main.getorders().stream().filter(orderdat -> orderdat.getorderid().equals(orderid)).collect(Collectors.toList());
		List<Paymentclass> paymentdata = Main.getpayment().stream().filter(paymentdat -> paymentdat.getorderid().equals(orderid)).collect(Collectors.toList());
		List<Itemsclass> itemsdata = Main.getitems().stream().filter(itemdat -> itemdat.getorderid().equals(orderid)).collect(Collectors.toList());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Receipt.class.getResource("/main/logo/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 795);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		setContentPane(contentPane);
		setTitle("ICE CREAM SHOP");
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(139, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Customer Details");
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Century", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_4_2 = new JLabel("Order Details");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setForeground(Color.BLACK);
		lblNewLabel_4_2.setFont(new Font("Century", Font.PLAIN, 18));
		lblNewLabel_4_2.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_4_1 = new JLabel("Total: RM" + priceformatter.format(paymentdata.get(0).gettotalprice()));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setForeground(Color.BLACK);
		lblNewLabel_4_1.setFont(new Font("Century", Font.BOLD, 18));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		
		JLabel custpaiddisplay = new JLabel("Customer paid: RM" + priceformatter.format(paymentdata.get(0).getcustpay()));
		custpaiddisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		custpaiddisplay.setFont(new Font("Century", Font.PLAIN, 14));
		
		JLabel balancedisp = new JLabel("Balance: RM" + priceformatter.format(paymentdata.get(0).getcustpay() - paymentdata.get(0).gettotalprice()));
		balancedisp.setHorizontalAlignment(SwingConstants.RIGHT);
		balancedisp.setFont(new Font("Century", Font.PLAIN, 14));
		
		String addressline = "<html>" + customerdata.get(0).getaddress().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4)
					.addGap(626)
					.addComponent(lblNewLabel_4_2, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(334, Short.MAX_VALUE)
					.addComponent(balancedisp, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(724, Short.MAX_VALUE)
					.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(706, Short.MAX_VALUE)
					.addComponent(custpaiddisplay, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_4_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(custpaiddisplay)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(balancedisp, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Payment Type:");
		lblNewLabel_1_2_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_2 = new JLabel("Order Date:");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel orderdatedisplay = new JLabel(orderdata.get(0).getdate() + " " + orderdata.get(0).getordertime());
		orderdatedisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		orderdatedisplay.setForeground(Color.BLACK);
		orderdatedisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Order ID:");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel orderiddisplay = new JLabel(orderid);
		orderiddisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		orderiddisplay.setForeground(Color.BLACK);
		orderiddisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JLabel paymenttypedisplay = new JLabel(paymentdata.get(0).getpaymenttype());
		paymenttypedisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		paymenttypedisplay.setForeground(Color.BLACK);
		paymenttypedisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_2_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
							.addComponent(paymenttypedisplay, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel_1_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(orderdatedisplay, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(orderiddisplay, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(orderdatedisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2_1)
						.addComponent(orderiddisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(paymenttypedisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2_2))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Address:");
		lblNewLabel_2_1.setBounds(10, 94, 77, 19);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		JLabel addressdisplay = new JLabel(addressline);
		addressdisplay.setBounds(93, 94, 455, 70);
		addressdisplay.setVerticalAlignment(SwingConstants.TOP);
		addressdisplay.setForeground(Color.BLACK);
		addressdisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2_2 = new JLabel("Gender:");
		lblNewLabel_2_2.setBounds(10, 66, 108, 21);
		lblNewLabel_2_2.setForeground(Color.BLACK);
		lblNewLabel_2_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel lblGendeDisp = new JLabel(customerdata.get(0).getgender());
		lblGendeDisp.setBounds(93, 63, 207, 21);
		lblGendeDisp.setForeground(Color.BLACK);
		lblGendeDisp.setFont(new Font("Courier New", Font.PLAIN, 14));
		
				JLabel lblNewLabel_2 = new JLabel("Phone Number:");
				lblNewLabel_2.setBounds(10, 39, 139, 21);
				lblNewLabel_2.setForeground(Color.BLACK);
				lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel phonenodisplay = new JLabel(customerdata.get(0).getphoneno());
		phonenodisplay.setBounds(153, 39, 207, 19);
		phonenodisplay.setForeground(Color.BLACK);
		phonenodisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		
				JLabel lblNewLabel_1 = new JLabel("Name:");
				lblNewLabel_1.setBounds(10, 12, 60, 21);
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		JLabel namedisplay = new JLabel(customerdata.get(0).getname());
		namedisplay.setBounds(68, 14, 333, 19);
		namedisplay.setForeground(Color.BLACK);
		namedisplay.setFont(new Font("Courier New", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Regular customer:");
		lblNewLabel_2_2_1.setBounds(10, 174, 174, 21);
		lblNewLabel_2_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_2_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		
		String regularcuststate = "No";
		if(customerdata.get(0).getregularcustomer()) {
			regularcuststate = "Yes";
		}
		
		JLabel displayregularcust = new JLabel(regularcuststate);
		displayregularcust.setBounds(194, 173, 207, 21);
		displayregularcust.setForeground(Color.BLACK);
		displayregularcust.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel_2_1);
		panel_1.add(addressdisplay);
		panel_1.add(lblNewLabel_2_2);
		panel_1.add(lblGendeDisp);
		panel_1.add(lblNewLabel_2);
		panel_1.add(phonenodisplay);
		panel_1.add(lblNewLabel_1);
		panel_1.add(namedisplay);
		panel_1.add(lblNewLabel_2_2_1);
		panel_1.add(displayregularcust);
		
		table = new JTable();
		DefaultTableModel listitemmodel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Item Number", "Item Name", "Quantity", "Price"
				}
			){
			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
			};
		table.setModel(listitemmodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(62);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Receipt for Order ID " + orderid);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Receipt.class.getResource("/main/logo/receiptframe.png")));
		lblNewLabel.setFont(new Font("Century", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(276, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
					.addGap(191))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		listitemmodel.setRowCount(0);
		for(int i = 0; i < itemsdata.size(); i++) {	
				listitemmodel.addRow(new Object[]{itemsdata.get(i).getitemnumber(), itemsdata.get(i).getitemname(), itemsdata.get(i).getquantity(), "RM " + priceformatter.format(itemsdata.get(i).gettotalitems())});
		}
	}
}
