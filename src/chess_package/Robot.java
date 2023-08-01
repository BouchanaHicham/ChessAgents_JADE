package chess_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

// Robot agent
public class Robot extends Agent 
{
	// Create a new JFrame
    JFrame frame = new JFrame("Chess Board");
    String[] positions;
  @Override
  protected void setup() 
  {
	  Chess_Board();
    // Add a behavior to the agent to receive and handle messages from the chess piece agents
    addBehaviour(new CyclicBehaviour() 
    {
    	
      @Override
      public void action() 
      {
        // Receive a message from any chess piece agent
        ACLMessage msg = receive();

        if (msg != null) 
        {
          // Get the content of the message
          String content = msg.getContent();
          // System.out.println("Content Received: "+ content );
          positions = content.split(",");

          // Handle the message based on the content
          
       // Get the buttons from the JFrame
          Component[] buttons = frame.getContentPane().getComponents();

   

       // Iterate through the buttons
          for (Component c : buttons) 
          {
              // Check if the button is a JButton
              if (c instanceof JButton) {
                  JButton button = (JButton) c;
                  Color currentColor = button.getBackground();
                  // Store the current color in the map as the previous color
                  

                  // Iterate through the positions array
                  for (String position : positions) 
                  		{
                      // Check if the name of the button matches the current position
                      if (button.getName().equals(position)) 
                      {
                          // Set the background color of the button to red
                          button.setBackground(Color.RED);
                      }
                  }
              }
          }
          
        } 
        else 
        {
          block();
        }
      }
    });
  }
// ----------------------------------------------------------------------------------------- [Chess Board] -----------------------------------------------------------------------------------------
  static String lastButtonText = "";

  public void Chess_Board() 
  {
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
          if (isBlack) 
          {
              button.setBackground(Color.BLACK);
          } 
          else 
          {
              button.setBackground(Color.WHITE);
          }

          // Toggle the current color
          isBlack = !isBlack;

          // If we have reached the end of a row, switch the starting color
          if (i % 8 == 7) {
              isBlack = !isBlack;
          }
          
       // Set the name of the button to be its position on the chess board
          button.setName(Character.toString((char) ('A' + i % 8)) + (8 - i / 8));

         // Add a label to the button based on its position on the chess board
          if (i == 0 || i == 7) {
              button.setText("B_Rook");
          } else if (i == 1 || i == 6) {
              button.setText("B_Knight");
          } else if (i == 2 || i == 5) {
              button.setText("B_Bishop");
          } else if (i == 3) {
              button.setText("B_Queen");
          } else if (i == 4) {
              button.setText("B_King");
          } else if (i >= 8 && i <= 15) {
              button.setText("B_Pawn");
          } else if (i == 56 || i == 63) {
              button.setText("W_Rook");
          }else if (i == 57 || i == 62) {
              button.setText("W_Knight");
          } else if (i == 58 || i == 61) {
              button.setText("W_Bishop");
          } else if (i == 59) {
              button.setText("W_Queen");
          } else if (i == 60) {
              button.setText("W_King");
          } else if (i >= 48 && i <= 55) {
              button.setText("W_Pawn");
          }

          // Add an action listener to the button
          button.addActionListener(new ActionListener() 
          {
              @Override
              public void actionPerformed(ActionEvent e) 
              {
            	  //Display Chess Piece Positions
            	  System.out.println("Chess Piece Positions: "+ getChessPiecePositions());
            	 
            	  

            	//Resetting Colors
            	  resetColors();
              	// Store the text of the current button in a temporary variable
              	String currentButtonText = button.getText();
              	String Piece_Position = button.getName();
              	System.out.println("Selected Piece: "+ currentButtonText);
              	System.out.println("Selected Position: "+ Piece_Position);
              	
              	Check_Possible_Moves(currentButtonText,Piece_Position);
              	// Set the text of the current button to the text of the last button clicked
              	button.setText(lastButtonText);
              	// Set the text of the last button clicked to the text of the current button
              	lastButtonText = currentButtonText;
              	Map<String, JButton> chessPiecePositions = getChessPiecePositions();
          	  List<String> possibleMoves = Arrays.asList(positions);
          	  // This Highlights the buttons to diff colors based on what piece they are
          	  //highlightPossibleMoves(chessPiecePositions, possibleMoves);
               
              }
          });

          // Add the button to the frame
          frame.add(button);
      }

      // Set the size of the frame and make it visible
      frame.setSize(800, 800);
      frame.setVisible(true);
  }
  
  public void Check_Possible_Moves(String currentButtonText, String Piece_Position)
  {
	  ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
  	switch (currentButtonText)
  	{
  	case"W_Pawn":
  	{
  		AID receiver = new AID("Pawn", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  
  		break;
  	}
  	case"B_Pawn":
  	{
  		AID receiver = new AID("Pawn", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"W_Knight":
  	{
  		AID receiver = new AID("Knight", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"B_Knight":
  	{
  		AID receiver = new AID("Knight", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"W_Bishop":
  	{
  		AID receiver = new AID("Bishop", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"B_Bishop":
  	{
  		AID receiver = new AID("Bishop", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"W_King":
  	{
  		AID receiver = new AID("King", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"B_King":
  	{
  		AID receiver = new AID("King", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"W_Queen":
  	{
  		AID receiver = new AID("Queen", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"B_Queen":
  	{
  		AID receiver = new AID("Queen", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"W_Rook":
  	{
  		AID receiver = new AID("Rook", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	case"B_Rook":
  	{
  		AID receiver = new AID("Rook", AID.ISLOCALNAME);
  		msg.addReceiver(receiver);
  		msg.setContent(currentButtonText+"|"+Piece_Position);
  		send(msg);
  		
  		break;
  	}
  	
  	
  	
  	
  	}
  }
  public void resetColors() 
  {
	    // Get the buttons from the JFrame
	    Component[] buttons = frame.getContentPane().getComponents();

	    // Create a boolean to keep track of the current color
	    boolean isBlack = false;

	    // Iterate through the buttons
	    for (int i = 0; i < buttons.length; i++) {
	        // Check if the button is a JButton
	        if (buttons[i] instanceof JButton) {
	            JButton button = (JButton) buttons[i];
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
	        }
	    }
	}

  public Map<String, JButton> getChessPiecePositions() 
  {
      // Create a map to store the positions of the chess pieces
      Map<String, JButton> positions = new HashMap<>();
      
      // Loop through all the buttons on the chess board
      for (int i = 0; i < 64; i++) {
          JButton button =  (JButton) frame.getContentPane().getComponent(i);
          String position = button.getName();
          
          // If the button has a chess piece on it, add it to the map
          if (button.getText() != null && !button.getText().isEmpty()) {
              positions.put(position, button);
          }
      }
      
      return positions;
  }

  public void highlightPossibleMoves(Map<String, JButton> chessPiecePositions, List<String> possibleMoves) {
	    // Iterate through the list of possible moves
	    for (String move : possibleMoves) {
	        // Get the button at the current position
	        JButton button = chessPiecePositions.get(move);

	        // Check if the button is not null
	        if (button != null) {
	            // Check if the button is occupied by a chess piece
	            if (button.getText().length() > 0) 
	            {
	                // If the button is occupied by a piece of the same color, set the background color to blue
	                if (Character.isUpperCase(button.getText().charAt(0))) {
	                    button.setBackground(Color.BLUE);
	                }
	                // If the button is occupied by a piece of the opposite color, set the background color to green
	                else {
	                    button.setBackground(Color.GREEN);
	                }
	            }
	            // If the button is not occupied, set the background color to yellow
	            else {
	                button.setBackground(Color.YELLOW);
	            }
	        }
	       
	    }
	}







}
