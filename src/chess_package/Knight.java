package chess_package;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Knight extends Agent {
	  // Knights can move in an "L" shape in any direction
	  public static boolean canMove(int currentRow, int currentColumn, int newRow, int newColumn) {
	    int rowDiff = Math.abs(newRow - currentRow);
	    int colDiff = Math.abs(newColumn - currentColumn);
	    return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
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
	          	System.out.println("Content Sent From Knight: "+content1);
	          	
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

		  // Add the L-shaped moves for the knight
		  if (row > 1 && column > 0) {
		    // Move two rows up and one column left
		    moves.add((char)('A' + column - 1) + "" + (char)('1' + row - 2));
		  }
		  if (row > 0 && column > 1) {
		    // Move one row up and two columns left
		    moves.add((char)('A' + column - 2) + "" + (char)('1' + row - 1));
		  }
		  if (row < 6 && column > 1) {
		    // Move one row down and two columns left
		    moves.add((char)('A' + column - 2) + "" + (char)('1' + row + 1));
		  }
		  if (row < 7 && column > 0) {
		    // Move two rows down and one column left
		    moves.add((char)('A' + column - 1) + "" + (char)('1' + row + 2));
		  }
		  if (row < 7 && column < 6) {
		    // Move two rows down and one column right
		    moves.add((char)('A' + column + 1) + "" + (char)('1' + row + 2));
		  }
		  if (row < 6 && column < 7) {
		    // Move one row down and two columns right
			moves.add((char)('A' + column + 2) + "" + (char)('1' + row + 1));
		  }
	    if (row > 0 && column < 7) {
	        // Move one row up and two columns right
	        moves.add((char)('A' + column + 2) + "" + (char)('1' + row - 1));
	      }
	      if (row > 1 && column < 6) {
	        // Move two rows up and one column right
	        moves.add((char)('A' + column + 1) + "" + (char)('1' + row - 2));
	      }
	      
	      // Special case for the knight at position G1
	      if (position.equals("G1")) 
	      {
	        moves.add("H3");
	      }
	      if (position.equals("G2")) 
	      {
	        moves.add("H4");
	      }
	      if (position.equals("G3")) 
	      {
	        moves.add("H1");
	        moves.add("H5");
	      }
	      if (position.equals("G4")) 
	      {
	        moves.add("H2");
	        moves.add("H6");
	      }
	      if (position.equals("G5")) 
	      {
	        moves.add("H3");
	        moves.add("H7");
	      }
	      if (position.equals("G6")) 
	      {
	        moves.add("H4");
	        moves.add("H8");
	      }
	      if (position.equals("G7")) 
	      {
	        moves.add("H5");
	        moves.add("E8");
	      }
	      else if (position.equals("G8")) 
	      {
		    moves.add("H6");
		    
		  }
	      
	      else if (position.equals("A7")) 
	      {
	        moves.add("C8");
	      }
	      else if (position.equals("B7")) 
	      {
	        moves.add("D8");
	      }
	      else if (position.equals("C7")) 
	      {
	        moves.add("A8");
	        moves.add("E8");
	      }
	      else if (position.equals("D7")) 
	      {
	    	  moves.add("B8");
		      moves.add("F8");
	      }
	      else if (position.equals("E7")) 
	      {
	        moves.add("C8");
	        moves.add("G8");
	      }
	      else if (position.equals("F7")) 
	      {
	        moves.add("D8");
	        moves.add("H8");
	      }
	      else if (position.equals("H7")) 
	      {
	    	moves.add("F8");
	      }
	      // Remove any moves that are outside the bounds of the chessboard
	      moves.removeIf(move -> move.charAt(0) < 'A' || move.charAt(0) > 'H');
	      
	      // Return the list of moves
	      return moves;
	    }
    
	}
