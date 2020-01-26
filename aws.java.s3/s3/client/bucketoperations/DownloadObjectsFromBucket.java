package s3.client.bucketoperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import s3.connect.AWSConnection;

public class DownloadObjectsFromBucket {

	AmazonS3 s3BucketClientObj = AWSConnection.connection();

	public void downloadObjects(String bucketName) throws IOException {
		FileOutputStream fos = null;
		if (s3BucketClientObj.doesBucketExist(bucketName)) {
			try {
				String pathName = ".\\src\\DownloadedFiles\\" + bucketName;
				File file = new File(pathName);
				if (!file.exists())
					file.mkdir();

				ObjectListing objList = s3BucketClientObj.listObjects(bucketName);
				List<S3ObjectSummary> objSummList = objList.getObjectSummaries();
				for (S3ObjectSummary s3Obj : objSummList) {
					
					fos = new FileOutputStream(
							pathName + "\\" + s3BucketClientObj.getObject(bucketName, s3Obj.getKey()));
					System.out.println("File:\t" + s3Obj.getKey() + "\tdownloaded.\nSize:\t" + s3Obj.getSize() + "\n\n");
				}
			} catch (SdkClientException sce) {
				sce.printStackTrace();
			} catch (IllegalArgumentException iai) {
				iai.printStackTrace();
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} finally {
				fos.close();
			}
		} else
			System.out.println("Bucket NOT Found");
	}
}