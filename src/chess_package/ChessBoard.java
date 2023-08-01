package chess_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard {
    static String lastButtonText = "";

    public static void main(String[] args) 
    {
        // Create a new JFrame
        JFrame frame = new JFrame("Chess Board");

        // Declare the selectedButton variable as final
        JButton selectedButton = null;

        // Set the layout to be a GridLayout with 8 rows and 8 columns
        frame.setLayout(new GridLayout(8, 8));

        // Set the background color to be light gray
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Create a boolean to keep track of the current color
        boolean isBlack = false;

        // Add 64 buttons to the frame, alternating their colors
        for (int i = 0; i < 64; i++) {
            // Create a new button
            JButton button = new JButton();

            // Set the background color based on the current color
            if (isBlack) {
                button.setBackground(Color.BLACK);
            } else {
                button.setBackground(Color.WHITE);
            }

            // Toggle the current color
            isBlack = !isBlack;

            // If we have reached the end of a row, switch the starting color
            if (i % 8 == 7) {
                isBlack = !isBlack;
            }

            // Add a label to the button based on its position on the chess board
            if (i == 0 || i == 7) {
                button.setText("W_Rook");
            } else if (i == 1 || i == 6) {
                button.setText("W_Knight");
            } else if (i == 2 || i == 5) {
                button.setText("W_Bishop");
            } else if (i == 3) {
                button.setText("W_Queen");
            } else if (i == 4) {
                button.setText("W_King");
            } else if (i >= 8 && i <= 15) {
                button.setText("W_Pawn");
            } else if (i == 56 || i == 63) {
                button.setText("B_Rook");
            } else if (i == 57 || i == 62) {
                button.setText("B_Knight");
            } else if (i == 58 || i == 61) {
                button.setText("B_Bishop");
            } else if (i == 59) {
                button.setText("B_Queen");
            } else if (i == 60) {
                button.setText("B_King");
            } else if (i >= 48 && i <= 55) {
                button.setText("B_Pawn");
            }

            // Add an action listener to the button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	// Store the text of the current button in a temporary variable
                	String currentButtonText = button.getText();

                	// Set the text of the current button to the text of the last button clicked
                	button.setText(lastButtonText);

                	// Set the text of the last button clicked to the text of the current button
                	lastButtonText = currentButtonText;
                 
                }
            });

            // Add the button to the frame
            frame.add(button);
        }

        // Set the size of the frame and make it visible
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

