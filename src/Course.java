public class Course {
	// name of course
	private String courseName;
	// course start date
	private String startDate;
	// course end date
	private String endDate;
	// course length
	private String length;
	// course image
	private String image;
	// course price
	private String price;
	// course rate
	private double rate;

	public Course(String courseName, String startDate, String endDate, String length, double rate) {
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.length = length;
		this.rate = rate;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
