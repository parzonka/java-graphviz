package javagraphviz;

public class OutputType {

    private final String name;
    private String filePath;

    OutputType(String name) {
	this.name = name;
	this.filePath = "default." + name;
    }

    public void toFilePath(String filePath) {
	this.filePath = filePath;
    }

    public String name() {
	return this.name;
    }

    public String filePath() {
	return this.filePath;
    }

}
