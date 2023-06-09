
/**
 * Coffee class that contains all the information that
 * will be displayed for each drink item on the list.
 * @author Brody Criz
 *
 */
public class Coffee {

	private String name; // name of the drink
	private int calories; // calorie content 
	private String type; // (cold, hot, tea, etc.)
	private boolean seasonal; // if the drink is seasonal or not 
	private String link; // URL to Starbucks website 
	private String imgLink; // URL to Starbucks drink image
	
	public Coffee(String name, int calories, String type, boolean seasonal, String link, String imgLink) {
		this.name = name;
		this.calories = calories;
		this.type = type;
		this.seasonal = seasonal;
		this.link = link;
		this.imgLink = imgLink;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String i) {
		this.name = i;
	}
	
	public int getCalories() {
		return calories;
	}
	
	public void setCalories(int c) {
		this.calories = c;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public boolean getSeasonal() {
		return seasonal;
	}
	
	public void setSeasonal(Boolean s) {
		this.seasonal = s;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getImgLink() {
		return imgLink;	
	}
	
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;	
	}
	

	
	
} // end Coffee class






