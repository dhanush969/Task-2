package test;

import model.StudentRecord;
import org.junit.jupiter.api.*;
import service.FileHandler;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    private static final String TEMP_FILE = "temp_scores.csv";
    private FileHandler handler;

    @BeforeEach
    void init() throws IOException {
        Files.deleteIfExists(Path.of(TEMP_FILE)); // clean before each test
        handler = new FileHandler(TEMP_FILE);
    }

    @Test
    @DisplayName("Check single record write")
    void singleWriteTest() throws IOException {
        StudentRecord record = new StudentRecord("Sample", 1, 88);
        handler.saveToFile(record);

        List<String> lines = Files.readAllLines(Path.of(TEMP_FILE));
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("Sample"));
    }

    @Test
    @DisplayName("Check concurrent writes")
    void concurrentWriteTest() throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> handler.saveToFile(new StudentRecord("Alpha", 11, 90)));
        Thread t2 = new Thread(() -> handler.saveToFile(new StudentRecord("Beta", 12, 92)));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        List<String> lines = Files.readAllLines(Path.of(TEMP_FILE));
        assertEquals(2, lines.size(), "Two records should be written safely");
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Path.of(TEMP_FILE));
    }
}
