
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Neuronium {
	static Scanner in = new Scanner(System.in);
	static NeuralNetwork net;
	static boolean initialized = false;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InputMismatchException {
		System.out.println("Welcome to Neuronium!, the Forward Propagating Neural Network Model!");
		String savefileName = "Neuronium.dat";
		File savefile = new java.io.File(savefileName);
		
		boolean running = true;
		while(running) {
			System.out.println("\nNeuronium! - MAIN MENU");
			System.out.println("Enter 1 to LOAD a pre-existing neural network from file.");
			System.out.println("Enter 2 to SAVE your neural network to file.");
			System.out.println("Enter 3 to DELETE your neural network savefile.");
			System.out.println("Enter 4 to BUILD a new neural network.");
			System.out.println("Enter 5 to DISPLAY the state of neurons in a particular network layer.");
			System.out.println("Enter 6 to SET STATES of neurons in a particular network layer.");
			System.out.println("Enter 7 to SET THRESHOLDS of neurons in a particular network layer.");
			System.out.println("Enter 8 to SET WEIGHTS of axons in a particular network layer.");
			System.out.println("Enter 9 to FORWARD PROPAGATE values through the neural network.");
			System.out.println("Enter 0 to EXIT the program.");
			
			try {
				int choice = in.nextInt();
				
				if(choice >= 0 && choice <= 9) {
					switch (choice) {
					case 0: running = false; break;
					case 1: LoadNet(savefile); break;
					case 2: SaveNet(savefile); break;
					case 3: DeleteNet(savefile); break;
					case 4: BuildNet(); break;
					case 5: DisplayStates(); break;
					case 6: SetStates(); break;
					case 7: SetThresholds(); break;
					case 8: SetWeights(); break;
					case 9: Propagate(); break;
					}
				}
				else {
					System.out.println("Invalid input, please try again.");
					in.nextLine();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid input, please try again.");
				in.nextLine();
			}
			finally {
				if(!running) {
					System.out.println("\nThank you using Neuronium! Please come again!");
					in.close();
					System.exit(0);	
				}
			}
		}
	}
	
	static void LoadNet(File savefile) throws IOException, ClassNotFoundException{
		System.out.println("Loading pre-existing neural network from " + savefile.getName() + ".");
		boolean loaded = false;
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(savefile.getName()));) {
			net = (NeuralNetwork)(input.readObject());
			loaded = true;
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Savefile appears corruted!" 
					+ "\nAborting file load!");
		}
		catch(IOException ex) {
			System.out.println("File read/write error detected!" 
					+"\nAborting file load!.");
		}
		finally {
			if(loaded) {
				System.out.println("File succesfully loaded!");
				initialized = true;
			}
			else {
				System.out.println("File load unsuccessful!");
			}
		}
	}
	
	static void SaveNet(File savefile) throws IOException{
		if(savefile.exists()) {
			System.out.println("Previously saved neural network detected. "
					+ "\nWARNING: Saving now will overwrite neural network savefile."
					+ "\nWARNING: All previous work will be lost!\n"
					+ "\nEnter 1 to overwrite file."
					+ "\nEnter 0 to CANCEL.");
			boolean running = true;
			while(running) {
				boolean saved = false;
				try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(savefile.getName()));) {
					int choice = in.nextInt();
					
					if(choice == 1 || choice == 0) {
						if(choice == 1) {
							savefile.delete();
							System.out.println("Savefile deleted.");
							output.writeObject(net);
							saved = true;
							running = false;
						}
						if(choice == 0) {
							System.out.println("File save of neural network CANCELED!");
							running = false;
						}
					}
					else {
						System.out.println("User input invalid. Please try again.");
					}
				}
				catch(IOException ex) {
					System.out.println("File read/write error detected!" 
							+"/nAborting file save!.");
					running = false;
				}
				finally {
					if(!running) {
						if(saved) {
							System.out.println("\nFile succesfully saved!");
						}
						else {
							System.out.println("\nFile save unsuccessful!");
						}
					}
				}
			}
		}
		else {
			boolean saved = false;
			try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(savefile.getName()));) {
				output.writeObject(net);
				saved = true;
			}
			catch(IOException ex) {
				System.out.println("File read/write error detected!" 
						+"/nAborting file save!.");
			}
			finally {
				if(saved) {
					System.out.println("File succesfully saved!");
				}
				else {
					System.out.println("File save unsuccessful!");
				}
			}
		}
	}

	static void DeleteNet(File savefile) throws InputMismatchException{
		System.out.println("Are you SURE that you want to delete your savefile?"
				+ "\nEnter '1' to DELETE SAVEFILE!"
				+ "\nEnter '0' to CANCEL.");
		int choice = 0;
		boolean running = true;
		while(running) {
			try {
				choice = in.nextInt();
				if(choice >= 0 && choice <= 1) {
					running = false;
				}
				else {
					System.out.println("Input error! Please enter an integer value of either '1' or '0'.");
					in.nextLine();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Input error! Please enter an integer value of either '1' or '0'.");
				in.nextLine();
			}
			finally {
				if(!running) {
					if(choice == 0) {
						System.out.println("Savefile deletion aborted!");
					}
					else {
						if(savefile.exists()) {
							savefile.delete();
							System.out.println("\nSavefile deleted!");
						}
						else {
							System.out.println("\nNo savefile to delete! Aborting deletion.");
						}
					}
				}
			}
		}
		
		System.out.println("\nReturning to main menu...\n");
	}
	
	static void BuildNet() {
		int[] counts = new int[3];
		String[] prefixes = {"\nFirst off, ", "Secondly, ", "And finally, "};
		String body = "please decide how many neurons you would like in layer ";
		String[] layerNames = {", the input layer.", ", the hidden layer.", ", the output layer."};
		
		for(int i = 0; i < 3; i++) {
			System.out.println(prefixes[i] + body + i + layerNames[i]);
			counts[i] = in.nextInt();
		}
		
		net = new NeuralNetwork(counts);
		System.out.println("\nThe neural network has been initialized, now links between layers must be built!");
		net.buildLinks();
		System.out.println("\nNeural network build complete!");
		initialized = true;
	}
	
	static void DisplayStates() {
		int layer = WhichLayer(0);
		net.displayState(layer);
	}
	
	static void SetStates() throws InputMismatchException {
		int layer = WhichLayer(1);
		int range[] = WhichRange(layer, 0);
		boolean state = false;
		
		boolean running = true;
		while(running) {
			int stateChoice = 0;
			System.out.println("\nFinally, would you like to set neurons " + range[0] + " through " + range[1] + " of layer " + layer 
					+ " to excited or grounded?");
			System.out.println("Please enter '1' for EXCITED, or enter '0' for GROUNDED");
			try {
				stateChoice = in.nextInt();
				if(stateChoice == 0 || stateChoice == 1) {
					if(stateChoice == 1) {
						state = true;
						running = false;
					}
					else {
						state = false;
						running = false;
					}
				}
				else {
					System.out.println("Input error. An integer value of either '0' or '1' is required.");
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Input error. An integer value of either '0' or '1' is required.");
			}
			finally {
				if(!running) {
					if(state) {
						System.out.println("You've chosen to set neurons " + range[0] + " through " + range[1] + " of layer " + layer 
								+ " to EXCITED.\n");
					}
					else {
						System.out.println("You've chosen to set neurons " + range[0] + " through " + range[1] + " of layer " + layer 
								+ " to GROUNDED.\n");
					}
				}
			}
		}
		
		for(int i = range[0]; i < (range[1] + 1); i++) {
			net.getLayers().get(layer).getNeurons().get(i).setState(state);
			System.out.println(net.getLayers().get(layer).getNeurons().get(i).stateToString());
		}
		
		System.out.println("Operation complete. Returning to main menu...");		
	}
	
	static void SetThresholds() throws InputMismatchException {
		int layer = WhichLayer(2);
		int[] range = WhichRange(layer, 1);
		double threshold = 0;
		System.out.println("\nPlease enter a new threshold value of type double for neurons " + range[0] 
				+ " through " + range[1] + ", of layer " + layer + ".");
		
		
		boolean running = true;
		while(running) {
			try {
				threshold = in.nextDouble();
				if(threshold >= 0) {
					running = false;
				}
				else {
					System.out.println("Sorry, neurons require a non-negative threshold value of type double.");
					in.nextLine();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Sorry, neurons require a non-negative threshold value of type double.");
				in.nextLine();
			}
			finally {
				if(!running) {
					System.out.println("Setting thresholds for neurons " + range[0] + " through " + range[1] + " to " + threshold + ".");
				}
			}
 		}
		
		net.setThresholds(layer, range, threshold);
	}
	
	static void SetWeights() {
		int layer = WhichLayer(3);
		int[] neuronRange = WhichRange(layer, 2);
		System.out.println("What would you like to set the axon weight for neurons " + neuronRange[0] + "-" + neuronRange[1] + " in layer " + layer + " to?");
		double weight = 0;
		boolean running = true;
		while(running) {
			try {
				weight = in.nextDouble();
				if(weight < 0) {
					System.out.println("Error, weight must be a non-negative value of type double.");
					in.nextLine();
				}
				else {
					running = false;
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Error, weight must be a non-negative value of type double.");
				in.nextLine();
			}
			finally {
				if(!running) {
					System.out.println("You've chosen to set the axon weights of neurons " + neuronRange[0] + "-" + neuronRange[1] 
							+ " in layer " + layer +" to " + weight );
				}
			}
		}
		
		for(int i = neuronRange[0]; i <= neuronRange[1]; i++) {
			Neuron currentNeuron = net.getLayers().get(layer).getNeurons().get(i);
			for(int j = 0; j < currentNeuron.tabulateOutputs(); j++) {
				Axon currentAxon = currentNeuron.getOutputs().get(j);
				currentAxon.setWeight(weight);
				System.out.println("Axon " + j + " of neuron " + currentNeuron.getName() + " weight set to " + currentAxon.getWeight());
			}
		}
	}
	
	static void Propagate() {
		if(initialized) {
			net.getLayers().get(0).push(); 
			System.out.print("\n");
		}
		else {
			System.out.println("Neural network not yet initialized." 
					+ "\nPlease LOAD a network from file, or BUILD one from scratch.");
		}
	}
	
	static int WhichLayer(int op) throws InputMismatchException {
		int layer = 3;
		String[] phrase = {"\nFor which network layer would you like to display output states?", 
				"\nWhich neural network layer contains the neurons you would like to set the state of?", 
				"\nFor which neural network layer would you like to alter thresholds for?", 
				"\nWhich neural network layer contains the parent neurons of the axons you would like to set weights on?"};
		
		boolean running = true;
		while(running) {
			System.out.println(phrase[op]);
			System.out.println("Layer 0, \"Input\" (" + net.getLayers().get(0).getNeurons().size() + " neurons).");
			System.out.println("Layer 1, \"Hidden\" (" + net.getLayers().get(1).getNeurons().size() + " neurons).");
			System.out.println("Layer 2, \"Output\" (" + net.getLayers().get(2).getNeurons().size() + " neurons).");
			
			try {
				layer = in.nextInt();
				
				if(layer <= 2 || layer >= 0) {
					running = false;
				}
				else {
					System.out.println("Invalid input, single diget integer values allowed only. "
							+ "Please choose layer '0', '1', or '2'.");
					in.nextLine();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid input, single diget integer values allowed only. "
						+ "Please choose layer '0', '1', or '2'.");
				in.nextLine();
			}
			finally {
				if(!running) {
					System.out.println("You've chosen neural network layer " + layer + ".");
				}
			}
		}
		
		return(layer);
	}
	
	static int[] WhichRange(int layer, int op) throws InputMismatchException {
		String[] phrase = {"On what range of these neurons would you like to set states?", 
				"On what range of these neurons would you like to set thresholds?",
				"What range of these neurons contains the axons you would like to set weights for?"};
		int[] range = new int[2];
		int neuronCount = net.getLayers().get(layer).getNeurons().size();
		System.out.println("\nLayer " + layer + " comprises a total of " + neuronCount + " neurons.");
		System.out.println(phrase[op] + " [0-" + (neuronCount -1) + "]");
		
		boolean running = true;
		while(running) {
			System.out.println("Please enter the first neuron within the range of [0-" + (neuronCount -1) + "] "
					+ "you would like to set the state of.");
			try {
				range[0] = in.nextInt();
				if(range[0] >= 0 && range[0] < neuronCount) {
					running = false;
				}
				else {
					System.out.println("Input error. An integer value between 0 and " + (neuronCount -1) + " is required.");
					in.nextLine();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Input error. An integer value between 0 and " + (neuronCount -1) + " is required.");
				in.nextLine();
			}
			finally {
				if(!running) {
					System.out.println("Thank you. You've chosen " + range[0] + " as the first neuron.");
				}
			}
		}
		
		running = true;
		while(running) {
			System.out.println("\nPlease enter the last neuron within the range of [" + range[0] + "-" + (neuronCount - 1) +"] "
					+ "you would like to set the state of.");
			try {
				range[1] = in.nextInt();
				if(range[1] >= range[0] && range[1] < neuronCount) {
					running = false;
				}
				else {
					System.out.println("Input error. An integer value between " + range[0] + " and " + (neuronCount -1) + " is required.");
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Input error. An integer value between 0 and " + (neuronCount -1) + " is required.");
			}
			finally {
				if(!running) {
					System.out.println("Thank you. You've chosen " + range[1] + " as the last neuron.");
				}
			}
		}
		return(range);
	}
}

