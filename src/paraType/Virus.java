package paraType;

public class Virus {
	private String VirusName;
	private double CureRate;
	private double DeathRate;
	private double kFac;
	private double rFac;
	
	
	public Virus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Virus(String virusName, double Dea,double cure, double rFac, double kFac) {
		super();
		VirusName = virusName;
		this.DeathRate=Dea;
		this.CureRate=cure;
		this.kFac = kFac;
		this.rFac = rFac;
	}
	public double getDeathRate() {
		return DeathRate;
	}
	public void setDeathRate(double deathRate) {
		DeathRate = deathRate;
	}
	public double getkFac() {
		return kFac;
	}
	public void setkFac(double kFac) {
		this.kFac = kFac;
	}
	public double getrFac() {
		return rFac;
	}
	public void setrFac(double rFac) {
		this.rFac = rFac;
	}
	public String getVirusName() {
		return VirusName;
	}

	public void setVirusName(String virusName) {
		VirusName = virusName;
	}
	public double getCureRate() {
		return CureRate;
	}
	public void setCureRate(double cureRate) {
		CureRate = cureRate;
	}
	

}
