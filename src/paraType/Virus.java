package paraType;

public class Virus {
	private String VirusName;
	private double CureRate;
	private double kFac;
	private double rFac;
	
	
	public Virus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Virus(String virusName, double kFac,double cure, double rFac ) {
		super();
		VirusName = virusName;
		this.CureRate=cure;
		this.kFac = kFac;
		this.rFac = rFac;
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
