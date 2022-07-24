package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestAnnotationCounter implements ITestListener {

    private final String REPORT_PATH = "target/methodsWithTestAnnotation.txt";

    @Override
    public void onFinish(ITestContext arg0) {
        try {
            String text = "Amount of @Test annotation used: " + arg0.getAllTestMethods().length + "\n";
            text += Arrays.stream(arg0.getAllTestMethods())
                    .map(ITestNGMethod::getMethodName)
                    .collect(Collectors.joining(", "));

            BufferedWriter bw = new BufferedWriter(new FileWriter(REPORT_PATH, false));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
