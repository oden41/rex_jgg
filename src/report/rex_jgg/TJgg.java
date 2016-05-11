package report.rex_jgg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TJgg {
	private Random fRandom;
	private TPopulation fpopulation;
	private TRex fRex;
	private TSphereFunction fFunction;

	public TJgg(TSphereFunction function, TPopulation initialPopulation, Random rand) {
		fRandom = rand;
		fpopulation = initialPopulation;
		fFunction = function;

		fRex = new TRex(rand);
	}

	public void doOneIteration() {
		// 複製選択 n+1個体ランダムに選択する．
		int n = fpopulation.getIndividual(0).getVector().getDimension();
		ArrayList<Integer> parentsIndex = new ArrayList<>();
		TIndividual[] parents = new TIndividual[n + 1];
		for (int i = 0; i < n + 1; i++) {
			int index = fRandom.nextInt(fpopulation.getPopulationSize());
			while (parentsIndex.contains(index)) {
				index = fRandom.nextInt(fpopulation.getPopulationSize());
			}
			parentsIndex.add(index);
			parents[i] = fpopulation.getIndividual(index).clone();
		}

		// 子個体の生成
		TIndividual[] children = fRex.makeOffspring(parents, 5 * n);

		// 子個体の評価
		for (int i = 0; i < children.length; i++) {
			children[i].setEvaluationValue(fFunction.evaluate(children[i].getVector()));
		}

		// 生存選択
		// 上位n+1個体をparentsIndexの個体と置き換える
		Arrays.sort(children, (a, b) -> ((Double) a.getEvaluationValue()).compareTo((Double) b.getEvaluationValue()));
		for (int i = 0; i < parentsIndex.size(); i++) {
			fpopulation.getIndividual(parentsIndex.get(i)).copyFrom(children[i]);
		}
	}

	public TPopulation getPopulation() {
		return fpopulation;
	}
}
