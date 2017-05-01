/**
Neuronium.java
a.	Import java.io.*, java.util.InputMismatchException, and java.util.Scanner.
b.	Declare public class Neuronium.
c.	Declare static Scanner “in” as a new Scanner.
d.	Declare static NeuralNetwork “net”.
e.	Declare static Boolean “initialized” as false.
f.	Declare static void method main which throws IOException, ClassNotFoundException, and InputMismatchException.
i.	Print welcome message.
ii.	Declare String “savefileName” as “Neuronium.dat”.
iii.	Declare new File “savefile” with “savefileName” as an argument.
iv.	Declare Boolean “running” as true.
v.	While-loop, while “running” is true.
1.	Print main menu options for user.
2.	Try
a.	Declare int “choice” as the next int from Scanner “in”.
b.	If choice is greater than or equal to 0 AND less than or equal to 9
i.	Switch based on choice
1.	0 – Set running to false.
2.	1 – LoadNet with savefile as an argument.
3.	2 – SaveNet with savefile as an argument.
4.	3 – DeleteNet with savefile as an argument.
5.	4 - BuildNet.
6.	5 - DisplayStates.
7.	6 - SetStates.
8.	7 - SetThresholds.
9.	8 - SetWeights.
10.	9 – Propagate
c.	Otherwise, print error message and advance Scanner “in” to the next line.
3.	Catch InputMisMatchException “ex”.
a.	Print error message and advance Scanner “in” to the next line.
4.	Finally, 
a.	If running is false.
i.	Print exit message.
ii.	Close Scanner “in”.
iii.	Exit System.
g.	Declare static void method “LoadNet” which takes a File “savefile” as an argument and throws IOException and ClassNotFoundException.
i.	Declare Boolean “loaded” as false.
ii.	Try with ObjectInputStream “input” declared with “savefile” as an argument.
1.	Set “net” to the NeuralNetwork output of the readObject method of ObjectInputStream “input”.
2.	Set “loaded” to true.
iii.	Catch ClassNotFoundException “ex”.
1.	Print error message for user.
iv.	Catch IOException “ex”.
1.	Print error message for user.
v.	Finally,
1.	If loaded is true, print success message and set “initialized” to true.
2.	Otherwise, print error message.
h.	Declare static void method SaveNet which takes File “savefile as an argument and throws an IOException.
i.	If savefile exists, print warning message to user about overwriting file.
ii.	If user chooses, write “net” to “savefile” via ObjectOutputStream “output”.
iii.	If IOException “ex” is thrown, print error message.
iv.	Finally, depending on success, print appropriate message.
i.	Declare static void method DeleteNet which takes File “savefile” as an argument and throws an InputMismatchException.
i.	Confirm user wants to delete “savefile”.
ii.	If they do, delete the savefile and return to main menu.
iii.	Otherwise, return to main menu.
j.	Declare static void method “BuildNet”.
i.	Declare 3-element int array “counts”.
ii.	Prompt user for input on how many neurons they want in each layer. Store answers in “counts”.
iii.	Assign “net” a new NeuralNetwork with “counts” as an argument.
iv.	Set “initialized” to true.
k.	Declare static void method “DisplayStates”.
i.	Declare new int “layer” as the output of method “WhichLayer” with 0 as an argument.
ii.	Execute the “displayState” method of “net” with “layer” as an arugment.
l.	Declare static void method “SetStates” which throws an InputMismatchException.
i.	Declare int “layer” as the output of method “WhichLayer” with 1 as an argument.
ii.	Declare int array “range” as the output of method “WhichRange” with “layer” and 0 as an arugment.
iii.	Declare Boolean “state” as false.
iv.	Declare Boolean “running” as true, While-loop while “running” is true.
1.	Declare int “stateChoice” as 0.
2.	Prompt user for value of state they would like to set neurons to.
3.	Try to get appropriate input for int “stateChoice”, and set Boolean “state” based upon it (1 for excited, 0 for grounded).
4.	Catch InputMismatchException “ex”, and print error messages if appropriate.
5.	Finally, notify the user they have set the neurons within the selected range to either excited or grounded, depending on input.
v.	For loop from range element 0 to range element 1.
1.	Get neuron “i” of layer “layer” of NeuralNetwork “net” and execute the “setState” method with “state as an argument.
2.	Print appropriate message for user.
m.	Declare static void method “SetThresholds” which throws an InputMismatchException.
i.	Declare int “layer” as the output of method WhichLayer with 2 as an argument.
ii.	Declare int array range as the output of WhichRange with layer and 1 as arguments.
iii.	Declare double “threshold” as 0.
iv.	Prompt user to enter a threshold value for the selected range of neurons.
v.	Get the next input double, print appropriate error messages based on caught exceptions.
vi.	Execute the “setThresholds” method of NeuralNetwork “net” with “layer”, “range” and “threshold” as arguments.
n.	Declare static void method “SetWeights”.
i.	Declare int “layer” as the output of WhichLayer with 3 as an arugment.
ii.	Declare int array “neuronRange” as a the output of method WhichRange with “layer” and 2 as arguments.
iii.	Prompt user to enter a double value for Axon threshold in the selected range of Neurons.
iv.	Declare double “weight” as 0.
v.	Declare Boolean “running” as true, while-loop while “running” is true.
1.	Try to set “weight” to the next input double.
2.	Catch InputMismatchExceptions and print appropriate error messages.
3.	Finally, confirm for user their chosen input, if successful.
vi.	For-loop from neuronRange 0 to neuronRange 1.
1.	Declare Neuron “currentNeuron” as the Neuron at “i” of layer “layer” in NeuralNetwork “net”.
2.	For-loop from 0 to the size of currentNeuron’s “outputs” array list.
a.	Declare Axon “currentAxon” as the Axon at “j” of the outputs of “currentNeuron”.
b.	Execute the “setWeight” method of currentAxon with “weight” as an argument.
o.	Declare static void method “Propagate”
i.	If initialized is true,
1.	Execute the “push” method of layer 0 in NeuralNetwork “net”.
ii.	Otherwise, print error message.
p.	Declare static method “WhichLayer” which returns an int, takes an int “op” as an argument”, and throws an InputMismatchException.
i.	Declare int “layer” as 3.
ii.	Declare String array “phrase” as a sequence of phrases appropriate in context.
iii.	Declare Boolean running as true, while-loop while running is true.
1.	Print Message prompting user for layer choice input based on argument “op” and derived from String array “phrase”.
2.	Try to assing “layer” the next input int from Scanner “in”.
3.	Catch exceptions, print error messages.
4.	Finally, print message confirming the user’s choice.
iv.	Return “layer”.
q.	Declare static method “WhichRange” which returns an int array, takes ints “layer” and “op” as arguments, and throws an InputMismatchException.
i.	Declare String array “phrase” as appropriate phrases based on context.
ii.	Declare 2-element int array range.
iii.	Declare int “neuronCount” as the size of the neurons array of layer “layer” in NeuralNetwork “net”.
iv.	Declare Boolean “running” as true. While-loop while “running” is true.
1.	Prompt user for first digit in range.
2.	Try to assign next int from Scanner “in” to “range” 0.
a.	If value is appropriate, set running to false.
3.	Catch exceptions, print error messages.
4.	Finally, if running is false, confirm input with user.
v.	 Set “running” to true. While-loop while “running is true.
1.	Prompt user for second digit in range.
2.	Try to assign next int from Scanner “in” to “range” 1.
a.	If value is appropriate, set running to false.
3.	Catch exceptions, print error messages.
4.	Finally, if running is false, confirm input with user.
vi. Return "range".

 */

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

