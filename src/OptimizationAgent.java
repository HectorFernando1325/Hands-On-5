import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class OptimizationAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("OptimizationAgent " + getAID().getName() + " is ready.");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    // Lógica para realizar la optimización
                    String response = performOptimization(content);

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

    private String performOptimization(String data) {
        // Lógica para realizar optimización (simulada aquí con un ejemplo sencillo)
        if (data.equals("Optimization: GA")) {
            return "GA Result: [Optimal Values]";
        } else if (data.equals("Optimization: PSO")) {
            return "PSO Result: [Optimal Values]";
        } else {
            return "Unknown optimization type";
        }
    }
}
