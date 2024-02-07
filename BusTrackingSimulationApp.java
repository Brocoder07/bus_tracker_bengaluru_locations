import java.util.awt.*;
import javax.swing.*;
public class BusTrackingSimulationApp extends JFrame {
    private MapPanel mapPanel;
    public BusTrackingSimulationApp() {
        super("Bus Tracking Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel appPanel = new JPanel();
        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
        appPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Bus Tracking Simulation");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        appPanel.add(titleLabel);
        appPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel startLabel = new JLabel("Enter starting place:");
        appPanel.add(startLabel);
        
        JTextField startTextField = new JTextField();
        appPanel.add(startTextField);
        appPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel destinationLabel = new JLabel("Enter destination place:");
        appPanel.add(destinationLabel);
        
        JTextField destinationTextField = new JTextField();
        appPanel.add(destinationTextField);
        appPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton trackButton = new JButton("Track Bus");
        trackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        trackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startPlace = startTextField.getText();
                String destinationPlace = destinationTextField.getText();
                simulateBusTracking(startPlace, destinationPlace);
            }
        });
        appPanel.add(trackButton);
        add(appPanel, BorderLayout.NORTH);

        mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void simulateBusTracking(String startPlace, String destinationPlace) {
    startPlace = startPlace.toLowerCase();
    destinationPlace = destinationPlace.toLowerCase();
    if (startPlace.trim().isEmpty() || destinationPlace.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter both starting and destination places.");
        return;
    }
    if (startPlace.equalsIgnoreCase(destinationPlace)) {
        JOptionPane.showMessageDialog(this, "Source and destination cannot be the same. Please enter different locations.");
        return;
    }
    if (!BengaluruLocation.isValidLocation(startPlace) || !BengaluruLocation.isValidLocation(destinationPlace)) {
        JOptionPane.showMessageDialog(this, "Invalid location. Please enter valid locations in Bengaluru city.");
        return;
    }
    mapPanel.startTrackingAnimation(startPlace, destinationPlace);
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new BusTrackingSimulationApp());
    }
}
class MapPanel extends JPanel {
    private String startPlace;
    private String destinationPlace;
    private int currentX;
    private int endX;
    private Timer timer;
    private double estimatedTime;

    public void startTrackingAnimation(String startPlace, String destinationPlace) {
        this.startPlace = startPlace;
        this.destinationPlace = destinationPlace;
        this.currentX = 50; 
        this.endX = getWidth() - 50;
        this.estimatedTime = calculateEstimatedTime();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateObjectPosition();
            }
        });
        timer.start();
    }
    
    private double calculateEstimatedTime() {
        double distance = BengaluruLocation.getDistance(startPlace, destinationPlace);
        double speed = 30.0;
        return distance / speed;
    }
    
    private void updateObjectPosition() {
        currentX += 5; 
        if (currentX >= endX) {
            timer.stop();
            displayArrivalMessage();
        } else {
            updateEstimatedTime();
        }
        repaint();
    }

    private void updateEstimatedTime() {
        double remainingDistance = endX - currentX;
        estimatedTime = remainingDistance / 5 / 3600;
    }

    private void displayArrivalMessage() {
        JOptionPane.showMessageDialog(this, "You have arrived at your destination!");
        estimatedTime = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startPlace != null && destinationPlace != null) {
            Graphics2D g2d = (Graphics2D) g;
            int startY = getHeight() / 2;
            int endY = getHeight() / 2;
            drawBus(g2d, currentX, startY);
            drawDestination(g2d, startPlace, currentX, startY - 20);
            drawBus(g2d, endX, endY);
            drawDestination(g2d, destinationPlace, endX, endY - 20);
            drawLine(g2d, currentX + 5, startY, endX - 5, endY);
            drawEstimatedTime(g2d, estimatedTime);
        }
    }

    private void drawBus(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x - 10, y - 10, 20, 20);
    }

    private void drawLine(Graphics2D g2d, int startX, int startY, int endX, int endY) {
        g2d.setColor(Color.BLUE);
        g2d.drawLine(startX, startY, endX, endY);
    }

    private void drawDestination(Graphics2D g2d, String destination, int x, int y) {
        g2d.setColor(Color.BLACK);
        g2d.drawString(destination, x - 10, y);
    }

    private void drawEstimatedTime(Graphics2D g2d, double estimatedTime) {
        if (estimatedTime > 0) {
            g2d.setColor(Color.BLACK);
            String timeText = (estimatedTime > 1) ? String.format("%.2f", estimatedTime) + " hours"
                    : String.format("%.2f", estimatedTime) + " hour";
            g2d.drawString(timeText + " before arrival", getWidth() / 2 - 50, getHeight() - 20);
        }
    }
}
