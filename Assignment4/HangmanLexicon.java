/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
	private ArrayList <String> lexicon = new ArrayList <String> ();

	public HangmanLexicon() {
		
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true){
				String line = rd.readLine();
				if (line == null) break;
				lexicon.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}

	}
	
	public String getWord(int index) {
		return lexicon.get(index);
 
	}
	
	public int getWordCount() {
		return lexicon.size();
	}
	
}
