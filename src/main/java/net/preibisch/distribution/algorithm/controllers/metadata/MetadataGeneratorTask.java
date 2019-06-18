package main.java.net.preibisch.distribution.algorithm.controllers.metadata;

import java.io.File;

import main.java.net.preibisch.distribution.algorithm.controllers.items.AbstractTask;
import main.java.net.preibisch.distribution.algorithm.controllers.items.BlocksMetaData;
import main.java.net.preibisch.distribution.algorithm.controllers.items.Job;
import main.java.net.preibisch.distribution.algorithm.controllers.items.callback.AbstractCallBack;
import main.java.net.preibisch.distribution.gui.items.DataPreview;

public class MetadataGeneratorTask implements AbstractTask {
	private final static String METADATA_FILE_NAME = "METADATA.json";

	@Override
	public void start(int pos, AbstractCallBack callback) throws Exception {
		callback.log("Creating metadata..");
				
		BlocksMetaData md = MetadataGenerator.genarateMetaData(DataPreview.getDims(),DataPreview.getBlocksSizes(),DataPreview.getOverlap(), callback);
		File file = new File(Job.getTmpDir(),METADATA_FILE_NAME);
		md.toJson(file);

		callback.onSuccess(pos);
	}

}
