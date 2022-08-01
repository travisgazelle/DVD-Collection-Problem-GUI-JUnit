package dvdPackage;

public class DVD {

	private String title;
	private String rating;
	private int runningTime;
	private String genre;

	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;
		this.genre = "GENERIC";
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getRating() {
		return this.rating;
	}
	
	public int getRunningTime() {
		return this.runningTime;
	}
	
	public String getGenre() {
		return this.genre;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setRating(String newRating) {
		this.rating = newRating;
	}

	public void setRunningTime(int newRunningTime) {
		this.runningTime = newRunningTime;
	}
	
	public void setGenre(String newGenre) {
		this.genre = newGenre;
	}

	public String toString() {
		return Integer.toString(this.runningTime);
	}
	
}