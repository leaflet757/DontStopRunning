package com.myertse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

import android.util.Log;

import com.myertse.framework.FileIO;
import com.myertse.framework.Game;

public class HighScoreManager {

	Game game;

	private final String fileName = "swag";

	private int highScore;
	private final int NOT_LOADED = -1;

	public HighScoreManager(Game game) {
		this.game = game;
		highScore = loadHighScoreFromFile(); // initial value
	}

	public int gethighScore() {
		if (highScore == NOT_LOADED) {
			highScore = loadHighScoreFromFile();
		}
		return highScore;
	}

	public int loadHighScoreFromFile() {
		
		FileIO io = game.getFileIO();
		try {
			InputStream stream = io.readFile(fileName);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			if ((line = reader.readLine()) != null) {
				line = line.trim();
				highScore = Integer.parseInt(line);
			}
		} catch (FileNotFoundException e) {
			saveHighScore(0); // create a newFile with high score of 0
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return highScore;
	}

	public void saveHighScore(int newHighScore) {
		highScore = newHighScore;
		try {
			FileIO io = game.getFileIO();
			File file = new File(io.getExternalStoragePath() + fileName);
			if (!file.exists()) {
				file.createNewFile();
			} 
			OutputStream os = io.writeFile(fileName);
			byte[] data = String.valueOf(highScore).getBytes();
			os.write(data);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
