package main.java.net.preibisch.distribution.io.img.n5.tests;

import java.io.File;
import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import main.java.net.preibisch.distribution.algorithm.clustering.ClusterFile;
import main.java.net.preibisch.distribution.algorithm.clustering.jsch.SCPManager;
import main.java.net.preibisch.distribution.algorithm.controllers.items.Job;
import main.java.net.preibisch.distribution.algorithm.controllers.items.server.Login;
import main.java.net.preibisch.distribution.algorithm.controllers.logmanager.MyLogger;
import main.java.net.preibisch.distribution.io.img.XMLFile;
import mpicbg.spim.data.SpimDataException;

public class SendN5 {

	private final static String input_path = "/home/mzouink/Desktop/testn5/dataset.xml";

	private static String path = "/home/mzouink/Desktop/testn5/";
	private static String[] files = new String[] { "back_output45.n5"};

	public static void main(String[] args) throws SpimDataException, IOException, JSchException, SftpException {

		MyLogger.initLogger();
		XMLFile inputFile = XMLFile.XMLFile(input_path);
		for (String s : files)
			inputFile.getRelatedFiles().add(new File(path, s));

		// Connection
		new Job();
		Login.login();
		// SessionManager.connect();

		ClusterFile clusterFolderName = new ClusterFile(Login.getServer().getPath(), Job.getId());

		SCPManager.sendInput(inputFile, clusterFolderName);
	}

}