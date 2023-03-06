package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Saver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaverTest {
    private Saver testSaver;
    private String targetFile;

    @BeforeEach
    public void setup() {
        targetFile = "saved_progress.json";
        testSaver = new Saver(targetFile);
    }

    @Test
    public void testConstructor() {
        assertEquals(targetFile, testSaver.getFileName());
        assertEquals("JOJI.json", testSaver.getFileName());
    }

}
