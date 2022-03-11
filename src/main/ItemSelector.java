package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemSelector extends JFrame {
	
	DecimalFormat priceformatter = new DecimalFormat("#0.00");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField deletenumberfield;
	DefaultTableModel listitemmodel;
	private String orderid;
	JLabel totalpricedisplay;
	

	/**
	 * Create the frame.
	 */

	public ItemSelector(final String orderid) throws IOException {
		this.orderid = orderid;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ItemSelector.class.getResource("/main/logo/logo.png")));
		setTitle("ICE CREAM SHOP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 783, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);

		JScrollPane scrollPane = new JScrollPane();

		// READ ITEM FROM FILE
		BufferedReader itemlistinput = null;
		List<String> itemlist = new ArrayList<String>();
		List<String> itemlistname = new ArrayList<String>();
		List<Double> priceperitem = new ArrayList<Double>();
		
		try {
			itemlistinput = new BufferedReader(new FileReader("items.txt"));
			String bakeryitemline = null;
			itemlist.add("Select Item");
			while ((bakeryitemline = itemlistinput.readLine()) != null) {
				String[] listitemcomma = bakeryitemline.split(",");
				itemlist.add(listitemcomma[0] + " RM"+ listitemcomma[1]);
				itemlistname.add(listitemcomma[0]);
				priceperitem.add(Double.parseDouble(listitemcomma[1]));
			}
		}
		catch (FileNotFoundException e) {
			// DISABLE WHEN IN WINDOWBUILDER EDITING
			// JOptionPane.showMessageDialog(null, "Error: File bakery.txt not found. Go to
			// jar file location and create bakery.txt");
			System.err.println("Error, file didn't exist.");
			// System.exit(0);
		} finally {
			itemlistinput.close();
		}

		String[] itemlistArray = itemlist.toArray(new String[] {});
		String[] itemlistnameArray = itemlistname.toArray(new String[] {});
		Double[] priceperitemArray = priceperitem.toArray(new Double[] {});
		
		JComboBox itemcombobox = new JComboBox(itemlistArray);
		itemcombobox.setFont(new Font("Malgun Gothic", Font.PLAIN, 19));

		JSpinner quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, null, 1));
		

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Lucida Bright", Font.PLAIN, 10));
		btnNewButton.setForeground(new Color(139, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			int lastitemnumber = 1;
			@Override
			public void mouseClicked(MouseEvent e) {
				//ADD ITEMS TO LIST ORDERS FOR CUSTOMER
				int selecteditem = 0;
				int quantityno;
				double totalitemsprice = 0;
				
				selecteditem = itemcombobox.getSelectedIndex();
				
				try {
					if(selecteditem != 0) {
						if(table.getRowCount() > 0) {				
							lastitemnumber = (int) table.getModel().getValueAt(table.getRowCount() - 1, 0) + 1;
						}else {
							lastitemnumber = 1;
						}
						
						//CALCULATE PRICE FOR SELECTED ITEM AND QUANTITY
						quantityno = (Integer) quantity.getValue();
						totalitemsprice = priceperitemArray[selecteditem - 1] * quantityno;
						Main.getitems().add(new Itemsclass(orderid, lastitemnumber, String.valueOf(itemlistnameArray[selecteditem - 1]), (Integer)quantity.getValue(), totalitemsprice));
						quantity.setValue(1);
						showdata();
					}else {
						JOptionPane.showMessageDialog(null, "Please select item", "No item selected", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception error) {
					System.out.println("Error: " + error);
				}
				calctotalprice();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Items");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnNewButton_1.setForeground(new Color(139, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		deletenumberfield = new JTextField();
		deletenumberfield.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Item Number");
		lblNewLabel_3.setForeground(new Color(139, 0, 0));
		lblNewLabel_3.setFont(new Font("Lucida Bright", Font.BOLD, 14));
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setForeground(new Color(139, 0, 0));
		btnNewButton_2.setFont(new Font("Lucida Bright", Font.BOLD, 10));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//DELETE ITEMS HERE
				int deletenumber;
				try {
					deletenumber = Integer.parseInt(deletenumberfield.getText());
					Predicate<Itemsclass> condition2 = p->p.getitemnumber()==deletenumber && p.orderid == orderid;
					Main.getitems().removeIf(condition2);
					calctotalprice();
					showdata();
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter a valid item number", "Invalid Item Number", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		totalpricedisplay = new JLabel("Total Price: RM 0");
		totalpricedisplay.setForeground(new Color(139, 0, 0));
		totalpricedisplay.setFont(new Font("Lucida Bright", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(608)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
							.addGap(19))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(itemcombobox, Alignment.LEADING, 0, 149, Short.MAX_VALUE)
								.addComponent(deletenumberfield, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_2)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
							.addGap(71)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(18)
									.addComponent(quantity, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
									.addGap(114))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(totalpricedisplay)
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(itemcombobox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1)
								.addComponent(quantity, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(totalpricedisplay))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(deletenumberfield, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		table = new JTable();
		listitemmodel = new DefaultTableModel(
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

		JLabel lblNewLabel_2 = new JLabel("Items for order ID: " + orderid);

		lblNewLabel_2.setIcon(new ImageIcon(ItemSelector.class.getResource("/main/logo/contract.png")));
		lblNewLabel_2.setForeground(new Color(139, 0, 0));
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(146, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
					.addGap(145))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_2)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		showdata();
	}
	
	private void calctotalprice() {
		double listpricecust = 0;
		for(int i = 0; i < Main.getitems().size(); i++) {
			if(String.valueOf(Main.getitems().get(i).getorderid()).equals(orderid)) {				
				listpricecust = listpricecust + Main.getitems().get(i).gettotalitems();
			}
		}
		totalpricedisplay.setText("Total Price: RM " + priceformatter.format(listpricecust));
		NewOrder.calctotalprice(listpricecust);
	}
	
	private void showdata() {
		//ADD DATA HERE
		listitemmodel.setRowCount(0);
		for(int i = 0; i < Main.getitems().size(); i++) {
			if(String.valueOf(Main.getitems().get(i).getorderid()).equals(orderid)) {				
				listitemmodel.addRow(new Object[]{Main.getitems().get(i).getitemnumber(), Main.getitems().get(i).getitemname(), Main.getitems().get(i).getquantity(), "RM " + priceformatter.format(Main.getitems().get(i).gettotalitems())});
			}
		}
	}
}
