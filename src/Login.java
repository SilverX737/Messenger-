import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Login extends JFrame {
  private List<User> users;
  private JTextField firstNameField;
  private JTextField lastNameField;

  public Login() {
    setTitle("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 200);
    setLayout(new BorderLayout());

    JPanel loginPanel = new JPanel();
    loginPanel.setLayout(new BorderLayout());

    JPanel fieldsPanel = new JPanel();
    fieldsPanel.setLayout(new GridLayout(2, 2));

    JLabel firstNameLabel = new JLabel("First Name:");
    firstNameField = new JTextField();
    JLabel lastNameLabel = new JLabel("Last Name:");
    lastNameField = new JTextField();

    fieldsPanel.add(firstNameLabel);
    fieldsPanel.add(firstNameField);
    fieldsPanel.add(lastNameLabel);
    fieldsPanel.add(lastNameField);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    JButton signUpButton = new JButton("Login");
    signUpButton.addActionListener(new SignUpButtonListener());

    buttonPanel.add(signUpButton);

    loginPanel.add(fieldsPanel, BorderLayout.CENTER);
    loginPanel.add(buttonPanel, BorderLayout.SOUTH);

    getContentPane().add(loginPanel, BorderLayout.CENTER);

    users = new ArrayList<>();

    // Add some styling
    loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    signUpButton.setBackground(new Color(70, 130, 180));
    signUpButton.setForeground(Color.WHITE);
    signUpButton.setFocusPainted(false);
    signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    setVisible(true);
  }

  private class SignUpButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      if (!firstName.isEmpty() && !lastName.isEmpty()) {
        User newUser = new User(firstName, lastName);
        users.add(newUser);
        JOptionPane.showMessageDialog(Login.this, "Login Successful.");
        openMessenger(newUser);
      } else {
        JOptionPane.showMessageDialog(Login.this, "Please enter both first name and last name.");
      }
    }
  }

  private void openMessenger(User user) {
    // Open Messenger window with the provided user
    Messenger messenger = new Messenger();
    messenger.setVisible(true);
    messenger.setTitle("Messenger - " + user.getFullName());
    // You can add more initialization logic for Messenger here, such as setting up
    // user's contacts, etc.
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Login();
      }
    });
  }
}
