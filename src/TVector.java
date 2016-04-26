import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class TVector {
	private double[] fArray;

	public TVector(){
	}

	public TVector(TVector src){
		fArray = new double[src.fArray.length];
		for (int i = 0; i < fArray.length; i++) {
			fArray[i] = src.fArray[i];

		}
	}

	public TVector copyFrom(TVector src) {
		if (fArray.length != src.fArray.length) {
			fArray = new double[src.fArray.length];
		}

		for (int i = 0; i < fArray.length; i++) {
			fArray[i] = src.fArray[i];

		}
		return this;
	}


	@Override
	public TVector clone() {
		return new TVector(this);
	}


	@Override
	public String toString(){
		String str = "";
		for (int i = 0; i < fArray.length; i++) {
			str += fArray[i] + " ";
		}
		return str;
	}


	public void writeTo(PrintWriter pw) {
		pw.println(fArray.length);
		/*for (int i = 0; i < fArray.length; i++) {
			pw.print(fArray[i] + " ");
		}*/
		pw.println(this);
	}


	public void  readFrom(BufferedReader br)  throws IOException {
		int dimension = Integer.parseInt(br.readLine());
		String[] strArray = br.readLine().split("");
		if(fArray.length != dimension){
			fArray = new double[dimension];
		}

		for (int i = 0; i < fArray.length; i++) {
			fArray[i] = Double.parseDouble(strArray[i]);
		}
	}


	public int getDimension() {
		return fArray.length;
	}

	public void setDimension(int dimension) {
		if(fArray.length != dimension){
			fArray = new double[dimension];
		}
	}


	public double getElement(int index) {
		return fArray[index];
	}

	public void setElement(int index, double x) {
		fArray[index] = x;
	}


	//y += x; のように単項演算子として定義
	public TVector add(TVector x) {
		//assert x.fArray.length == fArray.length;
		for (int i = 0; i < fArray.length; i++) {
			fArray[i] += x.fArray[i];
		}
		return this;
	}


	public TVector substract(TVector x) {

	}


	public TVector innerProduct(TVector x) {

	}


	public TVector scalarProduct(double a) {

	}


	public double getL2Norm() {

	}


	//単位ベクトル化
	public TVector normalize() {

	}


	@Override
	public boolean equals(Object t){

	}


	public static void  main(String[] args){

	}
}
