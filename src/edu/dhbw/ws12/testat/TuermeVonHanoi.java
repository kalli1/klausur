package edu.dhbw.ws12.testat;

import javax.swing.JOptionPane;

/**
 * Filename: TuermeVonHanoi.java
 * Author: 	Lukas K�mmerer
 * 			DHBW-Mannheim
 * Created: 04.12.2012
 *
 *	(c)2012
 */

public class TuermeVonHanoi {
	//Test1
	private static String[][] towerArray;
	private static int zuege = 0;
	private static int rekursionen = 0;
	
	private static long zstStart;
	private static long zstRechnung;
	private static long zstEnde;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//showTowers();
		zstStart = System.currentTimeMillis();
		
		
		createEmptyTowers();
		fillDefaultTower();
		
		change(towerArray[0].length-1, 0, 1, 2);

		
		System.out.println("-------------------------------------------------");
		
		zstEnde = System.currentTimeMillis();
		
		String ausgabe = "Es wurden "+zuege+" Zuege mit "+rekursionen+" Rekursionen benoetigt\n";
			   ausgabe += "Berechnungen haben ca. "+(zstEnde-zstRechnung)+"ms gedauert; das Programm war ca. "+(zstEnde-zstStart)+"ms aktiv.";
		
		System.out.println(ausgabe);
		JOptionPane.showMessageDialog(null, ausgabe);
	}
	
	private static int getHigh(){
		return Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die Anzahl der Scheiben an:"));
		//return 3;
	}
	
	private static void createEmptyTowers(){
		int anz = getHigh();
		
		zstRechnung = System.currentTimeMillis();
		
		towerArray = new String[3][anz+1];
		
		for (int i = 0; i < towerArray.length; i++){
			for(int j = 0; j< towerArray[i].length;j++){
				towerArray[i][j]=".";
			}
		}
	}
	
	
	private static void moveDisc(int source, int destination){
		int highSource = getLetzteGefuellteZelle(source);
		int highDestination = getLetzteGefuellteZelle(destination)-1;
		
		
		towerArray[destination][highDestination] = towerArray[source][highSource];
		towerArray[source][highSource] = ".";
		
		zuege++;
		
		System.out.println("verschiebe Scheibe-"+towerArray[destination][highDestination]+" von Turm-"+source+" nach Turm-"+destination);
		
		showTowers();
		
		try{Thread.sleep(500);} catch (InterruptedException ignore){}
	}
	
	private static int getLetzteGefuellteZelle(int turm){
		int high = towerArray[turm].length;
		
		for(int i = 0; i<high; i++){
			if(!towerArray[turm][i].equals(".")){
				return i;
			}
		}
		
		return high;
	}
	
	private static void showTowers(){
		for(int i = 0; i<towerArray[0].length; i++){
			System.out.println(towerArray[0][i]+"\t\t"+towerArray[1][i]+"\t\t"+towerArray[2][i]);
		}
	}
	
	private static void fillDefaultTower(){
		int high = towerArray[0].length;
		
		for(int i = 1; i<high;i++){
			String temp = "";
			
			for(int j = 0; j<i; j++){
				temp = temp+i;
			}
			
			towerArray[0][i] = temp;
		}
	}

	//a = source b=temp c=target
	private static void change(int i, int a, int b, int c){
		rekursionen++;
		if(i>0){
			change(i-1, a, c, b);
			moveDisc(a, c);
			change(i-1, b, a, c);
		}
	}
}
