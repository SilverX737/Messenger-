
// Messenger.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Messenger extends JFrame {
  private JTextArea messageTextArea;
  private JTextArea conversationTextArea;
  private Map<String, StringBuilder> conversations;
  private String selectedUser;

  public Messenger() {
    setTitle("Messenger");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    setLayout(new BorderLayout());

    JPanel userPanel = new JPanel();
    userPanel.setLayout(new GridLayout(0, 1));
    userPanel.setBackground(Color.LIGHT_GRAY);

    String[] users = { "User 1", "User 2", "User 3", "User 4", "User 5" };
    conversations = new HashMap<>();
    for (String user : users) {
      JButton userButton = new JButton(user);
      userButton.setPreferredSize(new Dimension(100, 50));
      userButton.addActionListener(new UserButtonListener(user));
      userPanel.add(userButton);
      conversations.put(user, new StringBuilder());
    }

    JPanel conversationPanel = new JPanel();
    conversationPanel.setLayout(new BorderLayout());

    conversationTextArea = new JTextArea();
    conversationTextArea.setEditable(false);
    JScrollPane conversationScrollPane = new JScrollPane(conversationTextArea);
    conversationPanel.add(conversationScrollPane, BorderLayout.CENTER);

    JPanel messagePanel = new JPanel();
    messagePanel.setLayout(new BorderLayout());

    messageTextArea = new JTextArea(3, 20);
    JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
    messagePanel.add(messageScrollPane, BorderLayout.CENTER);

    JButton sendButton = new JButton("Send");
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        sendMessage();
      }
    });
    messagePanel.add(sendButton, BorderLayout.SOUTH);

    getContentPane().add(userPanel, BorderLayout.WEST);
    getContentPane().add(conversationPanel, BorderLayout.CENTER);
    getContentPane().add(messagePanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  private void sendMessage() {
    String message = messageTextArea.getText();
    StringBuilder conversation = conversations.getOrDefault(selectedUser, new StringBuilder());
    conversation.append("You: ").append(message).append("\n");
    conversationTextArea.setText(conversation.toString());
    messageTextArea.setText("");
  }

  private class UserButtonListener implements ActionListener {

    private String user;

    public UserButtonListener(String user) {
      this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      selectedUser = user;
      updateConversation(selectedUser);
    }
  }

  private void updateConversation(String user) {
    StringBuilder conversation = conversations.getOrDefault(user, new StringBuilder());
    conversationTextArea.setText(conversation.toString());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Messenger();
      }
    });
  }
}
