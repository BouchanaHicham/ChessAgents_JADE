package chess_package;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Rook extends Agent {
	  // Rooks can move horizontally or vertically in any direction
	  public static boolean canMove(int currentRow, int currentColumn, int newRow, int newColumn) {
	    return currentRow == newRow || currentColumn == newColumn;
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
            	System.out.println("Content Sent From Rook: "+content1);
            	
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

		    // Add the horizontal and vertical moves for the rook
		    for (int i = 0; i < 8; i++) {
		      // Check the horizontal moves
		      if (i != row) {
		        moves.add((char)('A' + column) + "" + (char)('1' + i));
		      }
		      // Check the vertical moves
		      if (i != column) {
		        moves.add((char)('A' + i) + "" + (char)('1' + row));
		      }
		    }

		    // Return the list of next possible moves
		    return moves;
		  }
	}
