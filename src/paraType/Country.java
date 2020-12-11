package paraType;

public class Country {
	private String CountryName;
	private double Density;//average contact
	private double  PolicyChangeDay;//Policy Change day 
	
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Country(String countryName, double density, double simulationday) {
		super();
		CountryName = countryName;
		Density = density;
		PolicyChangeDay = simulationday;
	}
	
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public double getDensity() {
		return Density;
	}
	public void setDensity(double density) {
		Density = density;
	}
	public double getPolicyChangeDay() {
		return PolicyChangeDay;
	}

	public void setPolicyChangeDay(double policychangeday) {
		PolicyChangeDay = policychangeday;
	}



}
