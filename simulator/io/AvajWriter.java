package simulator.io;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AvajWriter {
    public AvajWriter(String str) {
        try {
            File simulation = new File("simulation.txt");
            PrintWriter printer = new PrintWriter(new FileWriter(simulation, true));
            printer.write(str);
            printer.write("\n");
            printer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
