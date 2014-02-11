package proteinmusic;

import java.util.*;
import java.io.*;

// Class to read in and partially translate nucleotide sequences from given files
public class Input {

    private String file;
    private ArrayList<Character> nuc;
    private ArrayList<AminoAcid> bass;
    private BufferedReader reader;
    private int ch;
    private Mapping mapping;
    private Codon newCodon;

    public Input(String fileName) {
        mapping = new Mapping();
        file = fileName;
        nuc = new ArrayList();
        bass = new ArrayList<AminoAcid>();
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            System.exit(0);
        }

        try {
            int i = 0;
            while ((ch = reader.read()) != -1) {
                if (ch=='A' || ch=='C' || ch=='G' || ch=='T') {
                    char c = (char)ch;
                    nuc.add(c);

                    if (i >= 2) {
                        int s = nuc.size();
                        newCodon = new Codon(nuc.get(s-3), nuc.get(s-2),
                                                nuc.get(s-1));
                        bass.add(new AminoAcid(newCodon, mapping));
                        i = 0;
                    } else {
                        i++;
                    } // if else
                } // if
            } // while
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            System.exit(0);
        }

        //Convert AA's

    } // Method - Input

    public ArrayList getNuc() {
        return nuc;
    } // Method - getNuc

    public ArrayList<AminoAcid> getBass() {
        return bass;
    } // Method - getBass
} // Class - Input