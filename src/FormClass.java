import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

public class FormClass extends JDialog {
    public JTextArea textArea1;
    private JPanel contentPane;
    private JButton DegrodBut;

    public FormClass() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(DegrodBut);

        DegrodBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    OurTurns.Turn(OurTurns.Turn(textArea1.getText()));
                } catch (IOException e1) {
                    System.out.println(e1.toString());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void CteateForm() {
        FormClass dialog = new FormClass();
        dialog.setName("Какой сайт?");
        dialog.pack();
        dialog.setLocation(500,300);
        dialog.setSize(500,300);
        dialog.setVisible(true);

    }

}
