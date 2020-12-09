package paraType;

public class Policy {
	public static int count=0;
	private String PName;
	private boolean ifMaskRequired;
	private boolean ifsocialDistance;
	private boolean ifTesting;
	private boolean ifTracingInfectedIndividual;

	
	public Policy() {
		super();
		count++;
		this.PName="Policy"+count;
		// TODO Auto-generated constructor stub
	}

	public Policy(boolean ifMaskRequired, boolean ifBarrierBetweenCity, boolean ifTesting,
		boolean ifTracingInfectedIndividual) {
		super();
		count++;
		this.PName="Policy"+count;
		this.ifMaskRequired = ifMaskRequired;
		this.ifsocialDistance = ifBarrierBetweenCity;
		this.ifTesting = ifTesting;
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
	public boolean isIfTesting() {
		return ifTesting;
	}
	public void setIfTesting(boolean ifTesting) {
		this.ifTesting = ifTesting;
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
