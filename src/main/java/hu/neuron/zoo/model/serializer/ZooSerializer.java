package hu.neuron.zoo.model.serializer;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import hu.neuron.zoo.model.zoo.Zoo;

import java.io.*;

public class ZooSerializer {

    private Zoo zoo;
    String file;

    public ZooSerializer(Zoo o, String f) {
        zoo = o;
        file=f;
    }

    public void Serializing(){
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(zoo);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /tmp/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public Zoo Deserializing(){
        Zoo z;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            z = (Zoo) in.readObject();
            in.close();
            fileIn.close();
            return z;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Zoo class not found");
            c.printStackTrace();
            return null;
        }
    }

}


