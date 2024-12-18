package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import factory.ConcreteCsvCreator;
import factory.ConcreteCsvProduct;
import factory.ConcreteTxtCreator;
import factory.Creator;
import factory.Product;
import guiVolkshochschulkurs.VolkshochschulkursControl;
import ownUtil.Observable;
import ownUtil.Observer;


public class VolkshochschulkursModel implements Observable{
	
	private Volkshochschulkurs vhk;
	static VolkshochschulkursModel model;
	public Vector<Observer> observers = new Vector<Observer>();
	
	private VolkshochschulkursModel() {
		
	}
	
	public static VolkshochschulkursModel getInstance() {
		if(model == null) {
			model = new VolkshochschulkursModel();
		} 
		return model;
	}
	
	public void schreibeVolkshochschulkursInCsvDatei() throws IOException {	
		BufferedWriter aus = new BufferedWriter(new FileWriter("Volkshochschulkurs.csv", true));
		aus.write(this.getVhk().gibVolkshochschulkursZurueck(';'));
		aus.close();
	}
	
	public void leseVolkshochschulkursAusCsvDatei()throws IOException{	
		Creator creator = new ConcreteCsvCreator();
		Product reader = creator.factoryMethod();
		String[] zeile = reader.leseAusDatei();
		this.vhk = new Volkshochschulkurs(zeile[0], 
				zeile[1],
				zeile[2], zeile[3], zeile[4].split("_"));
		reader.schliesseDatei();
		notifyObservers();
	}
	
	public void leseVolkshochschulkursAusTxtDatei()throws IOException{
		Creator creator = new ConcreteTxtCreator();
		Product reader = creator.factoryMethod();
		String[] zeile = reader.leseAusDatei();
		this.vhk = new Volkshochschulkurs(zeile[0], 
				zeile[1],
				zeile[2], zeile[3], zeile[4].split("_"));
		reader.schliesseDatei();
		notifyObservers();
	}
	
	public Volkshochschulkurs getVhk() {
		return vhk;
	}

	public void setVhk(Volkshochschulkurs vhk) {
		this.vhk = vhk;
		notifyObservers();
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
}