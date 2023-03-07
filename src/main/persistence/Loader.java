package persistence;

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
    public List<Command> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder content = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            content.append(line);
            line = reader.readLine();
        }
        return parseFile(content.toString());
    }

    // EFFECTS: return the name of the targeted file
    public String getFileName() {
        return this.fileName;
    }

    // EFFECTS: parse the JSON entry and turn it into a Command
    private Command parseCommand(JSONObject commandJson) throws InvalidArgumentException {
        Command command;
        DataType operandOne = new DataType(commandJson.getInt("operandOne"));
        DataType operandTwo = new DataType(commandJson.getInt("operandTwo"));
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
            default:
                throw new JSONException("Command not found");
        }
        command.input(operandOne, operandTwo);
        return command;
    }

    // EFFECTS: parse JSON file into list of commands
    private List<Command> parseFile(String fileContent) {
        JSONObject commandsJson = new JSONObject(fileContent);
        List<Command> commands = new LinkedList<>();
        for (int i = 0; i < commandsJson.keySet().size(); i++) {
            JSONObject commandToBeParsed = commandsJson.getJSONObject(Integer.toString(i));
            try {
                commands.add(parseCommand(commandToBeParsed));
            } catch (InvalidArgumentException | JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return commands;
    }
}
