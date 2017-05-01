
import java.io.*;
import java.util.ArrayList;

public class Neuron implements Serializable {
	//static final long serialVersionUID = 31337L;
	private int neuronIndex;
	private int layerIndex;
	private ArrayList<Axon> inputs;
	private ArrayList<Axon> outputs;
	private double threshold;
	private double inputSum;
	private boolean excited;
	
	public Neuron(double threshold, double inputSum, boolean excited, int neuronIndex, int layerIndex) {
		this.threshold = threshold;
		this.inputSum = inputSum;
		this.excited = excited;
		inputs = new ArrayList<Axon>();
		outputs = new ArrayList<Axon>();
		this.neuronIndex = neuronIndex;
		this.layerIndex = layerIndex;
	}
	
	public Neuron(int neuronIndex, int layerIndex) {
		this(0, 0, false, neuronIndex, layerIndex);
	}
	
	public Neuron() {
		this(0, 0, false, 0, 0);
	}
	
	public void compute() {
		if(excited) {
			System.out.print("\nNeuron " + getName() + " going from excited to... ");
		}
		else {
			System.out.print("\nNeuron " + getName() + " going from grounded to... ");
		}
		
		inputSum = 0;
		for(int i = 0; i < inputs.size(); i++) {
			inputSum += inputs.get(i).getOutput();
		}
		if(inputSum >= threshold) {
			excited = true;
			System.out.print("EXCITED!");
		}
		else {
			excited = false;
			System.out.print("GROUNDED!");
		}
	}
	
	public void push() {
		//set up boolean to make sure all of the input axions have reported before pushing forward
		if(layerIndex > 0) {
			compute();
		}
		
		for(int i = 0; i < outputs.size(); i++) {
			outputs.get(i).push(excited);
		}
	}
	
	//Accessors
	
	public double getThreshold() {
		return(threshold);
	}
	
	public double getInputSum() {
		return(inputSum);
	}
	
	public boolean getState() {
		return(excited);
	}
	
	public String stateToString() {
		if(excited) {
			return("L" + layerIndex + "N" + neuronIndex + ": Excited ");
		}
		else {
			return("L" + layerIndex + "N" + neuronIndex + ": Grounded");
		}
	}
	
	public String getName() {
		return("L" + layerIndex + "N" + neuronIndex);
	}
	
	public int getLayerIndex() {
		return(layerIndex);
	}
	
	public int getNeuronIndex() {
		return(neuronIndex);
	}
	
	public int tabulateInputs() {
		return(inputs.size());
	}
	
	public int tabulateOutputs() {
		return(outputs.size());
	}
	
	public ArrayList<Axon> getOutputs() {
		return(outputs);
	}

	
	//Mutators
	public void setState(boolean state) {
		this.excited = state;
	}
	public void setThreshold(double threshold){
		this.threshold = threshold;
	}
	
	public void setInputSum(double inputSum) {
		this.inputSum = inputSum;
	}
	
	public void buildLink(Neuron distantEnd) {
		Axon link = new Axon(this, distantEnd);
		outputs.add(link);
		System.out.println("Link built between L" + layerIndex + "N" + neuronIndex + 
				", and L" + distantEnd.getLayerIndex() + "N" + distantEnd.getNeuronIndex());
	}
	
	public void receiveLink(Axon link) {
		inputs.add(link);
	}
	
	public void removeInput(Axon link) {
		inputs.remove(link);
	}
	
	public void removeOutput(Axon link) {
		outputs.remove(link);
	}
}
