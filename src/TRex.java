import java.util.Random;

public class TRex {

	private Random fRandom;

	public TRex(Random rand) {
		fRandom = rand;
	}


	public TIndividual[] makeOffspring(TIndividual[] parents, int noOfOffspring) {
		//初期化
		TIndividual[] children = new TIndividual[noOfOffspring];
		for (int i = 0; i < children.length; i++) {
			children[i] = new TIndividual();
			children[i].getVector().setDimension(3);
		}

		//はじめに重心を計算(すべてのベクトルは長さが同じと仮定)
		int dimension = parents[0].getVector().getDimension();
		TVector gVector = new TVector();
		gVector.setDimension(dimension);;
		for (int i = 0; i < parents.length; i++) {
			gVector.add(parents[i].getVector());
		}
		gVector.scalarProduct((double)1/parents.length);

		for (int i = 0; i < children.length; i++) {
			//第2項のベクトルを計算
			TVector term2Vector = new TVector();
			term2Vector.setDimension(dimension);
			for (int j = 0; j < parents.length; j++) {
				TVector subVector = new TVector(parents[j].getVector());
				subVector.substract(gVector);
				//TODO εの計算
				double eps = normalDist(fRandom.nextDouble(),parents.length);
				subVector.scalarProduct(eps);
				term2Vector.add(subVector);
			}
			children[i].getVector().copyFrom(gVector.clone().add(term2Vector));
		}
		return children;
	}



	/**
	 * 平均0，分散1/(η-1)とした正規分布の確率密度関数
	 * @param x
	 * @return
	 */
	private double normalDist(double x, int noOfParents) {
		return Math.sqrt((noOfParents - 1)/(2 * Math.PI)) * Math.exp(- x * x * (noOfParents - 1)/2);
	}


	/**
	 * 平均0，分散1/(η-1)とした一様分布の確率密度関数
	 * @param x
	 * @param noOfParents
	 * @return
	 */
	private double uniformDist(double x, int noOfParents) {
		return ((double)(noOfParents - 1)) / 12;
	}


	public static void main(String[] args) {
		Random rand = new Random();
		TRex rex = new TRex(rand);

		//親個体を生成
		TIndividual[] parents = new TIndividual[3];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = new TIndividual();
			parents[i].getVector().setDimension(3);
			for (int j = 0; j < parents[i].getVector().getDimension(); j++) {
				parents[i].getVector().setElement(j, rand.nextInt(100));
			}
		}

		TIndividual[] children = rex.makeOffspring(parents, 2);

		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i]);
		}
	}
}
