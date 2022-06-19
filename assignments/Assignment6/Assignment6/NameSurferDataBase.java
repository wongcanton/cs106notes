/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 * @param filename: String representing filename.
 * @return NameSurferDatabase
 */
	public NameSurferDataBase(String filename) {
		database = new HashMap<String, NameSurferEntry>();
		String line = "";
		try {
			rd = new BufferedReader(new FileReader(filename));
			while (true) {
				line = rd.readLine();
				if (line == null) break;
				holderEntry = new NameSurferEntry(line);
				database.put(holderEntry.getName(), holderEntry);
			}
		} catch (IOException e) {
			throw new Error(e);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 * @param name: String of a name to search for in the database.
 * @return entry: NameSurferEntry for the given name, otherwise null.
 */
	public NameSurferEntry findEntry(String name) {
		return database.get(name);
	}
	
	/* Instance variables. */
	private HashMap<String, NameSurferEntry> database;
	private BufferedReader rd;
	private NameSurferEntry holderEntry;
}

