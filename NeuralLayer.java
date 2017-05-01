/**
1.	Import java.io.* and java.util.ArrayList.
2.	Declare class NeuralLayer which implements Serializable.
3.	Declare private in “layerIndex”, private Neurallayer “distantEnd”, and private array lists of Neurons “distantNeurons” and “neurons”.
4.	Declare public constructor methods which set specified values and default values for each attribute.
5.	Declare public getter and setter methods for each attribute.
6.	Declare public method push which activates the push method for each Neuron in array list “neuron” via a for-loop.
7.	Declare public method “buildLinks” which takes NeuralLayer “distantEnd” as an argument. Private attribute “distantEnd” is set to the value of argument “distantEnd”. “distantNeurons” is set to the value of the “getNeurons” method of “distantEnd”. For loop through “neurons” and “distantNurons”, execute “buildLink” for each Neuron in “neurons” with each neuron in “distantNurons” as an argument. 
**/

import java.io.*;
import java.util.ArrayList;

public class NeuralLayer implements Serializable {
	//static final long serialVersionUID = 31337L;
	private int layerIndex;
	private NeuralLayer distantEnd;
	private ArrayList<Neuron> distantNeurons;
	private ArrayList<Neuron> neurons;
	
	
	public NeuralLayer(int size, NeuralLayer distantEnd, int layerIndex) {
		this.neurons = new ArrayList<Neuron>();
		
		for(int i = 0; i < size; i++) {
			neurons.add(new Neuron(i, layerIndex));
		}
		
		this.distantEnd = distantEnd;
		this.distantNeurons = distantEnd.getNeurons();
		this.layerIndex = layerIndex;
	}
	
	public NeuralLayer(int size, int layerIndex) {
		this.layerIndex = layerIndex;
		neurons = new ArrayList<Neuron>();
		for(int i = 0; i < size; i++) {
			neurons.add(new Neuron(i, layerIndex));
		}
	}
	
	public NeuralLayer() {
		this(0, 0);
	}
	
	public void push() {
		for(int i = 0; i < neurons.size(); i++) {
			neurons.get(i).push();
		}
	}
	
	public void buildLinks() {
		for(int i = 0; i < neurons.size(); i++)
			for(int j = 0; j < distantNeurons.size(); j++) {
				neurons.get(i).buildLink(distantNeurons.get(j));
			}
	}
	
	public void buildLinks(NeuralLayer distantEnd) {
		this.distantEnd = distantEnd;
		this.distantNeurons = distantEnd.getNeurons();
		for(int i = 0; i < neurons.size(); i++)
			for(int j = 0; j < distantNeurons.size(); j++) {
				neurons.get(i).buildLink(distantNeurons.get(j));
			}
	}
	
	public ArrayList<Neuron> getNeurons() {
		return(neurons);
	}
	
	public void checkInputs() {
		for(int i = 0; i < neurons.size(); i++) {
			System.out.println(neurons.get(i).tabulateInputs());
		}
	}
	
	public void setNeurons(int neuronCount) {
		for(int i = 0; i < neuronCount; i++) {
			neurons.add(new Neuron());
		}
	}
}
