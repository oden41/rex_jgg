import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class TPopulation extends TBaseMethods<TPopulation> {
	private TIndividual[] fArray;


	public TPopulation() {
		fArray = new TIndividual[0];
	}


	public TPopulation(TPopulation population) {
		fArray = new TIndividual[population.getNumberOfPopulation()];
		for (int i = 0; i < population.getNumberOfPopulation(); i++) {
			fArray[i] = population.getIndividual(i).clone();
		}
	}


	@Override
	public TPopulation copyFrom(TPopulation src) {
		if(getNumberOfPopulation() != src.getNumberOfPopulation())
			setnumberOfPopulation(src.getNumberOfPopulation());

		for (int i = 0; i < src.getNumberOfPopulation(); i++) {
			fArray[i].copyFrom(src.getIndividual(i));
		}
		return this;
	}


	@Override
	public TPopulation clone() {
		return new TPopulation(this);
	}


	@Override
	public String toString() {
		String string = getNumberOfPopulation() + "\n";
		for (TIndividual tIndividual : fArray) {
			string += tIndividual;
		}
		return string;
	}


	@Override
	public void readFrom(BufferedReader br) throws IOException {
		int numOfPopulation = Integer.parseInt(br.readLine());
		setnumberOfPopulation(numOfPopulation);
		for (int i = 0; i < numOfPopulation; i++) {
			fArray[i] = new TIndividual();
			fArray[i].readFrom(br);
		}
	}


	/**
	 * 書式は<br>
	 * <br>
	 * 個体数<br>
	 * 各個体の情報<br>
	 * <br>
	 * となっている
	 *
	 * @see TBaseMethods#writeTo(java.io.PrintWriter)
	 */
	@Override
	public void writeTo(PrintWriter pw) {
		pw.println(getNumberOfPopulation());
		for (TIndividual tIndividual : fArray) {
			tIndividual.writeTo(pw);
		}
	}


	@Override
	public boolean equals(Object t) {
		if(t == null || getClass() != t.getClass())
			return false;

		TPopulation tPopulation = (TPopulation)t;
		if(getNumberOfPopulation() != tPopulation.getNumberOfPopulation())
			return false;

		for (int i = 0; i < getNumberOfPopulation(); i++) {
			if(fArray[i] == null || !fArray[i].equals(tPopulation.getIndividual(i)))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int num = 1;
		for (TIndividual tIndividual : fArray) {
			num *= tIndividual.hashCode();
		}
		return num * getNumberOfPopulation();
	}


	public int getNumberOfPopulation() {
		return fArray.length;
	}


	public void setnumberOfPopulation(int num) {
		fArray = new TIndividual[num];
		for (int i = 0; i < fArray.length; i++) {
			fArray[i] = new TIndividual();
		}
	}


	public TIndividual getIndividual(int index) {
		return fArray[index];
	}


//	public static void main(String[] args) throws IOException {
//		//File file = new File("TPopulationTest.txt");
//		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
//		//BufferedReader br = new BufferedReader(new FileReader(file));
//		TPopulation population = new TPopulation();
//		population.setnumberOfPopulation(5);
//		for (int i = 0; i < population.getNumberOfPopulation(); i++) {
//			TIndividual individual =  population.getIndividual(i);
//			individual.getVector().setDimension(3);
//			for (int j = 0; j < individual.getVector().getDimension(); j++) {
//				individual.getVector().setElement(j, j * i);
//			}
//			individual.setEvaluationValue(100 * i);
//		}
//		TPopulation population2 = population.clone();
////		population2.setnumberOfPopulation(5);
////		for (int i = 0; i < population2.getNumberOfPopulation(); i++) {
////			TIndividual individual =  population2.geTIndividual(i);
////			individual.getVector().setDimension(3);
////			for (int j = 0; j < individual.getVector().getDimension(); j++) {
////				individual.getVector().setElement(j, j * i * 10);
////			}
////			individual.setEvaluationValue(0.125 * i);
////		}
//
//		System.out.println(population.equals(population2));
//		//population2.getIndividual(1).setEvaluationValue(1000);
//		population2.getIndividual(2).getVector().setElement(1, 1.0005);
//
//		System.out.println(population.equals(population2));
//		//System.out.println(population2);
//		//pw.close();
//		//br.close();
//	}

}
