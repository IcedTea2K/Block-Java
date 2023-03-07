package model;

import except.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Type of commands that do computation
public abstract class Operator implements Command {
    protected DataType operandOne;
    protected DataType operandTwo;
    protected DataType result;

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
    // EFFECTS: return the constraints specific to this command
    public String getConstraints() {
        return "Operators only accept two inputs.";
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
        checkCurrentInputs();

        List<DataType> tempInputs = new ArrayList<>();
        tempInputs.add(operandOne);
        tempInputs.add(operandTwo);
        return tempInputs;
    }

    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        if (inputs.length != 2) {
            throw new UnexpectedNumberOfArgumentsException(inputs.length, 2);
        }

        try {
            int tempNumOne = inputs[0].getNumber();
            int tempNumTwo = inputs[1].getNumber();
        } catch (InvalidReturnTypeException e) {
            throw new WrongArgumentTypeException(e.getMessage(), "number");
        }
    }

    // EFFECTS: return the number the DataType is representing
    protected int retrieveData(DataType num) {
        int tempNum = 0;
        tempNum = num.getNumber();
        return tempNum;
    }

    // EFFECTS: verify the current inputs
    protected void checkCurrentInputs() throws MissingArgumentException {
        if (operandOne == null || operandTwo == null) {
            throw new MissingArgumentException();
        }
    }
}
