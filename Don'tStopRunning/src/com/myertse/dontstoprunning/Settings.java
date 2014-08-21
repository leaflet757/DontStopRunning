package com.myertse.dontstoprunning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.myertse.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	// this is for testing purposes
	// saves the last ship location before exiting
	public static int[] savedLocation = new int[] { 100, 100 };

	public static void load(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readFile(".projectinfinitypoc")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i = 0; i < savedLocation.length; i++) {
				savedLocation[i] = Integer.parseInt(in.readLine());
			}
		} catch (IOException e) {
			// :( It's ok we have defaults
		} catch (NumberFormatException e) {
			// :/ It's ok, defaults save our day
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	// in a AAA title, warn the user about saving/loading failure
	public static void save(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeFile(".projectinfinitypoc")));
			out.write(Boolean.toString(soundEnabled));
			for (int i = 0; i < savedLocation.length; i++) {
				out.write(Integer.toString(savedLocation[i]));
			}
		} catch (IOException e) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
	
	/*
	 * Call before save. Saves the current (x,y) location of the ship.
	 */
	public static void saveLocation(int x, int y) {
		savedLocation[0] = x;
		savedLocation[1] = y;
	}

}
