import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ProblemClassifierAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("ProblemClassifierAgent " + getAID().getName() + " is ready.");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    // Lógica para determinar el tipo de análisis
                    String response = classifyProblem(content);

                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(response);
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }

    private String classifyProblem(String data) {
        // Lógica para clasificar el problema (simulada aquí con un ejemplo sencillo)
        if (data.contains("SLR")) {
            return "Regression: SLR";
        } else if (data.contains("MLR")) {
            return "Regression: MLR";
        } else {
            return "Optimization: GA";
        }
    }
}
