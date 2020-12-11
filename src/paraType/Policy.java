package paraType;

public class Policy {
	public static int count=0;
	private String PName;
	private boolean ifMaskRequired;
	private boolean ifsocialDistance;
	private boolean ifTracingInfectedIndividual;

	
	public Policy() {
		super();
		count++;
		this.PName="Policy"+count;
		// TODO Auto-generated constructor stub
	}

	public Policy(boolean ifMaskRequired, boolean ifBarrierBetweenCity,
		boolean ifTracingInfectedIndividual) {
		super();
		count++;
		this.PName="Policy"+count;
		this.ifMaskRequired = ifMaskRequired;
		this.ifsocialDistance = ifBarrierBetweenCity;
		this.ifTracingInfectedIndividual = ifTracingInfectedIndividual;

	}
	
	public boolean isIfMaskRequired() {
		return ifMaskRequired;
	}
	public void setIfMaskRequired(boolean ifMaskRequired) {
		this.ifMaskRequired = ifMaskRequired;
	}
	public boolean isIfsocialDistance() {
		return ifsocialDistance;
	}
	public void setIfsocialDistance(boolean ifsocialDistance) {
		this.ifsocialDistance = ifsocialDistance;
	}
	public boolean isIfTracingInfectedIndividual() {
		return ifTracingInfectedIndividual;
	}
	public void setIfTracingInfectedIndividual(boolean ifTracingInfectedIndividual) {
		this.ifTracingInfectedIndividual = ifTracingInfectedIndividual;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}
	



	
	
}
