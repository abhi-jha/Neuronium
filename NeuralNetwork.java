
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
