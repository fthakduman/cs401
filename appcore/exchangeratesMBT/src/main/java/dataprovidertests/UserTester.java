package dataprovidertests;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class UserTester {



    public static void main(String[] argv) {

        UserModel model = new UserModel();
        Tester tester = new LookaheadTester(model);
        //RandomTester tester = new RandomTester(model);
        // Tester tester = new AllRoundTester(model);
        // Tester tester = new GreedyTester(model);
        tester.buildGraph();

        tester.addListener(new VerboseListener());
        tester.addListener(new StopOnFailureListener());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(20);
        tester.printCoverage();
    }

}
