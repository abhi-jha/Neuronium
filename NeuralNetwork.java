/**
1.	Import java.io.* and java.util.ArrayList.
2.	Declare public class NeuralNetwork which implements Serializable.
3.	Declare array list of NeuralLayers “layers”.
4.	Declare constructor method which takes an int array “counts” as an argument and sets “layers” to a list of three NeuralLayer objects. The size of each NeuralLayer object is decided by array “counts”, depending on the element.
5.	Declare method “buildLinks” which executes the “buildLinks” method for each element of “layers” and prints an appropriate message to the user.
6.	Declare getLayers method which returns “layers”.
7.	Declare “displaySatate” method which takes an int “layer” as an argument. 
8.	Declare array list “neurons” as the output of method “getNeurons” of NeuralLayer “layer” of array list “layers”.
9.	For loop from 0 to the size of “neurons.
10.	Print the output of each Neurons “stateToString” method appropriately for the user.
11.	Declare method “setThresholds” which takes an int “layer, and int array “range” and a double “threshold” as arguments.
12.	Declare array list “neurons” as the output of the “getNeurons” method of the NeuralLayer object at element “layer” of array list “layers”.
13.	For loop from “range” element 0 to “range” element 1.
14.	At each iteration, get the Neuron at that element of “neurons” and set it’s threshold to the value of “threshold” via the “setThreshold” method.
15.	Print an appropriate message for the user.
 */

import java.io.*;
import java.util.ArrayList;

public class NeuralNetwork implements Serializable{
	//static final long serialVersionUID = 31337L;
	private ArrayList<NeuralLayer> layers;
	
	public NeuralNetwork(int[] counts) {
		layers = new ArrayList<NeuralLayer>(3);
		for(int i = 0; i < 3; i++) {
			layers.add(new NeuralLayer(counts[i], i));
		}
	}
	
	public void buildLinks() {
		for(int i = 0; i < 2; i++) {
			layers.get(i).buildLinks(layers.get(i+1));
		}
		System.out.println("Links built!");
	}
	
	public ArrayList<NeuralLayer> getLayers() {
		return(layers);
	}
	
	public void displayState(int layer) {
		ArrayList<Neuron> neurons = layers.get(layer).getNeurons();
		System.out.println("\nCurrent state for neurons in neural network layer " + layer + ": \n");
		for(int i = 0; i < neurons.size(); i++) {
			if(i % 4 == 0 && i > 0) {
				System.out.print("\n");
			}
			System.out.print(neurons.get(i).stateToString());
			if(i < (neurons.size() - 1)) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
	}
	
	public void setThresholds(int layer, int[] range, double threshold) {
		ArrayList<Neuron> neurons = layers.get(layer).getNeurons();
		
		for(int i = range[0]; i <= range[1]; i++) {
			neurons.get(i).setThreshold(threshold);
			System.out.println(neurons.get(i).getName() + " Threshold: " + neurons.get(i).getThreshold());
		}
		
		System.out.println("Threshold attributes for neurons " + range[0] + " through " + range[1] +
				" in layer " + layer + " have been set to " + threshold + ".");
	}
}
