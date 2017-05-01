# Neuronium
Forward-propagation model of an artificial neural network, in java.

You'll want to run Neuronium.java to run the program. The menu options are pretty self explanatory.
First, build yourself a neural network. You can save it to file or load a previously built network as well.

There are three Neural Layers to each network. Top layer is layer 0, the "input" layer. The middle layer is layer 1, the "hidden" layer.
The third layer is layer 2, the "output" layer. There aren't any real inputs or outputs for these layers, so everything is just simulated.

The way it works is like this. Each Neuron in the top layer are connected to every Neuron in the middle layer via Axon links.
Each Neuron in the middle layer are connected to every Neuron in the output layer.

Neurons have an inherent state, which can be excited or grounded. Neurons state depends on their threshold attribute, compared to the sum
of their input Axons. Each Axon has an inherent weight attribute, which is multiplied by the state of the parent Neuron (0 for grounded,
1 for excited).Users can set the state of any Neuron manually, but in practice it's probably best to set the state of only input Neurons.
Axon weights should be set for all Axons, but are '1' by default. Thresholds should be set for the middle and output layers as well, and are
0 by default. 

Typical use would go as follows. Build a network -> Excite some or all of the input Neurons -> Set thresholds for the middle and output 
Neural Layers -> Set weights on the connecting Axon objects on the Input and Middle layers -> Forward Propagate and see what happens.

This program is pretty quick and dirty. The only practical purpose it really serves is demonstrational in nature. 

Have fun!

-reed
