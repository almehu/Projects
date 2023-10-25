import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import java.io.*;
// import sun.audio.*;
import java.io.InputStream.*;
// import sun.audio.AudioPlayer.*;  
// import sun.audio.AudioStream.*;  

public class Window extends JPanel implements KeyListener, MouseListener, ActionListener
{

	private Image white, start, help, introSelection, fullSelection, backgroundSelection, battleScene;
	private Image map, settings, inventory, party, shop;
	private Image boy, girl;
	private Image exit, back, next, nextKey, select, view, reset, swap, switchPos, switchPos2, up, down;
	// props
	private Image egg, KaijuBall, Potion, GreatPotion, SuperPotion;
	// Alerts (battle)
	private Image kaijuAlert, challengerAlert;
	// Scenes
	private Image townScene, wild1Scene;
	// Kaiju Squares
	private Image FlareonSquare, VaporeanSquare, LeafeonSquare;
	// Kiaju
	private Image Flareon, Growlithe, Vulpix, Vaporean, Marill, Squirtle, Leafeon, Bulbasaur, Shaymin;
	private Image pos1Type, pos2Type; // KAIJU IMAGES

	// Characters
	private MaleTrainer boy1;
	private FemaleTrainer girl1;
	private boolean male;
	private boolean female;

	private int width;
	private int height;
	private int centerCounter, genderCounter, wildKaijuCounter, battleCounter, runOnce, neverAgain, addToStatsCounter, catchCounter, pos1Initialization;
	// Stats
	private int baseAttack, baseDefense, baseHP, maxExp, currentHP, currentExp, trainerLevel;
	private int battleAttack, battleDefense, battleHP, elementalFactor;
	private int wildBaseAttack, wildBaseDefense, wildBaseHP, wildCurrentHP, kaijuLevel;
	private int wildBattleAttack, wildBattleDefense, wildBattleHP, wildElementalFactor;

	// Cash and objects
	private int cash = 50; // starts with $50
	private int balls = 3; // starts with one Kiaju ball, costs 20
	private int potions = 0; // starts with 0, costs 15
	private int greatPotions = 0; // starts with 0, costs 25
	private int superPotions = 0; // starts with 0, costs 35
	private int ballCost = 20;
	private int potionCost = 15;
	private int greatPotionCost = 25;
	private int superPotionCost = 35;
	private int total;
	private int buyBalls = 0;
	private int buyPotions = 0;
	private int buyGreatPotions = 0;
	private int buySuperPotions = 0;

	// coordinates starting for challengers
	private int challenger1X, challenger1Y, challenger2X, challenger2Y;

	// Kaiju type
	private String starterType;
	private String kaijuType, wildKaijuType, elementalType; // KAIJU TYPES

	// Directions to move
	private boolean walkLeftFlag, walkRightFlag, walkUpFlag, walkDownFlag, allowedUpFlag, allowedDownFlag, allowedLeftFlag, allowedRightFlag;
	// Keys
	private boolean helpFlag, mapFlag, partyFlag, inventoryFlag, settingsFlag, exitFlag, shopFlag;
	// Selection
	private boolean fireSelection, waterSelection, grassSelection, fireCenter, waterCenter, grassCenter;
	// States and battle
	private boolean battleFlag, battleEndFlag, battleStart, inGrass, pos1Flag, pos2Flag, pos1TurnFlag, pos2TurnFlag, move, continuedBefore, continued, continuedAgain, leveledUp;
	private boolean attackOption, itemsOption, runOption, showMoves, showItems, run, wild, opponent;
	// Screen
	private boolean startScreen, genderSelection, selectionScreen1, selectionScreen2, selectionScreen3, townScreen, map1Screen, map2Screen;
	private boolean doNextThing, nextThing;

	// catch
	private boolean caught, fullParty;
	// shop
	private boolean bought;

	private Image partyPos1, partyPos2, partyPos3, partyPos4;
	private String partyPos1Type, partyPos2Type, partyPos3Type, partyPos4Type;
	private boolean pos1Filled, pos2Filled, pos3Filled, pos4Filled;
	private boolean switch12, switch34, switch13, switch24;


	// CODE
	public Window () { 

		super();

		centerCounter = 1; // Starts with fire as the center selection
		genderCounter = 1; // Starts as male
		battleCounter = 1; // Battle starts with pos1
		runOnce = 1; // counter to only run once in game
		neverAgain = 1;
		catchCounter = 1;
		pos1Initialization = 1;

		white = (new ImageIcon("white.png")).getImage();
		map = (new ImageIcon("map.png")).getImage();
		settings = (new ImageIcon("settings.png")).getImage();
		party = (new ImageIcon("party.png")).getImage();
		inventory = (new ImageIcon("inventory.png")).getImage();
		shop = (new ImageIcon("shop.png")).getImage();

		// Buttons and Misc
		exit = (new ImageIcon("exit.png")).getImage();
		back = (new ImageIcon("back.png")).getImage();
		up = (new ImageIcon("up.png")).getImage();
		down = (new ImageIcon("down.png")).getImage();
		nextKey = (new ImageIcon("nextButton.png")).getImage();
		next = (new ImageIcon("next.png")).getImage();
		select = (new ImageIcon("select.png")).getImage();
		view = (new ImageIcon("view.png")).getImage();
		reset = (new ImageIcon("reset.png")).getImage();
		swap = (new ImageIcon("swap.png")).getImage();
		switchPos = (new ImageIcon("switch.png")).getImage();
		switchPos2 = (new ImageIcon("switch2.png")).getImage();
		// props
		egg = (new ImageIcon("egg.gif")).getImage();
		KaijuBall= (new ImageIcon("KaijuBall.png")).getImage();
		Potion = (new ImageIcon("Potion.png")).getImage();
		GreatPotion = (new ImageIcon("Greatpotion.png")).getImage();
		SuperPotion = (new ImageIcon("SuperPotion.png")).getImage();

		// CHaracters
		boy = (new ImageIcon("boy1.gif")).getImage();
		girl = (new ImageIcon("girl1.gif")).getImage();

		// Alerts (Battle)
		kaijuAlert = (new ImageIcon("kaijuAlert2.png")).getImage();
		challengerAlert = (new ImageIcon("challengerAlert.png")).getImage();

		// Screens
		start = (new ImageIcon("titleScreen.png")).getImage();
		introSelection = (new ImageIcon("introSelection.png")).getImage();
		fullSelection = (new ImageIcon("fullSelection.png")).getImage();
		backgroundSelection = (new ImageIcon("backgroundSelection.png")).getImage();
		help = (new ImageIcon("help.png")).getImage();
		townScene = (new ImageIcon("townScene.png")).getImage();
		wild1Scene = (new ImageIcon("wild2Map.png")).getImage();
		battleScene = (new ImageIcon("battleScene.png")).getImage();

		// Kaiju 
		// Eevee = (new ImageIcon("images/Eevee.png")).getImage();
		FlareonSquare = (new ImageIcon("FlareonSquare.png")).getImage();
		VaporeanSquare = (new ImageIcon("VaporeanSquare.png")).getImage();
		LeafeonSquare = (new ImageIcon("LeafeonSquare.png")).getImage();
		Flareon = (new ImageIcon("Flareon.png")).getImage();
		Vaporean = (new ImageIcon("Vaporean.png")).getImage();
		Leafeon = (new ImageIcon("Leafeon.png")).getImage();
		Growlithe = (new ImageIcon("Growlithe.png")).getImage();
		Vulpix = (new ImageIcon("Vulpix.png")).getImage();
		Marill = (new ImageIcon("Marill.png")).getImage();
		Squirtle = (new ImageIcon("Squirtle.png")).getImage();
		Bulbasaur = (new ImageIcon("Bulbasaur.png")).getImage();
		Shaymin = (new ImageIcon("Shaymin.png")).getImage();

		challenger1X = 100;
		challenger1Y = 100;

		//Characters
		boy1 = new MaleTrainer(0, 0);
		girl1 = new FemaleTrainer(0, 0);

		// BOOLEANS
		// Directions
		walkUpFlag = false;
		walkDownFlag = false;
		walkLeftFlag = false;
		walkRightFlag = false;
		allowedUpFlag = false;
		allowedDownFlag = false;
		allowedLeftFlag = false;
		allowedRightFlag = false;
		// Maps
		startScreen = true;
		selectionScreen1 = false;
		selectionScreen2 = false;
		selectionScreen3 = false;
		map1Screen = false;
		map2Screen = false;
		// Buttons
		helpFlag = false;
		exitFlag = false;
		//States
		battleFlag = false;
		battleEndFlag = false;
		battleStart = false;
		attackOption = false;
		itemsOption = false;
		runOption = false;
		inGrass = false;

		fireCenter = true;
		waterCenter = false;
		grassCenter = false;

		male = true;
		female = false;
		genderSelection = false;

		//party
		partyPos1 = null;
		partyPos2 = null;
		partyPos3 = null;
		partyPos4 = null;
		partyPos1Type = null;
		partyPos2Type = null;
		partyPos3Type = null;
		partyPos4Type = null;

		// backup
		baseAttack = 3;
		baseDefense = 3;
		baseHP = 3;

		// maxExp = baseAttack + baseDefense + baseHP;
		// currentHP = baseHP;
		currentExp = 0;
		trainerLevel = 1;
		int random= (int) Math.random() * 1 - 1;
		kaijuLevel = trainerLevel; // random
		wildKaijuCounter = 0;
		addToStatsCounter = 0;

		pos1Filled = false;
		pos2Filled = false;
		pos3Filled = false;
		pos4Filled = false;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

		width = getWidth();
		height = getHeight();

		// Exit button width and height
		int exitWidth = exit.getWidth(null);
		int exitHeight = exit.getHeight(null);

		while (pos1Initialization  == 1)
		{
			// Kiaju type 
			if (fireCenter == true)
			{
				pos1Type = Flareon;
			}
			else if (waterCenter == true)
			{
				pos1Type = Vaporean;
			}
			else if (grassCenter == true)
			{
				pos1Type = Leafeon;
			}

			partyPos1 = pos1Type;
			pos1Initialization++;
		}

		// START SCREEN
		if (startScreen == true)
		{
			setBackground(Color.WHITE);
			g.drawImage(start, 0, 0, width, height, null);

			if (helpFlag == true)
			{
				// Background
				g.drawImage(help, 0, 0, width, height, null);
				// Exit
				g.drawImage(exit, 622, 8, 35, 35, null);
			}
		}

		else
		{	// Draw Character

			setBackground(Color.WHITE);

			if (genderSelection == true)
			{
				// Background
				g.drawImage(white, 0, 0, width, height, null);

				// Draw title
				g.setFont(new Font("Monospaced", Font.BOLD, 50));
				g.setColor(Color.BLACK);
				g.drawString("Choose a character", 70, 150);

				// Characters
				if (male == true)
				{
					g.drawImage(boy, width / 2 - 42, height / 2 - 42, 84, 84, null);
					g.drawImage(girl, width / 2 - 30 + 75, height / 2 - 30 - 75, 60, 60, null);

					g.drawImage(swap, width / 2 - 96 + 200, height / 2 + 75, 64, 64, null); // button
				}
				if (female == true)
				{
					g.drawImage(girl, width / 2 - 42, height / 2 - 42, 84, 84, null);
					g.drawImage(boy, width / 2 - 30 + 75, height / 2 - 30 - 75, 60, 60, null);

					g.drawImage(swap, width / 2 - 96 + 200, height / 2 + 75, 64, 64, null); // button
				}

				g.drawImage(select, width / 2 - 96, height / 2 + 75, 192, 64, null); // button
			}

			if (selectionScreen1 == true)
			{
				// Background
				g.drawImage(introSelection, 0, 0, width, height, null);
				// Next
				g.drawImage(nextKey, 250, 580, 50, 50, null);
			}
			if (selectionScreen2 == true)
			{
				int imgWidth = fullSelection.getWidth(null);
				int imgHeight = fullSelection.getHeight(null);

				// Background
				g.drawImage(fullSelection, 0, 0, width, height, null);

				// Kaiju
				if (fireCenter == true)
				{
					fireCenterSelection(g);
				}
				if (waterCenter == true)
				{
					waterCenterSelection(g);
				}
				if (grassCenter == true)
				{
					grassCenterSelection(g);
				}

			}
			if (selectionScreen3 == true)
			{
				int imgWidth = white.getWidth(null);
				int imgHeight = white.getHeight(null);

				// Background
				g.drawImage(white, 0, 0, width, height, null);
				// Exit
				g.drawImage(exit, 622, 8, 35, 35, null);

				if (fireCenter == true)
				{
					fireView(g);

					stats();

					// elementalType
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ELEMENT: " + elementalType, 300, 225);
					// Attack
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ATTACK: " + Integer.toString(baseAttack), 300, 300);
					// Defense
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("DEFENSE: " + Integer.toString(baseDefense), 300, 375);
					// HP
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("HP: " + Integer.toString(baseHP), 300, 450);

				}
				if (waterCenter == true)
				{
					waterView(g);

					stats();

					// elementalType
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ELEMENT: " + elementalType, 300, 225);
					// Attack
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ATTACK: " + Integer.toString(baseAttack), 300, 300);
					// Defense
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("DEFENSE: " + Integer.toString(baseDefense), 300, 375);
					// HP
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("HP: " + Integer.toString(baseHP), 300, 450);
				}
				if (grassCenter == true)
				{
					grassView(g);

					stats();

					// elementalType
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ELEMENT: " + elementalType, 300, 225);
					// Attack
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("ATTACK: " + Integer.toString(baseAttack), 300, 300);
					// Defense
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("DEFENSE: " + Integer.toString(baseDefense), 300, 375);
					// HP
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("HP: " + Integer.toString(baseHP), 300, 450);
				}
				// select is going to make all centers false again? no make selectionScreen2 and 3 false and map1 true

			}

			if (selectionScreen1 == false && selectionScreen2 == false && selectionScreen3 == false)
			{
				setBackground(Color.BLACK);

				// Screens
				if (map1Screen == true)
				{
					g.drawImage(townScene, 0, 0, width, height, null);
				}
				if (map2Screen == true)
				{
					g.drawImage(wild1Scene, 0, 0, width, height, null);
				}



				if (genderSelection == false)
				{
					// Draw Character
					if (male == true)
					{
						boy1.draw(g,this);
					}
					else if (female == true)
					{
						girl1.draw(g, this);
					}

					//challengerRight.draw(g, this);
				}



				// Battle
				if (battleFlag == true)
				{
					int statsBoxCounter = 0;

					g.drawImage(kaijuAlert, 0, 0, width, height, null);
					if (battleStart == true)
					{
						doNextThing = true;
						// Background
						g.drawImage(battleScene, 0, 0, width, height, null);

						if (battleCounter == 1 && battleEndFlag == false)
						{
							battleOptionsBox(g);
						}

						// battleOptionsBox(g);
						if (battleEndFlag == false)
						{
							statsBox1(g);
							statsBox2(g);
						}

						// Kaiju
						g.drawImage(partyPos1, 45, 325, 180, 180, null);
						g.drawImage(pos2Type, width - 240 + 80, 150, 120, 120, null);

						if (move == true)
						{
							// timer.restart();
							// trainer's move
							battle();
							// add Timer
							/*timer = new Timer(100000, this);
							//timer.start();
							timer.setInitialDelay(2000000);
							timer.start();*/

							// auto kaiju's move
							if (currentHP > 0 && wildCurrentHP > 0)
							{
								battle();
								move = false;
							}

							if (currentHP <= 0 || wildCurrentHP <= 0)
							{
								battleEndFlag = true;
								move = false;
							}
						}

						/*// moves
						if (showMoves == true)
						{
							movesBox(g);
						}*/

						if (showItems == true)
						{
							nextThing = true;

							// border
							g.setColor(Color.WHITE);
							g.fillRect(359, 455, 288, 192);
							// back
							g.setColor(Color.GRAY);
							g.drawRect(359, 455, 15, 192);
							g.drawImage(back, 359, 455 + (192 / 2) - 24, 15, 48, null);
							// border again
							g.setColor(Color.GRAY);
							g.drawRect(359, 455, 288, 192);

							// spacers for items :  + (273 - 4 * 48) / 5

							// ITEMS
							// ball
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("x" + balls, 359 + 15 + (273 - 4 * 48) / 5, 455  + (192 / 2 - 24) - 5);
							g.setColor(Color.BLUE);
							g.fillRect(359 + 15 + (273 - 4 * 48) / 5, 455  + (192 / 2 - 24), 48, 48);
							g.drawImage(KaijuBall, 359 + 15 + (273 - 4 * 48) / 5, 455  + (192 / 2 - 24), 48, 48, null);
							g.setColor(Color.GRAY);
							g.drawRect(359 + 15 + (273 - 4 * 48) / 5, 455  + (192 / 2 - 24), 48, 48);

							// potion
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("x" + potions, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) - 5);
							g.setColor(Color.BLUE);
							g.fillRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48);
							g.drawImage(Potion, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48, null);
							g.setColor(Color.GRAY);
							g.drawRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48, 455  + (192 / 2 - 24), 48, 48);

							// great potion
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("x" + greatPotions, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) - 5);
							g.setColor(Color.BLUE);
							g.fillRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48);
							g.drawImage(GreatPotion, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48, null);
							g.setColor(Color.GRAY);
							g.drawRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455  + (192 / 2 - 24), 48, 48);

							// super potion
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("x" + superPotions, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) - 5);
							g.setColor(Color.BLUE);
							g.fillRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48);
							g.drawImage(SuperPotion, 359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24), 48, 48, null);
							g.setColor(Color.GRAY);
							g.drawRect(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455  + (192 / 2 - 24), 48, 48);
						}
					}
				}

				if (battleEndFlag == true)
				{	
					stats();


					String winner = "_winner_";
					// who is the winner
					if (currentHP > wildCurrentHP)
					{
						winner = "Trainer!";
					}
					else if (wildCurrentHP > currentHP)
					{ 
						winner = " Kaiju! ";
					}
					else 
					{
						winner = "  tie!  ";
					}

					g.setColor(Color.LIGHT_GRAY);
					g.fillRect( width / 2 - 122, height / 2 - 50, 244, 100);
					g.setColor(Color.WHITE);
					g.fillRect( width / 2 - 120, height / 2 - 48, 240, 96);
					g.setColor(Color.GRAY);
					g.drawRect( width / 2 - 120, height / 2 - 48, 240, 96);
					// winner: 
					if (caught == false)
					{
						g.setFont(new Font("Monospaced", Font.BOLD, 20));
						g.setColor(Color.BLACK);
						g.drawString("Winner is:", width / 2 - 60, height / 2 - 20);
						// the winner
						g.setFont(new Font("Monospaced", Font.BOLD, 20));
						g.setColor(Color.BLACK);
						g.drawString(winner, width / 2 - 50, height / 2);
					}
					else if (caught == true)
					{
						if (fullParty == true)
						{
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("Party is full!", width / 2 - 100, height / 2);
						}
						else if (fullParty == false)
						{
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString(wildKaijuType, width / 2 - 60, height / 2 - 20);
							// the winner
							g.setFont(new Font("Monospaced", Font.BOLD, 20));
							g.setColor(Color.BLACK);
							g.drawString("has been caught!", width / 2 - 100, height / 2);
						}
					}

					// stats increase or level up?
					if (winner.equals("Trainer!"))
					{				
						int addedExp = (baseAttack + baseDefense + baseHP) / 3;
						int addedCash = 5 * trainerLevel;
						// cash += 5 * trainerLevel;

						if (addToStatsCounter == 0)
						{
							currentExp = currentExp + addedExp;
							cash = cash + addedCash;
							addToStatsCounter++;
						}

						g.setFont(new Font("Monospaced", Font.BOLD, 15));
						g.setColor(Color.BLACK);
						g.drawString("Experience + " + addedExp, width / 2 - 100, height / 2 + 30);
						g.setFont(new Font("Monospaced", Font.BOLD, 15));
						g.setColor(Color.BLACK);
						g.drawString("Cash + " + addedCash, width / 2 - 100, height / 2 + 45);

						// level up
						if (currentExp >= maxExp  && continued == true)
						{
							// levelUp();
							leveledUp = true;
							stats();

							if (currentExp <= maxExp)
							{
								// border
								g.setColor(Color.LIGHT_GRAY);
								g.fillRect( width / 2 - 122, height / 2 - 50 + 250, 244, 100);
								g.setColor(Color.WHITE);
								g.fillRect( width / 2 - 120, height / 2 - 48 + 250, 240, 96);
								g.setColor(Color.GRAY);
								g.drawRect( width / 2 - 120, height / 2 - 48 + 250, 240, 96);

								// draw level up
								g.setFont(new Font("Monospaced", Font.BOLD, 15));
								g.setColor(Color.BLACK);
								g.drawString("LEVEL UP", width / 2 - 40, height / 2 + 20);

								// base stats increase drawn
								continuedAgain = true;
							}
						}

					}

					// click to end battle
					if (continued == true)
					{
						continuedAgain = true;
					}

					// Background
					//g.drawImage(battleScene, 0, 0, width, height, null);
				}


				// Key Menus
				if (helpFlag == true)
				{
					// Background
					g.drawImage(help, 0, 0, width, height, null);
					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);
				}
				if (mapFlag == true)
				{
					// Background
					g.drawImage(map, 0, 0, width, height, null);
					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);

					if (map1Screen == true)
					{
						g.setFont(new Font("Monospaced", Font.BOLD, 20));
						g.setColor(Color.WHITE);
						g.drawString("You are here", width / 2 - 70, height / 2 + 190);
					}
					else if (map2Screen == true)
					{
						g.setFont(new Font("Monospaced", Font.BOLD, 20));
						g.setColor(Color.WHITE);
						g.drawString("You are here", width / 2 - 70, height / 2 - 80);
					}
				}
				if (partyFlag == true)
				{
					// Background
					g.drawImage(party, 0, 0, width, height, null);

					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);

					partyBoxes(g);

				}
				if (inventoryFlag == true)
				{
					// Background
					g.drawImage(inventory, 0, 0, width, height, null);
					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);

					// balls
					g.setColor(Color.WHITE);
					g.fillRect(100, 150, 100, 100);
					g.drawImage(KaijuBall, 100, 150, 100, 100, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, 150, 100, 100);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Kaiju Ball", 100 + 100 + 10, 150 + 20);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("(To catch Kaiju)", 100 + 100 + 10, 150 + 50);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("You have " + balls + " Kiaju balls", 100 + 100 + 10, 150 + 80);

					// potions
					g.setColor(Color.WHITE);
					g.fillRect(100, 150 + 125, 100, 100);
					g.drawImage(Potion, 100, 150 + 125, 100, 100, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, 150 + 125, 100, 100);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Potions", 100 + 100 + 10, 150 + 20 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("(+5 HP)", 100 + 100 + 10, 150 + 50 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("You have " + potions + " potions", 100 + 100 + 10, 150 + 80 + 125);

					// great potions
					g.setColor(Color.WHITE);
					g.fillRect(100, 150 + 125 + 125, 100, 100);
					g.drawImage(GreatPotion, 100, 150 + 125 + 125, 100, 100, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, 150 + 125 + 125, 100, 100);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Great Potions", 100 + 100 + 10, 150 + 20 + 125 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("(+10 HP)", 100 + 100 + 10, 150 + 50 + 125 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("You have " + greatPotions + " great potions", 100 + 100 + 10, 150 + 80 + 125 + 125);

					// super potions
					g.setColor(Color.WHITE);
					g.fillRect(100, 150 + 125 + 125 + 125, 100, 100);
					g.drawImage(SuperPotion, 100, 150 + 125 + 125 + 125, 100, 100, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, 150 + 125 + 125 + 125, 100, 100);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Super Potions", 100 + 100 + 10, 150 + 20 + 125 + 125 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("(+20 HP)", 100 + 100 + 10, 150 + 50 + 125 + 125 + 125);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("You have " + superPotions + " super potions", 100 + 100 + 10, 150 + 80 + 125 + 125 + 125);
				}
				if (settingsFlag == true)
				{
					int resetWidth = reset.getWidth(null);
					int resetHeight = reset.getHeight(null);

					// Background
					g.drawImage(settings, 0, 0, width, height, null);
					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);
					// Reset button
					g.drawImage(reset, 240, 305, 192, 70, null);
				}
				if (shopFlag == true)
				{
					// Background
					g.drawImage(shop, 0, 0, width, height, null);
					// Exit
					g.drawImage(exit, 622, 8, 35, 35, null);

					// Your cash balance
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Your cash: $" + cash, 220, 140);

					// Pos 1
					g.setColor(Color.WHITE);
					g.fillRect(150, 150, (width - 450) / 2, (width - 450) / 2);
					g.drawImage(KaijuBall, 150, 150, (width - 450) / 2, (width - 450) / 2, null);
					g.setColor(Color.GRAY);
					g.drawRect(150, 150, (width - 450) / 2, (width - 450) / 2);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("Kaiju Ball ($" + ballCost + ")", 150, 150 + (width - 450) / 2 + 25);
					// your inventory
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLUE);
					g.drawString("Inventory: " + balls, 150, 150 + (width - 450) / 2 + 25 + 25);
					// Buy string
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.RED);
					g.drawString("Buy: " + buyBalls, 150, 150 + (width - 450) / 2 + 25 + 25 + 25);
					// Buy buttons
					//up
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120, 150, 55, 55);
					g.drawImage(up, 150 + 120, 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120, 150, 55, 55);
					//down
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120, 150 + 55, 55, 55);
					g.drawImage(down, 150 + 120, 150 + 55, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120, 150 + 55, 55, 55);

					// Pos 2
					g.setColor(Color.WHITE);
					g.fillRect(150 + 150 + (width - 450) / 2, 150, (width - 450) / 2, (width - 450) / 2);
					g.drawImage(Potion, 150  + 150 + (width - 450) / 2, 150, (width - 450) / 2, (width - 450) / 2, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 150 + (width - 450) / 2, 150, (width - 450) / 2, (width - 450) / 2);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("Potion ($" + potionCost + ")", 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25);
					// your inventory
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLUE);
					g.drawString("Inventory: " + potions, 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25 + 25);
					// Buy
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.RED);
					g.drawString("Buy: " + buyPotions, 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25 + 25 + 25);
					// Buy buttons
					//up
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120  + 150 + (width - 450) / 2, 150, 55, 55);
					g.drawImage(up, 150 + 120  + 150 + (width - 450) / 2, 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120  + 150 + (width - 450) / 2, 150, 55, 55);
					//down
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120  + 150 + (width - 450) / 2, 150 + 55, 55, 55);
					g.drawImage(down, 150 + 120  + 150 + (width - 450) / 2, 150 + 55, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120  + 150 + (width - 450) / 2, 150 + 55, 55, 55);

					// Pos 3
					g.setColor(Color.WHITE);
					g.fillRect(150, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2);
					g.drawImage(GreatPotion, 150, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2, null);
					g.setColor(Color.GRAY);
					g.drawRect(150, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("Great Potion ($" + greatPotionCost + ")", 150, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150);
					// your inventory
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLUE);
					g.drawString("Inventory: " + greatPotions, 150, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150 + 25);
					// Buy
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.RED);
					g.drawString("Buy: " + buyGreatPotions, 150, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150 + 25 + 25);
					// Buy buttons
					//up
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120, 150  + (width - 450) / 2 + 150, 55, 55);
					g.drawImage(up, 150 + 120, 150  + (width - 450) / 2 + 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120, 150  + (width - 450) / 2 + 150, 55, 55);
					//down
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120, 150 + 55  + (width - 450) / 2 + 150, 55, 55);
					g.drawImage(down, 150 + 120, 150 + 55  + (width - 450) / 2 + 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120, 150 + 55  + (width - 450) / 2 + 150, 55, 55);

					// Pos 4
					g.setColor(Color.WHITE);
					g.fillRect(150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2);
					g.drawImage(SuperPotion, 150  + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 150, (width - 450) / 2, (width - 450) / 2);
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLACK);
					g.drawString("Super Potion ($" + superPotionCost + ")", 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150);
					// your inventory
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.BLUE);
					g.drawString("Inventory: " + superPotions, 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150 + 25);
					// Buy
					g.setFont(new Font("Monospaced", Font.BOLD, 20));
					g.setColor(Color.RED);
					g.drawString("Buy: " + buySuperPotions, 150 + 150 + (width - 450) / 2, 150 + (width - 450) / 2 + 25 + (width - 450) / 2 + 150 + 25 + 25);
					// Buy buttons
					//up
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120  + 150 + (width - 450) / 2, 150  + (width - 450) / 2 + 150, 55, 55);
					g.drawImage(up, 150 + 120  + 150 + (width - 450) / 2, 150  + (width - 450) / 2 + 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120  + 150 + (width - 450) / 2, 150  + (width - 450) / 2 + 150, 55, 55);
					//down
					g.setColor(Color.WHITE);
					g.fillRect(150 + 120  + 150 + (width - 450) / 2, 150 + 55  + (width - 450) / 2 + 150, 55, 55);
					g.drawImage(down, 150 + 120  + 150 + (width - 450) / 2, 150 + 55  + (width - 450) / 2 + 150, 55, 55, null);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 120 + 150 + (width - 450) / 2, 150 + 55  + (width - 450) / 2 + 150, 55, 55);

					total = (buyBalls * ballCost) + (buyPotions * potionCost) + (buyGreatPotions * greatPotionCost) + (buySuperPotions * superPotionCost);

					// Purchase all button
					g.setColor(Color.WHITE);
					g.fillRect(150 + 150 + (width - 450) / 2 - 150, 150 + 55  + (width - 450) / 2 + 150 + 30 + 110, 150, 55);
					g.setColor(Color.GRAY);
					g.drawRect(150 + 150 + (width - 450) / 2 - 150, 150 + 55  + (width - 450) / 2 + 150 + 30 + 110, 150, 55);
					// string
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString("Buy", 150 + 150 + (width - 450) / 2 - 150 + 50, 150 + 55  + (width - 450) / 2 + 150 + 65 + 110);

					if (bought == true)
					{
						cash = cash - total;
						balls += buyBalls;
						potions += buyPotions;
						greatPotions += buyGreatPotions;
						superPotions += buySuperPotions;
						buyBalls = 0;
						buyPotions = 0;
						buyGreatPotions = 0;
						buySuperPotions = 0;
						total = 0;
						bought = false;
					}


					/*// Pos 1
					g.setColor(Color.WHITE);
					g.fillRect(100, 100, dimensions, dimensions);
					g.drawImage(partyPos1, 100, 100, dimensions, dimensions, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, 100, dimensions, dimensions);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString(partyPos1Type, 100, 100 + dimensions + 25);

					// Pos 2
					g.setColor(Color.WHITE);
					g.fillRect(width - 100 - dimensions, 100, dimensions, dimensions);
					g.drawImage(partyPos2, width - 100 - dimensions, 100, dimensions, dimensions, null);
					g.setColor(Color.GRAY);
					g.drawRect(width - 100 - dimensions, 100, dimensions, dimensions);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString(partyPos2Type, width - 100 - dimensions, 100 + dimensions + 25);

					// Pos 3
					g.setColor(Color.WHITE);
					g.fillRect(100, dimensions + 200, dimensions, dimensions);
					g.drawImage(partyPos3, 100, dimensions + 200, dimensions, dimensions, null);
					g.setColor(Color.GRAY);
					g.drawRect(100, dimensions + 200, dimensions, dimensions);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString(partyPos3Type, 100, 200 + dimensions * 2 + 25);

					// Pos 4
					g.setColor(Color.WHITE);
					g.fillRect(width - 100 - dimensions, dimensions + 200, dimensions, dimensions);
					g.drawImage(partyPos4, width - 100 - dimensions, dimensions + 200, dimensions, dimensions, null);
					g.setColor(Color.GRAY);
					g.drawRect(width - 100 - dimensions, dimensions + 200, dimensions, dimensions);
					g.setFont(new Font("Monospaced", Font.BOLD, 30));
					g.setColor(Color.BLACK);
					g.drawString(partyPos4Type, width - 100 - dimensions, 200 + dimensions * 2 + 25);*/

				}

			}

		}
	}


	// Stats Box 1 in battle graphics
	public void statsBox1 (Graphics g)
	{

		// stats();

		// Stats box
		if (battleFlag == true)
		{
			int boxX = 25;
			int boxY = 200;

			g.setColor(Color.WHITE);
			g.fillRect(boxX, boxY, 240, 96);
			g.setColor(Color.GRAY);
			g.drawRect(boxX, boxY, 240, 96);

			// BOX CONTENT
			// Kaiju type name
			g.setFont(new Font("Monospaced", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString(kaijuType + " Lvl. " + trainerLevel, boxX + 15, boxY + 25);

			// HP string
			g.setFont(new Font("Monospaced", Font.BOLD, 15));
			g.setColor(Color.BLACK);
			g.drawString("HP", boxX + 15, boxY + 55);

			/*battleHP = 5;
			currentHP = 5;
			maxExp = 5;
			currentExp = 0;*/

			stats();

			// HP bar
			g.setColor(Color.WHITE);
			g.fillRect(boxX + 35, boxY + 55 - 12, 240 - 70, 15);
			g.setColor(Color.GREEN);
			g.fillRect(boxX + 35, boxY + 55 - 12, (240 - 70) / battleHP * currentHP, 15);
			g.setColor(Color.GRAY);
			g.drawRect(boxX + 35, boxY + 55 - 12, 240 - 70 - 3, 15);
			// HP string values
			g.setFont(new Font("Monospaced", Font.BOLD, 10));
			g.setColor(Color.BLACK);
			g.drawString(currentHP + " / " + battleHP, boxX + 35, boxY + 72);
			// experience bar
			g.setColor(Color.CYAN);
			g.fillRect(boxX, boxY + 92, 240 / maxExp * currentExp, 4);

			// redraw box border to cover experience
			g.setColor(Color.GRAY);
			g.drawRect(boxX, boxY, 240, 96);
		}
	}


	// Stats Box 2 in battle graphics
	public void statsBox2(Graphics g)
	{
		// Stats box
		if (battleFlag == true)
		{
			int boxX = width - 240 - 25;
			int boxY = 25;

			g.setColor(Color.WHITE);
			g.fillRect(boxX, boxY, 240, 96);
			g.setColor(Color.GRAY);
			g.drawRect(boxX, boxY, 240, 96);

			stats();

			// BOX CONTENT
			// Kaiju type name
			g.setFont(new Font("Monospaced", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString(wildKaijuType  + " Lvl. " + kaijuLevel, boxX + 15, boxY + 25);
			// HP string
			g.setFont(new Font("Monospaced", Font.BOLD, 15));
			g.setColor(Color.BLACK);
			g.drawString("HP", boxX + 15, boxY + 55);
			// HP bar
			g.setColor(Color.WHITE);
			g.fillRect(boxX + 35, boxY + 55 - 12, 240 - 70, 15);
			g.setColor(Color.GREEN);
			g.fillRect(boxX + 35, boxY + 55 - 12, (240 - 70) / wildBattleHP * wildCurrentHP, 15);
			g.setColor(Color.GRAY);
			g.drawRect(boxX + 35, boxY + 55 - 12, 240 - 70 - 3, 15);
			// HP string values
			g.setFont(new Font("Monospaced", Font.BOLD, 10));
			g.setColor(Color.BLACK);
			g.drawString(wildCurrentHP + " / " + wildBattleHP, boxX + 35, boxY + 72);

			// redraw box border to cover experience
			g.setColor(Color.GRAY);
			g.drawRect(boxX, boxY, 240, 96);
		}
	}

	// Shows options in battle box
	public void battleOptionsBox (Graphics g)
	{
		// Stats box
		if (battleFlag == true)
		{
			int boxX = width - 25 - 288;
			int boxY = height - 25 - 192;

			String attack = "ATTACK";
			String items= "ITEMS";
			String run = "RUN";
			int attackWidth = g.getFontMetrics().stringWidth(attack);
			int itemsWidth = g.getFontMetrics().stringWidth(items);
			int runWidth = g.getFontMetrics().stringWidth(run);

			// border
			g.setColor(Color.WHITE);
			g.fillRect(boxX, boxY, 288, 192);
			g.setColor(Color.GRAY);
			g.drawRect(boxX, boxY, 288, 192);

			// option 1: attack
			g.setColor(Color.RED);
			g.fillRect(boxX + 15, boxY + 15, 288 - 30, (192 - 60) / 3);
			g.setColor(Color.GRAY);
			g.drawRect(boxX + 15, boxY + 15, 288 - 30, (192 - 60) / 3);
			g.setFont(new Font("Monospaced", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString(attack, boxX + 96, boxY + 15 + 30);

			// option 2: items
			g.setColor(Color.BLUE);
			g.fillRect(boxX + 15, boxY + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);
			g.setColor(Color.GRAY);
			g.drawRect(boxX + 15, boxY + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);
			g.setFont(new Font("Monospaced", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString(items, boxX + 5 + 96, boxY + 15 + ((192 - 60) / 3) + 15 + 30);

			// option 3: run
			g.setColor(Color.ORANGE);
			g.fillRect(boxX + 15, boxY + 15  + ((192 - 60) / 3) + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);
			g.setColor(Color.GRAY);
			g.drawRect(boxX + 15, boxY + 15 + ((192 - 60) / 3) + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);
			g.setFont(new Font("Monospaced", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString(run, boxX + 20 + 96, boxY + 15  + ((192 - 60) / 3) + 15 + ((192 - 60) / 3) + 15 + 30);
		}
	}

	public void movesBox (Graphics g)
	{
		int boxX = width - 25 - 288;
		int boxY = height - 25 - 192;

		// border
		g.setColor(Color.WHITE);
		g.fillRect(boxX, boxY, 288, 192);
		g.setColor(Color.GRAY);
		g.drawRect(boxX, boxY, 288, 192);

		// move 1
		g.setColor(Color.RED);
		g.fillRect(boxX + 15, boxY + 15, (288 - 45) / 2, (192 - 45) / 2);
		g.setColor(Color.GRAY);
		g.drawRect(boxX + 15, boxY + 15, (288 - 45) / 2, (192 - 45) / 2);

		//  move 2
		g.setColor(Color.RED);
		g.fillRect(boxX + 15 + ((288 - 45) / 2) + 15, boxY + 15, (288 - 45) / 2, (192 - 45) / 2);
		g.setColor(Color.GRAY);
		g.drawRect(boxX + 15 + ((288 - 45) / 2) + 15, boxY + 15, (288 - 45) / 2, (192 - 45) / 2);

		// move 3
		g.setColor(Color.RED);
		g.fillRect(boxX + 15, boxY + 15 + (192 - 45) / 2 + 15, (288 - 45) / 2, (192 - 45) / 2);
		g.setColor(Color.GRAY);
		g.drawRect(boxX + 15, boxY + 15 + (192 - 45) / 2 + 15, (288 - 45) / 2, (192 - 45) / 2);

		// move 4
		g.setColor(Color.RED);
		g.fillRect(boxX + 15 + ((288 - 45) / 2) + 15, boxY + 15 + (192 - 45) / 2 + 15, (288 - 45) / 2, (192 - 45) / 2);
		g.setColor(Color.GRAY);
		g.drawRect(boxX + 15 + ((288 - 45) / 2) + 15, boxY + 15 + (192 - 45) / 2 + 15, (288 - 45) / 2, (192 - 45) / 2);

	}

	// PARTY SCREEN BOXES
	public void partyBoxes (Graphics g)
	{
		int dimensions = (width - 300) / 2 ;
		int switcharoo = partyPos1.getWidth(null) / 2 + switchPos.getWidth(null) / 2;

		// pos1   pos2
		//
		// pos3   pos4


		if (partyPos1 == null)
		{
			partyPos1 = white;
			partyPos1Type = null;
		}
		if (partyPos2 == null)
		{
			partyPos2 = white;
			partyPos2Type = null;
		}
		if (partyPos3 == null)
		{
			partyPos3 = white;
			partyPos3Type = null;
		}
		if (partyPos4 == null)
		{
			partyPos4 = white;
			partyPos4Type = null;
		}

		// backup
		if (partyPos1Type == null)
		{
			partyPos1Type = "";
		}
		if (partyPos2Type == null)
		{
			partyPos2Type = "";
		}
		if (partyPos3Type == null)
		{
			partyPos3Type = "";
		}
		if (partyPos4Type == null)
		{
			partyPos4Type = "";
		}

		/*partyPos1 = Vaporean;
		partyPos2 = Leafeon;
		partyPos3 = Shaymin;
		partyPos4 = Vulpix;*/


		// DRAW BOXES
		// Pos 1
		g.setColor(Color.WHITE);
		g.fillRect(100, 100, dimensions, dimensions);
		g.drawImage(partyPos1, 100, 100, dimensions, dimensions, null);
		g.setColor(Color.GRAY);
		g.drawRect(100, 100, dimensions, dimensions);
		g.setFont(new Font("Monospaced", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString(partyPos1Type, 100, 100 + dimensions + 20);

		// Pos 2
		g.setColor(Color.WHITE);
		g.fillRect(width - 100 - dimensions, 100, dimensions, dimensions);
		g.drawImage(partyPos2, width - 100 - dimensions, 100, dimensions, dimensions, null);
		g.setColor(Color.GRAY);
		g.drawRect(width - 100 - dimensions, 100, dimensions, dimensions);
		g.setFont(new Font("Monospaced", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString(partyPos2Type, width - 100 - dimensions, 100 + dimensions + 20);

		// Pos 3
		g.setColor(Color.WHITE);
		g.fillRect(100, dimensions + 200, dimensions, dimensions);
		g.drawImage(partyPos3, 100, dimensions + 200, dimensions, dimensions, null);
		g.setColor(Color.GRAY);
		g.drawRect(100, dimensions + 200, dimensions, dimensions);
		g.setFont(new Font("Monospaced", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString(partyPos3Type, 100, 200 + dimensions * 2 + 20);

		// Pos 4
		g.setColor(Color.WHITE);
		g.fillRect(width - 100 - dimensions, dimensions + 200, dimensions, dimensions);
		g.drawImage(partyPos4, width - 100 - dimensions, dimensions + 200, dimensions, dimensions, null);
		g.setColor(Color.GRAY);
		g.drawRect(width - 100 - dimensions, dimensions + 200, dimensions, dimensions);
		g.setFont(new Font("Monospaced", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString(partyPos4Type, width - 100 - dimensions, 200 + dimensions * 2 + 20);

		// draw switches
		// pos 1 and 3
		g.drawImage(switchPos, 100 + 186 / 2 - 15, 100 + 186 + 25, 30, 50, null);
		g.setColor(Color.GRAY);
		g.drawRect(100 + 186 / 2 - 15, 100 + 186 + 25, 30, 50);
		// pos 2 and 4
		g.drawImage(switchPos, 386 + 186 / 2 - 15, 100 + 186 + 25, 30, 50, null);
		g.setColor(Color.GRAY);
		g.drawRect(386 + 186 / 2 - 15, 100 + 186 + 25, 30, 50);
		// pos 1 and 2
		g.drawImage(switchPos2, 100 + 186 + 25, 100 + 186 / 2 - 15, 50, 30, null);
		g.setColor(Color.GRAY);
		g.drawRect(100 + 186 + 25, 100 + 186 / 2 - 15, 50, 30);
		// pos 3 and 4
		g.drawImage(switchPos2, 100 + 186 + 25, 100 + 186 + 100 + 186 / 2 - 15, 50, 30, null);
		g.setColor(Color.GRAY);
		g.drawRect(100 + 186 + 25, 100 + 186 + 100 + 186 / 2 - 15, 50, 30);
	}


	// SELECTION SCREEN KAIJU
	// FireCenter
	public void fireCenterSelection (Graphics g)
	{
		// Selection screen with fire in center
		if (fireCenter == true)
		{
			// water left, fire center, grass right
			int backgroundWidth = townScene.getWidth(null);
			int eggWidth = egg.getWidth(null);

			int KaijuX = (backgroundWidth / 4) - (eggWidth / 4);
			int KaijuY = 250;

			// Draw eggs
			g.drawImage(egg, KaijuX, KaijuY, 192, 192, null); // Center
			g.drawImage(egg, KaijuX - eggWidth, KaijuY, 96, 96, null); // Left
			g.drawImage(egg, KaijuX + eggWidth * 2, KaijuY, 96, 96, null); // Right

			// Kaiju name
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("Fire", KaijuX + 47, KaijuY + 200); // Center

			// BUTTONS
			// view
			g.drawImage(view, KaijuX, KaijuY + 245, 192, 77, null); // Center
			// shift left
			g.drawImage(back, KaijuX - eggWidth, KaijuY + 235, 96, 96, null); // Left
			// shift right
			g.drawImage(next, KaijuX + eggWidth * 2, KaijuY + 235, 96, 96, null); // Right
		}
	}

	// WaterCenter
	public void waterCenterSelection (Graphics g)
	{
		// Selection screen with water in center
		if (waterCenter == true)
		{
			// grass left, water center, fire right
			int backgroundWidth = townScene.getWidth(null);
			int eggWidth = egg.getWidth(null);

			int KaijuX = (backgroundWidth / 4) - (eggWidth / 4);
			int KaijuY = 250;

			// Draw eggs
			g.drawImage(egg, KaijuX, KaijuY, 192, 192, null); // Center
			g.drawImage(egg, KaijuX - eggWidth, KaijuY, 96, 96, null); // Left
			g.drawImage(egg, KaijuX + eggWidth * 2, KaijuY, 96, 96, null); // Right

			// Kaiju name
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("Water", KaijuX + 37, KaijuY + 200); // Center

			// BUTTONS
			// view
			g.drawImage(view, KaijuX, KaijuY + 245, 192, 77, null); // Center
			// shift left
			g.drawImage(back, KaijuX - eggWidth, KaijuY + 235, 96, 96, null); // Left
			// shift right
			g.drawImage(next, KaijuX + eggWidth * 2, KaijuY + 235, 96, 96, null); // Right
		}
	}

	// GrassCenter
	public void grassCenterSelection (Graphics g)
	{
		// Selection screen with grass in center
		if (grassCenter == true)
		{
			// fire right, grass center, water right
			int backgroundWidth = townScene.getWidth(null);
			int eggWidth = egg.getWidth(null);

			int KaijuX = (backgroundWidth / 4) - (eggWidth / 4);
			int KaijuY = 250;

			// Draw eggs
			g.drawImage(egg, KaijuX, KaijuY, 192, 192, null); // Center
			g.drawImage(egg, KaijuX - eggWidth, KaijuY, 96, 96, null); // Left
			g.drawImage(egg, KaijuX + eggWidth * 2, KaijuY, 96, 96, null); // Right

			// Kaiju name
			g.setFont(new Font("Monospaced", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("Grass", KaijuX + 42, KaijuY + 200); // Center

			// BUTTONS
			// view
			g.drawImage(view, KaijuX, KaijuY + 245, 192, 77, null); // Center
			// shift left
			g.drawImage(back, KaijuX - eggWidth, KaijuY + 235, 96, 96, null); // Left
			// shift right
			g.drawImage(next, KaijuX + eggWidth * 2, KaijuY + 235, 96, 96, null); // Right
		}

	}

	// Selection Screen Viewed
	public void fireView (Graphics g)
	{
		// Draw Kaiju on left
		g.drawImage(FlareonSquare, 30, 220, 192, 192, null); // Left

		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawString("Flareon", 40, 450); // Kaiju Name

		// Draw Select Button
		g.drawImage(select, 30, 480, 192, 64, null); // button
	}
	public void waterView (Graphics g)
	{
		// Draw Kaiju on left
		g.drawImage(VaporeanSquare, 30, 220, 192, 192, null); // Left

		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawString("Vaporean", 30, 450); // Kaiju Name

		// Draw Select Button
		g.drawImage(select, 30, 480, 192, 64, null); // button
	}
	public void grassView (Graphics g)
	{
		// Draw Kaiju on left
		g.drawImage(LeafeonSquare, 30, 220, 192, 192, null); // Left

		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawString("Leafeon", 40, 450); // Kaiju Name

		// Draw Select Button
		g.drawImage(select, 30, 480, 192, 64, null); // button
	}




	// STRINGS : TYPES AND STUFF
	public String kaijuType ()
	{
		return null;
	}



	// KEY LISTENER
	public void keyPressed(KeyEvent e) {
		int xLeft = boy1.getX();
		int xRight = boy1.getX() + boy1.getWidth();
		int yUp = boy1.getY();
		int yDown = boy1.getY() + boy1.getHeight();

		if (female == true)
		{
			xLeft = girl1.getX();
			xRight = girl1.getX() + girl1.getWidth();
			yUp = girl1.getY();
			yDown = girl1.getY() + girl1.getHeight();
		}

		// Move left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			walkLeftFlag = true;

			if (map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp < (48 * 5))
			{
				// map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp <= (48 * 6)

				inGrass = true;
			}
			// checkCharacterGrass happens after thread.sleep
		} 
		// Move right
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (selectionScreen1 == true)
			{
				selectionScreen1 = false;
				selectionScreen2 = true;
			}

			walkRightFlag = true;

			if (map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp < (48 * 6))
			{
				// map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp <= (48 * 6)

				inGrass = true;
			}
			// checkCharacterGrass happens after thread.sleep
		} 
		// Move up
		else if (e.getKeyCode() == KeyEvent.VK_UP) {

			walkUpFlag = true;

			if (map1Screen == true && allowedUpFlag == true)
			{
				map1Screen = false;
				map2Screen = true;
				allowedUpFlag = false;

				if (male == true)
				{
					boy1.newMapYUp();
				}
				else if (female == true)
				{
					girl1.newMapYUp();
				}
			}

			if (map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp < (48 * 6))
			{
				// map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp <= (48 * 6)

				inGrass = true;
			}
			// checkCharacterGrass happens after thread.sleep

		} 
		// Move down
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			walkDownFlag = true;

			if (map2Screen == true && allowedDownFlag == true)
			{
				map2Screen = false;
				map1Screen = true;
				allowedDownFlag = false;

				if (male == true)
				{
					boy1.newMapYDown();
				}
				else if (female == true)
				{
					girl1.newMapYDown();
				}
			}

			if (map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp < (48 * 6))
			{
				// map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp <= (48 * 6)

				inGrass = true;
			}
			// checkCharacterGrass happens after thread.sleep
		} 
		// START
		else if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (startScreen == true)
			{
				startScreen = false;
				genderSelection = true;
				// selectionScreen1 = true;
			}
		}
		// Help
		else if (e.getKeyCode() == KeyEvent.VK_H)
		{
			if (selectionScreen1 == false && selectionScreen2 == false && selectionScreen3 == false && battleFlag == false)
			{
				helpFlag = true;
			}
		}
		// Map
		else if (e.getKeyCode() == KeyEvent.VK_M  && battleFlag == false)
		{
			mapFlag = true;
		}
		// Inventory
		else if (e.getKeyCode() == KeyEvent.VK_I  && battleFlag == false)
		{
			inventoryFlag = true;
		}
		// Party
		else if (e.getKeyCode() == KeyEvent.VK_P)
		{
			partyFlag = true;
		}
		// Settings
		else if (e.getKeyCode() == KeyEvent.VK_S && battleFlag == false)
		{
			// settingsFlag = true;
			shopFlag = true;
		}


	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			walkLeftFlag = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			walkRightFlag = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			walkUpFlag = false;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// JUMP
			walkDownFlag = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			// nothing
		}
		else if (e.getKeyCode() == KeyEvent.VK_H)
		{
			// nothing
		}
		// Map
		else if (e.getKeyCode() == KeyEvent.VK_M)
		{
			// nothing
		}
		// Inventory
		else if (e.getKeyCode() == KeyEvent.VK_I)
		{
			// nothing
		}
		// Party
		else if (e.getKeyCode() == KeyEvent.VK_P)
		{
			// nothing
		}
		// Settings
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			// nothing
		}
	}

	public void keyTyped(KeyEvent e) {

	}





	// MOUSE LISTENER
	@Override
	public void mouseClicked(MouseEvent evt) {

		// Buttons
		if (next != null && selectionScreen1 == true) 
		{
			int nextWidth = next.getWidth(null);
			int nextHeight = next.getHeight(null);

			Rectangle bounds = new Rectangle(300, 550, nextWidth, nextHeight);
			if ((bounds).contains(evt.getPoint()) == true) 
			{
				selectionScreen1 = false;
				selectionScreen2 = true;
			}
			repaint();
		}

		if (exit != null) 
		{
			int exitWidth = exit.getWidth(null);
			int exitHeight = exit.getHeight(null);

			Rectangle bounds = new Rectangle(622 - 5, 8 + 5, exitWidth, exitHeight);

			if (helpFlag == true)
			{
				if ((bounds).contains(evt.getPoint()) == true) 
				{
					helpFlag = false;
				}
				repaint();
			}

			if (startScreen == false)
			{
				if ((bounds).contains(evt.getPoint()) == true) 
				{
					mapFlag = false;
					inventoryFlag = false;
					partyFlag = false;
					settingsFlag = false;
					shopFlag = false;
				}
				repaint();
			}
		}

		if (select != null && selectionScreen3 == true)
		{
			// g.drawImage(select, 30, 480, 192, 64, null); // button

			Rectangle bounds = new Rectangle(30, 512, 192, 64);
			if ((bounds).contains(evt.getPoint()) == true) 
			{
				selectionScreen1 = false;
				selectionScreen2 = false;
				selectionScreen3 = false;
				map1Screen = true;

				if (fireCenter == true)
				{
					starterType = "fire";
				}
				if (waterCenter == true)
				{
					starterType = "water";
				}
				if (grassCenter == true)
				{
					starterType = "grass";
				}

			}
			repaint();
		}

		if (select != null && genderSelection == true)
		{
			// g.drawImage(select, 30, 480, 192, 64, null); // button\

			// swap character genders
			Rectangle swapBounds = new Rectangle(width / 2 - 96 + 200, height / 2 + 75, 64, 64);
			if ((swapBounds).contains(evt.getPoint()) == true) 
			{
				genderCounter++;

				if (genderCounter % 2 == 0)
				{
					female = true;
					male = false;
				}
				else
				{
					male = true;
					female = false;
				}
			}

			// select button to proceed
			Rectangle selectBounds = new Rectangle(width / 2 - 96, height / 2 + 100, 192, 64);
			if ((selectBounds).contains(evt.getPoint()) == true) 
			{
				selectionScreen1 = true;
				genderSelection = false;

			}
			repaint();
		}

		if (reset != null && settingsFlag == true)
		{
			// 240, 336, 192, 80

			Rectangle bounds = new Rectangle(240, 376, 192, 80);
			if ((bounds).contains(evt.getPoint()) == true) 
			{
				// turn off all booleans
				map1Screen = false;
				selectionScreen1 = false;
				selectionScreen2 = false;
				selectionScreen3 = false;
				settingsFlag = false;
				battleFlag = false;
				battleStart = false;

				// SET ALL STATS TO 0

				// reset to start from beginning
				startScreen = true;
			}
			repaint();
		}


		if (partyFlag == true)
		{
			Image positionHolder = null;
			String positionTypeHolder = null;

			// swap along y
			Rectangle bounds1and3 = new Rectangle(100 + 186 / 2 - 15, 100 + 186 + 25 + 30, 30, 50);
			Rectangle bounds2and4 = new Rectangle(386 + 186 / 2 - 15, 100 + 186 + 25 + 30, 30, 50);
			// swap along x
			Rectangle bounds1and2 = new Rectangle(100 + 186 + 25, 100 + 186 / 2 - 15 + 30, 50, 30);
			Rectangle bounds3and4 = new Rectangle(100 + 186 + 25, 100 + 186 + 100 + 186 / 2 - 15 + 30, 50, 30);

			if (partyPos1 != white && partyPos3 != white && (bounds1and3).contains(evt.getPoint()) == true)
			{
				positionHolder = partyPos1;
				partyPos1 = partyPos3;
				partyPos3 = positionHolder;

				positionTypeHolder = partyPos1Type;
				partyPos1Type = partyPos3Type;
				partyPos3Type = positionTypeHolder;
				
				if (battleFlag == true)
				{
					currentHP = battleHP;
				}
			}
			if (partyPos2 != white && partyPos4 != white && (bounds2and4).contains(evt.getPoint()) == true)
			{
				positionHolder = partyPos2;
				partyPos2 = partyPos4;
				partyPos4 = positionHolder;

				positionTypeHolder = partyPos2Type;
				partyPos2Type = partyPos4Type;
				partyPos4Type = positionTypeHolder;
				
				if (battleFlag == true)
				{
					currentHP = battleHP;
				}
			}
			if (partyPos1 != white && partyPos2 != white && (bounds1and2).contains(evt.getPoint()) == true)
			{
				positionHolder = partyPos2;
				partyPos2 = partyPos1;
				partyPos1 = positionHolder;

				positionTypeHolder = partyPos1Type;
				partyPos1Type = partyPos2Type;
				partyPos2Type = positionTypeHolder;
				
				if (battleFlag == true)
				{
					currentHP = battleHP;
				}
			}
			if (partyPos3 != white && partyPos4 != white && (bounds3and4).contains(evt.getPoint()) == true)
			{
				positionHolder = partyPos3;
				partyPos3 = partyPos4;
				partyPos4 = positionHolder;

				positionTypeHolder = partyPos3Type;
				partyPos3Type = partyPos4Type;
				partyPos4Type = positionTypeHolder;
				
				if (battleFlag == true)
				{
					currentHP = battleHP;
				}
			}

		}



		// Shop
		if (shopFlag == true)
		{
			// pos 1 up and down
			Rectangle boundsUp1 = new Rectangle(150 + 120, 180, 55, 55);
			Rectangle boundsDown1 = new Rectangle(150 + 120, 180 + 55, 55, 55);
			// pos 2 up and down
			Rectangle boundsUp2 = new Rectangle(150 + 120  + 150 + (width - 450) / 2, 180, 55, 55);
			Rectangle boundsDown2 = new Rectangle(150 + 120  + 150 + (width - 450) / 2, 180 + 55, 55, 55);
			// pos 3 up and down
			Rectangle boundsUp3 = new Rectangle(150 + 120, 180  + (width - 450) / 2 + 150, 55, 55);
			Rectangle boundsDown3 = new Rectangle(150 + 120, 180  + (width - 450) / 2 + 150 + 55, 55, 55);
			// pos 4 up and down
			Rectangle boundsUp4 = new Rectangle(150 + 120  + 150 + (width - 450) / 2, 180  + (width - 450) / 2 + 150, 55, 55);
			Rectangle boundsDown4 = new Rectangle(150 + 120  + 150 + (width - 450) / 2, 180  + (width - 450) / 2 + 150 + 55, 55, 55); 
			// buy
			Rectangle boundsBuy = new Rectangle(150 + 150 + (width - 450) / 2 - 150, 180 + 55  + (width - 450) / 2 + 150 + 30 + 110, 150, 55);

			if ((boundsBuy).contains(evt.getPoint()) == true)
			{
				if (cash >= total)
				{
					// cash = cash - total;
					bought = true;
				}
			}

			// balls in pos 1
			if ((boundsUp1).contains(evt.getPoint()) == true)
			{
				buyBalls++;
			}
			if ((boundsDown1).contains(evt.getPoint()) == true)
			{
				if (buyBalls > 0)
				{
					buyBalls--;
				}
			}
			// potions in pos 2
			if ((boundsUp2).contains(evt.getPoint()) == true)
			{
				buyPotions++;
			}
			if ((boundsDown2).contains(evt.getPoint()) == true)
			{
				if (buyPotions > 0)
				{
					buyPotions--;
				}
			}
			// great potions in pos 3
			if ((boundsUp3).contains(evt.getPoint()) == true)
			{
				buyGreatPotions++;
			}
			if ((boundsDown3).contains(evt.getPoint()) == true)
			{
				if (buyGreatPotions > 0)
				{
					buyGreatPotions--;
				}
			}
			// super potions in pos 4
			if ((boundsUp4).contains(evt.getPoint()) == true)
			{
				buySuperPotions++;
			}
			if ((boundsDown4).contains(evt.getPoint()) == true)
			{
				if (buySuperPotions > 0)
				{
					buySuperPotions--;
				}
			}

			repaint();
		}

		// Battle & states
		if (battleFlag == true)
		{
			Rectangle bounds = new Rectangle(0, 0, width, height);
			if ((bounds).contains(evt.getPoint()) == true && battleStart == false) 
			{
				battleStart = true;
			}
			repaint();

			if (battleStart == true && battleCounter == 1 && battleEndFlag == false && doNextThing == true)
			{
				Rectangle boundsAttack = new Rectangle(374, 505, 288 - 30, (192 - 60) / 3);
				Rectangle boundsItems = new Rectangle(374, 505 + (192 - 60) / 3 + 15, 288 - 30, (192 - 60) / 3);
				Rectangle boundsRun = new Rectangle(374, 505  + (192 - 60) / 3 + 15 + (192 - 60) / 3 + 15, 288 - 30, (192 - 60) / 3);

				if ((boundsAttack).contains(evt.getPoint()) == true && move == false)
				{
					// showMoves = true;
					// battle();
					// statsBox2(g);

					move = true;
				}
				if ((boundsItems).contains(evt.getPoint()) == true && showItems == false)
				{
					showItems = true;
				}
				if ((boundsRun).contains(evt.getPoint()) == true && showItems == false)
				{
					battleReset();
				}


				Rectangle boundsBack = new Rectangle(359, 455, 15, 192);
				Rectangle boundsBall = new Rectangle(359 + 15 + (273 - 4 * 48) / 5, 455  + (192 / 2 - 24) + 30, 48, 48);
				Rectangle boundsPotion = new Rectangle(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) + 30, 48, 48);
				Rectangle boundsGreatPotion = new Rectangle(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) + 30, 48, 48);
				Rectangle boundsSuperPotion = new Rectangle(359 + 15 + (273 - 4 * 48) / 5 + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48  + (273 - 4 * 48) / 5 + 48, 455 + (192 / 2 - 24) + 30, 48, 48);

				// showItems
				if (showItems == true && nextThing == true)
				{
					if ((boundsBack).contains(evt.getPoint()) == true)
					{
						showItems = false;
						nextThing = false;
					}
					else if ((boundsBall).contains(evt.getPoint()) == true)
					{
						catchCounter = 1;
						catchKaiju();
						if (balls > 0)
						{
							balls--;
							showItems = false;
							nextThing = false;
						}
					}
					else if ((boundsPotion).contains(evt.getPoint()) == true)
					{
						// add to HP
						currentHP += 5;
						if (currentHP > battleHP)
						{
							currentHP = battleHP;
						}

						if (potions > 0)
						{
							potions--;
							showItems = false;
							nextThing = false;
						}
					}
					else if ((boundsGreatPotion).contains(evt.getPoint()) == true)
					{
						// add to HP
						currentHP += 10;
						if (currentHP > battleHP)
						{
							currentHP = battleHP;
						}

						if (greatPotions > 0)
						{
							greatPotions--;
							showItems = false;
							nextThing = false;
						}
					}
					else if ((boundsSuperPotion).contains(evt.getPoint()) == true)
					{
						// add to HP
						currentHP += 20;
						if (currentHP > battleHP)
						{
							currentHP = battleHP;
						}

						if (superPotions > 0)
						{
							superPotions--;
							showItems = false;
							nextThing = false;
						}
					}
				}
			}

		}

		if (battleEndFlag == true)
		{
			Rectangle bounds = new Rectangle(0, 0, width, height);
			if ((bounds).contains(evt.getPoint()) == true) 
			{
				if (continuedBefore == false)
				{
					continued = true;
					if (continuedAgain == true)
					{
						battleReset();
					}
				}
			}
		}

		if (continuedAgain == true && currentExp <= maxExp)
		{
			Rectangle bounds = new Rectangle(0, 0, width, height);
			if ((bounds).contains(evt.getPoint()) == true) 
			{
				battleReset();
			}
		}

		/*if (battleStart == true)
		{
			// Rectangle boundsAttack = new Rectangle(width - 25 - 288 + 15, height - 25 - 192 + 15, 288 - 30, (192 - 60) / 3);
			Rectangle boundsAttack = new Rectangle(0, 0, width, height);
			Rectangle boundsItems = new Rectangle(width - 25 - 288 + 15, height - 25 - 192 + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);
			Rectangle boundsRun = new Rectangle(width - 25 - 288 + 15, height - 25 - 192 + 15 + ((192 - 60) / 3) + 15 + ((192 - 60) / 3) + 15, 288 - 30, (192 - 60) / 3);

			if ((boundsAttack).contains(evt.getPoint()) == true)
			{
				//showMoves = true;
			}

			if ((boundsItems).contains(evt.getPoint()) == true)
			{
				// showItems = true;
			}

			if ((boundsRun).contains(evt.getPoint()) == true)
			{
				// battleStart = false;
				// battleFlag = false;
			}
			repaint();
		}*/


		// Screens

		if (selectionScreen2 == true)
		{
			boolean leftShift = false;
			boolean rightShift = false;

			int backgroundWidth = fullSelection.getWidth(null);
			int eggWidth = egg.getWidth(null);

			int KaijuX = (backgroundWidth / 4) - (eggWidth / 4);
			int KaijuY = 250;

			Rectangle leftBounds = new Rectangle(KaijuX - eggWidth, KaijuY + 235, 96, 96);
			Rectangle rightBounds = new Rectangle(KaijuX + eggWidth * 2, KaijuY + 235, 96, 96);
			Rectangle viewBounds = new Rectangle(KaijuX, KaijuY + 245, 192, 112);

			if ((leftBounds).contains(evt.getPoint()) == true) 
			{
				leftShift = true;
				rightShift = false;
			}

			else if ((rightBounds).contains(evt.getPoint()) == true) 
			{
				rightShift = true;
				leftShift = false;
			}

			else if ((viewBounds).contains(evt.getPoint()) == true) 
			{
				selectionScreen2 = false;
				selectionScreen3 = true;
			}


			// Which egg is in the center for shifting
			// Fire Center
			if (fireCenter == true)
			{

				if (leftShift == true)
				{
					fireCenter = false;
					waterCenter = true;
					leftShift = false;
				}
				if (rightShift == true)
				{
					fireCenter = false;
					grassCenter = true;
					rightShift = false;
				}

				kaijuType = "Flareon";
				// System.out.println(kaijuType);
				// partyPos1 = Flareon;

				//repaint();
			}
			// Water Center
			if (waterCenter == true)
			{
				if (leftShift == true)
				{
					waterCenter = false;
					grassCenter = true;
					leftShift = false;

				}
				if (rightShift == true)
				{
					waterCenter = false;
					fireCenter = true;
					rightShift = false;
				}

				kaijuType = "Vaporeon";
				// System.out.println(kaijuType);
				// partyPos1 = Vaporean;
				stats();

				//repaint();
			}
			// Grass Center
			if (grassCenter == true)
			{

				if (leftShift == true)
				{
					grassCenter = false;
					fireCenter = true;
					leftShift = false;
				}
				if (rightShift == true)
				{
					grassCenter = false;
					waterCenter = true;
					rightShift = false;
				}

				kaijuType = "Leafeon";
				// System.out.println(kaijuType);
				// partyPos1 = Leafeon;
				stats();

				//repaint();
			}

			partyPos1Type = kaijuType;
		}

		if (selectionScreen3 == true)
		{
			if (exit != null) 
			{
				int exitWidth = exit.getWidth(null);
				int exitHeight = exit.getHeight(null);

				Rectangle bounds = new Rectangle(622, 8, exitWidth, exitHeight);
				if ((bounds).contains(evt.getPoint()) == true) 
				{
					selectionScreen3 = false;
					selectionScreen2 = true;
				}
				repaint();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



	public void Shop()
	{
		int spent = 0;
		int ballsAmount = 0;
		int potionsAmount = 0;
		int greatPotionsAmount = 0;
		int superPotionsAmount = 0;

		// Code for initializing amount spent in shop

		cash = cash - spent;
		balls += ballsAmount;
		potions += potionsAmount;
		greatPotions += greatPotionsAmount;
		superPotions += superPotionsAmount;
	}


	public void wildKaiju ()
	{
		int probability;

		probability = (int) (Math.random() * 6 + 1);

		if (probability == 1)
		{
			wildKaijuType = "Growlithe";
			pos2Type = Growlithe;
		}
		else if (probability == 2)
		{
			wildKaijuType = "Vulpix";
			pos2Type = Vulpix;
		}
		else if (probability == 3)
		{
			wildKaijuType = "Marill";
			pos2Type = Marill;
		}
		else if (probability == 4)
		{
			wildKaijuType = "Squirtle";
			pos2Type = Squirtle;
		}
		else if (probability == 5)
		{
			wildKaijuType = "Bulbasaur";
			pos2Type = Bulbasaur;
		}
		else if (probability == 6)
		{
			wildKaijuType = "Shaymin";
			pos2Type = Shaymin;
		}
		else
		{
			// backup
			wildKaijuType = "Shaymin";
			pos2Type = Shaymin;
		}

	}


	public void checkCharacter () {
		// If Character goes off screen

		int xLeft = boy1.getX();
		int xRight = boy1.getX() + boy1.getWidth();
		int yUp = boy1.getY();
		int yDown = boy1.getY() + boy1.getHeight();

		allowedUpFlag = false;
		allowedDownFlag = false;

		if (female == true)
		{
			xLeft = girl1.getX();
			xRight = girl1.getX() + girl1.getWidth();
			yUp = girl1.getY();
			yDown = girl1.getY() + girl1.getHeight();
		}

		if (xLeft <= 0)
		{
			walkLeftFlag = false;
		}
		if (xRight > 650)
		{
			walkRightFlag = false;
		}
		if (yUp == 0)
		{
			// System.out.println("1");
			if (map1Screen == true && xLeft >= 384 && xLeft <= 385)
			{
				allowedUpFlag = true;
				// System.out.println("allowedUp");
			}

			walkUpFlag = false;
		}
		if (yDown >= 650) // 672
		{
			// System.out.println("yDown");
			if (map2Screen == true && xLeft >= 384 && xLeft <=385) //xLeft < 408
			{
				allowedDownFlag = true;
				// System.out.println("allowedDown");
			}

			walkDownFlag = false;
		}
	}

	public void checkCharacterGrass () 
	{
		int xLeft = boy1.getX();
		int yUp = boy1.getY();

		if (female == true)
		{
			xLeft = girl1.getX();
			yUp = girl1.getY();
		}

		// draw rect. if point is in bounds then math.random

		// Encountering Kaiju in wild grass
		if (inGrass == true){
			battleEndFlag = false;

			int probability = (int)(Math.random() * 5 + 1);

			if (probability <= 1)
			{
				battleFlag = true;
				// System.out.println("Hello");
			}

			if (battleFlag == true)
			{
				walkDownFlag = false;
				walkUpFlag = false;
				walkLeftFlag = false;
				walkRightFlag = false;
				inGrass = false;
				// System.out.println("Goodbye");
			}
		}

		if (map2Screen == true && (xLeft < 0 || xLeft > width || yUp < 0 || yUp > (48 * 5)))
		{
			// map2Screen == true && xLeft >= 0 && xLeft <= width && yUp >= 0 && yUp <= (48 * 6)

			inGrass = false;
			// System.out.println("inGrass false");
		}
		// checkCharacterGrass happens after thread.sleep
	}



	// STATS
	public void stats ()
	{
		if (wildKaijuCounter < 1)
		{
			wildKaiju();
			wildKaijuCounter++;
		}

		if (trainerLevel == 1)
		{
			// Starters
			if (kaijuType.equals("Flareon"))
			{
				baseAttack = 5;
				baseDefense = 4;
				baseHP = 4;
				elementalType = "fire";
			}
			else if (kaijuType.equals("Vaporean"))
			{
				baseAttack = 4;
				baseDefense = 5;
				baseHP = 4;
				elementalType = "water";
			}
			else if (kaijuType.equals("Leafeon"))
			{
				baseAttack = 4;
				baseDefense = 4;
				baseHP = 5;
				elementalType = "grass";
			}

			// Available wild kaiju
			if (wildKaijuType.equals("Growlithe"))
			{
				wildBaseAttack = 4;
				wildBaseDefense = 3;
				wildBaseHP = 3;
			}
			else if (wildKaijuType.equals("Vulpix"))
			{
				wildBaseAttack = 4;
				wildBaseDefense = 3;
				wildBaseHP = 3;
			}
			else if (wildKaijuType.equals("Marill"))
			{
				wildBaseAttack = 3;
				wildBaseDefense = 4;
				wildBaseHP = 3;
			}
			else if (wildKaijuType.equals("Squirtle"))
			{
				wildBaseAttack = 3;
				wildBaseDefense = 4;
				wildBaseHP = 3;
			}
			else if (wildKaijuType.equals("Bulbasaur"))
			{
				wildBaseAttack = 3;
				wildBaseDefense = 3;
				wildBaseHP = 4;
			}
			else if (wildKaijuType.equals("Shaymin"))
			{
				wildBaseAttack = 3;
				wildBaseDefense = 3;
				wildBaseHP = 4;
			}
		}

		if (leveledUp == true)
		{
			levelUp();
			leveledUp = false;
		}

		if (leveledUp == true)
		{
			levelUp();
			leveledUp = false;
		}

		if (trainerLevel > 1)
		{
			kaijuLevel = trainerLevel + (int)(Math.random() * 1 - 1);
		}

		battleAttack = baseAttack * trainerLevel / 5;
		battleDefense = baseDefense / 5; // later in battleFlag do attack - defense;
		battleHP = baseHP;

		wildBattleAttack = wildBaseAttack * kaijuLevel / 5;
		wildBattleDefense = wildBaseDefense / 5; // later in battleFlag do attack - defense;
		wildBattleHP = wildBaseHP;

		maxExp = (battleAttack * 5) + (battleDefense * 5) + baseHP;

		if (battleAttack <= 0)
		{
			battleAttack = 1;
		}
		if (wildBattleAttack <= 0)
		{
			wildBattleAttack = 1;
		}

		if (battleAttack >= wildBattleHP / 2 && trainerLevel > 1)
		{
			battleAttack  = wildBattleHP / 2 - 1;
		}
		if (wildBattleAttack >= battleHP / 2 && kaijuLevel > 1)
		{
			wildBattleAttack  = battleHP / 3 + 1;
		}

		if (runOnce == 1)
		{

			currentHP = battleHP;
			wildCurrentHP = wildBattleHP;
			runOnce--;
		}

		/*if (neverAgain == 1)
		{
			currentExp = 
			neverAgain--;
		}*/
	}

	public void battle ()
	{
		stats();

		if (battleCounter == 1)
		{
			wildCurrentHP = wildCurrentHP - battleAttack; //  + (battleAttack - wildBattleDefense)
			battleCounter++;
		}
		else if (battleCounter == 2)
		{
			currentHP = currentHP - wildBattleAttack; //  + (wildBattleAttack - battleDefense)
			battleCounter--;
		}
	}

	public void catchKaiju ()
	{
		// only if not challenegr, create boolean
		int lessChance = 0;
		int chance = 0;

		if (catchCounter == 1 && balls > 0)
		{
			lessChance = (int)(Math.random() * 6 + 1);
			chance = (int)(Math.random() * 3 + 1); // between 1 and 3 for if health of opponent is down
			catchCounter++;
		}

		stats();

		if (wildCurrentHP > wildBattleHP / 2)
		{
			if (lessChance == 1)
			{
				caught = true;
			}
		}
		else 
		{
			if ((chance % 2) != 0) // 2/3 chance of catch
			{
				// catch kaiju, add to party
				caught = true;
			}
		}
		// if caught
		if (caught == true)
		{
			// end battle
			battleEndFlag = true;

			System.out.println("fill it");
			System.out.println(pos4Filled);

			if (pos2Filled == false)
			{
				System.out.println("pos2");
				stats();
				partyPos2 = pos2Type;
				partyPos2Type = wildKaijuType;
				pos2Filled = true;
			}
			else if (pos3Filled == false)
			{
				System.out.println("pos 3");
				stats();
				partyPos3 = pos2Type;
				partyPos3Type = wildKaijuType;
				pos3Filled = true;
			}
			else if (pos4Filled == false)
			{
				System.out.println("pos 4");
				stats();
				partyPos4 = pos2Type;
				partyPos4Type = wildKaijuType;
				pos4Filled = true;
			}
			else // if (pos1Filled == true && pos2Filled == true && pos3Filled && pos4Filled == true)
			{
				// then party is full
				fullParty = true;
			}
		}

	}

	public void battleReset ()
	{
		battleEndFlag = false;
		battleFlag = false;
		battleStart = false;
		battleCounter = 1;
		wildKaijuCounter = 0;
		addToStatsCounter = 0;
		runOnce = 1;
		doNextThing = false;
		continued = false;
		continuedAgain = false;
		caught = false;
		currentHP = battleHP;

	}

	public int levelUp ()
	{
		trainerLevel++;
		kaijuLevel++;

		baseAttack = baseAttack * trainerLevel;
		baseDefense = baseDefense * trainerLevel;
		baseHP = baseHP * trainerLevel;

		wildBaseAttack = wildBaseAttack * kaijuLevel;
		wildBaseDefense = wildBaseDefense * kaijuLevel;
		wildBaseHP = wildBaseHP * kaijuLevel;

		currentExp = currentExp - maxExp;

		return currentExp;
	}

	public void run () {
		while(true) {
			// MAKE A CHANGE


			// SHOW THE CHANGE
			if (walkUpFlag == true)
			{
				checkCharacter();

				if (male == true)
				{
					boy1.walkVertical(-1);
				}
				else if (female == true)
				{
					girl1.walkVertical(-1);
				}
			}
			if (walkDownFlag == true)
			{
				checkCharacter();

				if (male == true)
				{
					boy1.walkVertical(1);
				}
				else if (female == true)
				{
					girl1.walkVertical(1);
				}
			}
			if (walkLeftFlag == true)
			{
				checkCharacter();

				if (male == true)
				{
					boy1.walkHorizontal(-1);
				}
				else if (female == true)
				{
					girl1.walkHorizontal(-1);
				}
			}
			if (walkRightFlag == true)
			{
				checkCharacter();

				if (male == true)
				{
					boy1.walkHorizontal(1);
				}
				else if (female == true)
				{
					girl1.walkHorizontal(1);
				}
			}

			repaint();

			// Moving delay
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{ 
				e.printStackTrace();
			}
			checkCharacter();
			checkCharacterGrass();
		}

	}

	/*	private void playSound ()
	{
		try
		  {
		    // get the sound file as a resource out of my jar file;
		    // the sound file must be in the same directory as this class file.
		    // the input stream portion of this recipe comes from a javaworld.com article.
		    InputStream inputStream = getClass().getResourceAsStream("Punch.wav");

		    AudioStream audioStream = new AudioStream(inputStream);
		    AudioPlayer.player.start(audioStream);
		  }
		  catch (Exception e)
		  {

		  }
	}*/

	/*
	public void challengerMovement ()
	{
		int getX = challengerLeft.getX();
		boolean goRight = false;
		boolean goLeft = false;

		if (getX == challenger1X)
		{
			goRight = true;
			goLeft = false;
		}
		else if (getX >= challenger1X + 192)
		{
			goLeft = true;
			goRight = false;
		}

		if (goLeft == true)
		{
			challengerLeft.walkHorizontal(-1);
		}
		else if (goRight == true)
		{
			challengerRight.walkHorizontal(1);
		}
	}*/


	public static void main(String[] args)
	{
		JFrame w = new JFrame("CHIISAI KAIJU");
		w.setBounds(100, 100, 678, 707);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window panel = new Window();
		w.addKeyListener(panel);
		w.addMouseListener(panel);
		w.add(panel);
		w.setResizable(false);
		w.setVisible(true);
		panel.run();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}