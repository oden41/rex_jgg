package report.rex_jgg;

import java.util.Arrays;
import java.util.Random;

public class TJgg {
	private Random fRandom;
	private TPopulation fpopulation;
	private TRex fRex;
	private IFunction fFunction;
	private int fNoOfParents;
	private int fNoOfKids;

	// 以下デバッグ用変数
	public TPopulation d_Parents;
	public TPopulation d_Children;

	public TJgg(IFunction function, TPopulation initialPopulation, Random rand, int noOfParents, int noOfKids) {
		fRandom = rand;
		fpopulation = initialPopulation;
		fFunction = function;
		fNoOfParents = noOfParents;
		fNoOfKids = noOfKids;

		d_Parents = new TPopulation();
		d_Children = new TPopulation();

		fRex = new TRex(fRandom);
	}

	public void doOneIteration() {
		// 複製選択
		fpopulation.shuffle();
		TIndividual[] parents = fpopulation.getRange(0, fNoOfParents);
		d_Parents.setPopulationSize(parents.length);
		for (int i = 0; i < parents.length; i++) {
			d_Parents.getIndividual(i).copyFrom(parents[i]);
		}

		// 子個体の生成
		TIndividual[] children = fRex.makeOffspring(parents, fNoOfKids);

		// 子個体の評価
		for (int i = 0; i < children.length; i++) {
			children[i].setEvaluationValue(fFunction.evaluate(children[i].getVector()));
		}

		d_Children.setPopulationSize(children.length);
		for (int i = 0; i < children.length; i++) {
			d_Children.getIndividual(i).copyFrom(children[i]);
		}

		// 生存選択
		// 上位n+1個体をparentsの個体と置き換える
		Arrays.sort(children, (a, b) -> ((Double) a.getEvaluationValue()).compareTo((Double) b.getEvaluationValue()));
		for (int i = 0; i < parents.length; i++) {
			fpopulation.getIndividual(i).copyFrom(children[i]);
		}
	}

	public TPopulation getPopulation() {
		return fpopulation;
	}
}
