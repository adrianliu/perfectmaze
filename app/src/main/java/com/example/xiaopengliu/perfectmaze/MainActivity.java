package com.example.xiaopengliu.perfectmaze;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
	
	private Spinner spinner1, spinner2;
	private Button btnStartGame, btnExit;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
		
		
	}

	public void showExit() {
		new AlertDialog.Builder(this)
				.setTitle("Exit Game")
				.setMessage("Are you sure you want to exit this game ?")
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// continue with delete
						System.exit(0);
					}
				})
				.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// do nothing
					}
				})
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();

	}
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner_mode);
		spinner2 = (Spinner) findViewById(R.id.spinner_level);
		
		btnStartGame = (Button) findViewById(R.id.btnSubmit);
		btnExit = (Button) findViewById(R.id.btnExit);

		btnStartGame.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {

				Toast.makeText(MainActivity.this,
						"OnClickListener : " + 
						"\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
						"\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
						Toast.LENGTH_SHORT).show();
				
				
		    	Intent game = new Intent(MainActivity.this,Game.class);  //create an Intent to launch the Game Activity

				String levelStr = String.valueOf(spinner2.getSelectedItem());
				int level = 1;
				if(levelStr.equals("Level 1")) {
					level = 1;
				} else if(levelStr.equals("Level 2")) {
					level = 2;
				} else {
					level = 3;
				}

				Maze maze = MazeCreator.getMaze(level);    //use helper class for creating the Maze
				game.putExtra("maze", maze);			//add the maze to the intent which we'll retrieve in the Maze Activity
				startActivity(game);

			}

		});


		btnExit.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {

				showExit();
			}

		});

	}
}
