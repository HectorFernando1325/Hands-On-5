import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RegressionAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("RegressionAgent " + getAID().getName() + " is ready.");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    // Lógica para realizar la regresión
                    String response = performRegression(content);

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

    private String performRegression(String data) {
        // Lógica para realizar regresión (simulada aquí con un ejemplo sencillo)
        if (data.equals("Regression: SLR")) {
            return "SLR Result: [B0, B1]";
        } else if (data.equals("Regression: MLR")) {
            return "MLR Result: [B0, B1, B2]";
        } else {
            return "Unknown regression type";
        }
    }
}
