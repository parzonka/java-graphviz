package javagraphviz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GraphvizEngine {

    private String outputType;
    private String layoutManager;
    private String executionPath;
    private File graphvizPath;

    public GraphvizEngine() {
	this.layoutManager = "dot";
	this.outputType = "eps";
	this.executionPath = ".";
    }

    /**
     * Creates a diagram from the given graph.
     * 
     * @param graph
     */
    public void process(Graph graph) {

	final String graphDescription = graph.getDescription();

	try {
	    final StringBuilder sb = new StringBuilder();
	    sb.append(findExecutable(getLayoutManager()));
	    sb.append(" -T  ").append(getOutputType());
	    sb.append(" -o ").append(graph.getId()).append(".").append(getOutputType()).append(" ");
	    sb.append(createTempDescription(graphDescription).getPath());
	    File executionPath = new File(getExecutionPath());
	    if (!executionPath.exists()) {
		executionPath.mkdirs();
	    }

	    Process process = Runtime.getRuntime().exec(sb.toString(), null, executionPath);

	    if (process.waitFor() != 0) {
		throw new GraphvizEngineException("Process has not terminated normally!");
	    }

	} catch (IOException e) {
	    throw new GraphvizOutputException(e.getMessage(), e);

	} catch (InterruptedException e) {
	    throw new GraphvizOutputException(e.getMessage(), e);
	}

    }

    /**
     * Searches the paths for executables in the order as they are provided via:
     * <ol>
     * <li>java API</li>
     * <li>command-line</li>
     * <li>value of the PATH variable</li>
     * </ol>
     * 
     * @param executable
     * @return path of the executable
     */
    private String findExecutable(String executable) {
	List<String> paths = Arrays.asList(System.getenv().get("PATH").split(File.pathSeparator));
	final String graphvizLocationAsProperty = System.getProperty("graphviz.installation.location");
	if (graphvizLocationAsProperty != null) {
	    final File location = new File(graphvizLocationAsProperty);
	    if (!location.exists() || !location.isDirectory()) {
		throw new GraphvizEngineException("GraphViz installation location provided as property ["
			+ graphvizLocationAsProperty + "] does not exist or is not a directory.");
	    }
	    paths.add(0, graphvizLocationAsProperty);
	}
	if (graphvizPath != null) {
	    paths.add(0, graphvizPath.getAbsolutePath());
	}

	for (String path : paths) {
	    String file = (path == null) ? executable : (path + File.separator + executable);
	    if (new File(file).canExecute() && !new File(file).isDirectory()) {
		return file;
	    }
	}
	throw new GraphvizEngineException(executable + " program not found.");
    }

    /**
     * Create a temporary file containing the graph description
     * 
     * @param graphDescription
     * @return
     */
    private static File createTempDescription(String graphDescription) {
	try {
	    File temp = File.createTempFile("graph", ".dot");
	    if (graphDescription != null) {
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		out.write(graphDescription);
		out.close();
	    }
	    return temp;
	} catch (IOException e) {
	    throw new GraphvizOutputException(e.getMessage(), e);
	}
    }

    /**
     * Set the path to the location containing your GraphViz binaries (dot, neato, ...) which may be
     * /usr/local/graphviz-x.x, /usr/local/bin or similar.
     * 
     * @param graphvizPath
     */
    public void setGraphvizPath(String graphvizPath) {
	this.graphvizPath = new File(graphvizPath);
	if (!this.graphvizPath.exists() || !this.graphvizPath.isDirectory()) {
	    throw new GraphvizEngineException("GraphViz installation location [" + this.graphvizPath.getAbsolutePath()
		    + "] does not exist or is not a directory.");
	}
    }

    public String getOutputType() {
	return outputType;
    }

    /**
     * Sets the output format. Avaiable options are: bmp, canon, cgimage, cmap, cmapx, cmapx_np, dot, eps, exr, fig, gd,
     * gd2, gif, gv, imap, imap_np, ismap, jp2, jpe, jpeg, jpg, pct, pdf, pict, plain, plain-ext, png, ps, ps2, psd,
     * sgi, svg, svgz, tga, tif, tiff, tk, vml, vmlz, vrml, wbmp, xdot
     * 
     * @param outputFormat
     *            default is "eps"
     */
    public void setOutputType(String outputFormat) {
	this.outputType = outputFormat;
    }

    public String getLayoutManager() {
	return layoutManager;
    }

    /**
     * Set the layout manager. Available options are: dot, neato, fdp, sfdp, twopi, circo
     * 
     * @param layoutManager
     *            default is "dot"
     */
    public void setLayoutManager(String layoutManager) {
	this.layoutManager = layoutManager;
    }

    public String getExecutionPath() {
	return executionPath;
    }

    /**
     * Set the location, where the dot command will be executed.
     * 
     * @param path
     *            default is ".", i.e. the working path of the executing java program
     */
    public void setExecutionPath(String executionPath) {
	this.executionPath = executionPath;
    }

}
