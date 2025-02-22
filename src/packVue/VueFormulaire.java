package packVue;

import packControleur.ControlAjoutFormulaire;
import packControleur.ControlModifFormulaire;
import packControleur.ControlSupprFormulaire;
import packControleur.ControlSupprListe;
import packModele.Etudiant;
import packModele.Promotion;
import packObserver.Observer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class VueFormulaire extends AbstractVue implements Observer {

    private static final JTextField txtNumeroAjout = new JTextField(10);
    private static final JTextField txtNumeroSuppr = new JTextField(10);
    private static final JTextField txtNom = new JTextField(10);
    private static final JTextField txtPrenom = new JTextField(10);
    private static final JComboBox boxBac = new JComboBox();
    private static final JComboBox boxDpt = new JComboBox();
    private final JLabel lblNumeroAjout = new JLabel("Numero:");
    private final JLabel lblNumeroSuppr = new JLabel("Numero:");
    private final JLabel lblNom = new JLabel("Nom:");
    private final JLabel lblPrenom = new JLabel("Prénom:");
    private final JLabel lblBac = new JLabel("Bac:");
    private final JLabel lblDpt = new JLabel("Dpt:");
    private final JLabel lblPartieAjout = new JLabel("Ajout d'un étudiant");
    private final JLabel lblPartieSuppr = new JLabel("Suppression d'un étudiant:");
    private final JLabel lblEspace = new JLabel("    ");
    private final JLabel lblSeparation = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    private final JButton btAjout = new JButton("Ajout");
    private final JButton btSuppr = new JButton("Supprimer");
    private static final JButton btModif = new JButton("Modifier");

    public VueFormulaire() {
        // Add VueListe to Observers
        Promotion.addObserver(this);

        initFrame();
        this.pack();
    }

    private void initFrame() {
        //remplissage des box
        boxDpt.addItem("- - -");
        for (int i = 1; i < 96; i++) {
            if (i != 20) {
                if (i < 10) {
                    boxDpt.addItem("0" + i);
                } else {
                    boxDpt.addItem("" + i);
                }
            } else {
                boxDpt.addItem("2A");
                boxDpt.addItem("2B");
            }
        }
        for (int i = 971; i < 977; i++) {
            boxDpt.addItem("" + i);
        }
        //seconde box
        boxBac.addItem("- - -");
        boxBac.addItem("General");
        boxBac.addItem("Techno");
        boxBac.addItem("Pro");
        boxBac.addItem("Autre");

        // Disable modify button
        btModif.setEnabled(false);

        //placement des objets
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.add(lblPartieAjout, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        this.add(lblNumeroAjout, gc);
        gc.gridx = 1;
         this.add(txtNumeroAjout, gc);
        gc.gridx = 2;
        this.add(lblPrenom, gc);
        gc.gridx = 3;
        this.add(txtPrenom, gc);
        gc.gridx = 4;
        this.add(lblNom, gc);
        gc.gridx = 5;
        this.add(txtNom, gc);
        gc.gridx = 6;
        this.add(lblBac, gc);
        gc.gridx = 7;
        this.add(boxBac, gc);
        gc.gridx = 8;
        this.add(lblDpt, gc);
        gc.gridx = 9;
        this.add(boxDpt, gc);
        gc.gridx = 10;
        this.add(lblEspace, gc);
        gc.gridx = 11;
        gc.gridy = 0;
        this.add(btAjout, gc);
        gc.gridx = 11;
        gc.gridy = 1;
        this.add(btModif, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 11;
        //this.add(lblSeparation, gc);
        this.add(new JSeparator(SwingConstants.HORIZONTAL), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.add(lblPartieSuppr, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        this.add(lblNumeroSuppr, gc);
        gc.gridx = 1;
        this.add(txtNumeroSuppr, gc);
        gc.gridx = 11;
        this.add(btSuppr, gc);

        // Action
        btAjout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // List of new data to add
                ArrayList<String> listNewData = new ArrayList<>();

                // Add data from all form input to the list
                listNewData.add(txtNumeroAjout.getText().toUpperCase());
                listNewData.add(txtNom.getText().toUpperCase());
                listNewData.add(txtPrenom.getText().toUpperCase());
                listNewData.add(boxDpt.getSelectedItem().toString());
                listNewData.add(boxBac.getSelectedItem().toString());

                // Call the controller
                ControlAjoutFormulaire controlAddForm = new ControlAjoutFormulaire();
                controlAddForm.control(listNewData);
            }
        });

        btSuppr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // List of numero entered
                ArrayList<String> listNumeroStudent = new ArrayList<>();

                // Get numero entered
                listNumeroStudent.add(txtNumeroSuppr.getText());

                // Call the controller
                ControlSupprFormulaire controlRemoveForm = new ControlSupprFormulaire();
                controlRemoveForm.control(listNumeroStudent);
            }
        });

        btModif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // List of modified data
                ArrayList<String> listModifiedData = new ArrayList<>();

                listModifiedData.add(txtNumeroAjout.getText());
                listModifiedData.add(txtPrenom.getText());
                listModifiedData.add(txtNom.getText());
                listModifiedData.add(boxBac.getSelectedItem().toString());
                listModifiedData.add(boxDpt.getSelectedItem().toString());

                // Call the controller
                ControlModifFormulaire controlModifyForm = new ControlModifFormulaire();
                controlModifyForm.control(listModifiedData);

                // Disable modify button
                btModif.setEnabled(false);

            }
        });
    }

    public static void getStudentDataToModify() {
        try {
            // get the student to modify his data
            Etudiant studentToModify = Promotion.getStudentToModify();

            if (studentToModify == null) {
                throw new Exception("The student to modify does not exist");
            }

            txtNumeroAjout.setText(studentToModify.getNumero());
            txtPrenom.setText(studentToModify.getFirstName());
            txtNom.setText(studentToModify.getLastName());

            // Format type bac
            for (int i = 0; i < 5; i++) {
                if (boxBac.getItemAt(i).toString().matches("^" + studentToModify.getBac() + ".*$")) {
                    boxBac.setSelectedItem(boxBac.getItemAt(i));
                }
            }

            boxDpt.setSelectedItem(studentToModify.getDepartement());

            // Enable modify button
            btModif.setEnabled(true);

        } catch (Exception e) {
            System.err.println("ERROR : " + e.getMessage());
        }
    }

    @Override
    public void update() {
        getStudentDataToModify();
    }
}
