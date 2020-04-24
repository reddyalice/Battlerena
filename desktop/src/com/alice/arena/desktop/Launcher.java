package com.alice.arena.desktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class Launcher {

	public JFrame frame;
	public JComboBox comboBox;
	public JCheckBox chckbxVsync;
	public JButton btnLaunch;

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 246, 134);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnLaunch = new JButton("Launch");
		btnLaunch.setBounds(62, 68, 117, 25);
		panel.add(btnLaunch);
		
		JLabel lblViewportSize = new JLabel("Viewport Size :");
		lblViewportSize.setBounds(62, 6, 117, 15);
		panel.add(lblViewportSize);
		
		comboBox = new JComboBox();
		comboBox.addItem("1024 x 768");
		comboBox.addItem("640 x 480");
		comboBox.setBounds(62, 23, 117, 24);
		panel.add(comboBox);
		
		chckbxVsync = new JCheckBox("VSync");
		chckbxVsync.setBounds(64, 49, 115, 18);
		panel.add(chckbxVsync);
		
	
		
		
	}
}
