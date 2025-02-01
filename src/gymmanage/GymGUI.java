package gymmanage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
// import java.text.SimpleDateFormat;

public class GymGUI extends JFrame {
    private ArrayList<GymMember> memberList;
    private JTextField idField, nameField, locationField, phoneField, emailField;
    private JTextField dobField, startDateField, referralField, paidAmountField;
    private JTextField removalReasonField, trainerNameField;
    private JTextField regularPriceField, premiumChargeField, discountField;
    private JRadioButton maleButton, femaleButton;
    private ButtonGroup genderGroup;
    private JComboBox<String> planComboBox;

    public GymGUI() {
        memberList = new ArrayList<>();
        setTitle("Gym Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set a modern, minimalistic look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(245, 245, 245));

        // Add input fields
        addField(inputPanel, "Member ID:", idField = new JTextField());
        addField(inputPanel, "Name:", nameField = new JTextField());
        addField(inputPanel, "Location:", locationField = new JTextField());
        addField(inputPanel, "Phone:", phoneField = new JTextField());
        addField(inputPanel, "Email:", emailField = new JTextField());

        // Gender Radio Buttons
        inputPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(new Color(245, 245, 245));
        genderGroup = new ButtonGroup();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        inputPanel.add(genderPanel);

        addField(inputPanel, "Date of Birth:", dobField = new JTextField());
        addField(inputPanel, "Membership Start Date:", startDateField = new JTextField());
        addField(inputPanel, "Referral Source:", referralField = new JTextField());
        addField(inputPanel, "Personal Trainer:", trainerNameField = new JTextField());
        addField(inputPanel, "Paid Amount:", paidAmountField = new JTextField());
        addField(inputPanel, "Removal Reason:", removalReasonField = new JTextField());

        // Plan ComboBox
        inputPanel.add(new JLabel("Plan:"));
        planComboBox = new JComboBox<>(new String[] { "Basic", "Standard", "Deluxe" });
        inputPanel.add(planComboBox);

        // Non-editable fields
        addNonEditableField(inputPanel, "Regular Plan Price:", regularPriceField = new JTextField());
        addNonEditableField(inputPanel, "Premium Charge:", premiumChargeField = new JTextField());
        addNonEditableField(inputPanel, "Discount Amount:", discountField = new JTextField());

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(245, 245, 245));

        addButton(buttonPanel, "Add Regular Member", e -> addRegularMember());
        addButton(buttonPanel, "Add Premium Member", e -> addPremiumMember());
        addButton(buttonPanel, "Activate Membership", e -> activateMembership());
        addButton(buttonPanel, "Deactivate Membership", e -> deactivateMembership());
        addButton(buttonPanel, "Mark Attendance", e -> markAttendance());
        addButton(buttonPanel, "Upgrade Plan", e -> upgradePlan());
        addButton(buttonPanel, "Calculate Discount", e -> calculateDiscount());
        addButton(buttonPanel, "Revert Regular Member", e -> revertRegularMember());
        addButton(buttonPanel, "Revert Premium Member", e -> revertPremiumMember());
        addButton(buttonPanel, "Pay Due Amount", e -> payDueAmount());
        addButton(buttonPanel, "Display", e -> displayMembers());
        addButton(buttonPanel, "Clear", e -> clearFields());
        addButton(buttonPanel, "Save to File", e -> saveToFile());
        addButton(buttonPanel, "Read from File", e -> readFromFile());

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addField(JPanel panel, String labelText, JTextField textField) {
        panel.add(new JLabel(labelText));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(textField);
    }

    private void addNonEditableField(JPanel panel, String labelText, JTextField textField) {
        textField.setEditable(false);
        addField(panel, labelText, textField);
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobField.getText();
            String startDate = startDateField.getText();
            String referralSource = referralField.getText();

            RegularMember member = new RegularMember(id, name, location, phone, email, gender, dob, startDate,
                    referralSource);
            memberList.add(member);
            JOptionPane.showMessageDialog(this, "Regular member added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobField.getText();
            String startDate = startDateField.getText();
            String personalTrainer = trainerNameField.getText();

            PremiumMember member = new PremiumMember(id, name, location, phone, email, gender, dob, startDate,
                    personalTrainer);
            memberList.add(member);
            JOptionPane.showMessageDialog(this, "Premium member added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void activateMembership() {
        int id = Integer.parseInt(idField.getText());
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                member.activateMembership();
                JOptionPane.showMessageDialog(this, "Membership activated!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void deactivateMembership() {
        int id = Integer.parseInt(idField.getText());
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                member.deactivateMembership();
                JOptionPane.showMessageDialog(this, "Membership deactivated!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void markAttendance() {
        int id = Integer.parseInt(idField.getText());
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                member.markAttendance();
                JOptionPane.showMessageDialog(this, "Attendance marked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void upgradePlan() {
        int id = Integer.parseInt(idField.getText());
        String newPlan = (String) planComboBox.getSelectedItem();
        for (GymMember member : memberList) {
            if (member instanceof RegularMember && member.getId() == id) {
                RegularMember regularMember = (RegularMember) member;
                String result = regularMember.upgradePlan(newPlan);
                JOptionPane.showMessageDialog(this, result, "Upgrade Plan", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found or not a regular member!", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void calculateDiscount() {
        int id = Integer.parseInt(idField.getText());
        for (GymMember member : memberList) {
            if (member instanceof PremiumMember && member.getId() == id) {
                PremiumMember premiumMember = (PremiumMember) member;
                String result = premiumMember.calculateDiscount();
                JOptionPane.showMessageDialog(this, result, "Discount Calculation", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found or not a premium member!", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void revertRegularMember() {
        int id = Integer.parseInt(idField.getText());
        String removalReason = removalReasonField.getText();
        for (GymMember member : memberList) {
            if (member instanceof RegularMember && member.getId() == id) {
                RegularMember regularMember = (RegularMember) member;
                regularMember.revertRegularMember(removalReason);
                JOptionPane.showMessageDialog(this, "Regular member reverted!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found or not a regular member!", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void revertPremiumMember() {
        int id = Integer.parseInt(idField.getText());
        for (GymMember member : memberList) {
            if (member instanceof PremiumMember && member.getId() == id) {
                PremiumMember premiumMember = (PremiumMember) member;
                premiumMember.revertPremiumMember();
                JOptionPane.showMessageDialog(this, "Premium member reverted!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found or not a premium member!", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void payDueAmount() {
        int id = Integer.parseInt(idField.getText());
        double paidAmount = Double.parseDouble(paidAmountField.getText());
        for (GymMember member : memberList) {
            if (member instanceof PremiumMember && member.getId() == id) {
                PremiumMember premiumMember = (PremiumMember) member;
                String result = premiumMember.payDueAmount(paidAmount);
                JOptionPane.showMessageDialog(this, result, "Payment", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Member not found or not a premium member!", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void displayMembers() {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : memberList) {
            member.display();
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Member Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        genderGroup.clearSelection();
        dobField.setText("");
        startDateField.setText("");
        referralField.setText("");
        trainerNameField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        regularPriceField.setText("");
        premiumChargeField.setText("");
        discountField.setText("");
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
            writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n",
                    "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price", "Attendance",
                    "Loyalty Points", "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid");
            for (GymMember member : memberList) {
                writer.printf(
                        "%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10b %-15b %-15.2f %-15.2f\n",
                        member.getId(), member.getName(), member.getLocation(), member.getPhone(), member.getEmail(),
                        member.getMembershipStartDate(),
                        (member instanceof RegularMember ? ((RegularMember) member).getPlan() : "Premium"),
                        (member instanceof RegularMember ? ((RegularMember) member).getPrice()
                                : ((PremiumMember) member).getPremiumCharge()),
                        member.getAttendance(), member.getLoyaltyPoints(), member.isActiveStatus(),
                        (member instanceof PremiumMember ? ((PremiumMember) member).getIsFullPayment() : false),
                        (member instanceof PremiumMember ? ((PremiumMember) member).getDiscountAmount() : 0),
                        (member instanceof PremiumMember ? ((PremiumMember) member).getPaidAmount() : 0));
            }
            JOptionPane.showMessageDialog(this, "Data saved to file successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            JOptionPane.showMessageDialog(this, "Data read from file successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new GymGUI();
    }
}