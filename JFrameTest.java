import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class JFrameTest {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultTableModel tableModel;
    private Timer colorTimer;
    private int colorStep = 0;
    private JPanel dashboardPanel;

    public static void main(String[] args) {
        // Ensure all UI interactions are executed on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // 1. Pre-launch Warning Screen
            JOptionPane.showMessageDialog(null,
                    "System Warning: Unauthorized access is prohibited. Click OK to proceed.",
                    "Security Alert",
                    JOptionPane.WARNING_MESSAGE);

            // Only after 'OK' is clicked does the main application bootstrap
            new JFrameTest().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Application - Login State");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centers the window

        // 2. Setting up the CardLayout Master Panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add the two main views to the card deck
        cardPanel.add(createLoginPanel(), "LOGIN_VIEW");
        cardPanel.add(createDashboardPanel(), "DASHBOARD_VIEW");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JButton addAccountBtn = new JButton("Add Account");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel();
        btnPanel.add(loginBtn);
        btnPanel.add(addAccountBtn);
        panel.add(btnPanel, gbc);

        // Login Action
        loginBtn.addActionListener(e -> {
            // In a real app, you would validate credentials here. We will just transition.
            cardLayout.show(cardPanel, "DASHBOARD_VIEW");
            frame.setTitle("Application - Dashboard");
            frame.setJMenuBar(createMenuBar()); // Dynamically attach the menu bar
            frame.revalidate(); // Re-layout the frame with the new menu
        });

        // Add Account Action (Modal Dialog)
        addAccountBtn.addActionListener(e -> showAddAccountDialog());

        return panel;
    }

    private void showAddAccountDialog() {
        // 3. Modal Dialog for account provisioning
        JDialog dialog = new JDialog(frame, "Create New Account", true);
        dialog.setLayout(new FlowLayout());

        dialog.add(new JLabel("New Username:"));
        dialog.add(new JTextField(12));

        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Account stored successfully!");
            dialog.dispose(); // Closes the dialog and returns to the login screen
        });

        dialog.add(submitBtn);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true); // Halts execution here until dialog is disposed
    }

    private JPanel createDashboardPanel() {
        dashboardPanel = new JPanel(new BorderLayout());

        // 4. Dropdown (JComboBox) filtering logic
        String[] dropdownOptions = { "Select Data Profile...", "Data Profile A", "Data Profile B" };
        JComboBox<String> comboBox = new JComboBox<>(dropdownOptions);
        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            if (!selected.startsWith("Select")) {
                // Dynamically push a new row into the JTable
                tableModel.addRow(new Object[] { System.currentTimeMillis(), selected, "Loaded" });
            }
        });
        dashboardPanel.add(comboBox, BorderLayout.NORTH);

        // 5. Dynamic Data Table (JTable)
        String[] columnNames = { "Timestamp", "Profile", "Status" };
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(250, 0));
        dashboardPanel.add(scrollPane, BorderLayout.WEST);

        // 6. Interactive Graphing Canvas
        dashboardPanel.add(new GraphPanel(), BorderLayout.CENTER);

        // 7. Dynamic Frame Modification Button (Background Animation)
        JButton themeBtn = new JButton("Animate Background Theme");
        themeBtn.addActionListener(e -> startColorAnimation());
        dashboardPanel.add(themeBtn, BorderLayout.SOUTH);

        return dashboardPanel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("System");
        JMenuItem logoutItem = new JMenuItem("Logout");

        logoutItem.addActionListener(e -> {
            frame.setJMenuBar(null); // Remove the top menu dynamically
            frame.setTitle("Application - Login State");
            cardLayout.show(cardPanel, "LOGIN_VIEW"); // Route back to login
        });

        menu.add(logoutItem);
        menuBar.add(menu);
        return menuBar;
    }

    private void startColorAnimation() {
        if (colorTimer != null && colorTimer.isRunning())
            return;
        colorStep = 0;

        // Asynchronously update color values every 30 milliseconds
        colorTimer = new Timer(30, e -> {
            colorStep += 5;
            if (colorStep > 200) {
                colorStep = 200;
                colorTimer.stop();
            }
            // Transition background to a light blue hue
            dashboardPanel.setBackground(new Color(255 - colorStep, 255, 255));
            dashboardPanel.repaint(); // Force a UI update
        });
        colorTimer.start();
    }

    // Custom JPanel for mathematical coordinate plotting
    class GraphPanel extends JPanel {
        private List<Point> points = new ArrayList<>();

        public GraphPanel() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createTitledBorder("Click to Add Data Points"));

            // Listen for mouse clicks to implement points
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    points.add(e.getPoint()); // Cache the exact pixel coordinate
                    // Dynamically update the main window title
                    frame.setTitle("Application - Dashboard (Points Plotted: " + points.size() + ")");
                    repaint(); // Queue a visual refresh
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Required to clear the canvas frame
            Graphics2D g2 = (Graphics2D) g;

            // Turn on anti-aliasing for smooth lines
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2f));

            Point previousPoint = null;
            // Iterate sequentially over the implemented points
            for (Point p : points) {
                // Draw the point node
                g2.fill(new Ellipse2D.Double(p.x - 4, p.y - 4, 8, 8));

                // Draw a vector line connecting to the previous point
                if (previousPoint != null) {
                    g2.draw(new Line2D.Double(previousPoint.x, previousPoint.y, p.x, p.y));
                }
                previousPoint = p;
            }
        }
    }
}