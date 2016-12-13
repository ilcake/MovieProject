package vos;

public class Stars {

	private double stars;

	public Stars(double stars) {
		this.stars = stars;
	}

	public double getStars() {
		return stars;
	}
	
	public void minus(){
		this.stars -= 1;
		System.out.println(stars);
	}
	
	public void plus(){
		this.stars += 1;
		System.out.println(stars);
	}

}
