import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyfamilypcalculatorGUI {
    private JFrame frame;
    private JTextField[] nameFields = new JTextField[5];
    private JTextField[] ageFields = new JTextField[5];
    private JTextArea outputArea;
    private JButton printButton;

    public MyfamilypcalculatorGUI() {
        
        frame = new JFrame("Family Info Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 1));

        
        JPanel[] inputPanels = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            inputPanels[i] = new JPanel(new GridLayout(1, 4));

            JLabel nameLabel = new JLabel("Name " + (i + 1) + ": ");
            nameFields[i] = new JTextField(10);

            JLabel ageLabel = new JLabel("Age: ");
            ageFields[i] = new JTextField(5);

            inputPanels[i].add(nameLabel);
            inputPanels[i].add(nameFields[i]);
            inputPanels[i].add(ageLabel);
            inputPanels[i].add(ageFields[i]);

            frame.add(inputPanels[i]);
        }

        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

       
        JPanel buttonPanel = new JPanel();
        printButton = new JButton("Print Family Info");
        buttonPanel.add(printButton);

        
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printFamilyInfo();
            }
        });

        
        frame.add(buttonPanel);
        frame.add(scrollPane);

        
        frame.setVisible(true);
    }

    
    private void printFamilyInfo() {
        String[] names = new String[5];
        int[] ages = new int[5];
        int sum = 0;

        
        for (int i = 0; i < 5; i++) {
            names[i] = nameFields[i].getText();
            try {
                ages[i] = Integer.parseInt(ageFields[i].getText());
                sum += ages[i];
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid age input for family member " + (i + 1), "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        
        double averageAge = (double) sum / 5;

        
        StringBuilder output = new StringBuilder();
        output.append("Family Members:\n");
        for (int i = 0; i < 5; i++) {
            output.append(names[i] + " (" + ages[i] + " years old)\n");
        }
        output.append("\nSum of Ages: " + sum + "\n");
        output.append("Average Age: " + averageAge + "\n");

        
        if (averageAge > 60) {
            output.append("The family is mature.\n");
        } else {
            output.append("The family is young.\n");
        }

        
        outputArea.setText(output.toString());
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyfamilypcalculatorGUI();
            }
        });
    }
}
