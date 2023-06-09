package model;

import except.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Type of commands that do computation
public abstract class Operator implements Command {
    protected DataType operandOne;
    protected DataType operandTwo;
    protected DataType result;
    protected EventLog eventLogger;

    public Operator() {
        eventLogger = EventLog.getInstance();
    }

    @Override
    // EFFECTS: convert information the Operator holds to JSONarray
    public JSONObject toJson() {
        String commandName = getHeader().split(" ")[0];
        String opOne = operandOne.toString();
        String opTwo = operandTwo.toString();

        JSONObject info = new JSONObject();
        info.put("command", commandName);
        info.put("operandOne", opOne);
        info.put("operandTwo", opTwo);
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Operator operator = (Operator) o;
        return operandOne.equals(operator.operandOne) && operandTwo.equals(operator.operandTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operandOne, operandTwo);
    }

    @Override
    // EFFECTS: return the constraints specific to this command
    public String getConstraints() {
        return "Operators only accept two inputs.";
    }

    @Override
    // EFFECTS: return the result of the addition
    //          If the command has yet to executed, throw NotYetExecutedException
    public DataType getResult() throws NotYetExecutedException {
        if (result == null) {
            throw new NotYetExecutedException();
        }
        return result;
    }

    // EFFECTS: return the inputs of this command
    public List<DataType> getInputs() throws MissingArgumentException {
        isMissingInputs();

        List<DataType> tempInputs = new ArrayList<>();
        tempInputs.add(operandOne);
        tempInputs.add(operandTwo);
        return tempInputs;
    }

    // EFFECTS: verify the current inputs
    protected void isMissingInputs() throws MissingArgumentException {
        if (operandOne == null || operandTwo == null) {
            throw new MissingArgumentException();
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: take in inputs
    //          If the number of inputs is more than 2 or the type of input is not number,
    //          InvalidArgumentException will be raised.
    public void input(DataType... inputs) throws InvalidArgumentException {
        checkInput(inputs);
        operandOne = inputs[0];
        operandTwo = inputs[1];
        eventLogger.logEvent(new Event(operandOne + " and " + operandTwo + " have been added to "
                + this.getHeader()));
    }

    // EFFECTS: If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        if (inputs.length != 2) {
            throw new UnexpectedNumberOfArgumentsException(inputs.length, 2);
        }
    }
}
