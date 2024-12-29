import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentGradeTrackerUpdated {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HashMap<String, HashMap<String, ArrayList<Double>>> gradeData;

    public StudentGradeTrackerUpdated() {
        frame = new JFrame("Student Grade Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        gradeData = new HashMap<>();

        JPanel loginPanel = createLoginPanel();
        JPanel homePanel = createHomePanel();
        JPanel gradeAnalysisPanel = createGradeAnalysisPanel();
        JPanel addGradePanel = createAddGradePanel();

        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(homePanel, "HOME");
        mainPanel.add(gradeAnalysisPanel, "GRADE_ANALYSIS");
        mainPanel.add(addGradePanel, "ADD_GRADE");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createAddGradePanel() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(new Color(230, 230, 255));

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(400, 400));
        contentPanel.setLayout(null);
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(120, 150, 255), 2));

        JLabel titleLabel = new JLabel("Add Grade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(20, 20, 360, 30);

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        studentIDLabel.setBounds(50, 80, 100, 25);

        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 80, 200, 25);

        JLabel courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        courseIDLabel.setBounds(50, 130, 100, 25);

        JComboBox<String> courseDropdown = new JComboBox<>(new String[]{"PH", "CH", "SC", "MA", "GE", "CI", "HI", "EN"});
        courseDropdown.setBounds(150, 130, 200, 25);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gradeLabel.setBounds(50, 180, 100, 25);

        JTextField gradeField = new JTextField();
        gradeField.setBounds(150, 180, 200, 25);

        JButton addGradeButton = new JButton("Add Grade");
        addGradeButton.setFont(new Font("Arial", Font.BOLD, 14));
        addGradeButton.setBounds(100, 230, 200, 30);
        addGradeButton.setBackground(new Color(120, 150, 255));
        addGradeButton.setForeground(Color.WHITE);
        addGradeButton.setFocusPainted(false);
        addGradeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(150, 270, 100, 30);
        backButton.setBackground(new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        addGradeButton.addActionListener(e -> {
            String studentID = studentIDField.getText().trim();
            String courseID = (String) courseDropdown.getSelectedItem();
            String gradeText = gradeField.getText().trim();

            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Student ID is required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Grade is required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double grade = Double.parseDouble(gradeText);
                gradeData.putIfAbsent(studentID, new HashMap<>());
                gradeData.get(studentID).putIfAbsent(courseID, new ArrayList<>());
                gradeData.get(studentID).get(courseID).add(grade);
                JOptionPane.showMessageDialog(frame, "Grade added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid grade!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        contentPanel.add(titleLabel);
        contentPanel.add(studentIDLabel);
        contentPanel.add(studentIDField);
        contentPanel.add(courseIDLabel);
        contentPanel.add(courseDropdown);
        contentPanel.add(gradeLabel);
        contentPanel.add(gradeField);
        contentPanel.add(addGradeButton);
        contentPanel.add(backButton);

        container.add(contentPanel);

        return container;
    }

    private JPanel createGradeAnalysisPanel() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(new Color(230, 230, 255));

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(400, 400));
        contentPanel.setLayout(null);
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(120, 150, 255), 2));

        JLabel titleLabel = new JLabel("Grade Analysis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(20, 20, 360, 30);

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        studentIDLabel.setBounds(50, 80, 100, 25);

        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 80, 200, 25);

        JLabel courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        courseIDLabel.setBounds(50, 130, 100, 25);

        JComboBox<String> courseDropdown = new JComboBox<>(new String[]{"PH", "CH", "SC", "MA", "GE", "CI", "HI", "EN"});
        courseDropdown.setBounds(150, 130, 200, 25);

        JButton findAverageButton = new JButton("Find Average");
        findAverageButton.setFont(new Font("Arial", Font.BOLD, 14));
        findAverageButton.setBounds(50, 180, 150, 30);
        findAverageButton.setBackground(new Color(120, 150, 255));
        findAverageButton.setForeground(Color.WHITE);
        findAverageButton.setFocusPainted(false);

        JButton findHighestButton = new JButton("Find Highest");
        findHighestButton.setFont(new Font("Arial", Font.BOLD, 14));
        findHighestButton.setBounds(50, 230, 150, 30);
        findHighestButton.setBackground(new Color(120, 150, 255));
        findHighestButton.setForeground(Color.WHITE);
        findHighestButton.setFocusPainted(false);

        JButton findLowestButton = new JButton("Find Lowest");
        findLowestButton.setFont(new Font("Arial", Font.BOLD, 14));
        findLowestButton.setBounds(50, 280, 150, 30);
        findLowestButton.setBackground(new Color(120, 150, 255));
        findLowestButton.setForeground(Color.WHITE);
        findLowestButton.setFocusPainted(false);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(150, 320, 100, 30);
        backButton.setBackground(new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        findAverageButton.addActionListener(e -> analyzeGrades(studentIDField, courseDropdown, "average"));
        findHighestButton.addActionListener(e -> analyzeGrades(studentIDField, courseDropdown, "highest"));
        findLowestButton.addActionListener(e -> analyzeGrades(studentIDField, courseDropdown, "lowest"));

        contentPanel.add(titleLabel);
        contentPanel.add(studentIDLabel);
        contentPanel.add(studentIDField);
        contentPanel.add(courseIDLabel);
        contentPanel.add(courseDropdown);
        contentPanel.add(findAverageButton);
        contentPanel.add(findHighestButton);
        contentPanel.add(findLowestButton);
        contentPanel.add(backButton);

        container.add(contentPanel);

        return container;
    }

    private void analyzeGrades(JTextField studentIDField, JComboBox<String> courseDropdown, String operation) {
        String studentID = studentIDField.getText().trim();
        String courseID = (String) courseDropdown.getSelectedItem();

        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Student ID is required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HashMap<String, ArrayList<Double>> studentGrades = gradeData.get(studentID);

        if (studentGrades == null || !studentGrades.containsKey(courseID)) {
            JOptionPane.showMessageDialog(frame, "No grades found for the specified student and course.", "No Data", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Double> grades = studentGrades.get(courseID);

        switch (operation) {
            case "average":
                double sum = 0;
                for (double grade : grades) {
                    sum += grade;
                }
                double average = sum / grades.size();
                JOptionPane.showMessageDialog(frame, "Average Grade: " + average);
                break;
            case "highest":
                double highest = grades.stream().max(Double::compare).orElseThrow();
                JOptionPane.showMessageDialog(frame, "Highest Grade: " + highest);
                break;
            case "lowest":
                double lowest = grades.stream().min(Double::compare).orElseThrow();
                JOptionPane.showMessageDialog(frame, "Lowest Grade: " + lowest);
                break;
        }
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 230, 255));

        JPanel loginBox = new JPanel();
        loginBox.setLayout(null);
        loginBox.setPreferredSize(new Dimension(400, 300));
        loginBox.setBackground(Color.WHITE);
        loginBox.setBorder(BorderFactory.createLineBorder(new Color(120, 150, 255), 2));

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setBounds(150, 30, 100, 30);

        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(100, 100, 200, 30);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(140, 170, 120, 30);
        loginButton.setBackground(new Color(120, 150, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(e -> {
            if (new String(passwordInput.getPassword()).trim().equals("ADMIN")) {
                cardLayout.show(mainPanel, "HOME");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginBox.add(loginLabel);
        loginBox.add(passwordInput);
        loginBox.add(loginButton);

        panel.add(loginBox);
        return panel;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 230, 255));

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navBar.setBackground(new Color(100, 150, 255));
        navBar.setPreferredSize(new Dimension(800, 50));

        JButton homeButton = createNavButton("Home", () -> cardLayout.show(mainPanel, "HOME"));
        JButton gradeAnalysisButton = createNavButton("Grade Analysis", () -> cardLayout.show(mainPanel, "GRADE_ANALYSIS"));
        JButton addGradeButton = createNavButton("Add Grade", () -> cardLayout.show(mainPanel, "ADD_GRADE"));

        navBar.add(homeButton);
        navBar.add(gradeAnalysisButton);
        navBar.add(addGradeButton);

        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>Welcome to the Student Grade Tracker!<br>This tool helps you manage student grades and perform grade analysis easily.</div></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(70, 70, 70));

        panel.add(navBar, BorderLayout.NORTH);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }

    private JButton createNavButton(String name, Runnable action) {
        JButton button = new JButton(name);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(120, 150, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> action.run());
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeTrackerUpdated::new);
    }
}