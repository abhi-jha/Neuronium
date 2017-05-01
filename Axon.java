
import java.io.*;

public class Axon implements Serializable {
	//static final long serialVersionUID = 31337L;
	private int input;
	private double output;
	private double weight;
	private Neuron root;
	private Neuron branch;
	private boolean activated;
	
	public Axon(double weight, Neuron root, Neuron branch) {
		this.weight = weight;
		this.root = root;
		this.branch = branch;
		this.branch.receiveLink(this);
	}
	
	public Axon(Neuron root, Neuron branch) {
		this.weight = 1;
		this.root = root;
		this.branch = branch;
		this.branch.receiveLink(this);
	}
	
	public Axon(double weight) {
		this.weight = weight;
	}
	
	public Axon() {
		this(1);
	}
	
	public void push(boolean state) {
		//first, makes sure the axon is connected at both ends.
		//if not, delete the axon.
		if(branch == null || root == null) {
			delete();
		}
		else {
			activated = true;
			if(state) {
				input = 1;
			}
			else {
				input = 0;
			}
			
			output = input * weight;
			branch.push();
		}
	}
	
	public void delete() {
		branch.removeInput(this);
		root.removeOutput(this);
		branch = null;
		root = null;
	}
	
	//Accessors
	
	public boolean getActivated() {
		return(activated);
	}
	
	public double getWeight() {
		return(weight);
	}
	
	public double getOutput() {
		return(output);
	}
	
	public Neuron getRoot() {
		return(root);
	}
	
	public Neuron getBranch() {
		return(branch);
	}
	
	//Mutators
	
	public void reset() {
		activated = false;
	}
	
	public void setInput(int input) {
		this.input = input;
		output = input * weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
		output = input * weight;
	}
	
	public void setRoot(Neuron root) {
		this.root = root;
	}
	
	public void setBranch(Neuron branch) {
		this.branch = branch;
		this.branch.receiveLink(this);
	}
}
