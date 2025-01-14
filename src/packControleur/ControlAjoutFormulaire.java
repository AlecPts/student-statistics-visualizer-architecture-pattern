package packControleur;

import packModele.Etudiant;
import packModele.Promotion;

import javax.swing.*;
import java.util.ArrayList;

public class ControlAjoutFormulaire extends AbstractControleur {

    @Override
    public void control(ArrayList<String> list) {
        try {
            // Exception for numero
            if (!list.getFirst().matches("[0-9]+")) {
                throw new Exception("The numero must contain only digits");
            }

            if (Promotion.getStudent(list.getFirst()) != null) {
                throw new Exception("This number has already been assigned to a student");
            }

            // Exception for firstname and lastname
            if (!list.get(1).matches("[a-z|A-Z]*-*[a-z|A-Z]+") || !list.get(2).matches("[a-z|A-Z]*-*[a-z|A-Z]+")) {
                throw new Exception("The name must contain only characters");
            }

            // Exception for combobox
            if (list.get(3).equals("- - -") || list.get(4).equals("- - -")) {
                throw new Exception("Please select a department and a type of bac");
            }

            // Format bac name
            switch (list.get(4)) {
                case "General":
                    list.set(4, "G");
                    break;

                case "Techno":
                    list.set(4, "T");
                    break;

                case "Autre":
                    list.set(4, "A");
                    break;

                default:
                    break;
            }

            Promotion.addStudent(new Etudiant(
                    list.get(0),
                    list.get(1),
                    list.get(2),
                    list.get(3),
                    list.get(4)
            ));

        } catch (Exception e) {
            System.err.println("ERROR : " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
