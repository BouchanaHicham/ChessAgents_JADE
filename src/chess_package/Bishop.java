package chess_package;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

// Bishop class
public class Bishop extends Agent {
  // Bishops can move diagonally in any direction
  public static boolean canMove(int currentRow, int currentColumn, int newRow, int newColumn) {
    return Math.abs(newRow - currentRow) == Math.abs(newColumn - currentColumn);
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
            
           
          //System.out.println(getNextMoves(Piece_Position));
        	List<String> moves = getNextMoves(Piece_Position);
        	String content1 = String.join(",", moves);
        	System.out.println("Content Sent From Bishop: "+content1);
        	
        	// Sending The Possible Moves Back
        	AID receiver = new AID("Robot", AID.ISLOCALNAME);
      		msg.addReceiver(receiver);
      		msg.setContent(content1);
      		send(msg);
            
            
            
          } 
          else 
          {
            block();
          }
          
        
      }

    
    });
  }
  public static List<String> getNextMoves(String position) {
	    // Get the row and column of the current position
	    int row = position.charAt(1) - '1';
	    int column = position.charAt(0) - 'A';

	    // Create a list to store the next possible moves
	    List<String> moves = new ArrayList<>();

	    // Add the diagonal moves for the bishop
	    for (int i = 1; row - i >= 0 && column - i >= 0; i++) {
	      // Move diagonally up and left
	      moves.add((char)('A' + column - i) + "" + (char)('1' + row - i));
	    }
	    for (int i = 1; row - i >= 0 && column + i < 8; i++) {
	      // Move diagonally up and right
	      moves.add((char)('A' + column + i) + "" + (char)('1' + row - i));
	    }
	    for (int i = 1; row + i < 8 && column - i >= 0; i++) {
	      // Move diagonally down and left
	      moves.add((char)('A' + column - i) + "" + (char)('1' + row + i));
	    }
	    for (int i = 1; row + i < 8 && column + i < 8; i++) {
	      // Move diagonally down and right
	      moves.add((char)('A' + column + i) + "" + (char)('1' + row + i));
	    }

	    // Return the list of next possible moves
	    return moves;
	  }
}
