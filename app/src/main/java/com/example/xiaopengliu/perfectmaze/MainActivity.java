package com.example.xiaopengliu.perfectmaze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Spinner spinner1, spinner2;
	private Button btnStartGame;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
		
		
	}
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner_mode);
		spinner2 = (Spinner) findViewById(R.id.spinner_level);
		
		btnStartGame = (Button) findViewById(R.id.btnSubmit);

		btnStartGame.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {

				Toast.makeText(MainActivity.this,
						"OnClickListener : " + 
						"\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
						"\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
						Toast.LENGTH_SHORT).show();
				
				
		    	Intent game = new Intent(MainActivity.this,Game.class);  //create an Intent to launch the Game Activity
				Maze maze = MazeCreator.getMaze(1);    //use helper class for creating the Maze
				game.putExtra("maze", maze);			//add the maze to the intent which we'll retrieve in the Maze Activity
				startActivity(game);

			}

		});

	}
}
