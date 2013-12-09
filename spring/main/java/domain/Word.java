package domain;

public class Word {
	private String firstHalf;
	private String lastHalf;

	public String getFirstHalf() {
		return firstHalf;
	}
	public void setFirstHalf(String firstHalf) {
		this.firstHalf = firstHalf;
	}
	public String getLastHalf() {
		return lastHalf;
	}
	public void setLastHalf(String lastHalf) {
		this.lastHalf = lastHalf;
	}
	public String getFullWord(){
		return this.firstHalf+"|"+this.lastHalf;
	}
	
}
