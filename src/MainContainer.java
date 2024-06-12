import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MainContainer {

    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer mainContainer = rt.createMainContainer(p);

        try {
            AgentController problemClassifier = mainContainer.createNewAgent("problemClassifier", ProblemClassifierAgent.class.getName(), null);
            problemClassifier.start();

            AgentController regressionAgent = mainContainer.createNewAgent("regressionAgent", RegressionAgent.class.getName(), null);
            regressionAgent.start();

            AgentController optimizationAgent = mainContainer.createNewAgent("optimizationAgent", OptimizationAgent.class.getName(), null);
            optimizationAgent.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
