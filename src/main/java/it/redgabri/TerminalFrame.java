package it.redgabri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class TerminalFrame extends JFrame {
    private static JTextArea terminalTextArea;
    private static JTextField inputField;
    private int initialMouseX, initialMouseY;
    private int initialX, initialY;
    private boolean inputEnabled = false;

    public TerminalFrame() throws InterruptedException {
        setTitle("Terminal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 450);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(33, 33, 33));
        titlePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialMouseX = e.getXOnScreen();
                initialMouseY = e.getYOnScreen();
                initialX = getX();
                initialY = getY();
            }
        });
        titlePanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - initialMouseX;
                int deltaY = e.getYOnScreen() - initialMouseY;
                setLocation(initialX + deltaX, initialY + deltaY);
            }
        });

        JLabel titleLabel = new JLabel("Terminal", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);
        setupTerminal();
        setVisible(true);
    }

    private void setupTerminal() {
        terminalTextArea = new JTextArea();
        terminalTextArea.setEditable(false);
        terminalTextArea.setBackground(Color.BLACK);
        terminalTextArea.setForeground(Color.WHITE);
        terminalTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        terminalTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(terminalTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }
    public void appendToTerminal(String text, Color color) {
        terminalTextArea.setForeground(color);
        terminalTextArea.append("$ " + text + "\n");
    }

    public static void clearTerminal(){
        terminalTextArea.setText(null);
    }
    public static JTextArea getTerminalTextArea() {
        return terminalTextArea;
    }

    public boolean isInputEnabled() {
        return inputEnabled;
    }
}