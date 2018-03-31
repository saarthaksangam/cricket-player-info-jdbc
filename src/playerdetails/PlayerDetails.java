/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerdetails;

/**
 *
 * @author Sunny
 */
public class PlayerDetails {
        
	
		private String name;
		private String matches;
		private String runs;
		private String wickets;
	    private String centuries;
        private String fifties;

    public PlayerDetails(String name, String matches, String runs, String wickets, String centuries, String fifties) {
		super();
		this.name = name;
		this.matches = matches;
		this.runs = runs;
		this.wickets = wickets;
		this.centuries = centuries;
        this.fifties = fifties;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getCenturies() {
        return centuries;
    }

    public void setCenturies(String centuries) {
        this.centuries = centuries;
    }

    public String getFifties() {
        return fifties;
    }

    public void setFifties(String fifties) {
        this.fifties = fifties;
    }
@Override
	public String toString() {
		return String
				.format("PlayerDetails [name=%s, matches=%s, runs=%s, wickets=%s, centuries=%s, fifties=%s]",
						name, matches, runs, wickets, centuries, fifties);
	}

}
