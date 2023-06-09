import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * CoffeeReader class that reads in a text file that 
 * contains all of the Coffee drink item information to
 * be used in creating the GUI list. 
 * @author Abby Noonen
 *
 */

public class CoffeeReader {
	
	public static void main(String[] args) throws FileNotFoundException {
		CoffeeReader c = new CoffeeReader();
		ArrayList<Coffee> list = c.reader();
		for (Coffee cof : list) {
			System.out.println(cof.getName() + " " + cof.getCalories() + " " + cof.getType());
		}
		
	}
	
    public ArrayList<Coffee> reader() {
        ArrayList<Coffee> list = new ArrayList<Coffee>();
        InputStream file = this.getClass().getResourceAsStream("/res/CoffeeList.txt");
        Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
		    String name = in.next();
		    name = name.replaceAll("#", " ");
		    int cal = in.nextInt();
		    String temp = in.next();
		    Boolean season = in.nextBoolean();
		    String link = in.next();
		    String imgLink = in.next();

		    Coffee c = new Coffee(name, cal, temp, season, link, imgLink);

		    if (in.hasNextLine()) {
		        list.add(c);
		        in.nextLine();
		    } else {
		        break;
		    }
		}
		in.close();
        return list;
    }

}