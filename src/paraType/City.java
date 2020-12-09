package paraType;

public class City {
	private String CityName;
	private double Density;//average contact
	private double  SimulationDay;//test day
	
	
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String cityName, double density, double simulationday) {
		super();
		CityName = cityName;
		Density = density;
		SimulationDay = simulationday;
	}
	
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public double getDensity() {
		return Density;
	}
	public void setDensity(double density) {
		Density = density;
	}
	public double getSimulationDay() {
		return SimulationDay;
	}

	public void setSimulationDay(double numberOfHospital) {
		SimulationDay = numberOfHospital;
	}



}
