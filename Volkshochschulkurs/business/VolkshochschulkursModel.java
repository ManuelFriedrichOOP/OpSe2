package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import factory.ConcreteCsvCreator;
import factory.ConcreteCsvProduct;
import factory.ConcreteTxtCreator;
import factory.Creator;
import factory.Product;


public class VolkshochschulkursModel {
	
	public Volkshochschulkurs vhk;
	
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
	}
	
	public void leseVolkshochschulkursAusTxtDatei()throws IOException{
		Creator creator = new ConcreteTxtCreator();
		Product reader = creator.factoryMethod();
		String[] zeile = reader.leseAusDatei();
		this.vhk = new Volkshochschulkurs(zeile[0], 
				zeile[1],
				zeile[2], zeile[3], zeile[4].split("_"));
		reader.schliesseDatei();
	}
	
	public Volkshochschulkurs getVhk() {
		return vhk;
	}

	public void setVhk(Volkshochschulkurs vhk) {
		this.vhk = vhk;
	}
	
}