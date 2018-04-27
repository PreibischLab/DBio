package clustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ScriptGenerator {

	public static String generateScript(String jar, String[] input) throws FileNotFoundException {
		File file = new File("input");
		file.mkdir();
		String filePath = file.getAbsolutePath()+"/run.sh";

		try (PrintWriter out = new PrintWriter(filePath)) {
		    out.println("#!/bin/sh");
		    out.println("# This is my job script with qsub-options ");
		    out.println("#$ -pe smp 8");
		    out.println("##$ -pe orte 32");
		    out.println("#$ -V -N \"Java Task runner\"");
		    out.println("#$ -l h_rt=0:0:30 -l h_vmem=4G -l h_stack=128M -cwd");
		    out.println("#$ -o data/test_results-$JOB_ID.txt");
		    out.println("#$ -e data/test_results-$JOB_ID.txt");
		    out.println("# export NSLOTS=8");
		    out.println("# neccessary to prevent python error ");
		    out.println("#export OPENBLAS_NUM_THREADS=4");
		    out.println("# export NUM_THREADS=8");
		    out.println("java -jar "+jar+" "+String.join(" ", input));
		    return filePath;  
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println(System.getProperty("user.home"));
		generateScript("jar.jar", new String[] {"hello.tif"});
		
	}
}