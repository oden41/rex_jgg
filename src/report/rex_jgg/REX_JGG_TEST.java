package report.rex_jgg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class REX_JGG_TEST {

	private static void executeOneTrial(TJgg jgg, PrintWriter pw, long maxEvals, int dimension, int noOfParents, int noOfKids) {
		long noOfEvals = 0; // 評価回数を初期化．
		long noOfGeneration = 0;
		double best = jgg.getPopulation().getBestEvaluationValue(); // 集団の最良評価値を取得．

		int loopCount = 0; // ループカウンタを初期化する．
		while (best > 1e-7 && noOfEvals < maxEvals) { // 終了条件．最良値が10^-7以下，もしくは，評価回数が打ち切り評価回数を超えたとき．
			pw.println(noOfGeneration);
			jgg.getPopulation().writeTo(pw);// 初期集団の情報をログに保存．
			loopCount++;
			jgg.doOneIteration(); // GAの世代を１世代進める．
			noOfEvals += noOfKids;
			noOfGeneration++;
			jgg.d_Parents.writeTo(pw);
			jgg.d_Children.writeTo(pw);
			best = jgg.getPopulation().getBestEvaluationValue();
			System.out.println("NoOfEvals:" + noOfEvals + "NoOfBeneration:" + noOfGeneration +  ", Best:" + best); // 画面に試行数，評価回数，最良評価値を表示．
		}
		System.out.println("NoOfEvals:" + noOfEvals + "NoOfBeneration:" + noOfGeneration +  ", Best:" + best); // 画面に試行数，評価回数，最良評価値を表示．
	}

	public static void main(String[] args) throws IOException {
		int dimension = 2; // 次元数
		int populationSize = 7 * dimension; // 集団サイズ
		int noOfParents = dimension + 1;
		int noOfKids = 6 * dimension; // 子個体生成数
		double min = -5.00; // 初期化領域の最小値
		double max = +5.00; // 初期化領域の最大値
		long maxEvals = (long) (4 * dimension * 1e4); // 打ち切り評価回数
		String trialName = "RexJggSphereTestLog"; // 試行名
		String logFilename = trialName + ".txt"; // ログファイル名
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logFilename)));

		IFunction function = new TSphereFunction(dimension);
		TPopulation initialPopulation = new TPopulation();
		Random random = new Random();
		initialPopulation.setPopulationSize(populationSize);
		for (int i = 0; i < populationSize; i++) {
			initialPopulation.getIndividual(i).getVector().setDimension(dimension);
			for (int j = 0; j < dimension; j++) {
				initialPopulation.getIndividual(i).getVector().setElement(j, random.nextDouble() * (max - min) + min);
			}
			initialPopulation.getIndividual(i).setEvaluationValue(function.evaluate(initialPopulation.getIndividual(i).getVector()));
		}
		TJgg jgg = new TJgg(function, initialPopulation, new Random(), noOfParents, noOfKids);
		executeOneTrial(jgg, pw, maxEvals, dimension, noOfParents, noOfKids);

		pw.close();
	}

}
