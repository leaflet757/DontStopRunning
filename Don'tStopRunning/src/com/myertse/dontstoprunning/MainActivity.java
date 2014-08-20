package com.myertse.dontstoprunning;

import com.example.dontstoprunning.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

	private Game game;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        game = new Game(this);
        game.loadDependencies();
        game.loadContent();
        game.run();
        game.exit();
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    // TODO: Handle basic phone things
    
    @Override
    public void onPause() {
    	super.onPause();
    	game.onPause();
    	if (this.isFinishing()) {
    		game.exit();
    	}
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	game.onPause();
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	game.onStop();
    }
    
    @Override 
    public void onRestart() {
    	super.onRestart();
    	game.onRestart();
    }
}
