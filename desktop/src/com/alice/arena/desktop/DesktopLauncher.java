package com.alice.arena.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.alice.arena.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		Launcher window = new Launcher();
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		window.frame.setTitle("Battlerena");
		window.frame.setResizable(false);
		window.btnLaunch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((String)window.comboBox.getSelectedItem() == "1024 x 768") {
					Core.WIDTH = 1024;
					Core.HEIGHT = 768;
				
				}else if((String)window.comboBox.getSelectedItem() == "640 x 480") {
					Core.WIDTH = 640;
					Core.HEIGHT = 480;
				}
				
				config.setTitle("Battlerena: Maybe a game?");
				config.setWindowedMode(Core.WIDTH, Core.HEIGHT);
				config.useVsync(window.chckbxVsync.isSelected());
				window.frame.setVisible(false);
				new Lwjgl3Application(new Core(), config);
				
			}
		});
		
		
		window.frame.setVisible(true);
		
		
		
	}
}
