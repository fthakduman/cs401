package com.composite.modelbasedtesting;

import nz.ac.waikato.modeljunit.RandomTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class ModelBasedTester {
	public static void main(String[] argv) {
		Model model = new Model();
		// Tester tester = new LookaheadTester(model);
		RandomTester tester = new RandomTester(model);
		// Tester tester = new AllRoundTester(model);
		// Tester tester = new GreedyTester(model);
		tester.buildGraph();
		tester.addListener(new VerboseListener());
		tester.addListener(new StopOnFailureListener());
		tester.addCoverageMetric(new TransitionCoverage());
		tester.addCoverageMetric(new StateCoverage());
		tester.addCoverageMetric(new ActionCoverage());
		tester.generate(5);
		tester.printCoverage();
	}
}