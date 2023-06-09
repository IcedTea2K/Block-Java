package persistence;

import except.CorruptedFileWarning;
import except.InvalidArgumentException;
import model.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// An Object to parse specified JSON file to load the commands back into the program
public class Loader {
    private String fileName;
    private BufferedReader reader;

    // EFFECTS: create a Loader
    public Loader(String fileName) {
        this.fileName = fileName;
    }

    // EFFECTS: read from the file and return a list of saved commands
    public List<Command> read() throws CorruptedFileWarning {
        StringBuilder content = new StringBuilder();
        try {
            open();
            String line = reader.readLine();
            while (line != null) {
                content.append(line);
                line = reader.readLine();
            }
            close();
        } catch (IOException e) {
            throw new CorruptedFileWarning(fileName);
        }
        return parseFile(content.toString());
    }

    // MODIFIES: this
    // EFFECTS: initialize reader
    public void open() throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    // MODIFIES: this
    // EFFECTS: close and remove the reader
    public void close() throws IOException {
        reader.close();
        reader = null;
    }

    // EFFECTS: return the name of the targeted file
    public String getFileName() {
        return this.fileName;
    }

    // EFFECTS: parse the JSON entry and turn it into a Command
    @SuppressWarnings("methodlength")
    private Command parseCommand(JSONObject commandJson) throws InvalidArgumentException, JSONException {
        Command command;
        DataType operandOne = obtainOperand(commandJson, "operandOne");
        DataType operandTwo = obtainOperand(commandJson, "operandTwo");
        switch (commandJson.getString("command")) {
            case "ADD":
                command = new Add();
                break;
            case "SUB":
                command = new Subtract();
                break;
            case "MUL":
                command = new Multiply();
                break;
            case "DIV":
                command = new Divide();
                break;
            case "AND":
                command = new And();
                break;
            case "OR":
                command = new Or();
                break;
            case "LARGER":
                command = new Larger();
                break;
            case "SMALLER":
                command = new Smaller();
                break;
            case "EQUAL":
                command = new Equal();
                break;
            default:
                throw new JSONException("Command not found");
        }
        command.input(operandOne, operandTwo);
        return command;
    }

    // EFFECTS: obtain the operand value with given operandName
    //          Only throw JSONException is the return type is neither int or boolean
    private DataType obtainOperand(JSONObject commandJson, String opName) throws JSONException {
        DataType operand = null;
        try {
            operand = new DataType(commandJson.getInt(opName));
        } catch (JSONException e) {
            operand = new DataType(commandJson.getBoolean(opName));
        }
        return operand;
    }

    // EFFECTS: parse JSON file into list of commands
    private List<Command> parseFile(String fileContent) throws CorruptedFileWarning {
        JSONObject commandsJson = new JSONObject(fileContent);
        List<Command> commands = new LinkedList<>();
        for (int i = 0; i < commandsJson.keySet().size(); i++) {
            try {
                JSONObject commandToBeParsed = commandsJson.getJSONObject(Integer.toString(i));
                commands.add(parseCommand(commandToBeParsed));
            } catch (InvalidArgumentException | JSONException e) {
                throw new CorruptedFileWarning(fileName, i);
            }
        }
        return commands;
    }
}
