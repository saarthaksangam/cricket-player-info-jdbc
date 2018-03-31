package playerdetails;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import playerdetails.PlayerDetails;
import playerdetails.PlayerDetailsDAO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PlayerSearchApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private PlayerDetailsDAO PlayerDetailsDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerSearchApp frame = new PlayerSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerSearchApp() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});

		// create the DAO
				try {
					PlayerDetailsDAO = new PlayerDetailsDAO();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
				setTitle("SRM Software Project - Player Search App");
				
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterName = new JLabel("Enter Name");
		panel.add(lblEnterName);
		
		TextField nameTextField = new TextField();
		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.isAltDown()&&(e.getKeyChar()=='n'||e.getKeyChar()=='N'))
				{
					PlayerDetailsDialog dialog = new PlayerDetailsDialog(PlayerSearchApp.this, PlayerDetailsDAO, null, false);

					// show dialog
					dialog.setVisible(true);
				}
			}
		});
		nameTextField.setColumns(20);
		panel.add(nameTextField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String name = nameTextField.getText();

					List<PlayerDetails> PlayerDetails = null;

					if (name != null && name.trim().length() > 0) {
						PlayerDetails = PlayerDetailsDAO.searchPlayerDetails(name);
					} else {
						PlayerDetails = PlayerDetailsDAO.getAllPlayerDetails();
					}
					
					// create the model and update the "table"
					PlayerDetailsTableModel model = new PlayerDetailsTableModel(PlayerDetails);
					
					table.setModel(model);
					
					
					/*for (PlayerDetails temp : PlayerDetails) {
						System.out.println(temp);
					}*/
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(PlayerSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Add Player Details");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// create dialog
				PlayerDetailsDialog dialog = new PlayerDetailsDialog(PlayerSearchApp.this, PlayerDetailsDAO, null, false);

				// show dialog
				dialog.setVisible(true);
		
				
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnUpdatePlayerDetails = new JButton("Update Player Details");
		btnUpdatePlayerDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(PlayerSearchApp.this, "You must select a player", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current players
				//PlayerDetails tempPlayerDetails = (PlayerDetails) table.getValueAt(row, PlayerDetailsTableModel.OBJECT_COL);
				PlayerDetails p = new PlayerDetails((String) table.getValueAt(row, PlayerDetailsTableModel.NAME_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.MATCHES_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.RUNS_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.WICKETS_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.CENTURIES_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.FIFTIES_COL));

				// create dialog
				PlayerDetailsDialog dialog = new PlayerDetailsDialog(PlayerSearchApp.this, PlayerDetailsDAO,p, true);

				// show dialog
				dialog.setVisible(true);
			
				
			}
		});
		panel_1.add(btnUpdatePlayerDetails);
		
		JButton btnDeletePlayerDetails = new JButton("Delete Player Details");
		btnDeletePlayerDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				try {
				// get the selected row
				int row = table.getSelectedRow();

				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(PlayerSearchApp.this, 
							"You must select a player", "Error", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				// prompt the user
				int response = JOptionPane.showConfirmDialog(
						PlayerSearchApp.this, "Delete this player?", "Confirm", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response != JOptionPane.YES_OPTION) {
					return;
				}

				// get the current employee
				PlayerDetails p = new PlayerDetails((String) table.getValueAt(row, PlayerDetailsTableModel.NAME_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.MATCHES_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.RUNS_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.WICKETS_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.CENTURIES_COL),(String) table.getValueAt(row, PlayerDetailsTableModel.FIFTIES_COL));

				// delete the employee
				PlayerDetailsDAO.deletePlayerDetails(p.getName());

				// refresh GUI
				refreshPlayerDetailsView();

				// show success message
				JOptionPane.showMessageDialog(PlayerSearchApp.this,
						"Player deleted succesfully.", "Player Deleted",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception exc) {
				JOptionPane.showMessageDialog(PlayerSearchApp.this,
						"Error deleting Player: " + exc.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			
		}

		});
		panel_1.add(btnDeletePlayerDetails);
	}

	public void refreshPlayerDetailsView() {

		try {
			List<PlayerDetails> PlayerDetails = PlayerDetailsDAO.getAllPlayerDetails();

			// create the model and update the "table"
			PlayerDetailsTableModel model = new PlayerDetailsTableModel(PlayerDetails);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
