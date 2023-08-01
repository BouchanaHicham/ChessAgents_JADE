package chess_package;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

// Pawn class
public class Pawn extends Agent 
{
  // Pawns can only move forward
  public static boolean canMove(int currentRow, int currentColumn, int newRow, int newColumn) {
    return newRow > currentRow;
  }

  @Override
  protected void setup() 
  {
    // Register the agent with the JADE platform
    addBehaviour(new CyclicBehaviour() 
    {
      @Override
      public void action() 
      {
    	// Receive a message from Robot agent
          ACLMessage Received_msg = receive();
    	  

          if (Received_msg != null) 
          {
        	  ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            // Get the content of the message
            String content = Received_msg.getContent();
            
            // ---- Splitting the Content ----
            String[] parts = content.split("\\|");
            String currentButtonText = parts[0];
            String Piece_Position = parts[1];
            System.out.println(currentButtonText+"|"+ Piece_Position);
            
            char firstLetter = currentButtonText.charAt(0);
            
            if (firstLetter=='B')
            {
            	//System.out.println(getNextMoves(Piece_Position, true));
            	List<String> moves = getNextMoves(Piece_Position, true);
            	String content1 = String.join(",", moves);
            	System.out.println("Content Sent: "+content1);
            	
            	// Sending The Possible Moves Back
            	AID receiver = new AID("Robot", AID.ISLOCALNAME);
          		msg.addReceiver(receiver);
          		msg.setContent(content1);
          		send(msg);
            }
            else if (firstLetter=='W')
            {
            	//System.out.println(getNextMoves(Piece_Position, false));
            	List<String> moves = getNextMoves(Piece_Position, false);
            	String content1 = String.join(",", moves);
            	System.out.println("Content Sent From Pawn: "+content1);
            	
            	// Sending The Possible Moves Back
            	AID receiver = new AID("Robot", AID.ISLOCALNAME);
          		msg.addReceiver(receiver);
          		msg.setContent(content1);
          		send(msg);
            }

            
            
            
          } 
          else 
          {
            block();
          }
          
        
      }

    
    });
  }
  
  public static List<String> getNextMoves(String position, boolean isBlack) 
  {
	    // Get the row and column of the current position
	    int row = position.charAt(1) - '1';
	    int column = position.charAt(0) - 'A';

	    // Create a list to store the next possible moves
	    List<String> moves = new ArrayList<>();

	    // Add the forward moves for the pawn
	    if (isBlack) {
	      // White pawns move forward
	      if (row > 0) {
	        // Add the move one square forward
	        moves.add((char)('A' + column) + "" + (char)('1' + row - 1));

	        // Add the move two squares forward if the pawn is on its starting rank
	        if (row == 6) {
	          moves.add((char)('A' + column) + "" + (char)('1' + row - 2));
	        }
	      }
	    } else {
	      // Black pawns move backward
	      if (row < 7) {
	        // Add the move one square backward
	        moves.add((char)('A' + column) + "" + (char)('1' + row + 1));

	        // Add the move two squares backward if the pawn is on its starting rank
	        if (row == 1) 
	        {
	          moves.add((char)('A' + column) + "" + (char)('1' + row + 2));
	        }
	      }
	    }

	    return moves;
	  }
}
