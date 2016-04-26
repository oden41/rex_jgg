import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;



public class TIndividual {
	private TVector fVector;
	private double fEvalationValue;

	public TIndividual(){
		fVector = new TVector();
		fEvalationValue = Double.NaN;
	}


	public TIndividual(TIndividual src){
		fVector = new TVector(src.fVector);
		fEvalationValue = src.fEvalationValue;
	}


	@Override
	public TIndividual clone(){
		return new TIndividual(this);
	}

	public TIndividual copyFrom(TIndividual src) {
		fVector.copyFrom(src.fVector);
		fEvalationValue = src.fEvalationValue;
		return this;
	}


	@Override
	public String toString() {
		String str = fEvalationValue + "\n";
		str += fVector;
		return str;
	}


	public void readFrom(BufferedReader br) throws IOException {

	}


	public void writeTo(PrintWriter pw) {

	}


	public void setEvaluationValue(double eval) {
		fEvalationValue = eval;
	}


	public double getEvaluationValue() {
		return fEvalationValue;
	}


	public TVector getVector(){
		return fVector;
	}


	@Override
	public boolean equals(Object x){

	}
}
