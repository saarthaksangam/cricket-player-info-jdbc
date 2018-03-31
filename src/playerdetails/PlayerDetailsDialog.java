package playerdetails;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import playerdetails.PlayerDetails;
import playerdetails.PlayerDetailsDAO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;

public class PlayerDetailsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TextField nameTextField;
	private TextField matchesTextField;
	private TextField runsTextField;
	private TextField wicketsTextField;
	private TextField centuriesTextField;
	private TextField fiftiesTextField;
	
	private PlayerDetailsDAO playerDetailsDAO;

	private PlayerSearchApp playerSearchApp;	


	private PlayerDetails previousPlayerDetails = null;
	private boolean updateMode = false;	
	
	
	public PlayerDetailsDialog(PlayerSearchApp thePlayerDetailsSearchApp,
			PlayerDetailsDAO thePlayerDetailsDAO, PlayerDetails thePreviousPlayerDetails, boolean theUpdateMode) 
	{
		this();
		playerDetailsDAO = thePlayerDetailsDAO;
		playerSearchApp = thePlayerDetailsSearchApp;

		previousPlayerDetails = thePreviousPlayerDetails;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Player Details");
			
			populateGui(previousPlayerDetails);
		}
	}
	
	

	private void populateGui(PlayerDetails thePlayerDetails) {

		nameTextField.setText(thePlayerDetails.getName());
		matchesTextField.setText(thePlayerDetails.getMatches());
		runsTextField.setText(thePlayerDetails.getRuns());
		wicketsTextField.setText(thePlayerDetails.getWickets());
		centuriesTextField.setText(thePlayerDetails.getCenturies());
		fiftiesTextField.setText(thePlayerDetails.getFifties());
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlayerDetailsDialog dialog = new PlayerDetailsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlayerDetailsDialog() {
		setTitle("Add Player Details");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblName = new JLabel("Name");
			contentPanel.add(lblName, "2, 2");
		}
		
			nameTextField = new TextField();
			nameTextField.addTextListener(new TextListener() {
				public void textValueChanged(TextEvent arg0) {
				}
			});
			nameTextField.setColumns(60);
			contentPanel.add(nameTextField, "4, 2");
		
		{
			JLabel lblMatches = new JLabel("Matches");
			contentPanel.add(lblMatches, "2, 4");
		}
		{
			matchesTextField = new TextField();
			contentPanel.add(matchesTextField, "4, 4");
		}
		{
			JLabel lblRuns = new JLabel("Runs");
			contentPanel.add(lblRuns, "2, 6");
		}
		{
			runsTextField = new TextField();
			contentPanel.add(runsTextField, "4, 6");
		}
		{
			JLabel lblWickets = new JLabel("Wickets");
			contentPanel.add(lblWickets, "2, 8");
		}
		{
			wicketsTextField = new TextField();
			contentPanel.add(wicketsTextField, "4, 8");
		}
		{
			JLabel lblNewLabel = new JLabel("Centuries");
			contentPanel.add(lblNewLabel, "2, 10");
		}
		{
			centuriesTextField = new TextField();
			contentPanel.add(centuriesTextField, "4, 10");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fifties");
			contentPanel.add(lblNewLabel_1, "2, 12");
		}
		{
			fiftiesTextField = new TextField();
			contentPanel.add(fiftiesTextField, "4, 12");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						savePlayerDetails();

					}
				});
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void savePlayerDetails() {

		
		// get the employee info from GUI
		String name = nameTextField.getText();
		String matches =matchesTextField.getText();
		String runs = runsTextField.getText();
		String wickets = wicketsTextField.getText();
		String centuries = centuriesTextField.getText();
		String fifties = fiftiesTextField.getText();

		PlayerDetails tempPlayerDetails = null;

		if (updateMode) {
			tempPlayerDetails = previousPlayerDetails;
			
			tempPlayerDetails.setName(name);
			tempPlayerDetails.setMatches(matches);
			tempPlayerDetails.setRuns(runs);
			tempPlayerDetails.setWickets(wickets);
			tempPlayerDetails.setCenturies(centuries);
			tempPlayerDetails.setFifties(fifties);
		} else {
			tempPlayerDetails = new PlayerDetails(name, matches, runs, wickets, centuries, fifties);
		}

		try {
			// save to the database
			if (updateMode) {
				playerDetailsDAO.updatePlayerDetails(tempPlayerDetails);
			} else {
				playerDetailsDAO.addPlayerDetails(tempPlayerDetails);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			playerSearchApp.refreshPlayerDetailsView();
			
			// show success message
			JOptionPane.showMessageDialog(playerSearchApp,
					"Player Details added succesfully.",
					"Player Added",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					playerSearchApp,
					"Error saving Player Details: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
