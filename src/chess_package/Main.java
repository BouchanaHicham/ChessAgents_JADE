package chess_package;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
  public static void main(String[] args) {
    // Get the JADE runtime
    Runtime runtime = Runtime.instance();

    // Create a Profile for the Main Container
    Profile profile = new ProfileImpl();
    profile.setParameter(Profile.MAIN_HOST, "localhost");
    profile.setParameter(Profile.MAIN_PORT, "1099");
    profile.setParameter(Profile.CONTAINER_NAME, "Main Container");

    // Create the Main Container with the given profile
    ContainerController container = runtime.createMainContainer(profile);
    try {
        // Start the JADE GUI
        AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", null);
        rma.start();
	        try 
			    {
			      // Create and start the Robot agent
			      AgentController robot = container.createNewAgent("Robot", "chess_package.Robot", null);
			      robot.start();
			
			      // Create and start the Pawn agents
			      
			        AgentController pawn = container.createNewAgent("Pawn", "chess_package.Pawn", null);
			        pawn.start();
			      
			
			      // Create and start the Knight agents
			     
			        AgentController knight = container.createNewAgent("Knight", "chess_package.Knight", null);
			        knight.start();
			      
			
			      // Create and start the Bishop agents
			      
			        AgentController bishop = container.createNewAgent("Bishop", "chess_package.Bishop", null);
			        bishop.start();
			      
			
			      // Create and start the Rook agents
			      
			        AgentController rook = container.createNewAgent("Rook", "chess_package.Rook", null);
			        rook.start();
			      
			
			      // Create and start the Queen agent
			      AgentController queen = container.createNewAgent("Queen", "chess_package.Queen", null);
			      queen.start();
			
			      // Create and start the King agent
			      AgentController king = container.createNewAgent("King", "chess_package.King", null);
			      king.start();
			    } 
	       catch (Exception e) 
			    {
			      e.printStackTrace();
			    }
    	}
    catch (Exception e) {
        e.printStackTrace();
      }
  }
}
