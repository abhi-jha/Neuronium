/**
 * 
1.	Import java.io.*.
2.	Declare public class Axon, which implements Serializable.
3.	Declare private int “double”, private doubles “output” and “weight”, private Neurons “root” and “Branch”, along with private Boolean “activated”.
4.	Declare constructors that assign both specified and default values for all attributes.
5.	Declare setters and getters for attributes.
6.	Declare method “delete”, which removes references to the axon from the neurons stored in “branch” and “root”. Then set attributes “branch” and “root” to null.
7.	Declare method “push”, which takes Boolean “state” as an argument. If either “branch” or “root” are null, delete the axon. Otherwise, set “activated” to true. If “state” is true, set “input” to 1. Otherwise, set “input” to 0. “output” is assigned the value of “input” times “weight”. Execute the “push” method for neuron at “branch”.

 */
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
