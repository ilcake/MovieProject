package datas;

public class Comment {
	private String userID;
	private String userText;
	private Double grade;
	private String movieCD;
	private String userPic;

	public Comment(String userID, String userText, Double grade, String movieCD, String userPic) {
		super();
		this.userID = userID;
		this.userText = userText;
		this.grade = grade;
		this.movieCD = movieCD;
		this.userPic = userPic;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String getMovieCD() {
		return movieCD;
	}

	public void setMovieCD(String movieCD) {
		this.movieCD = movieCD;
	}

	@Override
	public String toString() {
		return "Comment [userID=" + userID + ", userText=" + userText + ", grade=" + grade + ", movieCD=" + movieCD
				+ "]";
	}

}
